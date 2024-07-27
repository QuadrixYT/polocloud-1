package dev.httpmarco.polocloud.node.groups;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.httpmarco.polocloud.api.groups.ClusterGroup;
import dev.httpmarco.polocloud.api.groups.ClusterGroupService;
import dev.httpmarco.polocloud.api.packet.group.GroupCreatePacket;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
@UtilityClass
public final class ClusterGroupFactory {

    private static final Path GROUP_DIR = Path.of("local/groups");
    private static final Gson GROUP_GSON = new GsonBuilder()
            .registerTypeAdapter(ClusterGroup.class, new ClusterGroupJsonTypeAdapter())
            .registerTypeHierarchyAdapter(ClusterGroup.class, new ClusterGroupJsonTypeAdapter())
            .setPrettyPrinting()
            .create();

    @SneakyThrows
    public void createLocalStorageGroup(@NotNull GroupCreatePacket packet, @NotNull ClusterGroupService clusterGroupService) {
        var group = new ClusterGroupImpl(
                packet.name(),
                packet.platformGroupDisplay(),
                packet.nodes(),
                packet.minMemory(),
                packet.maxMemory(),
                packet.staticService(),
                packet.minOnline(),
                packet.maxOnline()
        );


        // check every creation, if directory exists
        GROUP_DIR.toFile().mkdirs();

        var groupFile = GROUP_DIR.resolve(group.name() + ".json");

        Files.writeString(groupFile, GROUP_GSON.toJson(group));
        clusterGroupService.groups().add(group);
    }

    public List<ClusterGroup> readGroups() {
        var groups = new ArrayList<ClusterGroup>();
        for (File file : Objects.requireNonNull(GROUP_DIR.toFile().listFiles())) {

        }
        return groups;
    }

}
