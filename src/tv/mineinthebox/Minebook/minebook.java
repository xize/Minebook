package tv.mineinthebox.Minebook;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import tv.mineinthebox.Minebook.GateUtil.toggleGate;
import tv.mineinthebox.Minebook.events.handler;

public class minebook extends JavaPlugin {

	private static minebook plugin;
	private static Logger logger = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		plugin = this;
		log("has been enabled!", logType.info);
		configuration.createConfig();
		handler.runListeners();
		toggleGate.loadGates();
	}
	
	public void onDisable(){
		log("has been disabled!", logType.info);
		toggleGate.saveGateStatesOnShutDown();
	}
	
	public static void log(String msg, logType type) {
		String prefix = plugin.getDescription().getName() + " ";
		if(type == logType.info) {
			logger.info(prefix + msg);
		} else if(type == logType.severe) {
			logger.severe(prefix + msg);
		}
	}
	
	public static minebook getPlugin() {
		return plugin;
	}
	
}
