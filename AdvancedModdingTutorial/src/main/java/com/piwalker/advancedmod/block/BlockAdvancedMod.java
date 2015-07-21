package com.piwalker.advancedmod.block;

import com.piwalker.advancedmod.creativetabs.CreativeTab;
import com.piwalker.advancedmod.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

/**
 * Created by SamuelPiWalker on 7/21/2015.
 */
public class BlockAdvancedMod extends Block{
    public BlockAdvancedMod(Material m){
        super(m);
        this.setCreativeTab(CreativeTab.ADVANCED_MOD_TAB);
    }

    public BlockAdvancedMod(){
        this(Material.rock);
    }

    @Override
    public String getUnlocalizedName(){
        return String.format("tile.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        blockIcon = iconRegister.registerIcon(String.format("%s",getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
