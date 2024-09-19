package com.example.examplemod.tools.tooltypes;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.tools.tooltypes.Pickaxe;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ToolItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExampleMod.MODID);

    public static final Supplier<Item> PICKAXE = ITEMS.registerItem(
            "pickaxe",
            Item::new,
            new Pickaxe.Properties()
    );

    // Java classloading gets sad if this isn't here
    public static void init() {}
}
