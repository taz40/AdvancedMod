package com.piwalker.advancedmod.block;

import com.piwalker.advancedmod.reference.Names;
import com.piwalker.advancedmod.tileentity.TileEntityAdvancedMod;
import com.piwalker.advancedmod.tileentity.TileEntityCamoMine;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
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


    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float HitX, float HitY, float hitZ) {
        if(!world.isRemote) {
            TileEntityCamoMine te = (TileEntityCamoMine)world.getTileEntity(x, y, z);
            if(te.getCamouflage(side) != null){
                ItemStack camoStack = te.getCamouflage(side);
                te.setCamouflage(null, side);
                EntityItem itemEntity = new EntityItem(world, x, y, z, camoStack);
                world.spawnEntityInWorld(itemEntity);
            }else {
                ItemStack playerItem = player.getCurrentEquippedItem();
                if (playerItem != null) {
                    ItemStack camoStack = playerItem.splitStack(1);
                    te.setCamouflage(camoStack, side);
                }
            }
        }
        return true;
    }

    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        TileEntityCamoMine te = (TileEntityCamoMine) world.getTileEntity(x, y, z);
        ItemStack stack = te.getCamouflage(side);
        if(stack != null && stack.getItem() instanceof ItemBlock){
            Block block = ((ItemBlock) stack.getItem()).field_150939_a;
            return block.getIcon(side, world.getBlockMetadata(x, y, z));
        }else{
            return super.getIcon(world, x, y, z, side);
        }
    }
}
