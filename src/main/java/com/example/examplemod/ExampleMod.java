package com.example.examplemod;

import com.example.examplemod.tools.ToolBehavior;
import com.example.examplemod.tools.ToolData;
import com.example.examplemod.tools.ToolItem;
import com.example.examplemod.tools.ToolMaterial;
import com.example.examplemod.tools.ToolType;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NewRegistryEvent;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Supplier;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ExampleMod.MODID)
public class ExampleMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "examplemod";
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<ToolData>> TOOL_DATA = DATA_COMPONENTS.registerComponentType(
            "tool_data",
            builder -> builder
                    .persistent(ToolData.CODEC_DATA)
                    .networkSynchronized(ToolData.STREAM_CODEC_DATA)
    );

    public static final ResourceKey<Registry<ToolBehavior>> TOOL_BEHAVIOR_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MODID, "tool_behaviors"));
    public static final Registry<ToolBehavior> TOOL_BEHAVIOR_REGISTRY = new RegistryBuilder<>(TOOL_BEHAVIOR_REGISTRY_KEY)
            .defaultKey(ResourceLocation.fromNamespaceAndPath(MODID, "empty"))
            .create();

    public static final DeferredRegister<ToolBehavior> TOOL_BEHAVIORS = DeferredRegister.create(TOOL_BEHAVIOR_REGISTRY, MODID);

    public static final Supplier<Item> TOOL = ExampleMod.ITEMS.registerItem(
            "tool",
            ToolItem::new,
            new Item.Properties()
    );

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ExampleMod(IEventBus modEventBus, ModContainer modContainer)
    {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        DATA_COMPONENTS.register(modEventBus);
        TOOL_BEHAVIORS.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        modEventBus.addListener(this::registerRegistries);
        modEventBus.addListener(this::registerDatapackRegistries);
    }

    public static final ResourceKey<Registry<ToolMaterial>> TOOL_MATERIAL_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MODID, "tool_materials"));
    public static final ResourceKey<Registry<ToolType>> TOOL_TYPE_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(MODID, "tool_types"));

    public void registerRegistries(NewRegistryEvent event) {
        event.register(TOOL_BEHAVIOR_REGISTRY);
    }

    public void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
        event.dataPackRegistry(TOOL_MATERIAL_REGISTRY_KEY, ToolMaterial.CODEC_MATERIAL, ToolMaterial.CODEC_MATERIAL);
        event.dataPackRegistry(TOOL_TYPE_REGISTRY_KEY, ToolType.CODEC_TYPE, ToolType.CODEC_TYPE);
    }
}
