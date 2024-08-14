package net.sixik.sdmorestages.fabric;

import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.packs.PackType;
import net.sixik.sdmgamestages.SDMGameStages;
import net.sixik.sdmgamestages.api.GameStagesEvent;
import net.sixik.sdmorestages.SDMOreStages;
import net.fabricmc.api.ModInitializer;
import net.sixik.sdmorestages.common.BlockStageInfo;
import net.sixik.sdmorestages.common.OreStageContainer;
import net.sixik.sdmorestages.mixin.ChunkMapAccessor;

public class SDMOreStagesFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        SDMOreStages.init();
        SDMOreStages.STAGES = new FabricStageHelper();
        SDMOreStages.STAGE_CONTAINER = new FabricStageContainer();
//        ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener((FabricStageContainer) SDMOreStages.STAGE_CONTAINER);
        eventInit();
    }


    public void eventInit() {

        GameStagesEvent.ADD.register(((player, s) -> {
            if (player instanceof ServerPlayer serverPlayer) {
                ChunkMap map = ((ServerLevel)serverPlayer.level).getChunkSource().chunkMap;
                ((ChunkMapAccessor)map).setUpdatePlayerStatus(serverPlayer, true);
            }
        }));
        GameStagesEvent.REMOVE.register(((player, s) -> {
            if (player instanceof ServerPlayer serverPlayer) {
                ChunkMap map = ((ServerLevel)serverPlayer.level).getChunkSource().chunkMap;
                ((ChunkMapAccessor)map).setUpdatePlayerStatus(serverPlayer, true);
            }
        }));
        GameStagesEvent.CLEAR.register(((player) -> {
            if (player instanceof ServerPlayer serverPlayer) {
                ChunkMap map = ((ServerLevel)serverPlayer.level).getChunkSource().chunkMap;
                ((ChunkMapAccessor)map).setUpdatePlayerStatus(serverPlayer, true);
            }
        }));
    }
}