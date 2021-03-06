package covers1624.legacyfarms;

import covers1624.legacyfarms.gui.LFCreativeTab;
import covers1624.legacyfarms.handler.ConfigurationHandler;
import covers1624.legacyfarms.handler.CropHandler;
import covers1624.legacyfarms.handler.LFEventHandler;
import covers1624.legacyfarms.handler.LFGuiHandler;
import covers1624.legacyfarms.init.*;
import covers1624.legacyfarms.intermods.IntermodsHandler;
import covers1624.legacyfarms.proxy.CommonProxy;
import covers1624.legacyfarms.reference.Reference;
import covers1624.lib.util.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;

@Mod(name = Reference.MOD_NAME, modid = Reference.MOD_ID, version = "1.1", dependencies = "after:Forestry;after:AgriCraft")
public class LegacyFarms {

	public static final LFCreativeTab creativeTab = new LFCreativeTab();
	public static final LogHelper logger = new LogHelper(Reference.MOD_NAME);

	@SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
	public static CommonProxy proxy;

	@Instance(Reference.MOD_NAME)
	public static LegacyFarms instance;

	//public static final PacketPipeline pipeline = new PacketPipeline();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		instance = this;
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new LFGuiHandler());

		// Register to all the things
		LFEventHandler eventHandler = new LFEventHandler();
		MinecraftForge.EVENT_BUS.register(eventHandler);
		MinecraftForge.TERRAIN_GEN_BUS.register(eventHandler);
		FMLCommonHandler.instance().bus().register(eventHandler);

		ForestryProxy.init();
		ModBlocks.init();
		ModItems.init();
		IntermodsHandler.loadAllModules();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		//pipeline.initalise(Reference.MOD_ID);
		Recipes.init();
		IntermodsHandler.loadAllModuleRecipes();
		proxy.registerRenderers();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		//pipeline.postInitialise();
		Blueprints.init();
		Crops.init();
		CropHandler.init();
	}
}