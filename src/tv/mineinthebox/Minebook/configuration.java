package tv.mineinthebox.Minebook;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class configuration {
	
	public static void createConfig() {
		try {
			File f = new File(minebook.getPlugin().getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set("Elevator.enable", true);
				con.set("Bridges.enable", true);
				con.set("Bridges.maxLength", 30);
				con.set("Gates.enable", true);
				con.set("Gates.maxHeight", 80);
				con.set("Gates.maxWidth", 80);
				con.save(f);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Integer getBridgeLength() {
		try {
			File f = new File(minebook.getPlugin().getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				return con.getInt("Bridges.maxLength");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static Integer[] getGateSize() {
		try {
			File f = new File(minebook.getPlugin().getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				int height = con.getInt("Gates.maxHeight");
				int width = con.getInt("Gates.maxWidth");
				ArrayList<Integer> getSizes = new ArrayList<Integer>();
				getSizes.add(height);
				getSizes.add(width);
				Integer[] sizes = getSizes.toArray(new Integer[getSizes.size()]);
				return sizes;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean isBridgeEnabled() {
		try {
			File f = new File(minebook.getPlugin().getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				return con.getBoolean("Bridges.enable");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isElevatorEnabled() {
		try {
			File f = new File(minebook.getPlugin().getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				return con.getBoolean("enableElevator");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isGatesEnabled() {
		try {
			File f = new File(minebook.getPlugin().getDataFolder() + File.separator + "config.yml");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				return con.getBoolean("Gates.enable");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
