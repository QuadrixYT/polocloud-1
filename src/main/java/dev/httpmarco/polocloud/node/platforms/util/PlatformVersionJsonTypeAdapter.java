package dev.httpmarco.polocloud.node.platforms.util;

import com.google.gson.*;
import dev.httpmarco.polocloud.node.platforms.PlatformVersion;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

public final class PlatformVersionJsonTypeAdapter implements JsonDeserializer<PlatformVersion> {

    @Override
    public @NotNull PlatformVersion deserialize(@NotNull JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        var jsonObject = json.getAsJsonObject();

        var version = jsonObject.get("version").getAsString();
        var downloadLink = jsonObject.get("downloadLink").getAsString();

        return new PlatformVersion(version, jsonObject.has("build") ? downloadLink.replaceAll("%build%", String.valueOf(jsonObject.get("build").getAsInt())) : downloadLink);
    }
}
