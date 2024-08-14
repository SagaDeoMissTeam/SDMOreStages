package net.sixik.sdmorestages.fabric.crafttweaker;

import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.tag.type.KnownTag;
import com.blamejared.crafttweaker_annotations.annotations.Document;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.sixik.sdmorestages.fabric.crafttweaker.action.AddStageBlockInfo;
import org.openzen.zencode.java.ZenCodeType;

@ZenRegister
@Document("mods/orestages/OreStages")
@ZenCodeType.Name("mods.orestages.OreStages")
public class CraftTweaker {
    public CraftTweaker() {
    }

    @ZenCodeType.Method
    public static void addOreStage(String stage, BlockState block) {
        CraftTweakerAPI.apply(new AddStageBlockInfo(stage, block, Blocks.STONE.defaultBlockState(), false));
    }

    @ZenCodeType.Method
    public static void addOreStage(String stage, BlockState block, boolean explosion) {
        CraftTweakerAPI.apply(new AddStageBlockInfo(stage, block, Blocks.STONE.defaultBlockState(), explosion));
    }

    @ZenCodeType.Method
    public static void addOreStage(String stage, BlockState blockState, BlockState replaceBlock) {
        CraftTweakerAPI.apply(new AddStageBlockInfo(stage, blockState, replaceBlock, false));
    }

    @ZenCodeType.Method
    public static void addOreStage(String stage, BlockState blockState, BlockState replaceBlock, boolean explosion) {
        CraftTweakerAPI.apply(new AddStageBlockInfo(stage, blockState, replaceBlock, explosion));
    }

    @ZenCodeType.Method
    public static void addOreStage(String stage, KnownTag<Block> tag, BlockState replaceBlock) {
        CraftTweakerAPI.apply(new AddStageBlockInfo(stage, tag, replaceBlock, false));
    }

    @ZenCodeType.Method
    public static void addOreStage(String stage, KnownTag<Block> tag, BlockState replaceBlock, boolean explosion) {
        CraftTweakerAPI.apply(new AddStageBlockInfo(stage, tag, replaceBlock, explosion));
    }
}

