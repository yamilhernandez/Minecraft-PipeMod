package com.tuxedosteve.JP3.init;

import java.util.ArrayList;
import java.util.List;

import com.tuxedosteve.JP3.items.ItemBase;
import com.tuxedosteve.JP3.items.armor.ArmorBase;
import com.tuxedosteve.JP3.items.tools.ToolAxe;
import com.tuxedosteve.JP3.items.tools.ToolHoe;
import com.tuxedosteve.JP3.items.tools.ToolPickaxe;
import com.tuxedosteve.JP3.items.tools.ToolSpade;
import com.tuxedosteve.JP3.items.tools.ToolSword;
import com.tuxedosteve.JP3.util.Reference;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials
	public static final ToolMaterial MATERIAL_MONEY = EnumHelper.addToolMaterial("material_money", 3, 100, 7.0F, 4.0F, 10);
	public static final ArmorMaterial ARMOR_MATERIAL_MONEY = EnumHelper.addArmorMaterial("armor_material_money", Reference.MOD_ID +":money",6, 
			new int[] {2,3,4,2}, 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);
	
	
	//Items
	public static final Item LOCK = new ItemBase("lock");
	public static final Item CASH = new ItemBase("cash");
	public static final Item BITCOIN = new ItemBase("bitcoin");
	public static final Item FINANCIAL_HANDBOOK = new ItemBase("financial_handbook");
	
	//Tools
	public static final ItemSword MONEY_SWORD = new ToolSword("money_sword", MATERIAL_MONEY);
	public static final ItemSpade MONEY_SHOVEL = new ToolSpade("money_shovel", MATERIAL_MONEY);
	public static final ItemPickaxe MONEY_PICKAXE = new ToolPickaxe("money_pickaxe", MATERIAL_MONEY);
	public static final ItemAxe MONEY_AXE = new ToolAxe("money_axe", MATERIAL_MONEY);
	public static final ItemHoe MONEY_HOE = new ToolHoe("money_hoe", MATERIAL_MONEY);
	
	//Armor
	public static final Item MONEY_HELMET = new ArmorBase("money_helmet",ARMOR_MATERIAL_MONEY,1,EntityEquipmentSlot.HEAD);
	public static final Item MONEY_CHESTPLATE = new ArmorBase("money_chestplate",ARMOR_MATERIAL_MONEY,1,EntityEquipmentSlot.CHEST);
	public static final Item MONEY_LEGGINGS = new ArmorBase("money_leggings",ARMOR_MATERIAL_MONEY,2,EntityEquipmentSlot.LEGS);
	public static final Item MONEY_BOOTS = new ArmorBase("money_boots",ARMOR_MATERIAL_MONEY,1,EntityEquipmentSlot.FEET);
	
}