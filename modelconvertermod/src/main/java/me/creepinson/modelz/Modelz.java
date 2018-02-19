package me.creepinson.modelz;

import java.io.File;

import org.apache.logging.log4j.Logger;

import me.creepinson.modelz.events.GuiEventHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Modelz.MODID, name = Modelz.NAME, version = Modelz.VERSION)
public class Modelz
{
    public static final String MODID = "modelz";
    public static final String NAME = "Modelz";
    public static final String VERSION = "1.0.0";

    private static Logger logger;
    private static File modelzFolder;
    private static File dataFolder;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        
        if(modelzFolder == null){
        	modelzFolder = new File(Minecraft.getMinecraft().mcDataDir, "modelz");
        }
        if(!getModelzFolder().exists()){
        	modelzFolder.mkdir();
        }
        
        if(dataFolder == null){
        	dataFolder = new File(getModelzFolder(), "data");
        }
        if(!getDataFolder().exists()){
        	dataFolder.mkdir();
        }
     
      
        
    }

    public static Logger getLogger() {
		return logger;
	}

	public static File getModelzFolder() {
		return modelzFolder;
	}

	public static File getDataFolder() {
		return dataFolder;
	}

	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		  
		MinecraftForge.EVENT_BUS.register(GuiEventHandler.class);
		  
		  
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        
    }
    
}
