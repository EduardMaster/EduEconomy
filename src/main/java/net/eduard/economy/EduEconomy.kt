package net.eduard.economy

import net.eduard.api.server.EduardPlugin
import net.eduard.api.lib.modules.Mine
import net.eduard.api.lib.modules.VaultAPI
import net.eduard.economy.command.EconomyCommand
import net.eduard.economy.core.EconomyManager
import net.eduard.economy.core.EconomyUser
import net.eduard.economy.hooks.EconomyVaultSupport
import net.eduard.economy.listener.MoneyEvents
import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.bukkit.plugin.ServicePriority
import kotlin.concurrent.timer

class EduEconomy : EduardPlugin() {


    lateinit var manager: EconomyManager

    override fun onEnable() {
        isFree = true
        super.onEnable()
        instance = this
        MoneyEvents().register(this)
        EconomyCommand().register()
        reload()

        if (Mine.hasPlugin("FeatherBoard")) {
            log("Ativando suporte ao 'FeatherBoard' variavel %money")

        }
       syncTimer(60*20,60*20){
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
            val users = sqlManager.getAllData(EconomyUser::class.java)
            for (account in users) {
                manager.users.put(account.player, account)
                log("§aConta do " + account.name.toString() + " com saldo de " + account.amount
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