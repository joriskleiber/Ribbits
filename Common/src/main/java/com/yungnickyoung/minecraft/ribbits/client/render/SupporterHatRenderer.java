package com.yungnickyoung.minecraft.ribbits.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.yungnickyoung.minecraft.ribbits.RibbitsCommon;
import com.yungnickyoung.minecraft.ribbits.client.model.SupporterHatModel;
import com.yungnickyoung.minecraft.ribbits.client.supporters.SupportersListClient;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class SupporterHatRenderer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private static final ResourceLocation TEXTURE = RibbitsCommon.id("textures/entity/player/supporter_hat.png");

    private final SupporterHatModel hatModel;

    public SupporterHatRenderer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderLayerParent, Context context) {
        super(renderLayerParent);
        this.hatModel = new SupporterHatModel(context.bakeLayer(SupporterHatModel.LAYER_LOCATION));
    }

    @Override
    public void render(PoseStack stack, MultiBufferSource bufferSource, int packedLight, AbstractClientPlayer player, float f, float g, float tickDelta, float j, float k, float l) {
        if (SupportersListClient.isPlayerSupporterHatEnabled(player.getUUID())) {
            VertexConsumer consumer = bufferSource.getBuffer(RenderType.armorCutoutNoCull(TEXTURE));
            hatModel.head.y = this.getParentModel().head.y - 0.6f;
            this.getParentModel().getHead().translateAndRotate(stack);
            hatModel.renderToBuffer(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        }
    }
}