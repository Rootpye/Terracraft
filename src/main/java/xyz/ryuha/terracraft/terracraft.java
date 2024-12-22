package xyz.ryuha.terracraft;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry; // 올바른 레지스트리 import
import net.minecraft.registry.Registries; // 추가된 레지스트리 메커니즘
import net.minecraft.world.gen.chunk.ChunkGenerator;

// TerraCraft Mod Initializer
public class terracraft implements ModInitializer { // 클래스 이름 수정
    public static final String MOD_ID = "terracraft";

    @Override
    public void onInitialize() {
        System.out.println("[DEBUG]: onInitialize() 시작");
        System.out.println("TerraCraft Mod Initialized!");

        // Register the custom chunk generator
        Registry.register(
                Registries.CHUNK_GENERATOR, // 올바른 레지스트리 참조
                new Identifier(MOD_ID, "chunk_generator"),
                ChunkGenerator.CODEC // 코드 등록
        );
    }
}
