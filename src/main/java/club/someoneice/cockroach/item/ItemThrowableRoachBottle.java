package club.someoneice.cockroach.item;

import club.someoneice.cockroach.entity.BottleEntityRoach;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class ItemThrowableRoachBottle extends Item {
    public ItemThrowableRoachBottle() {
        super(new Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SPLASH_POTION_THROW, SoundSource.NEUTRAL, 0.5F, (float) (0.4F / (Math.random() * 0.4F + 0.8F)));

        if (world.isClientSide) {
            return InteractionResultHolder.sidedSuccess(itemstack, world.isClientSide());
        }

        BottleEntityRoach entity = new BottleEntityRoach(player, world);
        entity.setItem(itemstack);
        entity.shootFromRotation(player, player.xRotO, player.yRotO, -20.0F, 0.5F, 1.0F);
        world.addFreshEntity(entity);
        var enchantments = EnchantmentHelper.getEnchantments(itemstack).keySet();
        var flagInf = enchantments.contains(Enchantments.INFINITY_ARROWS);
        if (!player.getAbilities().instabuild && !flagInf) {
            itemstack.shrink(1);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, world.isClientSide());
    }
}
