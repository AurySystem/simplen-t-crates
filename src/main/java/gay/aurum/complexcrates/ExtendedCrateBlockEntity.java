package gay.aurum.complexcrates;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import xyz.sunrose.simplecrates.CrateBlockEntity;
import xyz.sunrose.simplecrates.SimpleCrates;

public class ExtendedCrateBlockEntity extends CrateBlockEntity {
    protected int MAX_ITEMS;
    public ExtendedCrateBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public boolean canPush(ItemStack stack) {
        return !stack.isEmpty()
                && stack.getCount() <= MAX_ITEMS - this.getSize()
                && (this.getItem() == Items.AIR || stack.getItem().equals(this.getItem()))
                && !stack.isIn(SimpleCrates.BANNED_ITEMS);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("MaxItems", MAX_ITEMS);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        MAX_ITEMS = nbt.getInt("MaxItems");
    }

    protected void setMax(int a){
        this.MAX_ITEMS = a;
    }
    protected int getMax(){
        return this.MAX_ITEMS;
    }
}
