package com.lypaka.pixeljobs;

import com.google.inject.Inject;
import com.lypaka.pixeljobs.Commands.PixelJobsAdminCmd;
import com.lypaka.pixeljobs.Commands.PixelJobsCmd;
import com.lypaka.pixeljobs.Listeners.catchListener;
import com.lypaka.pixeljobs.Listeners.evolveListener;
import com.lypaka.pixeljobs.Listeners.killListener;
import com.lypaka.pixeljobs.Utils.TimerTask;
import com.pixelmonmod.pixelmon.Pixelmon;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.EventContext;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartingServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.service.economy.Currency;
import org.spongepowered.api.service.economy.EconomyService;
import org.spongepowered.api.service.economy.account.UniqueAccount;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Optional;

@Plugin(
        id = "pixeljobs",
        name = "PixelJobs",
        version = "1.0.0",
        description = "A Pixelmon jobs plugin",
        authors = {
                "Lypaka"
        }
)
public class PixelJobs {
    @Inject
    @DefaultConfig(sharedRoot = false)
    public ConfigurationLoader<CommentedConfigurationNode> loader;

    @Inject
    @DefaultConfig(sharedRoot = false)
    public Path defaultConfig;

    @Inject
    @ConfigDir(sharedRoot = false)
    public Path configDir;


    @Inject
    public Logger logger;

    public ConfigurationNode config;

    public static PixelJobs INSTANCE;
    public PixelJobs plugin;



    @Listener
    public void onPreInit (GamePreInitializationEvent event) {
        try {
            config = loader.load();
            if (!defaultConfig.toFile().exists()) {
                config.getNode("Are Jobs enabled").setValue(true);
                config.getNode("Types of jobs").setValue("Catch,Kill,Evolve");
                config.getNode("Number of", "Catch jobs").setValue(1);
                config.getNode("Number of", "Kill jobs").setValue(1);
                config.getNode("Number of", "Evolve jobs").setValue(1);
                config.getNode("Catch", "Job 1", "Pokemon").setValue("Rattata");
                config.getNode("Catch", "Job 1", "Level").setValue(5);
                config.getNode("Catch", "Job 1", "Reward").setValue(1000);
                config.getNode("Catch", "Job 1", "Expiration", "how long the job lasts in minutes").setValue(1440);
                config.getNode("Catch", "Job 1", "Expiration", "expires at").setValue("2000-01-07T01:01:01.01");
                config.getNode("Catch", "Job 1", "Nature").setValue("Adamant");
                config.getNode("Catch", "Job 1", "has to be shiny").setValue(false);
                //----------------------------------------------------------------
                config.getNode("Kill", "Job 1", "Pokemon").setValue("Pidgey");
                config.getNode("Kill", "Job 1", "Level").setValue(10);
                config.getNode("Kill", "Job 1", "Reward").setValue(500);
                config.getNode("Kill", "Job 1", "Nature").setValue("Modest");
                config.getNode("Kill", "Job 1", "Expiration", "how long the job lasts in minutes").setValue("300");
                config.getNode("Kill", "Job 1", "Expiration", "expires at").setValue("2000-01-07T01:01:01.01");
                config.getNode("Kill", "Job 1", "has to be shiny").setValue(true);
                //----------------------------------------------------------------
                config.getNode("Evolve", "Job 1", "Pokemon").setValue("Wartortle");
                config.getNode("Evolve", "Job 1", "Level").setValue(36);
                config.getNode("Evolve", "Job 1", "Reward").setValue(10);
                config.getNode("Evolve", "Job 1", "Nature").setValue("Bold");
                config.getNode("Evolve", "Job 1", "has to be shiny").setValue(false);
                config.getNode("Evolve", "Job 1", "Expiration", "how long the job lasts in minutes").setValue(10);
                config.getNode("Evolve", "Job 1", "Expiration", "expires at").setValue("2000-01-07T01:01:01.01");

                loader.save(config);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pixelmon.EVENT_BUS.register(new catchListener(this));
        Pixelmon.EVENT_BUS.register(new killListener(this));
        Pixelmon.EVENT_BUS.register(new evolveListener(this));

        Sponge.getCommandManager().register(this, PixelJobsCmd.create(), "pixeljobs", "pj");
        Sponge.getCommandManager().register(this, PixelJobsAdminCmd.create(), "pixeljobsadmin", "pja");
    }

    @Listener
    public void onGameStart (GameStartingServerEvent event) {
        TimerTask.startTimer();
    }

    @Listener
    public void onReload(GameReloadEvent e) {
        try {
            config = loader.load();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        reloadConfig(config);
        try {
            loader.save(config);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        TimerTask.startTimer();
        System.out.println("PixelJobs config has reloaded!");
    }

    public void reloadConfig(ConfigurationNode config) {
        try {
            config.getNode("Are Jobs enabled").setValue(config.getNode("Are Jobs enabled").getValue());
            config.getNode("Catch").setValue(config.getNode("Catch").getValue());
            config.getNode("Kill").setValue(config.getNode("Kill").getValue());
            config.getNode("Evolve").setValue(config.getNode("Evolve").getValue());
            loader.save(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConfigurationNode getConfigNode() {
        return config;
    }

    public PixelJobs() {
        INSTANCE = this;
    }

    public ConfigurationLoader<CommentedConfigurationNode> getLoader() {
        return loader;
    }

    public void pay (Player player, int money) {
        Cause cause = Cause.builder().build(EventContext.empty());
        Optional<EconomyService> econ = Sponge.getServiceManager().provide(EconomyService.class);
        if (econ.isPresent()) {
            Optional<UniqueAccount> a = econ.get().getOrCreateAccount(player.getUniqueId());
            Currency defaultCur = econ.get().getDefaultCurrency();
            a.get().deposit(defaultCur, BigDecimal.valueOf(money), cause);
        }
    }

}
