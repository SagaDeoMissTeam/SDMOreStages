package net.sixik.sdmorestages.api;

import net.minecraft.world.entity.player.Player;

import java.util.List;

public interface IStageHelper {

    void addStage(Player player, String stage);
    void removeStage(Player player, String stage);
    boolean hasStage(Player player, String stage);

    List<String> getStages(Player player);


}
