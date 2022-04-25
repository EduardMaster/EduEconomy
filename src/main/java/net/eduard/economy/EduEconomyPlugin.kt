package net.eduard.economy

import net.eduard.api.lib.kotlin.fake
import net.eduard.api.lib.kotlin.format
import net.eduard.api.lib.modules.Mine
import net.eduard.api.lib.modules.VaultAPI
import net.eduard.api.server.*
import net.eduard.economy.command.EconomyCommand
import net.eduard.economy.core.EconomyManager
import net.eduard.economy.core.EconomyTransaction
import net.eduard.economy.core.EconomyUser
import net.eduard.economy.hooks.EconomyAPIImpl
import net.eduard.economy.hooks.EconomyVaultSupport
import net.eduard.economy.listener.EconomyListener
import net.eduard.economy.task.GlowBuyLimitAndSellLimitTask
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
        onActivation()
        reload()
        api = EconomyAPIImpl()
        api.registerAPI()
    }

    override fun onActivation() {
        EconomyListener().register(this)
        EconomyCommand().registerCommand(this)
        val delayUpdateTop = 60 * 5L
        syncTimer(delayUpdateTop, delayUpdateTop){
            manager.updateTop()
        }
        Mine.addReplacer("buy_limit"){ manager.getAccount(it.fake).buyLimit.format() }
        Mine.addReplacer("sell_limit"){ manager.getAccount(it.fake).sellLimit.format() }
        GlowBuyLimitAndSellLimitTask().syncTimer()
    }


    override fun save() {
        configs["economy"] = manager
        configs.saveConfig()
    }


    override fun reload() {
        configs.reloadConfig()
        messages.reloadConfig()
        storage.reloadConfig()
        messages.add("money-discount-bonus-update","§aO Conta do jogador %player foi atualizada seus Desconto e Bonus também")
        messages.add("money-pay-invalid","§cA Quantidade definida não pode ser menor que 1.")
        messages.add("system-reload","§aSistema de Economia recarregado.")
        messages.add("cant-pay-self", "&cVocê não pode enviar dinheiro para sí próprio.");
        messages.add("money-reset-all","&aFoi resetado %amount Contas.")

        messages.add("buy-limit-add", "&aVocê adicionou %amount de Limite de Compra para o jogador %player.")
        messages.add("buy-limit-set", "&aVocê definiu %amount de Limite de Compra para o jogador %player.")
        messages.add("buy-limit-bonus-set", "&aVocê definiu Bonus de Limite de Compra a cada x Tempo para %amount")
        messages.add("sell-limit-bonus-set", "&aVocê definiu Bonus de Limite de Venda a cada x Tempo para %amount")
        messages.add("buy-limit-remove", "&aVocê remevou %amount de Limite de Compra para o jogador %player.")
        messages.add("buy-limit-changed", "&aSeu Limite de Compra foi atualizado pelo %player.")
        messages.add("buy-limit-check", "&aSeu Limite de Compra é %amount.")


        messages.add("sell-limit-add", "&aVocê adicionou %amount de Limite de Venda para o jogador %player.")
        messages.add("sell-limit-set", "&aVocê definiu %amount de Limite de Venda para o jogador %player.")
        messages.add("sell-limit-remove", "&aVocê remevou %amount de Limite de Venda para o jogador %player.")
        messages.add("buy-limit-changed", "&aSeu Limite de Venda foi atualizado pelo %player.")
        messages.add("sell-limit-check", "&aSeu Limite de Venda é %amount.")



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
            val stringBuilder = StringBuilder()
            var amount = 0
            for (account in users) {
                val id = account.id
                manager.users[account.player] = account
                if (amount<=100) {
                    stringBuilder.append("§f§a" + account.name.toString() + "($id) §f-> §2" + account.amount.format())
                    stringBuilder.append(", ")
                }
                amount++
            }
            log("Contas carregadas: $amount -> $stringBuilder")
            manager.updateTop()
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