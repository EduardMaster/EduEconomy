package net.eduard.economy

import net.eduard.api.lib.kotlin.format
import net.eduard.api.server.EduardPlugin
import net.eduard.api.lib.modules.Mine
import net.eduard.api.lib.modules.VaultAPI
import net.eduard.economy.command.EconomyCommand
import net.eduard.economy.core.EconomyManager
import net.eduard.economy.core.EconomyTransaction
import net.eduard.economy.core.EconomyUser
import net.eduard.economy.hooks.EconomyVaultSupport
import net.eduard.economy.listener.MoneyListener
import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.bukkit.plugin.ServicePriority

class EduEconomy : EduardPlugin() {

    lateinit var manager: EconomyManager

    override fun onEnable() {
        isFree = true
        super.onEnable()
        instance = this
        MoneyListener().register(this)
        EconomyCommand().registerCommand(this)
        reload()

        if (Mine.hasPlugin("FeatherBoard")) {
            log("Ativando suporte ao 'FeatherBoard' variavel %money")

        }
        syncTimer(60 * 20L, 60 * 20L) {
            manager.reloadTop()
        }


    }


    override fun save() {
        configs["economy"] = manager
        configs.saveConfig()
    }


    override fun reload() {
        configs.reloadConfig()
        messages.reloadConfig()
        storage.reloadConfig()
        messages.add("cant-pay-self", "&cVocê não pode enviar dinheiro para sí próprio.");
        messages.add("money-add", "&aVocê adicionou %amount de dinheiro para o jogador %player")
        messages.add("money-add-player", "&aFoi adicionado ao seu banco %amount pelo Jogador %player")
        messages.add("money-set", "&aVocê definiu %amount de dinheiro para o jogador %player")
        messages.add("money-remove", "&aVocê remevou %amount de dinheiro para o jogador %player")
        messages.add("money-check", "&aSeu dinheiro é %amount")
        messages.add("money-changed", "&aSeu dinheiro foi atualizado pelo jogador %player")
        messages.add("money-need", "§cDinheiro insuficiente para poder pagar outro jogador.")
        messages.add("money-pay", "§aVocê pagou %amount para o %player")
        messages.add("money-payment" ,"§aVocê recebeu %amount do %player")
        messages.add("money-player-check", "§aO dinheiro do jogador %player é %amount")
        messages.add("top-format-header", "&a&lRank de Dinheiro")
        messages.add("top-format", "&b%positionº &f%player: &a%amount")
        messages.add("top-size", 10)
        messages.saveConfig()

        if (configs.contains("economy")) {
            manager = configs["economy", EconomyManager::class.java]
        } else {
            manager = EconomyManager()
            save()
        }
        Bukkit.getServicesManager().register(
            Economy::class.java,
            EconomyVaultSupport(), this, ServicePriority.Normal
        )
        VaultAPI.setupVault()
        if (sqlManager.hasConnection()) {
            sqlManager.createTable(EconomyUser::class.java)
            sqlManager.createTable<EconomyTransaction>()
            sqlManager.createReferences<EconomyTransaction>()
            val users = sqlManager.getAllData(EconomyUser::class.java)
            for (account in users) {
                manager.users[account.player] = account
                log(
                    "Conta §a" + account.name.toString() + " §f-> §2" + account.amount.format()
                )
            }
            manager.reloadTop()
        }
    }

    override fun onDisable() {
        save()
        super.onDisable()
    }

    companion object {
        @JvmStatic
        lateinit var instance: EduEconomy
    }




}