package com.lypaka.pixeljobs.Utils;

import com.lypaka.pixeljobs.PixelJobs;
import ninja.leaping.configurate.ConfigurationNode;
import org.spongepowered.api.scheduler.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class TimerTask {

    public static Task task;

    public static void startTimer() {
        int number = PixelJobs.INSTANCE.config.getNode("How often (in minutes) the code checks for expired jobs").getInt();
        task = Task.builder()
                .execute(t -> {
                    System.out.println("Checking for expired jobs...");
                    int numberCatch = PixelJobs.INSTANCE.getConfigNode().getNode("Number of", "Catch jobs").getInt();
                    if (numberCatch > 0) {
                        for (int i = 1; i <= numberCatch; i++) {
                            LocalDateTime time = LocalDateTime.now();
                            if (!PixelJobs.INSTANCE.getConfigNode().getNode("Catch", "Job 1").isVirtual()) {
                                ConfigurationNode node = PixelJobs.INSTANCE.getConfigNode().getNode("Catch", "Job " + i, "Expiration", "expires at");
                                String string = node.getString();
                                LocalDateTime configCatchTime = LocalDateTime.parse(string);
                                if (time.getHour() == configCatchTime.getHour() && time.getDayOfWeek() == configCatchTime.getDayOfWeek() && time.getMinute() == configCatchTime.getMinute() || time.isAfter(configCatchTime)) {
                                    System.out.println("[PixelJobs] Found expired catch job, removing it!");
                                    try {
                                        PixelJobs.INSTANCE.getConfigNode().getNode("Catch", "Job " + i).setValue(null);
                                        PixelJobs.INSTANCE.getConfigNode().getNode("Number of", "Catch jobs").setValue(numberCatch - 1);
                                        PixelJobs.INSTANCE.getLoader().save(PixelJobs.INSTANCE.getConfigNode());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                    int numberKill = PixelJobs.INSTANCE.getConfigNode().getNode("Number of", "Kill jobs").getInt();
                    if (numberKill > 0) {
                        for (int i = 1; i <= numberKill; i++) {
                            LocalDateTime time = LocalDateTime.now();
                            if (!PixelJobs.INSTANCE.getConfigNode().getNode("Kill", "Job 1").isVirtual()) {
                                ConfigurationNode node = PixelJobs.INSTANCE.getConfigNode().getNode("Kill", "Job " + i, "Expiration", "expires at");
                                String string = node.getString();
                                LocalDateTime configKillTime = LocalDateTime.parse(string);
                                if (time.getHour() == configKillTime.getHour() && time.getDayOfWeek() == configKillTime.getDayOfWeek() && time.getMinute() == configKillTime.getMinute() || time.isAfter(configKillTime)) {
                                    System.out.println("[PixelJobs] Found expired kill job, removing it!");
                                    try {
                                        PixelJobs.INSTANCE.getConfigNode().getNode("Kill", "Job " + i).setValue(null);
                                        PixelJobs.INSTANCE.getConfigNode().getNode("Number of", "Kill jobs").setValue(numberKill - 1);
                                        PixelJobs.INSTANCE.getLoader().save(PixelJobs.INSTANCE.getConfigNode());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }

                    int numberEvolve = PixelJobs.INSTANCE.getConfigNode().getNode("Number of", "Evolve jobs").getInt();
                    if (numberEvolve > 0) {
                        for (int i = 1; i <= numberKill; i++) {
                            LocalDateTime time = LocalDateTime.now();
                            if (!PixelJobs.INSTANCE.getConfigNode().getNode("Evolve", "Job 1").isVirtual()) {
                                ConfigurationNode node = PixelJobs.INSTANCE.getConfigNode().getNode("Evolve", "Job " + i, "Expiration", "expires at");
                                String string = node.getString();
                                LocalDateTime configEvolveTime = LocalDateTime.parse(string);
                                if (time.getHour() == configEvolveTime.getHour() && time.getDayOfWeek() == configEvolveTime.getDayOfWeek() && time.getMinute() == configEvolveTime.getMinute() || time.isAfter(configEvolveTime)) {
                                    System.out.println("[PixelJobs] Found expired evolve job, removing it!");
                                    try {
                                        PixelJobs.INSTANCE.getConfigNode().getNode("Evolve", "Job " + i).setValue(null);
                                        PixelJobs.INSTANCE.getConfigNode().getNode("Number of", "Evolve jobs").setValue(numberEvolve - 1);
                                        PixelJobs.INSTANCE.getLoader().save(PixelJobs.INSTANCE.getConfigNode());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                })
                .interval(number, TimeUnit.MINUTES)
                .submit(PixelJobs.INSTANCE);

    }

}
