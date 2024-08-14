package net.sixik.sdmorestages.fabric;

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.sixik.sdmorestages.SDMOreStages;
import net.sixik.sdmorestages.common.OreStageContainer;

public class FabricStageContainer extends OreStageContainer implements IdentifiableResourceReloadListener {
    @Override
    public ResourceLocation getFabricId() {
        return new ResourceLocation(SDMOreStages.MOD_ID, "stage_container");
    }
}
