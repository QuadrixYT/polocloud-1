package dev.httpmarco.polocloud.node.platforms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Set;

// todo checklist platforms

/**
 * paper
 * velocity
 * vanilla
 * purpur
 * spigot
 * bungeecord
 * sponge powered
 * minestom
 * multipaper
 * fabric
 * (folia)
 */

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public final class Platform {

    private String platform;
    private PlatformType type;
    private Set<PlatformAddition> additions;
    private Set<PlatformVersion> versions;

}
