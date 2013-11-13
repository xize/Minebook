package tv.mineinthebox.Minebook.GateUtil;

import java.util.ArrayList;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import tv.mineinthebox.Minebook.configuration;

public class GateShape {
	
	public static boolean getGateShape(Block behindSign) {
		int maxHeight = 80;
		int maxWidth = 80;
		
		Block onGround = behindSign.getRelative(BlockFace.DOWN);
		
		ArrayList<Block> SignPilar = new ArrayList<Block>();
		
		//create the first pilar, and store it into our arraylist1
		for(int y = 0; y < maxHeight; y++) {
			Block block = onGround.getWorld().getBlockAt(onGround.getX(), onGround.getY()+y,onGround.getZ());
			if(block.getType() == onGround.getType()) {
				SignPilar.add(block);
			} else {
				break;
			}
		}
		//now extract our last block from SignPilar and get the direction.
		Block getHighestSignPilarBlock = SignPilar.get((SignPilar.size() -1));
		BlockFace face = getAttachedFace(getHighestSignPilarBlock);
		if(face == null) {
			//the shape is invalid because there is no balk.
			return false;
		}
		//now we will make the upper balk between the pilars
		ArrayList<Block> balk = new ArrayList<Block>();
		for(int i = 0; i < maxWidth; i++) {
			if(balk.isEmpty()) {
				Block block = getHighestSignPilarBlock.getRelative(face);
				if(block.getType() == onGround.getType()) {
					balk.add(block);
				} else {
					return false;
				}
			} else {
				Block block = balk.get((i-1)).getRelative(face);
				if(block.getType() == onGround.getType()) {
					balk.add(block);
				} else {
					//we are done with collecting the balk information.
					break;
				}
			}
		}
		//now we are done with collecting the balks information but we need to shrink it so we can calculate the second pilar
		balk.remove((balk.size()-1));
		Block secondPilarBlock = balk.get((balk.size()-1)).getRelative(face);
		//now we made the pilar instance we can now remove the last entry from the balk array since we want to be sure we got the balk!	
		//now lets loop down on the y axis and see if these contains all the same materials!
		ArrayList<Block> secondPilar = new ArrayList<Block>();
		for(int y = 0; y < onGround.getY(); y++) {
			Block block = secondPilarBlock.getWorld().getBlockAt(secondPilarBlock.getX(), (secondPilarBlock.getY() - y), secondPilarBlock.getZ());
			if(block.getType() == onGround.getType()) {
				secondPilar.add(block);
			} else {
				//the loop goes futher so we disable this
				break;
			}
		}
		//now we check if all two of the pilars have the same height, and the balk is not empty!
		
		System.out.print("size of SignPilar: " + SignPilar.size());
		System.out.print("size of SecondPilar: " + secondPilar.size());
		System.out.print("size of balk: " + balk.size());
		if(SignPilar.size() == secondPilar.size() && !balk.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public static Block[] getSelection(Block behindSign) {
		int maxHeight = configuration.getGateSize()[0];
		int maxWidth = configuration.getGateSize()[1];
		
		System.out.print("this is the height: " + maxHeight + " and this is the width: " + maxWidth);
		
		Block onGround = behindSign.getRelative(BlockFace.DOWN);
		
		ArrayList<Block> SignPilar = new ArrayList<Block>();
		
		//create the first pilar, and store it into our arraylist1
		for(int y = 0; y < maxHeight; y++) {
			Block block = onGround.getWorld().getBlockAt(onGround.getX(), onGround.getY()+y,onGround.getZ());
			if(block.getType() == onGround.getType()) {
				SignPilar.add(block);
			} else {
				break;
			}
		}
		//now extract our last block from SignPilar and get the direction.
		Block getHighestSignPilarBlock = SignPilar.get((SignPilar.size() -1));
		BlockFace face = getAttachedFace(getHighestSignPilarBlock);
		if(face == null) {
			//the shape is invalid because there is no balk.
			return null;
		}
		//now we will make the upper balk between the pilars
		ArrayList<Block> balk = new ArrayList<Block>();
		for(int i = 0; i < maxWidth; i++) {
			if(balk.isEmpty()) {
				Block block = getHighestSignPilarBlock.getRelative(face);
				if(block.getType() == onGround.getType()) {
					balk.add(block);
				} else {
					return null;
				}
			} else {
				Block block = balk.get((i-1)).getRelative(face);
				if(block.getType() == onGround.getType()) {
					balk.add(block);
				} else {
					//we are done with collecting the balk information.
					break;
				}
			}
		}
		//now we are done with collecting the balks information but we need to shrink it so we can calculate the second pilar
		balk.remove((balk.size()-1));
		Block secondPilarBlock = balk.get((balk.size()-1)).getRelative(face);
		//now we made the pilar instance we can now remove the last entry from the balk array since we want to be sure we got the balk!	
		//now lets loop down on the y axis and see if these contains all the same materials!
		ArrayList<Block> secondPilar = new ArrayList<Block>();
		for(int y = 0; y < onGround.getY(); y++) {
			Block block = secondPilarBlock.getWorld().getBlockAt(secondPilarBlock.getX(), (secondPilarBlock.getY() - y), secondPilarBlock.getZ());
			if(block.getType() == onGround.getType()) {
				secondPilar.add(block);
			} else {
				//the loop goes futher so we disable this
				break;
			}
		}
		//now we check if all two of the pilars have the same height, and the balk is not empty!
		
		System.out.print("size of SignPilar: " + SignPilar.size());
		System.out.print("size of SecondPilar: " + secondPilar.size());
		System.out.print("size of balk: " + balk.size());
		if(SignPilar.size() == secondPilar.size() && !balk.isEmpty()) {
			ArrayList<Block> stackBlocks = new ArrayList<Block>();
			stackBlocks.add(secondPilarBlock);
			stackBlocks.add(onGround);
			Block[] blocks = stackBlocks.toArray(new Block[stackBlocks.size()]);
			return blocks;
		}
		return null;
	}

	
	public static BlockFace getAttachedFace(Block block) {
		for(BlockFace face : BlockFace.values()) {
			if(face == BlockFace.UP || face == BlockFace.DOWN || face == BlockFace.SELF) {
				//ignore these faces these are not relevant.
			} else {
				if(block.getRelative(face).getType() == block.getType()) {
					return face;
				}
			}
		}
		return null;
	}
	
}
