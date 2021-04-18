package net.eduard.economy.command;

import net.eduard.economy.EduEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;
import org.jetbrains.annotations.NotNull;

public class EconomyRemoveCommand extends CommandManager {

    public EconomyRemoveCommand() {
        super("remove", "take", "tirar", "remover");
        setUsage("/money remover <jogador> <quantidade>");
        setDescription("Remover saldo do jogador");
    }

    @Override
    public void command(@NotNull CommandSender sender, @NotNull String[] args) {
        if (args.length < 2) {
            sendUsage(sender);
            return;
        }
        FakePlayer fakeplayer = new FakePlayer(args[0]);
        double valor = Extra.fromMoneyToDouble(args[1]);
        valor = Math.abs(valor);
        EduEconomy.getInstance().getManager().removeCoins(fakeplayer, valor);
        if (args.length >= 3) {
            if (args[2].equals("-msg")) {
                return;
            }
        }
        sender.sendMessage(EduEconomy.getInstance().message("money-remove")
                .replace("%player", fakeplayer.getName())
                .replace("%amount", Extra.formatMoney(valor)));
        fakeplayer
                .sendMessage(EduEconomy.getInstance().message("money-changed")
                        .replace("%player", sender.getName()));


    }


}
