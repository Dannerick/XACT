package xk.xact.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import xk.xact.api.InteractiveCraftingGui;
import xk.xact.client.GuiUtils;
import xk.xact.gui.ContainerVanillaWorkbench;
import xk.xact.util.Textures;

public class GuiVanillaWorkbench extends GuiContainer implements InteractiveCraftingGui {

	private static final ResourceLocation guiTexture = new ResourceLocation(
			Textures.GUI_WORKBENCH);

	public GuiVanillaWorkbench(ContainerVanillaWorkbench container) {
		super(container);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.fontRendererObj.drawString(
				StatCollector.translateToLocal("container.crafting"), 28, 6,
				4210752);
		this.fontRendererObj.drawString(
				StatCollector.translateToLocal("container.inventory"), 8,
				this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GuiUtils.bindTexture(guiTexture);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize,
				this.ySize);
	}

	@Override
	public void sendGridIngredients(ItemStack[] ingredients, int buttonID) {
		GuiUtils.sendItemsToServer(ingredients, buttonID);
	}

	@Override
	public void handleKeyBinding(Container container) {
	}
}
