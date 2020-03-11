package com.lypaka.pixeljobs.Listeners;

import com.lypaka.pixeljobs.PixelJobs;
import com.pixelmonmod.pixelmon.api.events.EvolveEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class evolveListener {

    public evolveListener (PixelJobs plugin) {
        this.plugin = plugin;
    }
    public PixelJobs plugin;
    public ConfigurationNode config = PixelJobs.INSTANCE.config;

    @SubscribeEvent
    public void onEvolve(EvolveEvent.PostEvolve e) {
        Player player = (Player) e.player;
        if (config.getNode("Are Jobs enabled").getValue().equals(true)) {
            int number = config.getNode("Number of", "Evolve jobs").getInt();
            for (int i = 1; i <= number; i++) {
                //Checks name
                if (config.getNode("Evolve", "Job " + i, "Pokemon").getValue().toString().equals(e.preEvo.getName())) {
                    //Checks level, though this is really only useful for evolutions not determined by level
                    if (config.getNode("Evolve", "Job " + i, "Level").getInt() != 0 && config.getNode("Evolve", "Job " + i, "Level").getInt() == e.preEvo.getLvl().getLevel()) {
                        //Checks Nature
                        if (config.getNode("Evolve", "Job " + i, "Nature").getValue().toString().equals(e.preEvo.getPokemonData().getNature().toString())) {
                            //Checks shiny
                            if (config.getNode("Evolve", "Job " + i, "has to be shiny").getValue().equals(true)) {
                                if (e.preEvo.getPokemonData().isShiny()) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You evolved a " + e.preEvo.getName() + "! You earned " + config.getNode("Evolve", "Job " + i, "Reward").getInt()));
                                    plugin.pay(player, config.getNode("Evolve", "Job " + i, "Reward").getInt());
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You evolved a " + e.preEvo.getName() + "! You earned " + config.getNode("Evolve", "Job " + i, "Reward").getInt()));
                                plugin.pay(player, config.getNode("Evolve", "Job " + i, "Reward").getInt());
                            }
                        } else if (config.getNode("Evolve", "Job " + i, "Nature").getValue().toString().equals("none")) {
                            if (config.getNode("Evolve", "Job " + i, "has to be shiny").getValue().equals(true)) {
                                if (e.preEvo.getPokemonData().isShiny()) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You evolved a " + e.preEvo.getName() + "! You earned " + config.getNode("Evolve", "Job " + i, "Reward").getInt()));
                                    plugin.pay(player, config.getNode("Evolve", "Job " + i, "Reward").getInt());
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You evolved a " + e.preEvo.getName() + "! You earned " + config.getNode("Evolve", "Job " + i, "Reward").getInt()));
                                plugin.pay(player, config.getNode("Evolve", "Job " + i, "Reward").getInt());
                            }
                        }
                    } else if (config.getNode("Evolve", "Job " + i, "Level").getInt() != 0) {
                        if (config.getNode("Evolve", "Job " + i, "Nature").getValue().toString().equals(e.preEvo.getPokemonData().getNature().toString())) {
                            //Checks shiny
                            if (config.getNode("Evolve", "Job " + i, "has to be shiny").getValue().equals(true)) {
                                if (e.preEvo.getPokemonData().isShiny()) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You evolved a " + e.preEvo.getName() + "! You earned " + config.getNode("Evolve", "Job " + i, "Reward").getInt()));
                                    plugin.pay(player, config.getNode("Evolve", "Job " + i, "Reward").getInt());
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You evolved a " + e.preEvo.getName() + "! You earned " + config.getNode("Evolve", "Job " + i, "Reward").getInt()));
                                plugin.pay(player, config.getNode("Evolve", "Job " + i, "Reward").getInt());
                            }
                        } else if (config.getNode("Evolve", "Job " + i, "Nature").getValue().toString().equals("none")) {
                            if (config.getNode("Evolve", "Job " + i, "has to be shiny").getValue().equals(true)) {
                                if (e.preEvo.getPokemonData().isShiny()) {
                                    player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You evolved a " + e.preEvo.getName() + "! You earned " + config.getNode("Evolve", "Job " + i, "Reward").getInt()));
                                    plugin.pay(player, config.getNode("Evolve", "Job " + i, "Reward").getInt());
                                }
                            } else {
                                player.sendMessage(Text.of(TextColors.GOLD, "[", TextColors.DARK_RED, "PixelJobs", TextColors.GOLD, "] ", TextColors.WHITE, "You evolved a " + e.preEvo.getName() + "! You earned " + config.getNode("Evolve", "Job " + i, "Reward").getInt()));
                                plugin.pay(player, config.getNode("Evolve", "Job " + i, "Reward").getInt());
                            }
                        }
                    }
                }
            }
        }
    }
}
