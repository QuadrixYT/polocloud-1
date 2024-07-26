package dev.httpmarco.polocloud.node.platforms.util;

import com.google.gson.*;
import dev.httpmarco.polocloud.node.platforms.PlatformAddition;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;

public final class PlatformJsonTypeAdapter implements JsonSerializer<PlatformAddition>, JsonDeserializer<PlatformAddition> {

    @Contract(pure = true)
    @Override
    public @Nullable PlatformAddition deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        var jsonObject = json.getAsJsonObject();

        var type = jsonObject.get("addition").getAsString();

        // todo find a better way
        if (type.equalsIgnoreCase("CHECKSUM")) {

            return null;
        }

        return null;
    }

    @Contract(pure = true)
    @Override
    public @NotNull JsonElement serialize(@NotNull PlatformAddition addition, Type typeOfSrc, @NotNull JsonSerializationContext context) {
        var jsonElement = new JsonObject();
        //jsonElement.addProperty("addition", addition.name().toUpperCase());
        jsonElement.asMap().putAll(context.serialize(addition).getAsJsonObject().asMap());
        return jsonElement;
    }
}
