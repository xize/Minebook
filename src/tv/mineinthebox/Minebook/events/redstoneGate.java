package tv.mineinthebox.Minebook.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;

import tv.mineinthebox.Minebook.GateUtil.GateShape;
import tv.mineinthebox.Minebook.GateUtil.toggleGate;

public class redstoneGate implements Listener {
	
	@EventHandler
	public void doRedStone(BlockPhysicsEvent e) {
		if(e.getBlock().getType() == Material.WOOD) {
			if(e.getBlock().isBlockPowered()) {
				Block blockBehindSign = e.getBlock().getRelative(BlockFace.UP);
				Sign sign = getSignAttached(blockBehindSign);
				if(sign instanceof Sign) {
					//the sign is a instance of the Sign type so this couldn't be null here.
					if(ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("[Gate]")) {
						if(GateShape.getGateShape(blockBehindSign)) {
							Block[] blocks = GateShape.getSelection(blockBehindSign);
							Block highestBlock = blocks[0];
							Block lowestBlock = blocks[1];
							toggleGate.ToggleGate(lowestBlock, highestBlock);
						}
					}
				}
			}
		}
	}
	
	public Sign getSignAttached(Block block) {
		for(BlockFace face : BlockFace.values()) {
			if(face != BlockFace.UP || face != BlockFace.DOWN || face != BlockFace.SELF) {
				if(block.getRelative(face).getType() == Material.WALL_SIGN) {
					return (Sign) block.getRelative(face).getState();
				}
			}
		}
		return null;
	}

}
