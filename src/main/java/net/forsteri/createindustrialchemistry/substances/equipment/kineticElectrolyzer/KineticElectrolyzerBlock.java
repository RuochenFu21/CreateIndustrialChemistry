package net.forsteri.createindustrialchemistry.substances.equipment.kineticElectrolyzer;

import com.simibubi.create.content.contraptions.base.KineticBlock;
import com.simibubi.create.foundation.block.ITE;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.tileEntities.TileEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class KineticElectrolyzerBlock extends KineticBlock implements ITE<KineticElectrolyzerTileEntity> {
    public KineticElectrolyzerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return Direction.Axis.Y;
    }

    @Override
    public Class<KineticElectrolyzerTileEntity> getTileEntityClass() {
        return KineticElectrolyzerTileEntity.class;
    }

    @Override
    public BlockEntityType<? extends KineticElectrolyzerTileEntity> getTileEntityType() {
        return TileEntities.KINETIC_ELECTROLYZER_TILE_ENTITY.get();
    }
    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face.getAxis() == Direction.Axis.Y;
    }
}
