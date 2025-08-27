package club.someoneice.cockroach.item;

import club.someoneice.cockroach.Datas;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class ItemGokiburiYaki extends Item {
    public ItemGokiburiYaki() {
        super(new Properties()
                .food(new FoodProperties.Builder().nutrition(6).meat().build()));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        pLivingEntity.addEffect(new MobEffectInstance(Objects.requireNonNull(Datas.getRandom(Datas.COCKROACH_CLUSTER)), 20 * 30));
        Datas.removeEffectIf(pLivingEntity, 1, MobEffects.POISON);

        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
