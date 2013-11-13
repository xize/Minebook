package tv.mineinthebox.Minebook.BridgeUtil;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Sign;

public class isBridgeShape {

	public static boolean getBridgeShape(Block sign) {
		Block blockUnderSign = sign;
		System.out.print("this is the sign block: " + sign.getType().name());
		System.out.print("this is the blockUnderSign: " + blockUnderSign.getType().name());
		BlockFace[] faces = getAttachedFaces(blockUnderSign);
		if(faces == null) {
			return false;
		}
		if(faces.length == 2) {
			BlockFace faceOne = faces[0];
			BlockFace faceTwo = faces[1];
			if(faceOne.getOppositeFace() == faceTwo) {
				return true;
			}
		}
		return false;
	}

	public static BlockFace[] getAttachedFaces(Block centerblock) {
		ArrayList<BlockFace> faces = new ArrayList<BlockFace>();
		System.out.print("this is the sign: " + centerblock.getRelative(BlockFace.UP).getType().name());
		
		Sign sign = (Sign) centerblock.getRelative(BlockFace.UP).getState().getData();
		
		if(centerblock.getType() != Material.WOOD) {
			return null;
		}
		//now lets see if the type match with the orginal block
		if(sign.getFacing() == BlockFace.NORTH) {
			//we need here east and west
			if(centerblock.getRelative(BlockFace.EAST).getType() == centerblock.getType()) {
				BlockFace face = BlockFace.EAST;
				faces.add(face);
			}
			if(centerblock.getRelative(BlockFace.WEST).getType() == centerblock.getType()) {
				BlockFace face = BlockFace.WEST;
				faces.add(face);
			}
		} else if(sign.getFacing() == BlockFace.EAST) {
			//we need here north and south
			if(centerblock.getRelative(BlockFace.NORTH).getType() == centerblock.getType()) {
				BlockFace face = BlockFace.NORTH;
				faces.add(face);
			}
			if(centerblock.getRelative(BlockFace.SOUTH).getType() == centerblock.getType()) {
				BlockFace face = BlockFace.SOUTH;
				faces.add(face);
			}
		} else if(sign.getFacing() == BlockFace.SOUTH) {
			//we need here east and west
			if(centerblock.getRelative(BlockFace.EAST).getType() == centerblock.getType()) {
				BlockFace face = BlockFace.EAST;
				faces.add(face);
			}
			if(centerblock.getRelative(BlockFace.WEST).getType() == centerblock.getType()) {
				BlockFace face = BlockFace.WEST;
				faces.add(face);
			}
		} else if(sign.getFacing() == BlockFace.WEST) {
			//we need here north and south;)
			if(centerblock.getRelative(BlockFace.NORTH).getType() == centerblock.getType()) {
				BlockFace face = BlockFace.NORTH;
				faces.add(face);
			}
			if(centerblock.getRelative(BlockFace.SOUTH).getType() == centerblock.getType()) {
				BlockFace face = BlockFace.SOUTH;
				faces.add(face);
			}
		}
		BlockFace[] blockfaces = faces.toArray(new BlockFace[faces.size()]);
		return blockfaces;
	}

}
