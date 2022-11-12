package net.forsteri.createindustrialchemistry.substances.equipment.kineticElectrolyzer.recipe;

import net.forsteri.createindustrialchemistry.entry.substancesRegister.GasSubstances;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.LiquidSubstances;
import net.forsteri.createindustrialchemistry.substances.element.Hydrogen;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public enum ElectrolysisRecipes {
    // This is just for before we introduce recipe
    // never use this, very useless, also don't delete this, might need later

    WATER(
            1,
            new FluidStack(LiquidSubstances.PURE_WATER_SOURCE.get(), 100),
            new FluidStack[]{
                    new FluidStack(GasSubstances.HYDROGEN_SOURCE.get(), 50)
            },
            new ItemStack[]{}
    );

    public final FluidStack neededFluidStack;
    public final int priority;
    public final FluidStack[] returnedFluidStack;
    public final ItemStack[] returnedItemStack;

    ElectrolysisRecipes(int priority, FluidStack NeededFluidStack, FluidStack[] returnedFluidStack, ItemStack[] returnedItemStack){
        this.neededFluidStack = NeededFluidStack;
        this.priority = priority;
        this.returnedFluidStack = returnedFluidStack;
        this.returnedItemStack = returnedItemStack;
    }
}
