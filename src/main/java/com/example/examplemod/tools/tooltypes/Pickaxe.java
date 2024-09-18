package com.example.examplemod.tools.tooltypes;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class Pickaxe extends PickaxeItem {
    public Pickaxe(Tier tier, Properties properties) {
        super(tier, properties);
    }

    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
    }
}
