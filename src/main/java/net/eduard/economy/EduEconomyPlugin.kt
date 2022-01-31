package net.eduard.economy

import net.eduard.api.lib.kotlin.format
import net.eduard.api.lib.modules.VaultAPI
import net.eduard.api.server.*
import net.eduard.economy.command.EconomyCommand
import net.eduard.economy.core.EconomyManager
import net.eduard.economy.core.EconomyTransaction
import net.eduard.economy.core.EconomyUser
import net.eduard.economy.hooks.EconomyAPIImpl
import net.eduard.economy.hooks.EconomyVaultSupport
import net.eduard.economy.listener.EconomyListener
import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.bukkit.plugin.ServicePriority

class EduEconomyPlugin : EduardPlugin() {

    lateinit var manager: EconomyManager
    lateinit var api : EconomyAPIImpl
    override fun onEnable() {
        instance = this
        isFree = true
        super.onEnable()
        EconomyListener().register(this)
        EconomyCommand().registerCommand(this)
        reload()
        api = EconomyAPIImpl()
        api.registerAPI()
    }


    override fun save() {
        configs["economy"] = manager
        configs.saveConfig()
    }


    override fun reload() {
        configs.reloadConfig()
        messages.reloadConfig()
        storage.reloadConfig()
        messages.add("system-reload","§aSistema de Economia recarregado.")
        messages.add("cant-pay-self", "&cVocê não pode enviar dinheiro para sí próprio.");
        messages.add("money-add", "&aVocê adicionou %amount de dinheiro para o jogador %player.")
        messages.add("money-add-player", "&aFoi adicionado ao seu banco %amount pelo Jogador %player.")
        messages.add("money-set", "&aVocê definiu %amount de dinheiro para o jogador %player.")
        messages.add("money-remove", "&aVocê remevou %amount de dinheiro para o jogador %player.")
        messages.add("money-check", "&aSeu dinheiro é %amount.")
        messages.add("money-changed", "&aSeu dinheiro foi atualizado pelo jogador %player.")
        messages.add("money-need", "§cDinheiro insuficiente para poder pagar outro jogador.")
        messages.add("money-pay", "§aVocê pagou %amount para o %player.")
        messages.add("money-payment" ,"§aVocê recebeu %amount do %player.")
        messages.add("money-player-check", "§aO dinheiro do jogador %player é %amount.")
        messages.add("top-format-header", "&a&lRank de Dinheiro")
        messages.add("top-format", "&b%positionº &f%player: &a%amount.")
        messages.add("top-size", 10)
        messages.add("top-tycoon" , "§2§lMAGNATA")
        messages.add("tycoon-change", "§aO jogador §f%player §ase tornou o novo §b§lMagnata")
        messages.saveConfig()

        if (configs.contains("economy")) {
            manager = configs["economy", EconomyManager::class.java]
        } else {
            manager = EconomyManager()
            save()
        }
        Bukkit.getServicesManager().register(Economy::class.java,
            EconomyVaultSupport(), this, ServicePriority.Normal)
        VaultAPI.setupVault()
        if (sqlManager.hasConnection()) {
            sqlManager.createTable(EconomyUser::class.java)
            sqlManager.createTable<EconomyTransaction>()
            sqlManager.createReferences<EconomyTransaction>()
            val users = sqlManager.getAllData(EconomyUser::class.java)
            for (account in users) {
                val id = account.id
                manager.users[account.player] = account
                log("Conta ($id) §a" + account.name.toString() + " §f-> §2" + account.amount.format())
            }
            manager.reloadTop()
        }
    }

    override fun onDisable() {
        save()
        api.unregisterAPI()
        super.onDisable()
    }

    companion object {
        lateinit var instance: EduEconomyPlugin
    }




}