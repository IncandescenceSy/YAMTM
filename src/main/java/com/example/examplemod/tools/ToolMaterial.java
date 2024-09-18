package com.example.examplemod.tools;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record ToolMaterial(String name, String color,
                           int durability, float attackDamage, float miningSpeed, int miningLevel,
                           float drawSpeed, float airSpeed,
                           float weight, float duraMult,
                           boolean generateRecipes) {
    // Missing capabilities: items/tags to use for the material item, available/missing parts, innate augments

    public static final Codec<ToolMaterial> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("name").forGetter(ToolMaterial::name),
                    Codec.STRING.fieldOf("color").forGetter(ToolMaterial::color),
                    Codec.INT.fieldOf("durability").forGetter(ToolMaterial::durability),

                    // Materials that don't use these stats have them set to -1; if any stat is ever actually -1, something is wrong
                    Codec.FLOAT.optionalFieldOf("attackDamage", -1F).forGetter(ToolMaterial::attackDamage),
                    Codec.FLOAT.optionalFieldOf("miningSpeed", -1F).forGetter(ToolMaterial::miningSpeed),
                    Codec.INT.optionalFieldOf("miningLevel", -1).forGetter(ToolMaterial::miningLevel),
                    Codec.FLOAT.optionalFieldOf("drawSpeed", -1F).forGetter(ToolMaterial::drawSpeed),
                    Codec.FLOAT.optionalFieldOf("airSpeed", -1F).forGetter(ToolMaterial::airSpeed),
                    Codec.FLOAT.optionalFieldOf("weight", -1F).forGetter(ToolMaterial::weight),
                    Codec.FLOAT.optionalFieldOf("duraMult", -1F).forGetter(ToolMaterial::duraMult),

                    // Prevents making tools normally. Intended for Netherite
                    Codec.BOOL.optionalFieldOf("generateRecipes", true).forGetter(ToolMaterial::generateRecipes)
            ).apply(instance, ToolMaterial::new)
    );
}
