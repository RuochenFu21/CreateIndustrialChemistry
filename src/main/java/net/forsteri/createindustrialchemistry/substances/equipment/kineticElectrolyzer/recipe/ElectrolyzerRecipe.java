package net.forsteri.createindustrialchemistry.substances.equipment.kineticElectrolyzer.recipe;

import com.simibubi.create.content.contraptions.processing.BasinRecipe;
import com.simibubi.create.content.contraptions.processing.ProcessingRecipeBuilder;
import com.simibubi.create.foundation.utility.recipe.IRecipeTypeInfo;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.tileEntities.RecipeTypes;
import net.minecraft.world.item.crafting.RecipeType;

public class ElectrolyzerRecipe extends BasinRecipe {

    public static RecipeType<ElectrolyzerRecipe> TYPE = new ElectrolyzerRecipeType();
    public ElectrolyzerRecipe(ProcessingRecipeBuilder.ProcessingRecipeParams params) {
        super(RecipeTypes.ELECTROLYSIS, params);
    }
}
