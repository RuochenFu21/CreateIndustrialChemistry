package net.forsteri.createindustrialchemistry.substances.equipment.kineticElectrolyzer.recipe;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.compat.jei.category.animations.AnimatedKinetics;
import net.forsteri.createindustrialchemistry.entry.substancesRegister.tileEntities.Blocks;

public class NotAnimatedElectrolyzer extends AnimatedKinetics {
    @Override
    public void draw(PoseStack matrixStack, int xOffset, int yOffset) {
        matrixStack.pushPose();
        matrixStack.translate(xOffset, yOffset, 200);
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(-15.5f));
        matrixStack.mulPose(Vector3f.YP.rotationDegrees(22.5f));
        int scale = 23;

        blockElement(Blocks.KINETIC_ELECTROLYZER_BLOCK.getDefaultState())
                .atLocal(0, 0, 0)
                .scale(scale)
                .render(matrixStack);

        blockElement(AllBlocks.BASIN.getDefaultState())
                .atLocal(0, 1.65, 0)
                .scale(scale)
                .render(matrixStack);

        matrixStack.popPose();
    }
}
