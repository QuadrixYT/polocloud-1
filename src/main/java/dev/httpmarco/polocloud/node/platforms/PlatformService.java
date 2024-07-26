package dev.httpmarco.polocloud.node.platforms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Singleton;
import dev.httpmarco.polocloud.node.platforms.util.PlatformVersionJsonTypeAdapter;
import dev.httpmarco.polocloud.node.util.JsonUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.log4j.Log4j2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Objects;

@Log4j2
@Getter
@Accessors(fluent = true)
@Singleton
public final class PlatformService {

    private static final Gson PLATFORM_JSON = new GsonBuilder().setPrettyPrinting()
            .registerTypeAdapter(PlatformVersion.class, new PlatformVersionJsonTypeAdapter())
            .registerTypeHierarchyAdapter(PlatformVersion.class, new PlatformVersionJsonTypeAdapter())
            .create();

    private final Platform[] platforms;
    private final PlatformAdditionIdPool additionPool = new PlatformAdditionIdPool();

    @SneakyThrows
    public PlatformService() {
        var versionFile = Path.of("local/versions.json");

        if (!Files.exists(versionFile.getParent())) {
            Files.createDirectory(versionFile.getParent());
        }

        if (Files.exists(versionFile)) {
            // todo check new update (compare versions id)
        } else {
            this.loadLatestVersionFromClasspath(versionFile);
        }

        this.platforms = this.readLocalPlatformConfig(versionFile).platforms();
        log.info("Loading {} cluster platforms with {} versions&8.", platforms.length, versionsAmount());
    }

    @SneakyThrows
    private PlatformConfig readLocalPlatformConfig(Path versionFile) {
        return PLATFORM_JSON.fromJson(Files.readString(versionFile), PlatformConfig.class);
    }

    @SneakyThrows
    private void loadLatestVersionFromClasspath(Path versionFile) {
        Files.copy(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("versions.json")), versionFile, StandardCopyOption.REPLACE_EXISTING);
    }

    private int versionsAmount() {
        return Arrays.stream(this.platforms).mapToInt(it -> it.versions().size()).sum();
    }
}
