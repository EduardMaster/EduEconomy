
package net.eduard.economy.menu;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.eduard.api.lib.modules.Mine;
import net.eduard.api.lib.manager.EventsManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.VaultAPI;

public class MenuMagnata extends EventsManager {

	private static final String title = "Magnata";

	@EventHandler
	public void controler(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
		Player p = (Player) e.getWhoClicked();
			if (e.getInventory().getName().equals(title)) {
				e.setCancelled(true);
			}

		}
	}

	@SuppressWarnings("deprecation")
	public static void open(Player p) {

		Inventory menu = Mine.newInventory(title, 3 * 9);
		
		String nome = MagnataGerador.getPlayerMagnata();
		Double valor = VaultAPI.getEconomy().getBalance(nome);
//		PlayerInformation info = new PlayerInformation(new FakePlayer(nome).getId());
		String rankAtualPrefix = "Nenhum";
//		Rankup rankAtual = info.getPlayerRank();
//		if (rankAtual != null) {
//			rankAtualPrefix = rankAtual.getGroupPrefix().replaceAll("&", "§");
//		}else {
//			p.sendMessage("§cFulano não tem rank porisso bugou");
//		}
//		fakeplayer = 
//		String nextRank = info.getNextRank().getGroupPrefix().replaceAll("&", "§");
		String grupoPrefix = VaultAPI.getPlayerGroupPrefix(nome);
		ItemStack head = Mine.newHead("§a§l" + nome, nome ,1, "",
				"§bDinheiro: §3" + Extra.formatMoney(valor), "§bGrupo: " + (grupoPrefix.isEmpty() ? "Nenhum" : grupoPrefix),
				"§bRank: §3" + rankAtualPrefix);
		menu.setItem(Mine.getPosition(2, 5), head);
		p.openInventory(menu);
//		Dinheiro: [money]
//				Grupo: [prefix]
//				Rank: [suffix]

//		maisRico = 

	}
	
}
