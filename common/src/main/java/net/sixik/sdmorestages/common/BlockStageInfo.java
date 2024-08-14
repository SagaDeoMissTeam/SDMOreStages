package net.sixik.sdmorestages.common;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class BlockStageInfo {

    private String stage;
    private BlockState block;
    private BlockState blockReplace;
    private List<Block> tag;
    private boolean explosion;

    public BlockStageInfo(String stage, BlockState block, List<Block> tag, BlockState blockReplace, boolean explosion) {
        this.stage = stage;
        this.block = block;
        this.blockReplace = blockReplace;
        this.explosion = explosion;
        this.tag = tag;
    }

    public BlockStageInfo(String stage, BlockState blockReplace, List<Block> tag, boolean explosion) {
        this.stage = stage;
        this.block = null;
        this.tag = tag;
        this.blockReplace = blockReplace;
        this.explosion = explosion;
    }

    public BlockState getBlock() {
        return this.block;
    }

    public List<Block> getTag() {
        return this.tag;
    }

    public BlockState getBlockReplace() {
        return this.blockReplace;
    }

    public String getStage() {
        return this.stage;
    }

    public boolean isExplosion() {
        return this.explosion;
    }
}

