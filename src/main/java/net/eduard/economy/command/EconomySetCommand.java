package net.eduard.economy.command;

import net.eduard.economy.EduEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;
import org.jetbrains.annotations.NotNull;

public class EconomySetCommand extends CommandManager {

    public EconomySetCommand() {
        super("set", "setar", "define", "difinir");
        setUsage("/money set <jogador> <quantidade>");
        setDescription("Definir saldo para o jogador");
    }

    @Override
    public void command(@NotNull CommandSender sender, @NotNull String[] args) {
        if (args.length < 2) {
            sendUsage(sender);
            return;
        }
        FakePlayer fakeplayer = new FakePlayer(args[0]);
        Double valor = Extra.fromMoneyToDouble(args[1]);
        valor = Math.abs(valor);
        EduEconomy.getInstance().getManager().setCoins(fakeplayer, valor);
        if (args.length >= 3) {
            if (args[2] == ("-msg")) {
                return;
            }
        }
        sender.sendMessage(EduEconomy.getInstance().message("money-set")
                .replace("%player", fakeplayer.getName())
                .replace("%amount", Extra.formatMoney(valor)));

        fakeplayer
                .sendMessage(EduEconomy.getInstance().message("money-changed")
                        .replace("%player", sender.getName()));


    }


}
