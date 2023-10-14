package Mangopill.fruitstackjeisupport.integration.jei.category;

import Mangopill.fruitstackjeisupport.integration.jei.FDRecipeTypes;
import com.fruitstack.fruitstack.common.crafting.TvfmpoitRecipe;
import com.fruitstack.fruitstack.common.registry.ModItems;
import com.fruitstack.fruitstack.common.utility.TextUtils;
import com.fruitstack.fruitstack.fruitstack;
import com.mojang.blaze3d.vertex.PoseStack;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
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
	public static final ResourceLocation UID = new ResourceLocation(fruitstack.MODID, "tripod_vessel_for_making_pills_of_immortality");
	protected final IDrawable heatIndicator;
	protected final IDrawableAnimated arrow;
	private final Component title;
	private final IDrawable background;
	private final IDrawable icon;

	public TvfmpoitRecipeCategory(IGuiHelper helper) {
		title = TextUtils.getTranslation("jei.tripod_vessel_for_making_pills_of_immortality");
		ResourceLocation backgroundImage = new ResourceLocation(fruitstack.MODID, "textures/gui/tripod_vessel_for_making_pills_of_immortality.png");
		background = helper.createDrawable(backgroundImage, 11, 16, 142, 57);
		icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(ModItems.TRIPOD_VESSEL_FOR_MAKING_PILLS_OF_IMMORTALITY.get()));
		heatIndicator = helper.createDrawable(backgroundImage, 176, 0, 17, 15);
		arrow = helper.drawableBuilder(backgroundImage, 176, 15, 24, 17)
				.buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
	}

	@Override
	public ResourceLocation getUid() {
		return this.getRecipeType().getUid();
	}

	@Override
	public Class<? extends TvfmpoitRecipe> getRecipeClass() {
		return this.getRecipeType().getRecipeClass();
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
	public void setIngredients(TvfmpoitRecipe cookingPotRecipe, IIngredients ingredients) {
		List<Ingredient> inputAndContainer = new ArrayList<>(cookingPotRecipe.getIngredients());
		inputAndContainer.add(Ingredient.of(cookingPotRecipe.getOutputContainer()));

		ingredients.setInputIngredients(inputAndContainer);
		ingredients.setOutput(VanillaTypes.ITEM, cookingPotRecipe.getResultItem());
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, TvfmpoitRecipe recipe, IIngredients ingredients) {
		final int MEAL_DISPLAY = 8;
		final int CONTAINER_INPUT = 9;
		final int OUTPUT = 10;
		IGuiItemStackGroup itemStacks = recipeLayout.getItemStacks();
		NonNullList<Ingredient> recipeIngredients = recipe.getIngredients();

		int borderSlotSize = 18;
		for (int row = 0; row < 2; ++row) {
			for (int column = 0; column < 4; ++column) {
				int inputIndex = row * 4 + column;
				if (inputIndex < recipeIngredients.size()) {
					itemStacks.init(inputIndex, true, column * borderSlotSize, row * borderSlotSize);
					itemStacks.set(inputIndex, Arrays.asList(recipeIngredients.get(inputIndex).getItems()));
				}
			}
		}

		itemStacks.init(MEAL_DISPLAY, false, 112, 9);
		itemStacks.set(MEAL_DISPLAY, recipe.getResultItem());

		if (!recipe.getOutputContainer().isEmpty()) {
			itemStacks.init(CONTAINER_INPUT, false, 80, 38);
			itemStacks.set(CONTAINER_INPUT, recipe.getOutputContainer());
		}

		itemStacks.init(OUTPUT, false, 112, 38);
		itemStacks.set(OUTPUT, recipe.getResultItem());
	}

	@Override
	public void draw(TvfmpoitRecipe recipe, IRecipeSlotsView recipeSlotsView, PoseStack matrixStack, double mouseX, double mouseY) {
		arrow.draw(matrixStack, 79, 9);
		heatIndicator.draw(matrixStack, 27, 39);
	}
}
