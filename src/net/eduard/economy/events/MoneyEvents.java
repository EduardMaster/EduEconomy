
package net.eduard.economy.events;

import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.economy.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import net.eduard.api.lib.manager.EventsManager;

public class MoneyEvents extends EventsManager {

	@EventHandler
	public void event(PlayerMoveEvent e) {

	}
	@EventHandler
	public void event(PlayerJoinEvent e) {
		Main.getInstance().getManager().getCoins(new FakePlayer(e.getPlayer()));
	}

}
