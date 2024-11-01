package hearts;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsage;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class HeartItem extends Item {
    private final static Double HEALTH_INCREMENT = 2.0d;
    private final static Double HEALTH_MAX_MODIFIER = 20.0d;

    public HeartItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        EntityAttributeInstance maxHealth = user.getAttributeInstance(EntityAttributes.MAX_HEALTH);
        assert maxHealth != null;
        Identifier heartEffect = Identifier.of("hearts","heart");

        if (maxHealth.hasModifier(heartEffect)) {
            EntityAttributeModifier maxHealthModifier = maxHealth.getModifier(heartEffect);
            assert maxHealthModifier != null;
            if (maxHealthModifier.value() < HEALTH_MAX_MODIFIER) {
                maxHealth.overwritePersistentModifier(new EntityAttributeModifier(heartEffect, maxHealthModifier.value() + HEALTH_INCREMENT, EntityAttributeModifier.Operation.ADD_VALUE));
            } else {
                maxHealth.overwritePersistentModifier(new EntityAttributeModifier(heartEffect, HEALTH_MAX_MODIFIER, EntityAttributeModifier.Operation.ADD_VALUE));
            }
        } else {
            maxHealth.addPersistentModifier(new EntityAttributeModifier(heartEffect, HEALTH_INCREMENT, EntityAttributeModifier.Operation.ADD_VALUE));
        }

        user.getStackInHand(hand).decrement(1);
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
