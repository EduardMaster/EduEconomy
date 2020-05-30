
package net.eduard.economy.listener;

import net.eduard.api.lib.game.FakePlayer;
import net.eduard.economy.EduEconomy;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import net.eduard.api.lib.manager.EventsManager;
import org.bukkit.event.player.PlayerQuitEvent;

public class MoneyEvents extends EventsManager {

    @EventHandler
    public void event(PlayerMoveEvent e) {

    }

    @EventHandler
    public void event(PlayerJoinEvent e) {
        EduEconomy.getInstance().createAccountIfNotExists(new FakePlayer(e.getPlayer()));

    }
    @EventHandler
    public void event(PlayerQuitEvent e) {
        EduEconomy.getInstance().saveAccount(new FakePlayer(e.getPlayer()));

    }

}
