package tv.mineinthebox.Minebook.GateUtil;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import tv.mineinthebox.Minebook.minebook;

public class toggleGate {

	public static boolean toggle;
	public static ArrayList<Block> protectedFences = new ArrayList<Block>();
	
	public static boolean ToggleGate(Block lowestBlock, Block highestBlock) {
		toggle = false;
		if(lowestBlock.getX() == highestBlock.getX()) {
			//here we use the z axis
			if(highestBlock.getZ() < lowestBlock.getZ()) {
				for(int y = highestBlock.getY(); y >= lowestBlock.getY(); y--) {
					for(int z = highestBlock.getZ(); z < lowestBlock.getZ(); z++) {
						Block block = highestBlock.getWorld().getBlockAt(highestBlock.getX(), y, z);
						if(block.getType() == Material.AIR) {
							block.setType(Material.FENCE);
							protectedFences.add(block);
							toggle = true;
						} else if(block.getType() == Material.FENCE) {
							protectedFences.remove(block);
							block.setType(Material.AIR);
							toggle = false;
						}
					}
				}	
			} else if(highestBlock.getZ() > lowestBlock.getZ()) {
				for(int y = highestBlock.getY(); y >= lowestBlock.getY(); y--) {
					for(int z = highestBlock.getZ(); z > lowestBlock.getZ(); z--) {
						Block block = highestBlock.getWorld().getBlockAt(highestBlock.getX(), y, z);
						if(block.getType() == Material.AIR) {
							block.setType(Material.FENCE);
							toggle = true;
							protectedFences.add(block);
						} else if(block.getType() == Material.FENCE) {
							protectedFences.remove(block);
							block.setType(Material.AIR);
							toggle = false;
						}
					}
				}
			}
		} else if(lowestBlock.getZ() == highestBlock.getZ()) {
			//here we use the x axis
			if(highestBlock.getX() < lowestBlock.getX()) {
				for(int y = highestBlock.getY(); y >= lowestBlock.getY(); y--) {
					for(int x = highestBlock.getX(); x < lowestBlock.getX(); x++) {
						Block block = highestBlock.getWorld().getBlockAt(x, y, highestBlock.getZ());
						if(block.getType() == Material.AIR) {
							block.setType(Material.FENCE);
							toggle = true;
							protectedFences.add(block);
						} else if(block.getType() == Material.FENCE) {
							protectedFences.remove(block);
							block.setType(Material.AIR);
							toggle = false;
						}
					}
				}	
			} else if(highestBlock.getX() > lowestBlock.getX()) {
				for(int y = highestBlock.getY(); y >= lowestBlock.getY(); y--) {
					for(int x = highestBlock.getX(); x > lowestBlock.getX(); x--) {
						Block block = highestBlock.getWorld().getBlockAt(x, y, highestBlock.getZ());
						if(block.getType() == Material.AIR) {
							block.setType(Material.FENCE);
							toggle = true;
							protectedFences.add(block);
						} else if(block.getType() == Material.FENCE) {
							protectedFences.remove(block);
							block.setType(Material.AIR);
							toggle = false;
						}
					}
				}
			}
		}
		return toggle;
	}
	
	public static void saveGateStatesOnShutDown() {
		try {
			File f = new File(minebook.getPlugin().getDataFolder() + File.separator + "gates.db");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.isSet("database")) {
					con.set("database", null);
					con.save(f);
					con.set("database", Serialize(protectedFences));
					con.save(f);
					protectedFences.clear();
				} else {
					con.set("database", Serialize(protectedFences));
					con.save(f);
					protectedFences.clear();
				}
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set("database", Serialize(protectedFences));
				con.save(f);
				protectedFences.clear();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> Serialize(ArrayList<Block> block) {
		ArrayList<String> serialized = new ArrayList<String>();
		for(Block ablock : block) {
			serialized.add(ablock.getType().name()+","+ablock.getX()+","+ablock.getY()+","+ablock.getZ()+","+ablock.getWorld().getName());
		}
		return serialized;
	}
	
	public static ArrayList<Block> Deserialize(FileConfiguration con) {
		ArrayList<Block> blocks = new ArrayList<Block>();
		for(String serializedBlock : con.getStringList("database")) {
			String[] splitedName = serializedBlock.split(",");
			String MaterialName = splitedName[0];
			Integer x = Integer.parseInt(splitedName[1]);
			Integer y = Integer.parseInt(splitedName[2]);
			Integer z = Integer.parseInt(splitedName[3]);
			String world = splitedName[4];
			Location loc = new Location(Bukkit.getWorld(world), x, y, z);
			Block block = loc.getBlock();
			if(block.getType() == Material.valueOf(MaterialName)) {
				blocks.add(block);	
			}
		}
		return blocks;
	}
	
	public static void loadGates() {
		try {
			File f = new File(minebook.getPlugin().getDataFolder() + File.separator + "gates.db");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				ArrayList<Block> blocks = Deserialize(con);
				for(Block block : blocks) {
					protectedFences.add(block);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
