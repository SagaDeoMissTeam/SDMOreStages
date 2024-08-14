package net.sixik.sdmorestages.common;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.sixik.sdmorestages.api.BlockTags;

import java.util.ArrayList;
import java.util.List;

public class OreStageContainer extends SimplePreparableReloadListener<Void> {

    public static BlockStageInfo getOrCreateBlock(String stage, BlockState block, BlockTags<Block> blockTags, BlockState blockReplace, boolean explosion) {
        BlockStageInfo info = new BlockStageInfo(stage, block, blockTags.elements(), blockReplace, explosion);
        if (GLOBAL_BLOCK_INFO.contains(info))
            return info;

        GLOBAL_BLOCK_INFO.add(info);
        return info;

    }

    public static final List<BlockStageInfo> GLOBAL_BLOCK_INFO = new ArrayList();

    @Override
    protected Void prepare(ResourceManager p_10796_, ProfilerFiller p_10797_) {
        return null;
    }

    @Override
    protected void apply(Void p_10793_, ResourceManager p_10794_, ProfilerFiller p_10795_) {
        GLOBAL_BLOCK_INFO.clear();
    }
}
