package club.someoneice.cockroach.item;

import club.someoneice.cockroach.Datas;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemJuicyRoast extends Item {
    public ItemJuicyRoast() {
        super(new Properties().food(new FoodProperties.Builder().nutrition(3).build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLevel.isClientSide()) {
            return super.finishUsingItem(pStack, pLevel, pLivingEntity);
        }
        Datas.removeEffectIf(pLivingEntity, 1, MobEffects.POISON);

        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
