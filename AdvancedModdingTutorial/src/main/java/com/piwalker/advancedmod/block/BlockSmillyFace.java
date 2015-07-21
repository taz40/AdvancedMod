package com.piwalker.advancedmod.block;

import com.piwalker.advancedmod.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 * Created by SamuelPiWalker on 7/21/2015.
 */
public class BlockSmillyFace extends BlockAdvancedMod {
    public BlockSmillyFace(){
        super();
        this.setBlockName(Names.Blocks.SMILEY);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int I1, float F1, float F2, float F3){
        return false;
    }

}
