package com.piwalker.advancedmod.creativetabs;

import com.piwalker.advancedmod.init.ModBlocks;
import com.piwalker.advancedmod.reference.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by SamuelPiWalker on 7/21/2015.
 */
public class CreativeTab {
    public static CreativeTabs ADVANCED_MOD_TAB = new CreativeTabs(Reference.MOD_ID){

        @Override
        public Item getTabIconItem() {
            return new ItemStack(ModBlocks.smileface).getItem();
        }
    };
}
