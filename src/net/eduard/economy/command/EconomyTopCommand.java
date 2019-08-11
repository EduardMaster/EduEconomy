
package net.eduard.economy.command;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.economy.Main;

public class EconomyTopCommand extends CommandManager {

	public EconomyTopCommand() {
		super("top", "rank");
		setDescription("Verificar o top de dinheiro");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		new BukkitRunnable() {

			@Override
			public void run() {
				show(sender);
			}
		}.runTaskAsynchronously(getPlugin());

		return true;
	}

	public void show(CommandSender sender) {
		Map<FakePlayer, Double> currency = Main.getInstance().getManager().getCurrency();
		Stream<Entry<FakePlayer, Double>> streamOrdenada = currency.entrySet().stream()
				.sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
				.limit(Main.getInstance().getConfigs().getInt("top-size"));
		List<Entry<FakePlayer, Double>> lista = streamOrdenada.collect(Collectors.toList());
		sender.sendMessage(Main.getInstance().getConfigs().message("top-format-header"));
		int posicao = 1;
		for (Entry<FakePlayer, Double> entrada : lista) {
			sender.sendMessage(Main.getInstance().getConfigs().message("top-format")
					.replace("$player", entrada.getKey().getName())
					.replace("$amount", Extra.MONEY.format(entrada.getValue())).replace("$position", "" + posicao));
			posicao++;
		}
		
	}

}
