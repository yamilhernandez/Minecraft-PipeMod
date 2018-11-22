package com.tuxedosteve.JP3.blocks.animation;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelChest - Either Mojang or a mod author
 * Created using Tabula 7.0.0
 */
@SideOnly(Side.CLIENT)
public class ModelBank extends ModelBase {
    public ModelRenderer lock;
    public ModelRenderer top;
    public ModelRenderer bottom;

    public ModelBank() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.lock = new ModelRenderer(this, 0, 0);
        this.lock.setRotationPoint(8.0F, 7.0F, 15.0F);
        this.lock.addBox(-1.0F, -2.0F, -15.0F, 2, 4, 1, -0.2F);
        this.top = new ModelRenderer(this, 0, 0);
        this.top.setRotationPoint(1.0F, 7.0F, 15.0F);
        this.top.addBox(0.0F, -4.1F, -14.0F, 14, 5, 14, -0.9F);
        this.bottom = new ModelRenderer(this, 0, 19);
        this.bottom.setRotationPoint(1.0F, 6.0F, 1.0F);
        this.bottom.addBox(0.0F, 0.0F, 0.0F, 14, 10, 14, -0.9F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.lock.render(f5);
        this.top.render(f5);
        this.bottom.render(f5);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void renderAll() {
    	this.lock.rotateAngleX = this.top.rotateAngleX;
    	this.top.render(0.0625f);
    	this.lock.render(0.0625f);
    	this.bottom.render(0.0625f);
    }
}
