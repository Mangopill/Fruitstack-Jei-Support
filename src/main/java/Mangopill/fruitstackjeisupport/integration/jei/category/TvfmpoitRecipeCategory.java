package Mangopill.fruitstackjeisupport.integration.jei.category;

import Mangopill.fruitstackjeisupport.integration.jei.FDRecipeTypes;
import com.fruitstack.fruitstack.common.crafting.TvfmpoitRecipe;
import com.fruitstack.fruitstack.common.registry.ModItems;
import com.fruitstack.fruitstack.common.utility.TextUtils;
import com.fruitstack.fruitstack.fruitstack;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class TvfmpoitRecipeCategory implements IRecipeCategory<TvfmpoitRecipe>
{
	protected final IDrawable heatIndicator;
	protected final IDrawableAnimated arrow;
	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	public TvfmpoitRecipeCategory(IGuiHelper helper) {
		title = TextUtils.getTranslation("jei.tripod_vessel_for_making_pills_of_immortality");
		ResourceLocation backgroundImage = new ResourceLocation(fruitstack.MODID, "textures/gui/tripod_vessel_for_making_pills_of_immortality.png");
		background = helper.createDrawable(backgroundImage, 11, 16, 142, 57);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModItems.TRIPOD_VESSEL_FOR_MAKING_PILLS_OF_IMMORTALITY.get()));
		heatIndicator = helper.createDrawable(backgroundImage, 176, 0, 17, 15);
		arrow = helper.drawableBuilder(backgroundImage, 176, 15, 24, 17)
				.buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public RecipeType<TvfmpoitRecipe> getRecipeType() {
		return FDRecipeTypes.TRIPOD_VESSEL_FOR_MAKING_PILLS_OF_IMMORTALITY;
	}

	@Override
	public Component getTitle() {
		return this.title;
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
	public void setRecipe(IRecipeLayoutBuilder builder, TvfmpoitRecipe recipe, IFocusGroup focusGroup) {
		NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();
		ItemStack resultStack = recipe.getResultItem();
		ItemStack containerStack = recipe.getOutputContainer();

		int borderSlotSize = 18;
		for (int row = 0; row < 2; ++row) {
			for (int column = 0; column < 4; ++column) {
				int inputIndex = row * 4 + column;
				if (inputIndex < recipeIngredients.size()) {
					builder.addSlot(RecipeIngredientRole.INPUT, (column * borderSlotSize) + 1, (row * borderSlotSize) + 1)
							.addItemStacks(Arrays.asList(recipeIngredients.get(inputIndex).getItems()));
				}
			}
		}

		builder.addSlot(RecipeIngredientRole.OUTPUT, 113, 10).addItemStack(resultStack);

		if (!containerStack.isEmpty()) {
			builder.addSlot(RecipeIngredientRole.CATALYST, 81, 39).addItemStack(containerStack);
		}

		builder.addSlot(RecipeIngredientRole.OUTPUT, 113, 39).addItemStack(resultStack);
	}

	@Override
	public void draw(TvfmpoitRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {
		arrow.draw(matrixStack, 79, 9);
		heatIndicator.draw(matrixStack, 27, 39);
	}
}
