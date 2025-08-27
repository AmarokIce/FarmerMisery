package club.someoneice.cockroach.entity;

import club.someoneice.cockroach.EntityInit;
import club.someoneice.cockroach.ItemInit;
import com.github.alexthe666.alexsmobs.entity.AMEntityRegistry;
import com.github.alexthe666.alexsmobs.entity.EntityCockroach;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class BottleEntityRoach extends ThrowableItemProjectile implements ItemSupplier {
    public BottleEntityRoach(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public BottleEntityRoach(double pX, double pY, double pZ, Level pLevel) {
        super(EntityInit.ROACH.get(), pX, pY, pZ, pLevel);
    }

    public BottleEntityRoach(LivingEntity pShooter, Level pLevel) {
        super(EntityInit.ROACH.get(), pShooter, pLevel);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return ItemInit.THROWABLE_ROACH_BOTTLE.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        spawnRoach(pResult.getBlockPos());
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        spawnRoach(pResult.getEntity().getOnPos());
    }

    @Override
    protected float getGravity() {
        return 0.05f;
    }

    private void spawnRoach(BlockPos pPos) {
        if (this.level().isClientSide()) {
            return;
        }

        var roach = new EntityCockroach(AMEntityRegistry.COCKROACH.get(), this.level());
        roach.setPos(pPos.getX(), pPos.getY() + 1, pPos.getZ());
        this.level().addFreshEntity(roach);
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
