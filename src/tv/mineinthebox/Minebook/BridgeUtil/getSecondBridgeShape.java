package tv.mineinthebox.Minebook.BridgeUtil;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Sign;

import tv.mineinthebox.Minebook.configuration;

public class getSecondBridgeShape {

	public static boolean getSecondBridge(Block signBlock) {
		Sign sign = (Sign) signBlock.getState().getData();
		BlockFace face = sign.getFacing().getOppositeFace();
		System.out.print("sign back points to: " + face.name());
		int maxRange = configuration.getBridgeLength();
		if(face == BlockFace.NORTH) {
			//calculate towards the z axis (as minimize)
			System.out.print("found North!");
			int check = (signBlock.getZ()-maxRange);
			System.out.print("range is from: " + signBlock.getZ() + " to: " + check);
			for(int z = signBlock.getZ(); z > (signBlock.getZ()-maxRange); z--) {
				Block block = signBlock.getWorld().getBlockAt(signBlock.getX(), signBlock.getY(), z);
				System.out.print("this is the block: " + block.getType().name());
				if(block.getType() == Material.AIR || block.getType() == Material.SIGN_POST) {
					if(block.getType() == Material.SIGN_POST) {
						org.bukkit.block.Sign signn = (org.bukkit.block.Sign) block.getState();
						if(signn.getLine(0).contains("[Bridge]")) {
							return true;
						}
					}	
				} else {
					return false;
				}
			}
		} else if(face == BlockFace.EAST) {
			//calculate towards the x axis (as not minimize)
			System.out.print("found east!");
			int check = (signBlock.getX()+maxRange);
			System.out.print("range is from: " + signBlock.getX() + " to: " + check);
			for(int x = signBlock.getX(); x < (signBlock.getX()+maxRange); x++) {
				Block block = signBlock.getWorld().getBlockAt(x, signBlock.getY(), signBlock.getZ());
				System.out.print("this is the block: " + block.getType().name());
				if(block.getType() == Material.AIR || block.getType() == Material.SIGN_POST) {
					if(block.getType() == Material.SIGN_POST) {
						org.bukkit.block.Sign signn = (org.bukkit.block.Sign) block.getState();
						if(signn.getLine(0).contains("[Bridge]")) {
							return true;
						}
					}	
				} else {
					return false;
				}
			}
		} else if(face == BlockFace.SOUTH) {
			//calculate towards the x axis (as not minimize)
			System.out.print("found South!");
			int check = (signBlock.getZ()+maxRange);
			System.out.print("range is from: " + signBlock.getZ() + " to: " + check);
			for(int z = signBlock.getZ(); z < (signBlock.getZ()+maxRange); z++) {
				Block block = signBlock.getWorld().getBlockAt(signBlock.getX(), signBlock.getY(), z);
				System.out.print("this is the block: " + block.getType().name());
				if(block.getType() == Material.AIR || block.getType() == Material.SIGN_POST) {
					if(block.getType() == Material.SIGN_POST) {
						org.bukkit.block.Sign signn = (org.bukkit.block.Sign) block.getState();
						if(signn.getLine(0).contains("[Bridge]")) {
							return true;
						}
					}	
				} else {
					return false;
				}
			}
		} else if(face == BlockFace.WEST) {
			//calculate towards the x axis (minimize)
			System.out.print("found west!");
			int check = (signBlock.getX()-maxRange);
			System.out.print("range is from: " + signBlock.getX() + " to: " + check);
			for(int x = signBlock.getX(); x > (signBlock.getX()-maxRange); x--) {
				Block block = signBlock.getWorld().getBlockAt(x, signBlock.getY(), signBlock.getZ());
				System.out.print("this is the block: " + block.getType().name());
				if(block.getType() == Material.AIR || block.getType() == Material.SIGN_POST) {
					if(block.getType() == Material.SIGN_POST) {
						org.bukkit.block.Sign signn = (org.bukkit.block.Sign) block.getState();
						if(signn.getLine(0).contains("[Bridge]")) {
							return true;
						}
					}	
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public static Block[] getSecondBridgeFromBlock(Block signBlock) {
		Sign sign = (Sign) signBlock.getState().getData();
		BlockFace face = sign.getFacing().getOppositeFace();
		System.out.print("sign back points to: " + face.name());
		int maxRange = configuration.getBridgeLength();
		ArrayList<Block> blocks = new ArrayList<Block>();
		blocks.add(signBlock);
		if(face == BlockFace.NORTH) {
			//calculate towards the z axis (as minimize)
			System.out.print("found North!");
			int check = (signBlock.getZ()-maxRange);
			System.out.print("range is from: " + signBlock.getZ() + " to: " + check);
			for(int z = signBlock.getZ(); z > (signBlock.getZ()-maxRange); z--) {
				Block block = signBlock.getWorld().getBlockAt(signBlock.getX(), signBlock.getY(), z);
				System.out.print("this is the block: " + block.getType().name());
				if(!block.getLocation().equals(signBlock.getLocation())) {
					if(block.getType() == Material.SIGN_POST) {
						org.bukkit.block.Sign signn = (org.bukkit.block.Sign) block.getState();
						if(signn.getLine(0).contains("[Bridge]")) {
							blocks.add(block);
							Block[] allBlocks = blocks.toArray(new Block[blocks.size()]);
							return allBlocks;
						}
					}	
				}
			}
		} else if(face == BlockFace.EAST) {
			//calculate towards the x axis (as not minimize)
			System.out.print("found east!");
			int check = (signBlock.getX()+maxRange);
			System.out.print("range is from: " + signBlock.getX() + " to: " + check);
			for(int x = signBlock.getX(); x < (signBlock.getX()+maxRange); x++) {
				Block block = signBlock.getWorld().getBlockAt(x, signBlock.getY(), signBlock.getZ());
				System.out.print("this is the block: " + block.getType().name());
				if(!block.getLocation().equals(signBlock.getLocation())) {
					if(block.getType() == Material.SIGN_POST) {
						org.bukkit.block.Sign signn = (org.bukkit.block.Sign) block.getState();
						if(signn.getLine(0).contains("[Bridge]")) {
							blocks.add(block);
							Block[] allBlocks = blocks.toArray(new Block[blocks.size()]);
							return allBlocks;
						}
					}
				}
			}
		} else if(face == BlockFace.SOUTH) {
			//calculate towards the x axis (as not minimize)
			System.out.print("found South!");
			int check = (signBlock.getZ()+maxRange);
			System.out.print("range is from: " + signBlock.getZ() + " to: " + check);
			for(int z = signBlock.getZ(); z < (signBlock.getZ()+maxRange); z++) {
				Block block = signBlock.getWorld().getBlockAt(signBlock.getX(), signBlock.getY(), z);
				System.out.print("this is the block: " + block.getType().name());
				if(!block.getLocation().equals(signBlock.getLocation())) {
					if(block.getType() == Material.SIGN_POST) {
						org.bukkit.block.Sign signn = (org.bukkit.block.Sign) block.getState();
						if(signn.getLine(0).contains("[Bridge]")) {
							blocks.add(block);
							Block[] allBlocks = blocks.toArray(new Block[blocks.size()]);
							return allBlocks;
						}
					}
				}
			}
		} else if(face == BlockFace.WEST) {
			//calculate towards the x axis (minimize)
			System.out.print("found west!");
			int check = (signBlock.getX()-maxRange);
			System.out.print("range is from: " + signBlock.getX() + " to: " + check);
			for(int x = signBlock.getX(); x > (signBlock.getX()-maxRange); x--) {
				Block block = signBlock.getWorld().getBlockAt(x, signBlock.getY(), signBlock.getZ());
				System.out.print("this is the block: " + block.getType().name());
				if(!block.getLocation().equals(signBlock.getLocation())){
					if(block.getType() == Material.SIGN_POST) {
						org.bukkit.block.Sign signn = (org.bukkit.block.Sign) block.getState();
						if(signn.getLine(0).contains("[Bridge]")) {
							blocks.add(block);
							Block[] allBlocks = blocks.toArray(new Block[blocks.size()]);
							return allBlocks;
						}
					}
				}
			}
		}
		return null;
	}
}
