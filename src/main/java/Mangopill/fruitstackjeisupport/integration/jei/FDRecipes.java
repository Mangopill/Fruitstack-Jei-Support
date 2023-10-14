package Mangopill.fruitstackjeisupport.integration.jei;

import com.fruitstack.fruitstack.common.crafting.JuicerRecipe;
import com.fruitstack.fruitstack.common.crafting.TvfmpoitRecipe;
import com.fruitstack.fruitstack.common.registry.ModRecipeTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

public class FDRecipes
{
	private final RecipeManager recipeManager;

	public FDRecipes() {
		Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = minecraft.level;

		if (level != null) {
			this.recipeManager = level.getRecipeManager();
		} else {
			throw new NullPointerException("minecraft world must not be null.");
		}
	}

	public List<TvfmpoitRecipe> getTvfmpoitRecipe() {
		return recipeManager.getAllRecipesFor(ModRecipeTypes.COOKING.get()).stream().toList();
	}

	public List<JuicerRecipe> getJuicerRecipe() {
		return recipeManager.getAllRecipesFor(ModRecipeTypes.JUICER.get()).stream().toList();
	}
}
