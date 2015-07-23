package com.piwalker.advancedmod.init;

import com.piwalker.advancedmod.reference.Names;
import com.piwalker.advancedmod.tileentity.TileEntityCamoMine;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by SamuelPiWalker on 7/22/2015.
 */
public class ModTileEntities {

   public static void init(){
       GameRegistry.registerTileEntity(TileEntityCamoMine.class, Names.TileEntities.CAMOMINE);
   }

}
