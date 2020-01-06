package com.lypaka.pixeljobs.Listeners;

import com.lypaka.pixeljobs.PixelJobs;
import com.pixelmonmod.pixelmon.api.events.BeatWildPixelmonEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class killListener {
    public killListener (PixelJobs plugin) {
        this.plugin = plugin;
    }
    public PixelJobs plugin;
    public ConfigurationNode config = PixelJobs.INSTANCE.config;

    @SubscribeEvent
    public void onCatch(BeatWildPixelmonEvent e) {
        Player player = (Player) e.player;
        if (config.getNode("Are Jobs enabled").getValue().equals(true)) {
            int number = config.getNode("Number of", "Kill jobs").getInt();
            for (int i = 1; i <= number; i++) {
                //Checks Pokemon's name
                if (config.getNode("Kill", "Job " + i, "Pokemon").getValue().toString().equals(e.wpp.controlledPokemon.get(0).pokemon.getName())) {
                    //Checks Pokemon's level and checks to make sure level in config is not 0 (disabled/any level)
                    if (config.getNode("Kill", "Job " + i, "Level").getInt() != 0 && config.getNode("Kill", "Job " + i, "Level").getInt() == e.wpp.controlledPokemon.get(0).pokemon.getLvl().getLevel()) {
                        //Checks natures, if not disabled
                        if (!config.getNode("Kill", "Job " + i, "Nature").getValue().toString().equals("none") && config.getNode("Kill", "Job " + i, "Nature").getValue().toString().equals(e.wpp.controlledPokemon.get(0).pokemon.getNature().toString())) {
                            //Checks if the job requires the Pokemon to be shiny, and then checks if the Pokemon is shiny
                            if (config.getNode("Kill", "Job " + i, "has to be shiny").getValue().equals(true)) {
                                if (e.wpp.controlledPokemon.get(0).pokemon.isShiny()) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You killed a " + e.wpp.controlledPokemon.get(0).pokemon.getName() + "! You earned " + config.getNode("Kill", "Job " + i, "Reward").getInt() + "!"));
                                    plugin.pay(player, config.getNode("Kill", "Job " + i, "Reward").getInt());
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You killed a " + e.wpp.controlledPokemon.get(0).pokemon.getName() + "! You earned " + config.getNode("Kill", "Job " + i, "Reward").getInt() + "!"));
                            }
                        } else if (config.getNode("Kill", "Job " + i, "Nature").getValue().toString().equals("none")) {
                            if (config.getNode("Kill", "Job " + i, "has to be shiny").getValue().equals(true)) {
                                if (e.wpp.controlledPokemon.get(0).pokemon.isShiny()) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You killed a " + e.wpp.controlledPokemon.get(0).pokemon.getName() + "! You earned " + config.getNode("Kill", "Job " + i, "Reward").getInt() + "!"));
                                    plugin.pay(player, config.getNode("Kill", "Job " + i, "Reward").getInt());
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You killed a " + e.wpp.controlledPokemon.get(0).pokemon.getName() + "! You earned " + config.getNode("Kill", "Job " + i, "Reward").getInt() + "!"));
                            }
                        }
                    } else if (config.getNode("Kill", "Job " + i, "Level").getInt() == 0) {
                        if (!config.getNode("Kill", "Job " + i, "Nature").getValue().toString().equals("none") && config.getNode("Kill", "Job " + i, "Nature").getValue().toString().equals(e.wpp.controlledPokemon.get(0).pokemon.getNature().toString())) {
                            //Checks if the job requires the Pokemon to be shiny, and then checks if the Pokemon is shiny
                            if (config.getNode("Kill", "Job " + i, "has to be shiny").getValue().equals(true)) {
                                if (e.wpp.controlledPokemon.get(0).pokemon.isShiny()) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You killed a " + e.wpp.controlledPokemon.get(0).pokemon.getName() + "! You earned " + config.getNode("Kill", "Job " + i, "Reward").getInt() + "!"));
                                    plugin.pay(player, config.getNode("Kill", "Job " + i, "Reward").getInt());
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You killed a " + e.wpp.controlledPokemon.get(0).pokemon.getName() + "! You earned " + config.getNode("Kill", "Job " + i, "Reward").getInt() + "!"));
                            }
                        }
                    }
                }
            }
        }
    }

}
