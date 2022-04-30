package gay.aurum.complexcrates;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class ComplexCrates implements ModInitializer {
	public static String MODID = "complex-crates";
	public static Identifier id(String id) {return new Identifier(MODID, id);}
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	private static final ArrayList<Block> cratelist = new ArrayList<>();
	public static Block[] CRATES(ArrayList<Block> blocks) {
		Block[] aa = new Block[cratelist.size()];
		for (int i = 0; i < cratelist.size(); i++) {
			aa[i] = cratelist.get(i);
		}
		return aa;
	}

	public static final Block CHEST_CRATE = createCrate(
			"chest_crate", new ExtendedCrateBlock(FabricBlockSettings.copyOf(Blocks.BARREL), 64*9));

	public static final Block COPPER_CRATE = createCrate(
			"copper_crate", new ExtendedCrateBlock(FabricBlockSettings.copyOf(Blocks.BARREL), 64*9*8));

	public static final Block IRON_CRATE = createCrate(
			"iron_crate", new ExtendedCrateBlock(FabricBlockSettings.copyOf(Blocks.SMITHING_TABLE), 64*27*4));

	public static final Block GOLD_CRATE = createCrate(
			"gold_crate", new ExtendedCrateBlock(FabricBlockSettings.copyOf(Blocks.GOLD_BLOCK), 64*27*5));

	public static final Block DIAMOND_CRATE = createCrate(
			"diamond_crate", new ExtendedCrateBlock(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK), 64*27*6));

	public static final Item CHEST_CRATE_ITEM = Registry.register(
			Registry.ITEM, id("chest_crate"), new BlockItem(CHEST_CRATE, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

	public static final Item COPPER_CRATE_ITEM = Registry.register(
			Registry.ITEM, id("copper_crate"), new BlockItem(COPPER_CRATE, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

	public static final Item IRON_CRATE_ITEM = Registry.register(
			Registry.ITEM, id("iron_crate"), new BlockItem(IRON_CRATE, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

	public static final Item GOLD_CRATE_ITEM = Registry.register(
			Registry.ITEM, id("gold_crate"), new BlockItem(GOLD_CRATE, new FabricItemSettings().group(ItemGroup.DECORATIONS)));

	public static final Item DIAMOND_CRATE_ITEM = Registry.register(
			Registry.ITEM, id("diamond_crate"), new BlockItem(DIAMOND_CRATE, new FabricItemSettings().group(ItemGroup.DECORATIONS)));


	public static final BlockEntityType<ExtendedCrateBlockEntity> BASE_CRATE_BLOCK_ENTITY = Registry.register(
			Registry.BLOCK_ENTITY_TYPE, id("base_crates"),
			FabricBlockEntityTypeBuilder.create(ExtendedCrateBlockEntity::new, CRATES(cratelist)).build()
	);

	public static Block createCrate(String id, Block unregistered){
		Block crate = Registry.register(Registry.BLOCK, id(id), unregistered);
		cratelist.add(crate);
		return crate;
	}

	@Override
	public void onInitialize() {

		LOGGER.info("*Screaming*");
	}
}
