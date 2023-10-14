package Mangopill.fruitstackjeisupport.integration.jei;

import com.fruitstack.fruitstack.common.crafting.JuicerRecipe;
import com.fruitstack.fruitstack.common.crafting.TvfmpoitRecipe;
import com.fruitstack.fruitstack.fruitstack;
import mezz.jei.api.recipe.RecipeType;

public final class FDRecipeTypes
{
	public static final RecipeType<TvfmpoitRecipe> TRIPOD_VESSEL_FOR_MAKING_PILLS_OF_IMMORTALITY = RecipeType.create(fruitstack.MODID, "tripod_vessel_for_making_pills_of_immortality", TvfmpoitRecipe.class);
	public static final RecipeType<JuicerRecipe> JUICER = RecipeType.create(fruitstack.MODID, "juicer", JuicerRecipe.class);
}
