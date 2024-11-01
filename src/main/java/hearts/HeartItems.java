package hearts;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class HeartItems {
    private HeartItems() {

    }

    public static final Item HEART_ITEM = register("heart",HeartItem::new, new HeartItem.Settings());

    public static Item register(String path, Function<Item.Settings, Item> factory, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("hearts", path));
        return Items.register(registryKey, factory, settings);
    }

    public static void initialize() {

    }
}
