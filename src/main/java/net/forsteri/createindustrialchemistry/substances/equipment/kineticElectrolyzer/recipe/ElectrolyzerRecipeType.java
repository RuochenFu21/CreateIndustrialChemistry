package net.forsteri.createindustrialchemistry.substances.equipment.kineticElectrolyzer.recipe;

import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.minecraft.world.item.crafting.RecipeType;

public class ElectrolyzerRecipeType implements RecipeType<ElectrolyzerRecipe> {
    @Override
    public String toString() {
        return CreateIndustrialChemistry.MOD_ID+":electrolysis";
    }
}
