package net.forsteri.createindustrialchemistry.substances.equipment.kineticElectrolyzer.recipe;

import com.simibubi.create.compat.jei.category.CreateRecipeCategory;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.forsteri.createindustrialchemistry.CreateIndustrialChemistry;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.tileEntities.Blocks;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nonnull;

@SuppressWarnings("removal")
@MethodsReturnNonnullByDefault
public class ElectrolysisCategory implements IRecipeCategory<ElectrolyzerRecipe> {

    public final static ResourceLocation UID = new ResourceLocation(CreateIndustrialChemistry.MOD_ID, "gem_cutting");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(CreateIndustrialChemistry.MOD_ID, "textures/gui/gem_cutting_station_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public ElectrolysisCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(Blocks.KINETIC_ELECTROLYZER_BLOCK.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends ElectrolyzerRecipe> getRecipeClass() {
        return ElectrolyzerRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Electrolysis");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull ElectrolyzerRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 57, 18).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 60).addItemStack(recipe.getResultItem());
//        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 60).addItemStack(recipe.getFluidResults().get(0));
    }
}
