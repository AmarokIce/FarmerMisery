package club.someoneice.cockroach;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = ModMain.MODID)
public class ForgeEvent {
    @SubscribeEvent
    public void addBrewingRecipe(FMLCommonSetupEvent event) {
        BrewingRecipeRegistry.addRecipe(Ingredient.of(Items.POTION), Ingredient.of(ItemInit.ROACH_IN_BOTTLE.get()), ItemInit.KANGFU_XIN_YE.get().getDefaultInstance());
        BrewingRecipeRegistry.addRecipe(Ingredient.of(ItemInit.ROACH_IN_BOTTLE.get()), Ingredient.of(Items.GUNPOWDER), ItemInit.THROWABLE_ROACH_BOTTLE.get().getDefaultInstance());
    }
}
