package tv.mineinthebox.Minebook.events;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import tv.mineinthebox.Minebook.configuration;
import tv.mineinthebox.Minebook.minebook;

public class handler {
	
	public static void runListeners() {
		if(configuration.isGatesEnabled()) {setListener(new gateEvent());}
		if(configuration.isBridgeEnabled()){setListener(new bridgeEvent());}
	}
	
	public static void setListener(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, minebook.getPlugin());
	}
	
	public static void restart() {
		HandlerList.unregisterAll(minebook.getPlugin());
		runListeners();
	}

}
