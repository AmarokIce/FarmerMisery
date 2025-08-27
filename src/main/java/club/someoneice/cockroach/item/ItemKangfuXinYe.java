package club.someoneice.cockroach.item;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ItemKangfuXinYe extends Item {
    public ItemKangfuXinYe() {
        super(new Properties().food(new FoodProperties.Builder().meat().build()).craftRemainder(Items.GLASS_BOTTLE).stacksTo(1));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLevel.isClientSide()) {
            return super.finishUsingItem(pStack, pLevel, pLivingEntity);
        }
        pLivingEntity.clearFire();
        pLivingEntity.getActiveEffectsMap().keySet().stream().filter(it -> it.getCategory() == MobEffectCategory.HARMFUL).forEach(pLivingEntity::removeEffect);
        pLivingEntity.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 20 * 10));
        pLivingEntity.heal(2.0f);

        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
