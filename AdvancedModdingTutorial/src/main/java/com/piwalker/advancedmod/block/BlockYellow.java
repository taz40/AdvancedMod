package com.piwalker.advancedmod.block;

import com.piwalker.advancedmod.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by SamuelPiWalker on 7/21/2015.
 */
public class BlockYellow extends BlockAdvancedMod {
    public BlockYellow(){
        super();
        this.setBlockName(Names.Blocks.YELLOW);
        this.setTickRandomly(true);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        world.playSoundEffect(x, y, z, "mob.enderdragon.wings", 1.0F, 1.0F);
    }
}
