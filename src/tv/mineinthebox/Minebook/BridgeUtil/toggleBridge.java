package tv.mineinthebox.Minebook.BridgeUtil;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.material.Sign;

import tv.mineinthebox.Minebook.minebook;

public class toggleBridge {
	
	public static ArrayList<Block> protectedBridges = new ArrayList<Block>();
	
	public static boolean doToggleBridge(Block mainSign, Block secondSign) {
		boolean toggle = false;
		Sign mainFaces = (Sign)mainSign.getState().getData();
		if(mainFaces.getFacing().getOppositeFace() == BlockFace.NORTH) {
			Block getLeftBlock = mainSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getRelative(BlockFace.NORTH);
			Block getRightBlock = secondSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getRelative(BlockFace.SOUTH);
			if(getLeftBlock.getType() == Material.WOOD) {
				toggle = false;
				for(int z = getLeftBlock.getZ(); z > (getRightBlock.getZ()-1);z--) {
					for(int x = getLeftBlock.getX(); x < (getRightBlock.getX()+1);x++ ) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						if(protectedBridges.contains(block)) {
							block.setType(Material.AIR);
							protectedBridges.remove(block);
						}
					}
				}
			} else if(getLeftBlock.getType() == Material.AIR) {
				toggle = true;
				for(int z = getLeftBlock.getZ(); z > (getRightBlock.getZ()-1);z--) {
					for(int x = getLeftBlock.getX(); x < (getRightBlock.getX()+1);x++ ) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						block.setType(Material.WOOD);
						protectedBridges.add(block);
					}
				}
				
			}
		} else if(mainFaces.getFacing().getOppositeFace() == BlockFace.EAST) {
			Block getLeftBlock = mainSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);
			Block getRightBlock = secondSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST);
			if(getLeftBlock.getType() == Material.WOOD) {
				toggle = false;
				for(int x = getLeftBlock.getX(); x < (getRightBlock.getX()+1);x++) {
					for(int z = getLeftBlock.getZ(); z < (getRightBlock.getZ()+1);z++ ) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						if(protectedBridges.contains(block)) {
							block.setType(Material.AIR);
							protectedBridges.remove(block);
						}
					}
				}
			} else if(getLeftBlock.getType() == Material.AIR) {
				toggle = true;
				for(int x = getLeftBlock.getX(); x < (getRightBlock.getX()+1);x++) {
					for(int z = getLeftBlock.getZ(); z < (getRightBlock.getZ()+1);z++ ) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						block.setType(Material.WOOD);
						protectedBridges.add(block);
					}
				}
				
			}
		} else if(mainFaces.getFacing().getOppositeFace() == BlockFace.SOUTH) {
			Block getLeftBlock = mainSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getRelative(BlockFace.SOUTH);
			Block getRightBlock = secondSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getRelative(BlockFace.NORTH);
			if(getLeftBlock.getType() == Material.WOOD) {
				toggle = false;
				for(int z = getLeftBlock.getZ(); z < (getRightBlock.getZ()+1);z++) {
					for(int x = getLeftBlock.getX(); x > (getRightBlock.getX()-1);x--) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						if(protectedBridges.contains(block)) {
							block.setType(Material.AIR);
							protectedBridges.remove(block);
						}
					}
				}
			} else if(getLeftBlock.getType() == Material.AIR) {
				toggle = true;
				for(int z = getLeftBlock.getZ(); z < (getRightBlock.getZ()+1);z++) {
					for(int x = getLeftBlock.getX(); x > (getRightBlock.getX()-1);x--) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						block.setType(Material.WOOD);
						protectedBridges.add(block);
					}
				}
				
			}
		} else if(mainFaces.getFacing().getOppositeFace() == BlockFace.WEST) {
			Block getLeftBlock = mainSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST);
			Block getRightBlock = secondSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);
			if(getLeftBlock.getType() == Material.WOOD) {
				toggle = false;
				for(int x = getLeftBlock.getX(); x > (getRightBlock.getX()-1);x--) {
					for(int z = getLeftBlock.getZ(); z > (getRightBlock.getZ()-1);z--) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						if(protectedBridges.contains(block)) {
							block.setType(Material.AIR);
							protectedBridges.remove(block);
						}
					}
				}
			} else if(getLeftBlock.getType() == Material.AIR) {
				toggle = true;
				for(int x = getLeftBlock.getX(); x > (getRightBlock.getX()-1);x--) {
					for(int z = getLeftBlock.getZ(); z > (getRightBlock.getZ()-1);z--) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						block.setType(Material.WOOD);
						protectedBridges.add(block);
					}
				}
				
			}
		}
		if(toggle) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void saveBridgeStatesOnShutDown() {
		try {
			File f = new File(minebook.getPlugin().getDataFolder() + File.separator + "bridges.db");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				if(con.isSet("database")) {
					con.set("database", null);
					con.save(f);
					con.set("database", Serialize(protectedBridges));
					con.save(f);
					protectedBridges.clear();
				} else {
					con.set("database", Serialize(protectedBridges));
					con.save(f);
					protectedBridges.clear();
				}
			} else {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				con.set("database", Serialize(protectedBridges));
				con.save(f);
				protectedBridges.clear();
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
	
	public static void loadBridges() {
		try {
			File f = new File(minebook.getPlugin().getDataFolder() + File.separator + "bridges.db");
			if(f.exists()) {
				FileConfiguration con = YamlConfiguration.loadConfiguration(f);
				ArrayList<Block> blocks = Deserialize(con);
				for(Block block : blocks) {
					protectedBridges.add(block);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
