package com.lypaka.pixeljobs.Commands;

import com.lypaka.pixeljobs.GUI.pages;
import com.lypaka.pixeljobs.PixelJobs;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

public class PixelJobsCmd implements CommandExecutor {
    public PixelJobsCmd (PixelJobs plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfigNode();
    }
    public PixelJobs plugin;
    private ConfigurationNode config;

    public static CommandSpec create() {
        return CommandSpec.builder()
                .arguments(
                        GenericArguments.string(Text.of("catch|kill|evolve"))
                )
                .executor(new PixelJobsCmd(PixelJobs.INSTANCE))
                .build();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player player = (Player) src;
        String job = args.getOne("catch|kill|evolve").get().toString();
        if (job.equals("catch")) {
            if (!config.getNode("Catch", "Job 1").isVirtual()) {
                pages.openCatchJobsPage(player);
            } else {
                player.sendMessage(Text.of("No catch jobs exist right now! Try again later!"));
            }
        } else if (job.equals("kill")) {
            if (!config.getNode("Kill", "Job 1").isVirtual()) {
                pages.openKillJobsPage(player);
            } else {
                player.sendMessage(Text.of("No kill jobs exist right now! Try again later!"));
            }
        } else if (job.equals("evolve")) {
            if (!config.getNode("Evolve", "Job 1").isVirtual()) {
                pages.openEvolveJobsPage(player);
            } else {
                player.sendMessage(Text.of("No evolve jobs exist right now! Try again later!"));
            }
        }


        return CommandResult.success();
    }
}
