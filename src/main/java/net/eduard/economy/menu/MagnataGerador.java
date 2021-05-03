package net.eduard.economy.menu;

import java.util.List;

import net.eduard.economy.EduEconomy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.eduard.api.lib.modules.VaultAPI;

public class MagnataGerador extends BukkitRunnable {

	private static String playerMagnata;

	public static String getPlayerMagnata() {
		return playerMagnata;
	}

	public static void setPlayerMagnata(String playerMagnata) {
		MagnataGerador.playerMagnata = playerMagnata;
	}

	public void run() {
		String maisRico = "Ninguem";
		double dinheiroAnterior = 0;
		for (Player player : Bukkit.getOnlinePlayers()) {

			double dinheiroNovo = VaultAPI.getEconomy().getBalance(player);
			if (dinheiroAnterior <= dinheiroNovo) {
				dinheiroAnterior = dinheiroNovo;
				maisRico = player.getName();
			}
		}
		
		if (!maisRico.equalsIgnoreCase(playerMagnata)) {
			List<String> messages = EduEconomy.getInstance().getMessages().getMessages("Novo Magnata");
			for (String linha : messages) {
				Bukkit.broadcastMessage(linha.replace("$player_magnata", maisRico));
			}
		}
		setPlayerMagnata(maisRico);

		EduEconomy.getInstance().getConfigs().set("Magnata", maisRico);
		EduEconomy.getInstance().getConfigs().saveConfig();

	}

}
