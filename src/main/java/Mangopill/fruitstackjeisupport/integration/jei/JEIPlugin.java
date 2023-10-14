package Mangopill.fruitstackjeisupport.integration.jei;

import Mangopill.fruitstackjeisupport.integration.jei.category.JuicerRecipeCategory;
import Mangopill.fruitstackjeisupport.integration.jei.category.TvfmpoitRecipeCategory;
import com.fruitstack.fruitstack.client.gui.JuicerScreen;
import com.fruitstack.fruitstack.client.gui.TvfmpoiPotScreen;
import com.fruitstack.fruitstack.common.block.entity.container.JuicerBlockMenu;
import com.fruitstack.fruitstack.common.block.entity.container.TvfmpoitBlockMenu;
import com.fruitstack.fruitstack.common.registry.ModItems;
import com.fruitstack.fruitstack.common.registry.ModRecipeTypes;
import com.fruitstack.fruitstack.fruitstack;
import com.google.common.collect.ImmutableList;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.*;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.stream.Collectors;

@JeiPlugin
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
@SuppressWarnings("unused")
public class JEIPlugin implements IModPlugin
{
	private static final ResourceLocation ID = new ResourceLocation(fruitstack.MODID, "jei_plugin");
	private static final Minecraft MC = Minecraft.getInstance();

	private static List<Recipe<?>> findRecipesByType(RecipeType<?> type) {
		return MC.level
				.getRecipeManager()
				.getRecipes()
				.stream()
				.filter(r -> r.getType() == type)
				.collect(Collectors.toList());
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		registry.addRecipeCategories(new TvfmpoitRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
		registry.addRecipeCategories(new JuicerRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addRecipes(findRecipesByType(ModRecipeTypes.COOKING.get()), TvfmpoitRecipeCategory.UID);
		registration.addRecipes(findRecipesByType(ModRecipeTypes.JUICER.get()), JuicerRecipeCategory.UID);
	}

	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(ModItems.TRIPOD_VESSEL_FOR_MAKING_PILLS_OF_IMMORTALITY.get()), FDRecipeTypes.TRIPOD_VESSEL_FOR_MAKING_PILLS_OF_IMMORTALITY);
		registration.addRecipeCatalyst(new ItemStack(ModItems.JUICER.get()), FDRecipeTypes.JUICER);
	}

	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration) {
		registration.addRecipeClickArea(TvfmpoiPotScreen.class, 89, 25, 24, 17, FDRecipeTypes.TRIPOD_VESSEL_FOR_MAKING_PILLS_OF_IMMORTALITY);
		registration.addRecipeClickArea(JuicerScreen.class, 89, 25, 24, 17, FDRecipeTypes.JUICER);
	}

	@Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
		registration.addRecipeTransferHandler(TvfmpoitBlockMenu.class, FDRecipeTypes.TRIPOD_VESSEL_FOR_MAKING_PILLS_OF_IMMORTALITY, 0, 6, 9, 36);
		registration.addRecipeTransferHandler(JuicerBlockMenu.class, FDRecipeTypes.JUICER, 0, 6, 9, 36);
	}

	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}
}
