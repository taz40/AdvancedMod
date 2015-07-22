package com.piwalker.advancedmod.world.gen;

import com.piwalker.advancedmod.init.ModBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/**
 * Created by SamuelPiWalker on 7/21/2015.
 */
public class WorldGeneratorFlag implements IWorldGenerator {
    private WorldGenMinable flagGen = new WorldGenMinable(ModBlocks.blockyellow, 32);

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if(random.nextInt(10) == 0 && world.provider.dimensionId != -1 && world.provider.dimensionId != 1){
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            int y = world.getHeightValue(x, z);
            if(world.getBiomeGenForCoords(x, z) != BiomeGenBase.river && world.getBiomeGenForCoords(x, z) != BiomeGenBase.ocean && world.getBiomeGenForCoords(x, z) != BiomeGenBase.deepOcean) {
                Block block = world.getBlock(x, y-1, z);
                if(block != Blocks.water && block != Blocks.flowing_water && block != Blocks.flowing_lava && block != Blocks.lava && block != Blocks.leaves && block != Blocks.leaves2) {
                    generateFlag(world, x, y, z);
                }
            }
        }

        for(int i = 0; i < 5; i++){
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            int y = 20 + random.nextInt(40);
            flagGen.generate(world, random, x, y, z);
        }
    }

    private void generateFlag(World world, int x, int y, int z){
        for (int i = 0; i < 6; i++) {
            world.setBlock(x, y + i, z, Blocks.fence);
        }
        for(int i = 1; i < 6; i++){
            for(int iy = 3; iy < 6; iy++){
                world.setBlock(x+i, y + iy, z, ModBlocks.blockyellow);
            }
        }
        world.setBlock(x+3, y + 4, z, ModBlocks.smileface);
    }
}
