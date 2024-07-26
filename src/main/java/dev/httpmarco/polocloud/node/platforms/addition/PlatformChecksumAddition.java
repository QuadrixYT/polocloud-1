package dev.httpmarco.polocloud.node.platforms.addition;

import dev.httpmarco.polocloud.node.platforms.PlatformAddition;

public record PlatformChecksumAddition(String checksumUrl, String jsonField) implements PlatformAddition {

}
