package net.forsteri.createindustrialchemistry.entry.substancesRegister.tileEntities;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.components.millstone.MillstoneBlock;
import com.simibubi.create.content.contraptions.relays.encased.ShaftInstance;
import com.simibubi.create.foundation.block.BlockStressDefaults;
import com.simibubi.create.foundation.data.AssetLookup;
import com.simibubi.create.foundation.data.BlockStateGen;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.forsteri.createindustrialchemistry.entry.creativeModeTabs.EquipmentTab;
import net.forsteri.createindustrialchemistry.substances.equipment.kineticElectrolyzer.KineticElectrolyzerBlock;
import net.forsteri.createindustrialchemistry.substances.equipment.kineticElectrolyzer.KineticElectrolyzerInstance;
import net.forsteri.createindustrialchemistry.substances.equipment.kineticElectrolyzer.KineticElectrolyzerRenderer;
import net.forsteri.createindustrialchemistry.substances.equipment.kineticElectrolyzer.KineticElectrolyzerTileEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;

import static com.simibubi.create.AllTags.pickaxeOnly;
import static com.simibubi.create.foundation.data.ModelGen.customItemModel;

public class TileEntities {
    public static void register(){}

    private static final CreateRegistrate REGISTRATE = CreateIndustrialChemistry
            .registrate()
            .creativeModeTab(
                    () -> EquipmentTab.EQUIPMENT_TAB
            );

    public static final BlockEntityEntry<KineticElectrolyzerTileEntity> KINETIC_ELECTROLYZER_TILE_ENTITY = REGISTRATE
            .tileEntity("kinetic_electrolyzer", KineticElectrolyzerTileEntity::new)
            .instance(() -> KineticElectrolyzerInstance::new, false)
            .validBlocks(Blocks.KINETIC_ELECTROLYZER_BLOCK)
            .renderer(() -> KineticElectrolyzerRenderer::new)
            .register();
}
