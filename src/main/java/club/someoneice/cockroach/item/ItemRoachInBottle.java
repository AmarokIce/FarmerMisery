package club.someoneice.cockroach.item;

import com.google.common.base.Suppliers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ItemRoachInBottle extends Item {
    public ItemRoachInBottle() {
        super(new Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(1)
                        .effect(Suppliers.ofInstance(new MobEffectInstance(MobEffects.POISON, 30 * 20, 0)), 1.0f)
                        .build())
                .craftRemainder(Items.GLASS_BOTTLE));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLevel.isClientSide()) {
            return super.finishUsingItem(pStack, pLevel, pLivingEntity);
        }
        if (pLivingEntity instanceof Player player) {
            player.addItem(this.getCraftingRemainingItem(pStack));
        }
        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
