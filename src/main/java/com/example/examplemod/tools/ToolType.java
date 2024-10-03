package com.example.examplemod.tools;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
/*import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;*/
import net.minecraft.resources.RegistryFixedCodec;

import java.util.List;

import static com.example.examplemod.ExampleMod.TOOL_BEHAVIOR_REGISTRY_KEY;

public record ToolType(String name, List<Holder<ToolBehavior>> behaviors) {

    /*public static final StreamCodec<RegistryFriendlyByteBuf, ToolType> STREAM_CODEC_TYPE = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, ToolType::name,
            ByteBufCodecs.holderRegistry(TOOL_BEHAVIOR_REGISTRY_KEY).apply(ByteBufCodecs.list()), ToolType::behaviors, ToolType::new
    );*/

    public static final Codec<ToolType> CODEC_TYPE = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("name").forGetter(ToolType::name),
                    RegistryFixedCodec.create(TOOL_BEHAVIOR_REGISTRY_KEY).listOf().fieldOf("behavior").forGetter(ToolType::behaviors)
            ).apply(instance, ToolType::new)
    );
}
