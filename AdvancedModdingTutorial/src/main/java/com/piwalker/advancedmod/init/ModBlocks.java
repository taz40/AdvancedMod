package com.piwalker.advancedmod.init;

import com.piwalker.advancedmod.block.*;
import com.piwalker.advancedmod.reference.Names;
import com.piwalker.advancedmod.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by SamuelPiWalker on 7/21/2015.
 */
@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks {
    public static final BlockAdvancedMod smileface = new BlockSmillyFace();
    public static final BlockAdvancedMod blockyellow = new BlockYellow();
    public static final BlockAdvancedModTileEntity camoMine = new BlockCamoMine();

    public static void init(){
        GameRegistry.registerBlock(smileface, Names.Blocks.SMILEY);
        GameRegistry.registerBlock(blockyellow, Names.Blocks.YELLOW);
        GameRegistry.registerBlock(camoMine, Names.Blocks.CAMOMINE);
    }
}
