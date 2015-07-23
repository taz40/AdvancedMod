package com.piwalker.advancedmod.block;

import com.piwalker.advancedmod.reference.Names;
import com.piwalker.advancedmod.tileentity.TileEntityCamoMine;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by SamuelPiWalker on 7/22/2015.
 */
public class BlockCamoMine extends BlockAdvancedModTileEntity {

    public BlockCamoMine(){
        super();
        this.setBlockName(Names.Blocks.CAMOMINE);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityCamoMine();
    }
}
