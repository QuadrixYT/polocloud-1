package dev.httpmarco.polocloud.node.commands.type;

import com.google.inject.Inject;
import dev.httpmarco.polocloud.api.Named;
import dev.httpmarco.polocloud.api.groups.ClusterGroup;
import dev.httpmarco.polocloud.node.commands.CommandArgument;
import dev.httpmarco.polocloud.api.groups.ClusterGroupService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public final class GroupArgument extends CommandArgument<ClusterGroup> {

    @Inject
    private final ClusterGroupService groupService;

    public GroupArgument(String key, ClusterGroupService groupService) {
        super(key);
        this.groupService = groupService;
    }

    @Override
    public boolean predication(@NotNull String rawInput) {
        return groupService.exists(rawInput);
    }

    @Contract(pure = true)
    @Override
    public @NotNull String wrongReason() {
        return "The Argument " + key() + " is not a registered cluster group!";
    }

    @Contract(value = " -> new", pure = true)
    @Override
    public @NotNull @Unmodifiable List<String> defaultArgs() {
        return groupService.groups().stream().map(Named::name).toList();
    }

    @Contract("_ -> new")
    @Override
    public @NotNull ClusterGroup buildResult(String input) {
        //todo
        return null;
    }
}