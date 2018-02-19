package me.creepinson.modelz.events;

import me.creepinson.modelz.gui.GuiModelz;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class GuiEventHandler {
	@SubscribeEvent
	public static void initGui(GuiScreenEvent.InitGuiEvent.Post event) {

		if (event.getGui() instanceof GuiMainMenu) {
	
			int anchorX = event.getGui().mc.displayWidth / 2;//center of screen y

			int anchorY = event.getGui().mc.displayHeight / 2;//center of screen y

			int x = event.getGui().width / 2;
			int y = event.getGui().height / 2;
			GuiButton modelz = new GuiButton(25, anchorX / 2 - 100, anchorY / 2 - 120, 200, 20, "Modelz");
				
			event.getButtonList().add(modelz);
			
		}

	}
	
	@SubscribeEvent
	public static void actionPerformed(GuiScreenEvent.ActionPerformedEvent.Post event) {

		if(event.getButton().id == 25){
			Minecraft.getMinecraft().displayGuiScreen(new GuiModelz());
		}

	}
	
}
