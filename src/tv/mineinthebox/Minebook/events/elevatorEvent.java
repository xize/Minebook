package tv.mineinthebox.Minebook.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class elevatorEvent implements Listener {

	@EventHandler
	public void elevatorSign(SignChangeEvent e) {
		if(e.getLine(0).equalsIgnoreCase("[lift]")) {
			if(e.getPlayer().hasPermission("minebook.canCreateLifts")) {
				e.setLine(0, ChatColor.DARK_BLUE + e.getLine(0));
				e.getBlock().getState().update(true);
				e.getPlayer().sendMessage(ChatColor.GREEN + "successfully made a elevator sign!");
			} else {
				e.getBlock().breakNaturally();
				e.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to make elevator signs without permission!");
			}
		}
	}

	@EventHandler
	public void signInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.WALL_SIGN) {
				Sign sign = (Sign) e.getClickedBlock().getState();
				if(sign.getLine(0).contains("[lift]")) {
					if(e.getPlayer().hasPermission("minebook.canUseLifts")) {
						if(e.getPlayer().isSneaking()) {
							return;
						}
						//left click is line 2 (down)
						//right click is line 1 (up)
						if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
							if(sign.getLine(2).equalsIgnoreCase("down")) {
								Location sloc = sign.getLocation();
								int y = (int) sloc.getY() - 1;
								for(int Y = y; Y > 0; Y--) {
									Block block = sloc.getWorld().getBlockAt((int) sloc.getX(), Y , (int) sloc.getZ());
									if(block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
										Sign s = (Sign) block.getState();
										int sx = s.getBlock().getX();
										int sy = s.getBlock().getY();
										int sz = s.getBlock().getZ();
										for(int sX = 0; sX < 2; sX++) {
											for(int sZ =0; sZ < 2; sZ++) {
												for(int sY = 0; sY < 3; sY++) {
													Block space = s.getWorld().getBlockAt(sx+sX, sy+sY, sz+sZ);
													if(!(space.getType() == Material.AIR || space.getType() == Material.SIGN_POST || space.getType() == Material.WALL_SIGN || space.getType() == Material.TORCH)) {
														e.getPlayer().sendMessage(ChatColor.RED + "this location has been destructed");
														return;
													}
												}
											}
										}
										if(s.getLine(0).contains("[lift]")) {
											if(!s.getLine(3).isEmpty()) {
												e.getPlayer().sendMessage(ChatColor.GREEN + " teleporting down to level: " + s.getLine(3));
												e.getPlayer().teleport(block.getLocation());
												return;
											} else {
												e.getPlayer().sendMessage(ChatColor.GREEN + "teleporting down");
												e.getPlayer().teleport(block.getLocation());
												return;	
											}
										}
									}
								}
							} else {
								e.getPlayer().sendMessage(ChatColor.RED + "warning this line is specified for down!");
								return;
							}
						} else if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
							if(sign.getLine(1).equalsIgnoreCase("up")) {
								Location sloc = sign.getLocation();
								int y = (int) sloc.getY() + 1;
								for(int Y = 0; Y < 128; Y++) {
									Block block = sloc.getWorld().getBlockAt((int) sloc.getX(), y+Y , (int) sloc.getZ());
									if(block.getType() == Material.SIGN || block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
										Sign s = (Sign) block.getState();
										int sx = s.getBlock().getX();
										int sy = s.getBlock().getY();
										int sz = s.getBlock().getZ();
										for(int sX = 0; sX < 2; sX++) {
											for(int sZ =0; sZ < 2; sZ++) {
												for(int sY = 0; sY < 3; sY++) {
													Block space = s.getWorld().getBlockAt(sx+sX, sy+sY, sz+sZ);
													if(!(space.getType() == Material.AIR || space.getType() == Material.SIGN_POST || space.getType() == Material.WALL_SIGN || space.getType() == Material.TORCH)) {
														e.getPlayer().sendMessage(ChatColor.RED + "this location has been destructed");
														return;
													}
												}
											}
										}
										if(s.getLine(0).contains("[lift]")) {
											if(!s.getLine(3).isEmpty()) {
												e.getPlayer().sendMessage(ChatColor.GREEN + "teleporting up to level: " + s.getLine(3));
												e.getPlayer().teleport(block.getLocation());
												return; 
											} else {
												e.getPlayer().sendMessage(ChatColor.GREEN + "teleporting up");
												e.getPlayer().teleport(block.getLocation());
												return;
											}
										}
									}
								}
							} else {
								e.getPlayer().sendMessage(ChatColor.RED + "warning this line is specified for up!");
								return;
							}
						}
					} else {
						e.getPlayer().sendMessage(ChatColor.RED + "you are not allowed to use these signs!");
					}
				}
			}
		}
	}
}
