package com.lypaka.pixeljobs.Listeners;

import com.lypaka.pixeljobs.PixelJobs;
import com.pixelmonmod.pixelmon.api.events.CaptureEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class catchListener {

    public catchListener (PixelJobs plugin) {
        this.plugin = plugin;
    }
    private PixelJobs plugin;
    public ConfigurationNode config = PixelJobs.INSTANCE.config;

    @SubscribeEvent
    public void onCatch(CaptureEvent.SuccessfulCapture e) {
        Player player = (Player) e.player;
        if (config.getNode("Are Jobs enabled").getValue().equals(true)) {
            int number = config.getNode("Number of", "Catch jobs").getInt();
            for (int i = 1; i <= number; i++) {
                //Checks Pokemon's name
                if (config.getNode("Catch", "Job " + i, "Pokemon").getValue().toString().equals(e.getPokemon().getName())) {
                    //Checks Pokemon's level and checks to make sure level in config is not 0 (disabled/any level)
                    if (config.getNode("Catch", "Job " + i, "Level").getInt() != 0 && config.getNode("Catch", "Job " + i, "Level").getInt() == e.getPokemon().getLvl().getLevel()) {
                        //Checks natures, if not disabled
                        if (!config.getNode("Catch", "Job " + i, "Nature").getValue().toString().equals("none") && config.getNode("Catch", "Job " + i, "Nature").getValue().toString().equals(e.getPokemon().getNature().toString())) {
                            //Checks if the job requires the Pokemon to be shiny, and then checks if the Pokemon is shiny
                            if (config.getNode("Catch", "Job " + i, "has to be shiny").getValue().equals(true)) {
                                if (e.getPokemon().isShiny()) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You caught a " + e.getPokemon().getName() + "! You earned " + config.getNode("Catch", "Job " + i, "Reward").getInt() + "!"));
                                    plugin.pay(player, config.getNode("Catch", "Job " + i, "Reward").getInt());
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You caught a " + e.getPokemon().getName() + "! You earned " + config.getNode("Catch", "Job " + i, "Reward").getInt() + "!"));
                                plugin.pay(player, config.getNode("Catch", "Job " + i, "Reward").getInt());
                            }
                        } else if (config.getNode("Catch", "Job " + i, "Nature").getValue().toString().equals("none")) {
                            if (config.getNode("Catch", "Job " + i, "has to be shiny").getValue().equals(true)) {
                                if (e.getPokemon().isShiny()) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You caught a " + e.getPokemon().getName() + "! You earned " + config.getNode("Catch", "Job " + i, "Reward").getInt() + "!"));
                                    plugin.pay(player, config.getNode("Catch", "Job " + i, "Reward").getInt());
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You caught a " + e.getPokemon().getName() + "! You earned " + config.getNode("Catch", "Job " + i, "Reward").getInt() + "!"));
                                plugin.pay(player, config.getNode("Catch", "Job " + i, "Reward").getInt());
                            }
                        }
                    } else if (config.getNode("Catch", "Job " + i, "Level").getInt() == 0) {
                        if (!config.getNode("Catch", "Job " + i, "Nature").getValue().toString().equals("none") && config.getNode("Catch", "Job " + i, "Nature").getValue().toString().equals(e.getPokemon().getNature().toString())) {
                            //Checks if the job requires the Pokemon to be shiny, and then checks if the Pokemon is shiny
                            if (config.getNode("Catch", "Job " + i, "has to be shiny").getValue().equals(true)) {
                                if (e.getPokemon().isShiny()) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You caught a " + e.getPokemon().getName() + "! You earned " + config.getNode("Catch", "Job " + i, "Reward").getInt() + "!"));
                                    plugin.pay(player, config.getNode("Catch", "Job " + i, "Reward").getInt());
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You caught a " + e.getPokemon().getName() + "! You earned " + config.getNode("Catch", "Job " + i, "Reward").getInt() + "!"));
                                plugin.pay(player, config.getNode("Catch", "Job " + i, "Reward").getInt());
                            }
                        } else if (config.getNode("Catch", "Job " + i, "Nature").getValue().toString().equals("none")) {
                            if (config.getNode("Catch", "Job " + i, "has to be shiny").getValue().equals(true)) {
                                if (e.getPokemon().isShiny()) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You caught a " + e.getPokemon().getName() + "! You earned " + config.getNode("Catch", "Job " + i, "Reward").getInt() + "!"));
                                    plugin.pay(player, config.getNode("Catch", "Job " + i, "Reward").getInt());
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You caught a " + e.getPokemon().getName() + "! You earned " + config.getNode("Catch", "Job " + i, "Reward").getInt() + "!"));
                                plugin.pay(player, config.getNode("Catch", "Job " + i, "Reward").getInt());
                            }
                        }
                    }
                }
            }
        }
    }
}
