package net.sixik.sdmorestages.api;

import java.util.List;

public class BlockTags<T> {

    private final List<T> blocks;
    public BlockTags(List<T> blocks) {
        this.blocks = blocks;
    }

    public List<T> elements() {
        return blocks;
    }
}
