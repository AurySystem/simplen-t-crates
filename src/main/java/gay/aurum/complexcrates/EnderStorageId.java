package gay.aurum.complexcrates;

import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;
import net.minecraft.util.registry.Registry;

public class EnderStorageId {
    protected DyeColor[] key;
    protected Item item;

    EnderStorageId(Item item, DyeColor... key){
        this.key = key;
        this.item = item;
    }

    public EnderStorageId create(Item item, DyeColor... key){
        return new EnderStorageId(item, key);
    }

    private static String joinDyes(DyeColor... dyes){
        StringBuilder out = new StringBuilder();
        for (DyeColor dye : dyes) {
            out.append(dye);
            out.append(",");
        }
        return out.toString();
    }

    public static String directToString(Item item, DyeColor... key){
        return joinDyes(key) + "-" + Registry.ITEM.getId(item);
    }

    @Override
    public String toString() {
        return directToString( this.item, this.key);
    }
}
