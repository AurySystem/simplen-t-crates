package gay.aurum.complexcrates;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExtendedCrateBlock extends xyz.sunrose.simplecrates.CrateBlock {
    int maxitems;
    public ExtendedCrateBlock(Settings settings, int maxitems) {
        super(settings);
        this.maxitems = maxitems;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        ExtendedCrateBlockEntity crate = (ExtendedCrateBlockEntity) world.getBlockEntity(pos);
        if (crate == null) {
            return 0;
        } else {
            int size = crate.getSize();
            float amount = (float)size / crate.getMax();
            return MathHelper.floor(amount * 14.0F) + (size > 0 ? 1 : 0);
        }
    }

    @Override
    @Nullable
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        ExtendedCrateBlockEntity crate = new ExtendedCrateBlockEntity(pos, state);
        crate.setMax(maxitems);
        return crate;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        ExtendedCrateBlockEntity crate = (ExtendedCrateBlockEntity)world.getBlockEntity(pos);
        if (!world.isClient && crate != null) {
            ItemStack stack = new ItemStack(state.getBlock().asItem());
            if (!crate.items.isEmpty() || !player.isCreative()) {
                NbtCompound blockNBT = new NbtCompound();
                crate.writeNbt(blockNBT);
                stack.setSubNbt("BlockEntityTag", blockNBT);
                ItemEntity entity = new ItemEntity(world, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, stack);
                entity.setToDefaultPickupDelay();
                world.spawnEntity(entity);
            }
        }
        this.spawnBreakParticles(world, player, pos, state);
        if (state.isIn(BlockTags.GUARDED_BY_PIGLINS)) {
            PiglinBrain.onGuardedBlockInteracted(player, false);
        }
        world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, net.minecraft.loot.context.LootContext.Builder builder) {
        List<ItemStack> items = super.getDroppedStacks(state, builder);
        for(ItemStack stack : items) {
            if (stack.getItem() == state.getBlock().asItem()) {
                ExtendedCrateBlockEntity crate = (ExtendedCrateBlockEntity)builder.get(LootContextParameters.BLOCK_ENTITY);
                if (!crate.items.isEmpty() || !(builder.get(LootContextParameters.LAST_DAMAGE_PLAYER).isCreative())) {
                    NbtCompound blockNBT = new NbtCompound();
                    crate.writeNbt(blockNBT);
                    stack.setSubNbt("BlockEntityTag", blockNBT);
                }
            }
        }
        return items;
    }
}
