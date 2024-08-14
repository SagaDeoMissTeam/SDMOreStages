package net.sixik.sdmorestages.fabric;

import net.minecraft.world.entity.player.Player;
import net.sixik.sdmgamestages.api.GameStageHelper;
import net.sixik.sdmorestages.api.IStageHelper;

import java.util.List;

public class FabricStageHelper implements IStageHelper {
    @Override
    public void addStage(Player player, String stage) {
        GameStageHelper.addStage(stage, player);
    }

    @Override
    public void removeStage(Player player, String stage) {
        GameStageHelper.removeStage(stage ,player);
    }

    @Override
    public boolean hasStage(Player player, String stage) {
        return GameStageHelper.hasStage(stage,player);
    }

    @Override
    public List<String> getStages(Player player) {
        return GameStageHelper.getStages(player);
    }
}
