package com.tuxedosteve.JP3.blocks.gui;

import com.tuxedosteve.JP3.blocks.container.ContainerTinyChestBlock;
import com.tuxedosteve.JP3.blocks.tileentity.TileEntityTinyChestBlock;
import com.tuxedosteve.JP3.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiTinyChestBlock extends GuiContainer {
	
	
	private static final ResourceLocation GUI_CHEST= new ResourceLocation(Reference.MOD_ID+":textures/gui/bank_block.png");
	private final InventoryPlayer playerInventory;
	private final TileEntityTinyChestBlock te;
	
	public GuiTinyChestBlock(InventoryPlayer playerInventory, TileEntityTinyChestBlock chestInventory, EntityPlayer player) {
		super(new ContainerTinyChestBlock(playerInventory, chestInventory, player));
		this.playerInventory= playerInventory;
		this.te= chestInventory;
		
		this.xSize= 179;
		this.ySize= 256;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRenderer.drawString("Tiny Chest", 8, 110, 000000);
		this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedComponentText(), 6, this.ySize-92, 000000);
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1.0F,1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(GUI_CHEST);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
		
	}
}
