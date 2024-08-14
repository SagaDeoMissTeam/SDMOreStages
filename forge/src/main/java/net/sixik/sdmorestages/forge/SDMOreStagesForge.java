package net.sixik.sdmorestages.forge;

import dev.architectury.platform.forge.EventBuses;
import net.darkhax.gamestages.event.GameStageEvent;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.sixik.sdmorestages.SDMOreStages;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.sixik.sdmorestages.mixin.ChunkMapAccessor;

@Mod(SDMOreStages.MOD_ID)
public class SDMOreStagesForge {
    public SDMOreStagesForge() {
		// Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(SDMOreStages.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        SDMOreStages.init();
        SDMOreStages.STAGES = new ForgeStageHelper();
        SDMOreStages.STAGE_CONTAINER = new ForgeStageContainer();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(this::onReload);
    }

    private void onReload(AddReloadListenerEvent event) {
        event.addListener(SDMOreStages.STAGE_CONTAINER);
    }

    @SubscribeEvent
    public void onGameStageAdded(GameStageEvent.Added event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            ChunkMap map = ((ServerLevel)player.level()).getChunkSource().chunkMap;
            ((ChunkMapAccessor)map).setUpdatePlayerStatus(player, true);
        }
    }

    @SubscribeEvent
    public void onGameStageRemoved(GameStageEvent.Remove event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            ChunkMap map = ((ServerLevel)player.level()).getChunkSource().chunkMap;
            ((ChunkMapAccessor)map).setUpdatePlayerStatus(player, true);
        }

    }

    @SubscribeEvent
    public void onGameStageCleared(GameStageEvent.Cleared event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            ChunkMap map = ((ServerLevel)player.level()).getChunkSource().chunkMap;
            ((ChunkMapAccessor)map).setUpdatePlayerStatus(player, true);
        }
    }
}