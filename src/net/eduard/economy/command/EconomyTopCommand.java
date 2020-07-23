
package net.eduard.economy.command;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.eduard.economy.EduEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.game.FakePlayer;

public class EconomyTopCommand extends CommandManager {

    public EconomyTopCommand() {
        super("top", "rank");
        setDescription("Verificar o top de dinheiro");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        show(sender);
        return true;
    }

    public void show(CommandSender sender) {
        Map<FakePlayer, Double> currency = EduEconomy.getInstance().getManager().getCurrency();
        Stream<Entry<FakePlayer, Double>> streamOrdenada = currency.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(EduEconomy.getInstance().getMessages().getInt("top-size"));
        List<Entry<FakePlayer, Double>> lista = streamOrdenada.collect(Collectors.toList());
        sender.sendMessage(EduEconomy.getInstance().getMessages().message("top-format-header"));
        int posicao = 1;
        for (Entry<FakePlayer, Double> entrada : lista) {
            sender.sendMessage(EduEconomy.getInstance().getMessages().message("top-format")
                    .replace("$player", entrada.getKey().getName())
                    .replace("$amount", Extra.formatMoney(entrada.getValue())).replace("$position", "" + posicao));
            posicao++;
        }

    }

}
