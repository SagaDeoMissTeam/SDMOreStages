package net.sixik.sdmorestages.forge;

import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.sixik.sdmorestages.api.IStageHelper;

import java.util.List;

public class ForgeStageHelper implements IStageHelper {
    @Override
    public void addStage(Player player, String stage) {
        GameStageHelper.addStage((ServerPlayer) player, stage);
    }

    @Override
    public void removeStage(Player player, String stage) {
        GameStageHelper.removeStage((ServerPlayer) player,stage);
    }

    @Override
    public boolean hasStage(Player player, String stage) {
        return GameStageHelper.hasStage(player,stage);
    }

    @Override
    public List<String> getStages(Player player) {
        return GameStageHelper.getPlayerData(player).getStages().stream().toList();
    }
}
