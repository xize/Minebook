package tv.mineinthebox.Minebook.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Sign;

import tv.mineinthebox.Minebook.GateUtil.GateShape;
import tv.mineinthebox.Minebook.GateUtil.toggleGate;

public class gateEvent implements Listener {
	
	@EventHandler
	public void onSignPlace(SignChangeEvent e) {
		if(e.getBlock().getType() == Material.WALL_SIGN) {
			if(e.getLine(0).equalsIgnoreCase("[gate]")) {
				if(e.getPlayer().hasPermission("minebook.canCreateGates")) {
					Sign sign = (Sign) e.getBlock().getState().getData();
					Block block = e.getBlock().getRelative(sign.getAttachedFace());
					if(GateShape.getGateShape(block)) {
						e.setLine(0, ChatColor.translateAlternateColorCodes('&', "&1[Gate]"));
						e.getPlayer().sendMessage(ChatColor.GREEN + "successfully created a Gate!");
					} else {
						e.getPlayer().sendMessage(ChatColor.RED + "this gate shape is invalid!");
						e.getBlock().breakNaturally();
						e.setCancelled(true);
					}
				} else {
					e.getBlock().breakNaturally();
					e.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to make Gate signs!");
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onBreakFence(BlockBreakEvent e) {
		if(toggleGate.protectedFences.contains(e.getBlock())) {
			e.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to mine these fences!");
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onSignGate(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.WALL_SIGN) {
				org.bukkit.block.Sign sign = (org.bukkit.block.Sign) e.getClickedBlock().getState();
				Sign s = (Sign) e.getClickedBlock().getState().getData();
				if(sign.getLine(0).contains("[Gate]")) {
					if(e.getPlayer().hasPermission("minebook.canUseGates")) {
						if(GateShape.getGateShape(e.getClickedBlock().getRelative(s.getAttachedFace()))) {
							Block[] blocks = GateShape.getSelection(e.getClickedBlock().getRelative(s.getAttachedFace()));
							Block highestBlock = blocks[0];
							Block lowestBlock = blocks[1];
							System.out.print("this is block lowest!" + lowestBlock.toString());
							System.out.print("this is block highest!" + highestBlock.toString());
							if(toggleGate.ToggleGate(lowestBlock, highestBlock)) {
								e.getPlayer().sendMessage(ChatColor.GREEN + "Gate toggled!");
							} else {
								e.getPlayer().sendMessage(ChatColor.GREEN + "Gate untoggled!");
							}
						} else {
							e.getPlayer().sendMessage(ChatColor.RED + "this gate does not longer work!");
						}
					}
				} else {
					e.getPlayer().sendMessage(ChatColor.RED + "you don't have permission to do this!");
				}
			}
		}
	}
	
}