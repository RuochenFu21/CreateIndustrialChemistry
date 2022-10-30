package net.forsteri.createindustrialchemistry.substances.utilities.fluids;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.forsteri.createindustrialchemistry.substances.abstracts.FlowingFluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@SuppressWarnings({"DeprecatedIsStillUsed", "deprecation"})
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class FluidBlock extends LiquidBlock implements TankPickup{
    public static final IntegerProperty LEVEL = BlockStateProperties.LEVEL;
    @Deprecated // Use getFluid
    private final net.minecraft.world.level.material.FlowingFluid fluid;
    private final List<FluidState> stateCache;
    public static final VoxelShape STABLE_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    public static final ImmutableList<Direction> POSSIBLE_FLOW_DIRECTIONS = ImmutableList.of(Direction.DOWN, Direction.SOUTH, Direction.NORTH, Direction.EAST, Direction.WEST);

    @Deprecated  // Forge: Use the constructor that takes a supplier
    public FluidBlock(net.minecraft.world.level.material.FlowingFluid pFluid, BlockBehaviour.Properties pProperties) {
        super(pFluid, pProperties);
        this.fluid = pFluid;
        this.stateCache = Lists.newArrayList();
        this.stateCache.add(pFluid.getSource(false));

        for(int i = 1; i < 8; ++i) {
            this.stateCache.add(pFluid.getFlowing(8 - i, false));
        }

        this.stateCache.add(pFluid.getFlowing(8, true));
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));
        fluidStateCacheInitialized = true;
        supplier = pFluid.delegate;
    }
    public FluidBlock(java.util.function.Supplier<? extends net.minecraft.world.level.material.FlowingFluid> pFluid, BlockBehaviour.Properties pProperties) {
        super(pFluid, pProperties);
        this.fluid = null;
        this.stateCache = Lists.newArrayList();
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 0));
        this.supplier = pFluid;
    }

    /**
     * @return whether this block needs random ticking.
     */
    public boolean isRandomlyTicking(BlockState pState) {
        return pState.getFluidState().isRandomlyTicking();
    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, Random pRandom) {
        pState.getFluidState().randomTick(pLevel, pPos, pRandom);
    }

    public boolean propagatesSkylightDown(BlockState pState, BlockGetter pReader, BlockPos pPos) {
        return false;
    }

    public boolean isPathfindable(BlockState pState, BlockGetter pLevel, BlockPos pPos, PathComputationType pType) {
        return !this.fluid.is(FluidTags.LAVA);
    }

    public FluidState getFluidState(BlockState pState) {
        int i = pState.getValue(LEVEL);
        if (!fluidStateCacheInitialized) initFluidStateCache();
        return this.stateCache.get(Math.min(i, 8));
    }

    public boolean skipRendering(BlockState pState, BlockState pAdjacentBlockState, Direction pSide) {
        return pAdjacentBlockState.getFluidState().getType().isSame(this.fluid);
    }

    /**
     * The type of render function called. MODEL for mixed tesr and static model, MODELBLOCK_ANIMATED for TESR-only,
     * LIQUID for vanilla liquids, INVISIBLE to skip all rendering
     * whenever possible. Implementing/overriding is fine.
     */
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.INVISIBLE;
    }

    public List<ItemStack> getDrops(BlockState pState, LootContext.Builder pBuilder) {
        return Collections.emptyList();
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.empty();
    }

    /**
     * Update the provided state given the provided neighbor direction and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific direction passed in.
     */

    private void fizz(LevelAccessor pLevel, BlockPos pPos) {
        pLevel.levelEvent(1501, pPos, 0);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LEVEL);
    }

    // Forge start
    private final java.util.function.Supplier<? extends net.minecraft.world.level.material.Fluid> supplier;
    public net.minecraft.world.level.material.FlowingFluid getFluid() {
        return (ForgeFlowingFluid)supplier.get();
    }

    private boolean fluidStateCacheInitialized = false;
    protected synchronized void initFluidStateCache() {
        if (!fluidStateCacheInitialized) {
            this.stateCache.add(getFluid().getSource(false));

            for (int i = 1; i < 8; ++i)
                this.stateCache.add(getFluid().getFlowing(8 - i, false));

            this.stateCache.add(getFluid().getFlowing(8, true));
            fluidStateCacheInitialized = true;
        }
    }

    @Override
    public ItemStack tankPickupBlock(LevelAccessor pLevel, BlockPos pPos, BlockState pState) {
        if (pState.getValue(LEVEL) == 0) {
            pLevel.setBlock(pPos, Blocks.AIR.defaultBlockState(), 11);
            if(this.supplier.get() instanceof FlowingFluid){
                return new ItemStack(
                        ((FlowingFluid) this.supplier.get()).getTank()
                );
            }
            return new ItemStack(
                    ((FlowingFluid) this.supplier.get()).getTank()
            );
        } else {
            return ItemStack.EMPTY;
        }
    }
}