package hearts;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class HeartsRecipeGenerator extends FabricRecipeProvider {
    public HeartsRecipeGenerator(FabricDataOutput generator, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(generator, registriesFuture);
    }

    @Override
    public String getName() {
        return "Hearts Recipes";
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                // Heart
                createShaped(RecipeCategory.MISC, HeartItems.HEART_ITEM)
                        .pattern(" # ")
                        .pattern("EGH")
                        .pattern(" C ")
                        .input('#', Items.TOTEM_OF_UNDYING)
                        .input('E', Items.ENCHANTED_GOLDEN_APPLE)
                        .input('G', Items.GOLD_BLOCK)
                        .input('H', Items.HEART_OF_THE_SEA)
                        .input('C', Items.END_CRYSTAL)
                        .criterion("unlock_right_away", TickCriterion.Conditions.createTick())
                        .showNotification(false)
                        .offerTo(exporter);

                // Heart of the sea
                createShaped(RecipeCategory.MISC, Items.HEART_OF_THE_SEA)
                        .pattern("DSD")
                        .pattern("GEG")
                        .pattern("DWD")
                        .input('D', Items.DIAMOND)
                        .input('S', Items.SEA_LANTERN)
                        .input('G', Items.GOLD_INGOT)
                        .input('W', Items.WATER_BUCKET)
                        .input('E', Items.ENDER_PEARL)
                        .criterion("unlock_right_away", TickCriterion.Conditions.createTick())
                        .showNotification(false)
                        .offerTo(exporter);

                // Enchanted Golden Apple
                SmithingTransformRecipeJsonBuilder.create(Ingredient.ofItem(Items.ENCHANTED_BOOK), Ingredient.ofItem(Items.GOLDEN_APPLE), Ingredient.ofItem(Items.GOLD_BLOCK), RecipeCategory.MISC, Items.ENCHANTED_GOLDEN_APPLE)
                        .criterion("unlock_right_away", TickCriterion.Conditions.createTick())
                        .offerTo(exporter, "enchanted_golden_apple_smithing");

                // Totem of Undying
                createShaped(RecipeCategory.COMBAT, Items.TOTEM_OF_UNDYING)
                        .pattern("GgG")
                        .pattern("ene")
                        .pattern("GbG")
                        .input('G', Items.GOLD_BLOCK)
                        .input('g', Items.GOLD_INGOT)
                        .input('e', Items.EMERALD)
                        .input('n', Items.GOLD_NUGGET)
                        .input('b', Items.BLAZE_ROD)
                        .criterion("unlock_right_away", TickCriterion.Conditions.createTick())
                        .showNotification(false)
                        .offerTo(exporter);
            }
        };
    }


}
