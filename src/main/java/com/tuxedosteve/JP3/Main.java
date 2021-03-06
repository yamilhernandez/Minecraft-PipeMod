package com.tuxedosteve.JP3;

import com.tuxedosteve.JP3.World.ModWorldGen;
import com.tuxedosteve.JP3.init.ModRecipes;
import com.tuxedosteve.JP3.proxy.CommonProxy;
import com.tuxedosteve.JP3.tabs.JP3Tab;
import com.tuxedosteve.JP3.util.Reference;
import com.tuxedosteve.JP3.util.handlers.RegistryHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	
	public static final CreativeTabs javaprojecttab = new JP3Tab("javaprojecttab");

	@Instance
	public static Main Instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);

	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		ModRecipes.init();
		RegistryHandler.initRegistries();

	}

	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) {

	}

}
