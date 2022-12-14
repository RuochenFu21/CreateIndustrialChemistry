package net.forsteri.createindustrialchemistry.entry.registers;

import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.forsteri.createindustrialchemistry.entry.registers.entities.Types;
import net.forsteri.createindustrialchemistry.entry.registers.substances.*;
import net.forsteri.createindustrialchemistry.entry.registers.tileEntities.Blocks;
import net.forsteri.createindustrialchemistry.entry.registers.tileEntities.TileEntities;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class DeferredRegisters {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CreateIndustrialChemistry.MOD_ID);

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CreateIndustrialChemistry.MOD_ID);

    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, CreateIndustrialChemistry.MOD_ID);

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CreateIndustrialChemistry.MOD_ID);

    public static void register(IEventBus eventBus){
        Equipments.register();
        SolidSubstances.register();
        LiquidSubstances.register();
        GasSubstances.register();
        AqueousSolutions.register();
        Mixtures.register();
        TileEntities.register();
        Blocks.register();
        Types.register();
        MobEffects.register();
        ITEMS.register(eventBus);
        BLOCKS.register(eventBus);
        FLUIDS.register(eventBus);
        MOB_EFFECTS.register(eventBus);
    }

    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");


}
