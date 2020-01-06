package com.lypaka.pixeljobs.GUI;

import com.codehusky.huskyui.StateContainer;
import com.codehusky.huskyui.states.Page;
import com.codehusky.huskyui.states.action.ActionType;
import com.codehusky.huskyui.states.action.runnable.RunnableAction;
import com.codehusky.huskyui.states.element.ActionableElement;
import com.codehusky.huskyui.states.element.Element;
import com.google.common.collect.Lists;
import com.lypaka.pixeljobs.PixelJobs;
import com.pixelmonmod.pixelmon.config.PixelmonItemsPokeballs;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.InventoryDimension;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class pages {
    public pages (PixelJobs plugin) {
        this.plugin = plugin;
    }
    public PixelJobs plugin;

    public static void openCatchJobsPage(Player player) {
        StateContainer container = new StateContainer();
        Page.PageBuilder main = Page.builder()
                .setAutoPaging(false)
                .setInventoryDimension(InventoryDimension.of(9, 5))
                .setTitle(Text.of(TextColors.DARK_RED, "PixelJobs - Catch Jobs"))
                .setEmptyStack(emptyPage.empty());
        for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}) {
            main.putElement(i, new Element(emptyPage.empty()));
        }
        int numberCatch = PixelJobs.INSTANCE.getConfigNode().getNode("Number of", "Catch jobs").getInt();
        ArrayList<Text> lore = new ArrayList<>();
        lore.add(Text.EMPTY);
        for (int j = 1; j <= numberCatch; j++) {
            main.putElement(j-1, new ActionableElement(
                    new RunnableAction(container, ActionType.NONE, "", c -> openCatchJobsPage(player)),
                    ItemStack.builder()
                            .itemType((ItemType) PixelmonItemsPokeballs.pokeBall)
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, PixelJobs.INSTANCE.getConfigNode().getNode("Catch", "Job " + j, "Pokemon").getValue()))
                            .add(Keys.ITEM_LORE, Lists.newArrayList(
                                    Text.of("Reward: " + PixelJobs.INSTANCE.getConfigNode().getNode("Catch", "Job " + j, "Reward").getInt()),
                                    Text.of("Level: " + getLevelIfAny(PixelJobs.INSTANCE.getConfigNode().getNode("Catch", "Job " + j, "Level"))),
                                    Text.of("Nature: " + getNatureIfAny(PixelJobs.INSTANCE.getConfigNode().getNode("Catch", "Job " + j, "Nature"))),
                                    Text.of(getShinyIfAny(PixelJobs.INSTANCE.getConfigNode().getNode("Catch", "Job " + j, "has to be shiny"))),
                                    Text.of("Expires in: " + getHoursFromTime(PixelJobs.INSTANCE.getConfigNode().getNode("Catch", "Job " + j, "Expiration", "expires at")) + " hours, " + getMinutesFromTime(PixelJobs.INSTANCE.getConfigNode().getNode("Catch", "Job " + j, "Expiration", "expires at")) + " minutes")
                            ))
                            .build()
            ));

        }
        container.setInitialState(main.build("catch"));
        container.launchFor(player);
    }

    public static void openKillJobsPage(Player player) {
        StateContainer container = new StateContainer();
        Page.PageBuilder main = Page.builder()
                .setAutoPaging(false)
                .setInventoryDimension(InventoryDimension.of(9, 5))
                .setTitle(Text.of(TextColors.DARK_RED, "PixelJobs - Kill Jobs"))
                .setEmptyStack(emptyPage.empty());
        for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}) {
            main.putElement(i, new Element(emptyPage.empty()));
        }
        int numberCatch = PixelJobs.INSTANCE.getConfigNode().getNode("Number of", "Kill jobs").getInt();
        ArrayList<Text> lore = new ArrayList<>();
        lore.add(Text.EMPTY);
        for (int j = 1; j <= numberCatch; j++) {
            main.putElement(j-1, new ActionableElement(
                    new RunnableAction(container, ActionType.NONE, "", c -> openCatchJobsPage(player)),
                    ItemStack.builder()
                            .itemType((ItemType) PixelmonItemsPokeballs.pokeBall)
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, PixelJobs.INSTANCE.getConfigNode().getNode("Kill", "Job " + j, "Pokemon").getValue()))
                            .add(Keys.ITEM_LORE, Lists.newArrayList(
                                    Text.of("Reward: " + PixelJobs.INSTANCE.getConfigNode().getNode("Kill", "Job " + j, "Reward").getInt()),
                                    Text.of("Level: " + getLevelIfAny(PixelJobs.INSTANCE.getConfigNode().getNode("Kill", "Job " + j, "Level"))),
                                    Text.of("Nature: " + getNatureIfAny(PixelJobs.INSTANCE.getConfigNode().getNode("Kill", "Job " + j, "Nature"))),
                                    Text.of(getShinyIfAny(PixelJobs.INSTANCE.getConfigNode().getNode("Kill", "Job " + j, "has to be shiny"))),
                                    Text.of("Expires in: " + getHoursFromTime(PixelJobs.INSTANCE.getConfigNode().getNode("Kill", "Job " + j, "Expiration", "expires at")) + " hours, " + getMinutesFromTime(PixelJobs.INSTANCE.getConfigNode().getNode("Kill", "Job " + j, "Expiration", "expires at")) + " minutes")
                            ))
                            .build()
            ));

        }
        container.setInitialState(main.build("kill"));
        container.launchFor(player);
    }

    public static void openEvolveJobsPage(Player player) {
        StateContainer container = new StateContainer();
        Page.PageBuilder main = Page.builder()
                .setAutoPaging(false)
                .setInventoryDimension(InventoryDimension.of(9, 5))
                .setTitle(Text.of(TextColors.DARK_RED, "PixelJobs - Evolve Jobs"))
                .setEmptyStack(emptyPage.empty());
        for (int i : new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}) {
            main.putElement(i, new Element(emptyPage.empty()));
        }
        int numberCatch = PixelJobs.INSTANCE.getConfigNode().getNode("Number of", "Evolve jobs").getInt();
        ArrayList<Text> lore = new ArrayList<>();
        lore.add(Text.EMPTY);
        for (int j = 1; j <= numberCatch; j++) {
            main.putElement(j-1, new ActionableElement(
                    new RunnableAction(container, ActionType.NONE, "", c -> openCatchJobsPage(player)),
                    ItemStack.builder()
                            .itemType((ItemType) PixelmonItemsPokeballs.pokeBall)
                            .add(Keys.DISPLAY_NAME, Text.of(TextColors.YELLOW, PixelJobs.INSTANCE.getConfigNode().getNode("Evolve", "Job " + j, "Pokemon").getValue()))
                            .add(Keys.ITEM_LORE, Lists.newArrayList(
                                    Text.of("Reward: " + PixelJobs.INSTANCE.getConfigNode().getNode("Evolve", "Job " + j, "Reward").getInt()),
                                    Text.of("Level: " + getLevelIfAny(PixelJobs.INSTANCE.getConfigNode().getNode("Evolve", "Job " + j, "Level"))),
                                    Text.of("Nature: " + getNatureIfAny(PixelJobs.INSTANCE.getConfigNode().getNode("Evolve", "Job " + j, "Nature"))),
                                    Text.of(getShinyIfAny(PixelJobs.INSTANCE.getConfigNode().getNode("Evolve", "Job " + j, "has to be shiny"))),
                                    Text.of("Expires in: " + getHoursFromTime(PixelJobs.INSTANCE.getConfigNode().getNode("Evolve", "Job " + j, "Expiration", "expires at")) + " hours, " + getMinutesFromTime(PixelJobs.INSTANCE.getConfigNode().getNode("Evolve", "Job " + j, "Expiration", "expires at")) + " minutes")
                            ))
                            .build()
            ));

        }
        container.setInitialState(main.build("evolve"));
        container.launchFor(player);
    }

    public static String getLevelIfAny (ConfigurationNode node) {
        if (node.getInt() == 0) {
            return "Any";
        } else {
            return node.getValue().toString();
        }

    }

    public static String getNatureIfAny (ConfigurationNode node) {
        if (node.getValue().toString().equals("none")) {
            return "Any";
        } else {
            return node.getValue().toString();
        }
    }

    public static String getShinyIfAny (ConfigurationNode node) {
        if (node.getValue().toString().equals(true)) {
            return "Shiny";
        } else {
            return "Non-shiny";
        }
    }

    private static int getHoursFromTime(ConfigurationNode node) {
        LocalDateTime time = LocalDateTime.parse(node.getValue().toString());
        if (time.getHour() > 12) {
            int hours = time.getHour() - 12;
            int nowHours = LocalDateTime.now().getHour();
            if (nowHours > 12) {
                if (nowHours - 12 >= hours) {
                    return nowHours - 12 - hours;
                } else {
                    return hours - nowHours;
                }
            } else if (nowHours < 12) {
                if (nowHours >= hours) {
                    return nowHours - hours;
                } else {
                    return hours - nowHours;
                }
            }
        } else {
            int hours = time.getHour();
            int nowHours = LocalDateTime.now().getHour();
            if (nowHours > 12) {
                if (nowHours - 12 >= hours) {
                    int number = nowHours - 12 - hours;
                    return number;
                } else {
                    return hours - nowHours;
                }
            } else if (nowHours < 12) {
                if (nowHours >= hours) {
                    return nowHours - hours;
                } else {
                    return hours - nowHours;
                }
            }
        }
        return 0;
    }

    private static int getMinutesFromTime(ConfigurationNode node) {
        LocalDateTime time = LocalDateTime.parse(node.getValue().toString());
        int nowMins = LocalDateTime.now().getMinute();
        if (nowMins > time.getMinute()) {
            int number = nowMins - time.getMinute();
            return number;
        } else {
            int number = time.getMinute() - nowMins;
            return number;
        }
    }
}
