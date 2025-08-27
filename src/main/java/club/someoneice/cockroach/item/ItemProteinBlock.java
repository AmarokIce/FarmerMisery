package club.someoneice.cockroach.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ItemProteinBlock extends Item {
    public ItemProteinBlock() {
        super(new Properties().food(new FoodProperties.Builder().nutrition(8).build()));
    }
}
