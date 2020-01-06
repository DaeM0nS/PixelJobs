package com.lypaka.pixeljobs.Commands;

import com.lypaka.pixeljobs.PixelJobs;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.io.IOException;
import java.time.LocalDateTime;

public class PixelJobsAdminCmd implements CommandExecutor {
    public PixelJobsAdminCmd (PixelJobs plugin) {
        this.plugin = plugin;
        this.loader = plugin.getLoader();
    }
    public PixelJobs plugin;
    public ConfigurationNode config = PixelJobs.INSTANCE.config;
    public ConfigurationLoader<CommentedConfigurationNode> loader;

    public static CommandSpec create() {
        return CommandSpec.builder()
                .arguments(
                        GenericArguments.string(Text.of("create")),
                        GenericArguments.string(Text.of("Catch|Kill|Evolve")),
                        GenericArguments.string(Text.of("Pokemon")),
                        GenericArguments.integer(Text.of("reward")),
                        GenericArguments.integer(Text.of("time")),
                        GenericArguments.optional(GenericArguments.string(Text.of("nature"))),
                        GenericArguments.optional(GenericArguments.integer(Text.of("level"))),
                        GenericArguments.optional(GenericArguments.string(Text.of("shiny")))
                )
                .executor(new PixelJobsAdminCmd(PixelJobs.INSTANCE))
                .build();
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player player = (Player) src;
        if (player.hasPermission("pixeljobs.admin")) {
            String mode = args.<String>getOne("create").get();
            String jobType = args.<String>getOne("Catch|Kill|Evolve").get().substring(0, 1).toUpperCase() + args.<String>getOne("Catch|Kill|Evolve").get().substring(1);
            String name = args.<String>getOne("Pokemon").get();
            int money = args.<Integer>getOne("reward").get();
            int time = args.<Integer>getOne("time").get();
            if (mode.equals("create")) {
                int number = PixelJobs.INSTANCE.config.getNode("Number of", jobType + " jobs").getInt();
                for (int i = 1; i <= number + 1; i++) {
                    if (PixelJobs.INSTANCE.config.getNode(jobType, "Job " + i).isVirtual()) {
                        try {
                            config.getNode(jobType, "Job " + i, "Pokemon").setValue(name);
                            config.getNode(jobType, "Job " + i, "Reward").setValue(money);
                            if (args.<Integer>getOne("level").isPresent()) {
                                config.getNode(jobType, "Job " + i, "Level").setValue(args.<Integer>getOne("level").get());
                            } else {
                                config.getNode(jobType, "Job " + i, "Level").setValue(0);
                            }
                            config.getNode(jobType, "Job " + i, "Expiration", "expires at").setValue(setTime(time).toString());
                            config.getNode(jobType, "Job " + i, "Expiration", "how long the job lasts in minutes").setValue(time);
                            if (args.<String>getOne("nature").isPresent()) {
                                config.getNode(jobType, "Job " + i, "Nature").setValue(args.<String>getOne("nature").get().substring(0, 1).toUpperCase() + args.<String>getOne("nature").get().substring(1));
                            } else {
                                config.getNode(jobType, "Job " + i, "Nature").setValue("none");
                            }
                            if (args.<String>getOne("shiny").isPresent()) {
                                config.getNode(jobType, "Job " + i, "has to be shiny").setValue(true);
                            } else {
                                config.getNode(jobType, "Job " + i, "has to be shiny").setValue(false);
                            }
                            loader.save(config);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            config.getNode("Number of", jobType + " jobs").setValue(number + 1);
                            loader.save(config);
                        } catch (IOException er) {
                            er.printStackTrace();
                        }
                        player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "A " + jobType + " job has been created with the values: " + name + " " + money + " " + time + " " + args.<String>getOne("nature").orElse("Any") + " " + args.<Integer>getOne("level").orElse(0)));
                    }
                }
            }
        }

        return CommandResult.success();
    }

    public LocalDateTime setTime (int number) {
        return LocalDateTime.now().plusMinutes(number);
    }
}
