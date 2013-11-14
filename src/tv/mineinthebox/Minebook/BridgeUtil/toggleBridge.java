package tv.mineinthebox.Minebook.BridgeUtil;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Sign;

public class toggleBridge {
	
	public static boolean doToggleBridge(Block mainSign, Block secondSign) {
		boolean toggle = false;
		Sign mainFaces = (Sign)mainSign.getState().getData();
		if(mainFaces.getFacing().getOppositeFace() == BlockFace.NORTH) {
			System.out.print("NORTH");
			Block getLeftBlock = mainSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getRelative(BlockFace.NORTH);
			Block getRightBlock = secondSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getRelative(BlockFace.SOUTH);
			if(getLeftBlock.getType() == Material.WOOD) {
				toggle = false;
				for(int z = getLeftBlock.getZ(); z > (getRightBlock.getZ()-1);z--) {
					for(int x = getLeftBlock.getX(); x < (getRightBlock.getX()+1);x++ ) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						block.setType(Material.AIR);
					}
				}
			} else if(getLeftBlock.getType() == Material.AIR) {
				toggle = true;
				for(int z = getLeftBlock.getZ(); z > (getRightBlock.getZ()-1);z--) {
					for(int x = getLeftBlock.getX(); x < (getRightBlock.getX()+1);x++ ) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						block.setType(Material.WOOD);
					}
				}
				
			}
		} else if(mainFaces.getFacing().getOppositeFace() == BlockFace.EAST) {
			System.out.print("EAST");
			Block getLeftBlock = mainSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);
			Block getRightBlock = secondSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST);
			if(getLeftBlock.getType() == Material.WOOD) {
				toggle = false;
				for(int x = getLeftBlock.getX(); x < (getRightBlock.getX()+1);x++) {
					for(int z = getLeftBlock.getZ(); z < (getRightBlock.getZ()+1);z++ ) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						block.setType(Material.AIR);
					}
				}
			} else if(getLeftBlock.getType() == Material.AIR) {
				toggle = true;
				for(int x = getLeftBlock.getX(); x < (getRightBlock.getX()+1);x++) {
					for(int z = getLeftBlock.getZ(); z < (getRightBlock.getZ()+1);z++ ) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						block.setType(Material.WOOD);
					}
				}
				
			}
		} else if(mainFaces.getFacing().getOppositeFace() == BlockFace.SOUTH) {
			System.out.print("SOUTH");
			Block getLeftBlock = mainSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.EAST).getRelative(BlockFace.SOUTH);
			Block getRightBlock = secondSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.WEST).getRelative(BlockFace.NORTH);
			if(getLeftBlock.getType() == Material.WOOD) {
				toggle = false;
				for(int z = getLeftBlock.getZ(); z < (getRightBlock.getZ()+1);z++) {
					for(int x = getLeftBlock.getX(); x > (getRightBlock.getX()-1);x--) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						block.setType(Material.AIR);
					}
				}
			} else if(getLeftBlock.getType() == Material.AIR) {
				toggle = true;
				for(int z = getLeftBlock.getZ(); z < (getRightBlock.getZ()+1);z++) {
					for(int x = getLeftBlock.getX(); x > (getRightBlock.getX()-1);x--) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						block.setType(Material.WOOD);
					}
				}
				
			}
		} else if(mainFaces.getFacing().getOppositeFace() == BlockFace.WEST) {
			System.out.print("WEST");
			Block getLeftBlock = mainSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.SOUTH).getRelative(BlockFace.WEST);
			Block getRightBlock = secondSign.getRelative(BlockFace.DOWN).getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST);
			if(getLeftBlock.getType() == Material.WOOD) {
				toggle = false;
				for(int x = getLeftBlock.getX(); x > (getRightBlock.getX()-1);x--) {
					for(int z = getLeftBlock.getZ(); z > (getRightBlock.getZ()-1);z--) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						block.setType(Material.AIR);
					}
				}
			} else if(getLeftBlock.getType() == Material.AIR) {
				toggle = true;
				for(int x = getLeftBlock.getX(); x > (getRightBlock.getX()-1);x--) {
					for(int z = getLeftBlock.getZ(); z > (getRightBlock.getZ()-1);z--) {
						Block block = getLeftBlock.getWorld().getBlockAt(x, getLeftBlock.getY(), z);
						block.setType(Material.WOOD);
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

}
