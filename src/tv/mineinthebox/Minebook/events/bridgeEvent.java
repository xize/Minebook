package tv.mineinthebox.Minebook.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import tv.mineinthebox.Minebook.configuration;
import tv.mineinthebox.Minebook.BridgeUtil.getSecondBridgeShape;
import tv.mineinthebox.Minebook.BridgeUtil.isBridgeShape;
import tv.mineinthebox.Minebook.BridgeUtil.toggleBridge;

public class bridgeEvent implements Listener {

	@EventHandler
	public void onSignBridge(SignChangeEvent e) {
		if(e.getLine(0).equalsIgnoreCase("[Bridge]")) {
			if(e.getPlayer().hasPermission("minebook.canCreateBridges")) {
				if(isBridgeShape.getBridgeShape(e.getBlock().getRelative(BlockFace.DOWN))) {
					if(getSecondBridgeShape.getSecondBridge(e.getBlock())) {
						e.getPlayer().sendMessage(ChatColor.GREEN + "successfully attached both bridge signs to each other!");
						e.setLine(0, ChatColor.translateAlternateColorCodes('&', "&1[Bridge]"));
					} else {
						e.getPlayer().sendMessage(ChatColor.GREEN + "successfuly placed one bridge sign!");
						e.setLine(0, ChatColor.translateAlternateColorCodes('&', "&1[Bridge]"));
					}
				} else {
					e.getBlock().breakNaturally();
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.RED + "invalid bridge shape!, you need to put on a sign post and underneath 3 oak wood blocks");
				}
			} else {
				e.getBlock().breakNaturally();
				e.setCancelled(true);
				e.getPlayer().sendMessage(ChatColor.RED + "you don't have permission to create bridges!");
			}
		}
	}

	@EventHandler
	public void toggleSign(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.SIGN_POST) {
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(0).contains("[Bridge]")) {
					if(e.getPlayer().hasPermission("minebook.canUseBridges")) {
						if(isBridgeShape.getBridgeShape(e.getClickedBlock().getRelative(BlockFace.DOWN))) {
							if(getSecondBridgeShape.getSecondBridge(e.getClickedBlock())) {
								//Explanation, 1 is the block itself and 1 is the next sign block we need to check its shape.
								Block[] getSecondSign = getSecondBridgeShape.getSecondBridgeFromBlock(e.getClickedBlock());
								Block block1 = getSecondSign[1];
								if(getSecondSign.length == 2) {
									if(isBridgeShape.getBridgeShape(getSecondSign[0].getRelative(BlockFace.DOWN))) {
										if(toggleBridge.doToggleBridge(e.getClickedBlock(), block1)) {
											e.getPlayer().sendMessage(ChatColor.GREEN + "toggled bridge!");
										} else {
											e.getPlayer().sendMessage(ChatColor.GREEN + "untoggled bridge!");
										}
									} else {
										e.getPlayer().sendMessage(ChatColor.RED + "the next sign whas found, however the shape has been stated as invalid");
										e.setCancelled(true);
									}
								} else {
									e.getPlayer().sendMessage(ChatColor.RED + "the second signs text is invalid!, or the sign block whas not found");
									e.setCancelled(true);
								}
							} else {
								e.getPlayer().sendMessage(ChatColor.RED + "couldn't find next bridge sign!, note that the max length is: " + configuration.getBridgeLength());
								e.setCancelled(true);
							}
						} else {
							e.getPlayer().sendMessage(ChatColor.RED + "this bridge shape is invalid!");
							e.setCancelled(true);
						}
					} else {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "you don't have permission to use bridge signs!");
					}
				}
			}
		}
	}

	@EventHandler
	public void breakBlock(BlockBreakEvent e) {
		if(toggleBridge.protectedBridges.contains(e.getBlock())) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to break this bridge block!");
		}
	}

	@EventHandler
	public void explosion(EntityExplodeEvent e) {
		for(Block block : e.blockList()) {
			if(toggleBridge.protectedBridges.contains(block)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void piston(BlockPistonExtendEvent e) {
		for(Block block : e.getBlocks()) {
			if(toggleBridge.protectedBridges.contains(block)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void piston(BlockPistonRetractEvent e) {
		if(toggleBridge.protectedBridges.contains(e.getRetractLocation().getBlock())) {
			e.setCancelled(true);
		}
	}
}
