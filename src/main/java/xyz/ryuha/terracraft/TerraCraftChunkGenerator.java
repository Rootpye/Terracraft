package xyz.ryuha.terracraft;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.ChunkRegion;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.source.BiomeAccess;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.Blender;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.noise.NoiseConfig;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

// 추상 클래스
public abstract class TerraCraftChunkGenerator extends ChunkGenerator {
    protected final ChunkGeneratorSettings settings;

    public TerraCraftChunkGenerator(BiomeSource biomeSource, ChunkGeneratorSettings settings) {
        super(biomeSource);
        this.settings = settings;
    }

    @Override
    public abstract Codec<? extends ChunkGenerator> getCodec();

    public void populateNoise(Chunk chunk) {
        ChunkPos chunkPos = chunk.getPos();
        for (int x = 0; x < 16; x++) {
            int worldX = chunkPos.getStartX() + x;
            for (int z = 0; z < 16; z++) {
                if (z == 8) {
                    for (int y = 0; y < 64; y++) {
                        chunk.setBlockState(
                                new BlockPos(worldX, y, chunkPos.getStartZ() + z),
                                Blocks.STONE.getDefaultState(),
                                false
                        );
                    }
                }
            }
        }
    }

    public void buildSurface(Chunk chunk) {
        // 표면 생성은 비워둡니다.
    }
}

// 구체적인 구현 클래스
class CustomTerraCraftChunkGenerator extends TerraCraftChunkGenerator {
    public static final Codec<CustomTerraCraftChunkGenerator> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    BiomeSource.CODEC.fieldOf("biome_source").forGetter(ChunkGenerator::getBiomeSource),
                    ChunkGeneratorSettings.CODEC.fieldOf("settings").forGetter(generator -> generator.settings)
            ).apply(instance, CustomTerraCraftChunkGenerator::new)
    );

    public CustomTerraCraftChunkGenerator(BiomeSource biomeSource, ChunkGeneratorSettings settings) {
        super(biomeSource, settings);
    }

    @Override
    public Codec<? extends ChunkGenerator> getCodec() {
        return CODEC;
    }

    @Override
    public void carve(ChunkRegion chunkRegion, long seed, NoiseConfig noiseConfig, BiomeAccess biomeAccess, StructureAccessor structureAccessor, Chunk chunk, GenerationStep.Carver carverStep) {

    }

    @Override
    public void buildSurface(ChunkRegion region, StructureAccessor structures, NoiseConfig noiseConfig, Chunk chunk) {

    }

    @Override
    public void populateEntities(ChunkRegion region) {

    }

    @Override
    public int getWorldHeight() {
        return 0;
    }

    @Override
    public CompletableFuture<Chunk> populateNoise(Executor executor, Blender blender, NoiseConfig noiseConfig, StructureAccessor structureAccessor, Chunk chunk) {
        return null;
    }

    @Override
    public int getSeaLevel() {
        return 0;
    }

    @Override
    public int getMinimumY() {
        return 0;
    }

    @Override
    public int getHeight(int x, int z, Heightmap.Type heightmap, HeightLimitView world, NoiseConfig noiseConfig) {
        return 0;
    }

    @Override
    public VerticalBlockSample getColumnSample(int x, int z, HeightLimitView world, NoiseConfig noiseConfig) {
        return null;
    }

    @Override
    public void getDebugHudText(List<String> text, NoiseConfig noiseConfig, BlockPos pos) {

    }
}