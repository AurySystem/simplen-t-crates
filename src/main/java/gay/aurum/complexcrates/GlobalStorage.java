package gay.aurum.complexcrates;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.PersistentState;

import java.util.HashMap;
import java.util.Map;

public class GlobalStorage extends PersistentState {

    Map<String, DefaultedList<ItemStack>> storages = new HashMap<>();

    public static PersistentState readNbt(NbtCompound tag) {
        PersistentState storage = new GlobalStorage();

        return storage;
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        return null;
    }

}
