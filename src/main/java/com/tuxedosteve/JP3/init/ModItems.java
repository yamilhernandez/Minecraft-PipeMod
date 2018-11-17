package com.tuxedosteve.JP3.init;

import java.util.ArrayList;
import java.util.List;

import com.tuxedosteve.JP3.items.ItemBase;
import com.tuxedosteve.JP3.items.tools.ToolAxe;
import com.tuxedosteve.JP3.items.tools.ToolHoe;
import com.tuxedosteve.JP3.items.tools.ToolPickaxe;
import com.tuxedosteve.JP3.items.tools.ToolSpade;
import com.tuxedosteve.JP3.items.tools.ToolSword;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	public static final ToolMaterial MATERIAL_MONEY = EnumHelper.addToolMaterial("material_money", 3, 250, 8.0F, 4.0F, 10);
	
	
	//Items
	public static final Item LOCK = new ItemBase("lock");
	public static final Item CASH = new ItemBase("cash");
	
	//Tools
	public static final ItemSword MONEY_SWORD = new ToolSword("money_sword", MATERIAL_MONEY);
	public static final ItemSpade MONEY_SHOVEL = new ToolSpade("money_shovel", MATERIAL_MONEY);
	public static final ItemPickaxe MONEY_PICKAXE = new ToolPickaxe("money_pickaxe", MATERIAL_MONEY);
	public static final ItemAxe MONEY_AXE = new ToolAxe("money_axe", MATERIAL_MONEY);
	public static final ItemHoe MONEY_HOE = new ToolHoe("money_hoe", MATERIAL_MONEY);
}