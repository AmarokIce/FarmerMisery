package club.someoneice.cockroach;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;


public final class Datas {
    public static final Random RANDOM = new Random();
    public static final List<MobEffect> COCKROACH_CLUSTER = Lists.newArrayList(
            MobEffects.DAMAGE_BOOST,
            MobEffects.CONFUSION,
            MobEffects.WEAKNESS,
            MobEffects.MOVEMENT_SPEED,
            MobEffects.MOVEMENT_SLOWDOWN
    );

    @SuppressWarnings("all")
    public static final Set<List<MobEffect>> COCKROACH_BALL = Sets.newHashSet(
            Lists.newArrayList(
                    MobEffects.DAMAGE_BOOST,
                    MobEffects.WEAKNESS
            ),
            Lists.newArrayList(
                    MobEffects.MOVEMENT_SPEED,
                    MobEffects.MOVEMENT_SLOWDOWN
            ),
            Lists.newArrayList(
                    MobEffects.REGENERATION,
                    MobEffects.POISON
            )
    );

    public static <T> T getRandom(List<T> list) {
        return list.isEmpty() ? null : list.get(RANDOM.nextInt(list.size()));
    }

    public static void removeEffectIf(LivingEntity pLivingEntity, int lv, MobEffect pEffect) {
        if (!pLivingEntity.hasEffect(pEffect)) {
            return;
        }

        var effect = Objects.requireNonNull(pLivingEntity.getEffect(pEffect));
        if (effect.getAmplifier() >= lv) {
            return;
        }

        pLivingEntity.removeEffect(pEffect);
    }
}
