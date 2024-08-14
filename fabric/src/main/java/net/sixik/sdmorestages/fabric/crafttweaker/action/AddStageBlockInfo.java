package net.sixik.sdmorestages.fabric.crafttweaker.action;

import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import com.blamejared.crafttweaker.api.tag.type.KnownTag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.sixik.sdmorestages.SDMOreStages;
import net.sixik.sdmorestages.api.BlockTags;
import net.sixik.sdmorestages.common.OreStageContainer;

import java.util.ArrayList;

public class AddStageBlockInfo implements IRuntimeAction {
    private final String stage;
    private final BlockState block;
    private final BlockState blockReplace;
    private final boolean explosion;
    private final KnownTag<Block> tag;

    public AddStageBlockInfo(String stage, BlockState block, BlockState blockReplace, boolean explosion) {
        this.stage = stage;
        this.block = block;
        this.blockReplace = blockReplace;
        this.explosion = explosion;
        this.tag = null;
    }

    public AddStageBlockInfo(String stage, KnownTag<Block> tag, BlockState blockReplace, boolean explosion) {
        this.stage = stage;
        this.block = null;
        this.blockReplace = blockReplace;
        this.explosion = explosion;
        this.tag = tag;
    }

    public void apply() {
        OreStageContainer.getOrCreateBlock(this.stage, this.block, new BlockTags<>(this.tag == null ? new ArrayList<>() : this.tag.elements()), this.blockReplace, this.explosion);
    }

    public String describe() {
        return null;
    }

    public String systemName() {
        return "stage_block";
    }
}

