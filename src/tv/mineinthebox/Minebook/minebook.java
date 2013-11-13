package tv.mineinthebox.Minebook;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import tv.mineinthebox.Minebook.GateUtil.toggleGate;
import tv.mineinthebox.Minebook.events.handler;

public class minebook extends JavaPlugin {

	private static minebook plugin;
	
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
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + msg));
		} else if(type == logType.severe) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[Warning]"+prefix + msg));
		}
	}
	
	public static minebook getPlugin() {
		return plugin;
	}
	
}
