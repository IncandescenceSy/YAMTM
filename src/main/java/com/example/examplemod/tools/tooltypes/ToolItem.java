package com.example.examplemod.tools.tooltypes;

import com.example.examplemod.tools.ToolMaterial;
import com.example.examplemod.tools.ToolParts;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

import static com.example.examplemod.ExampleMod.TOOL_PARTS;

public class ToolItem extends Item {
    public ToolItem(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        ToolParts parts = stack.get(TOOL_PARTS);

        if (parts != null) {
            for (Holder<ToolMaterial> material : parts.material()) {
                tooltipComponents.add(Component.literal(material.value().name()));
            }
        }
        else { // Todo: switch from hardcoded text to lang entry
            tooltipComponents.add(Component.literal("This tool has no material data! If you somehow crafted this, report it as a bug!"));
        }
    }
}
