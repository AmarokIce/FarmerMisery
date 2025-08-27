package club.someoneice.cockroach.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;

public class ItemRoachWhiskerCandy extends Item {
    public ItemRoachWhiskerCandy() {
        super(new Properties().food(new FoodProperties.Builder().fast().nutrition(2).build()));
    }
}
