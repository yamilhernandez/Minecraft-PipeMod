package main.java.com.tuxedosteve.JP3.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class SafeBlock extends BlockBase {

	public SafeBlock(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.METAL);
		setHardness(100.0F);
		setResistance(6000.0F);
		setHarvestLevel("pickaxe",3);
		//setLightLevel();
		//setLightOpacity();
		//SetBlockUnbreakable();
		
		
	}

}
