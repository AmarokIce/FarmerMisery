package club.someoneice.cockroach.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ItemRoachSkewer extends Item {
    public ItemRoachSkewer() {
        super(new Properties().food(new FoodProperties.Builder().nutrition(11).build()));
    }
}
