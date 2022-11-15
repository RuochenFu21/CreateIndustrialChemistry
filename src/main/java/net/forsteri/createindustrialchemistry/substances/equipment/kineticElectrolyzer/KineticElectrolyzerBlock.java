package net.forsteri.createindustrialchemistry.substances.equipment.kineticElectrolyzer;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllShapes;
import com.simibubi.create.content.contraptions.base.HorizontalAxisKineticBlock;
import com.simibubi.create.content.contraptions.base.HorizontalKineticBlock;
import com.simibubi.create.foundation.block.ITE;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.tileEntities.TileEntities;
import net.forsteri.createindustrialchemistry.utility.BlockShapes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("deprecation")
@ParametersAreNonnullByDefault
public class KineticElectrolyzerBlock extends HorizontalKineticBlock implements ITE<KineticElectrolyzerTileEntity> {
    public KineticElectrolyzerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public Class<KineticElectrolyzerTileEntity> getTileEntityClass() {
        return KineticElectrolyzerTileEntity.class;
    }

    @Override
    public boolean canSurvive(@NotNull BlockState state, LevelReader worldIn, BlockPos pos) {
        return !AllBlocks.BASIN.has(worldIn.getBlockState(pos.below()));
    }
    @Override
    public VoxelShape getShape(@NotNull BlockState state, BlockGetter getter, BlockPos pos,
                               CollisionContext context) {
        return BlockShapes.CASING_10PX.get(Direction.UP);
    }

    @Override
    public SpeedLevel getMinimumRequiredSpeedLevel() {
        return SpeedLevel.FAST;
    }

    @Override
    public BlockEntityType<? extends KineticElectrolyzerTileEntity> getTileEntityType() {
        return TileEntities.KINETIC_ELECTROLYZER_TILE_ENTITY.get();
    }

    @Override
    public Direction.Axis getRotationAxis(BlockState state) {
        return Direction.Axis.Y;
    }

    @Override
    public boolean hasShaftTowards(LevelReader world, BlockPos pos, BlockState state, Direction face) {
        return face == Direction.UP;
    }
}
