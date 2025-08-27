package club.someoneice.cockroach;

import club.someoneice.cockroach.entity.BottleEntityRoach;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ModMain.MODID);

    public static final RegistryObject<EntityType<BottleEntityRoach>> ROACH = ENTITIES.register("roach_bottle", () -> EntityType.Builder.<BottleEntityRoach>of(BottleEntityRoach::new, MobCategory.MISC)
            .sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20)
            .build("roach_bottle"));
}
