package net.eduard.economy.command;

import net.eduard.economy.EduEconomy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.eduard.api.lib.modules.Mine;
import net.eduard.api.lib.manager.CommandManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;
import org.jetbrains.annotations.NotNull;

public class EconomyPayCommand extends CommandManager {

    public EconomyPayCommand() {
        super("pay", "pagar");
        setUsage("/money pagar <jogador> <quantidade>");
        setDescription("Pagar o jogador");

    }

    @Override
    public void playerCommand(@NotNull Player player, @NotNull String[] args) {
        if (args.length < 2) {
            sendUsage(player);
            return;
        }
        String alvonome = args[0];
        FakePlayer alvoConta = new FakePlayer(alvonome);
        FakePlayer playerConta = new FakePlayer(player);
        double quantidade = Extra.fromMoneyToDouble(args[1]);
        quantidade = Math.abs(quantidade);
        if (alvoConta.equals(playerConta)) {
            player.sendMessage(EduEconomy.getInstance().message("cant-pay-self"));
            return;
        }
        if (EduEconomy.getInstance().getManager().hasCoins(playerConta, quantidade)) {
            EduEconomy.getInstance().getManager().addCoins(alvoConta, quantidade);
            EduEconomy.getInstance().getManager().removeCoins(playerConta, quantidade);
            player.sendMessage(EduEconomy.getInstance().message("money-pay").
                    replace("%amount", Extra.formatMoney(quantidade)).
                    replace("%player", alvonome));

        } else {
            player.sendMessage(EduEconomy.getInstance().message("money-need"));
        }


    }


}
