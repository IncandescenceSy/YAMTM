package com.example.examplemod.tools;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import static com.example.examplemod.ExampleMod.TOOL_DATA;

public class ToolItem extends Item {

    public ToolItem(Properties properties) {
        super(properties);
    }

    // Todo: switch from hardcoded text to lang entries
        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
            ToolData data = stack.get(TOOL_DATA);
            Integer materialNumber = 0;

            if (data != null) {
                for (Holder<ToolType> type : data.type()) {
                    tooltipComponents.add(Component.literal("Tool Type " + type.value().name()));
                }
                for (Holder<ToolMaterial> material : data.material()) {
                    tooltipComponents.add(Component.literal("Material " + materialNumber.toString() + ": " + material.value().name()));
                    materialNumber++;
                }
            }
            else {
                tooltipComponents.add(Component.literal("This tool has no material data! If you somehow crafted this, report it as a bug!"));
            }
        }
}
