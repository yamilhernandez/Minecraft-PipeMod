package com.tuxedosteve.JP3.util.handlers;

import com.tuxedosteve.JP3.Main;
import com.tuxedosteve.JP3.blocks.tileentity.TileEntityTinyChestBlock;
import com.tuxedosteve.JP3.init.ModBlocks;
import com.tuxedosteve.JP3.init.ModItems;
import com.tuxedosteve.JP3.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@EventBusSubscriber
public class RegistryHandler {

	@SubscribeEvent
	public static void OnItemRegister(RegistryEvent.Register<Item> event) {

		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public static void OnBlockRegister(RegistryEvent.Register<Block> event) {

		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
		TileEntityHandler.registerTileEntities();
		
	}

	@SubscribeEvent
	public static void OnModelRegister(ModelRegistryEvent event) {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(ModBlocks.TINYCHEST_BLOCK), 0, "tiny_chest_block");
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(ModBlocks.PIPE_BLOCK), 0, "pipe_block");

		for (Item item : ModItems.ITEMS) {

			if (item instanceof IHasModel) {

				((IHasModel) item).registerModels();

			}
		}

		for (Block block : ModBlocks.BLOCKS) {

			if (block instanceof IHasModel) {

				((IHasModel) block).registerModels();
			}
		}

	}
	
	public static void initRegistries() {
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.Instance, new GuiHandler());
	}
}
