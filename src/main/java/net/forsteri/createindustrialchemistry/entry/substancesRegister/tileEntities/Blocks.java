package net.forsteri.createindustrialchemistry.entry.substancesRegister.tileEntities;

import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.EquipmentTab;
import net.forsteri.createindustrialchemistry.substances.equipment.kineticElectrolyzer.KineticElectrolyzerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static com.simibubi.create.AllTags.pickaxeOnly;

public class Blocks {
    private static final CreateRegistrate REGISTRATE = CreateIndustrialChemistry
            .registrate()
            .creativeModeTab(
                    () -> EquipmentTab.EQUIPMENT_TAB
            );

    public static final BlockEntry<KineticElectrolyzerBlock> KINETIC_ELECTROLYZER_BLOCK = REGISTRATE.block("kinetic_electrolyzer", KineticElectrolyzerBlock::new)
            .initialProperties(SharedProperties::softMetal)
            .blockstate(BlockStateGen.horizontalBlockProvider(true))
            .transform(pickaxeOnly())
            .properties(BlockBehaviour.Properties::noOcclusion)
            .item()
            .build()
            .transform(BlockStressDefaults.setImpact(8))
            .register();

    public static void register(){}
}
