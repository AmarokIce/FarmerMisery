package club.someoneice.cockroach.item;

import club.someoneice.cockroach.Datas;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class ItemRoachSaladPlatter extends Item {
    public ItemRoachSaladPlatter() {
        super(new Properties().food(new FoodProperties.Builder().nutrition(18).build()).craftRemainder(Items.BOWL).stacksTo(1));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        if (pLevel.isClientSide()) {
            return super.finishUsingItem(pStack, pLevel, pLivingEntity);
        }
        Datas.COCKROACH_BALL.forEach(it -> {
            pLivingEntity.addEffect(new MobEffectInstance(Objects.requireNonNull(Datas.getRandom(it)), 20 * 30));
        });

        Datas.removeEffectIf(pLivingEntity, 5, MobEffects.POISON);
        Datas.removeEffectIf(pLivingEntity, 5, MobEffects.WEAKNESS);

        if (pLivingEntity instanceof Player player) {
            player.addItem(this.getCraftingRemainingItem(pStack));
        }

        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
    }
}
