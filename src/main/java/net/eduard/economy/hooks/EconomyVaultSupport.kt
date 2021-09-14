package net.eduard.economy.hooks

import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.economy.EconomyResponse
import net.eduard.api.lib.modules.FakePlayer
import net.milkbowl.vault.economy.EconomyResponse.ResponseType
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomy
import org.bukkit.OfflinePlayer
import java.util.stream.Collectors

class EconomyVaultSupport : Economy {
    override fun bankBalance(bankName: String): EconomyResponse {
        val manager = EduEconomy.instance.manager
        val fake = FakePlayer(bankName)
        return EconomyResponse(0.0, manager.getCoins(fake), ResponseType.SUCCESS, null)
    }

    override fun bankDeposit(bankName: String, amount: Double): EconomyResponse {
        val manager = EduEconomy.instance.manager
        val fake = FakePlayer(bankName)
        return EconomyResponse(amount, manager.getCoins(fake), ResponseType.SUCCESS, null)
    }

    override fun bankHas(bankName: String, amount: Double): EconomyResponse {
        val manager = EduEconomy.instance.manager
        val fake = FakePlayer(bankName)
        return if (manager.getCoins(fake) >= amount) EconomyResponse(
            amount,
            manager.getCoins(fake),
            ResponseType.SUCCESS,
            null
        ) else EconomyResponse(amount, manager.getCoins(fake), ResponseType.FAILURE, null)
    }

    override fun bankWithdraw(bankName: String, amount: Double): EconomyResponse {
        val manager = EduEconomy.instance.manager
        val fake = FakePlayer(bankName)
        return EconomyResponse(amount, manager.getCoins(fake), ResponseType.SUCCESS, null)
    }


    override fun createBank(bankName: String, bankOwner: String): EconomyResponse {
        return EconomyResponse(0.0, 0.0, ResponseType.SUCCESS, null)
    }

    override fun createBank(bankName: String, player: OfflinePlayer): EconomyResponse {
        return EconomyResponse(0.0, 0.0, ResponseType.SUCCESS, null)
    }

    override fun createPlayerAccount(playerName: String): Boolean {
        return true
    }

    override fun createPlayerAccount(player: OfflinePlayer): Boolean {
        return true
    }

    override fun createPlayerAccount(playerName: String, worldName: String): Boolean {
        return true
    }

    override fun createPlayerAccount(player: OfflinePlayer, worldName: String): Boolean {
        return true
    }

    override fun currencyNamePlural(): String {
        return "Coins"
    }

    override fun currencyNameSingular(): String {
        return "Coin"
    }

    override fun deleteBank(bankName: String): EconomyResponse {
        val manager = EduEconomy.instance.manager
        val fake = FakePlayer(bankName)
        manager.currency.remove(fake)
        return EconomyResponse(0.0, 0.0, ResponseType.SUCCESS, null)
    }

    override fun depositPlayer(playerName: String, amount: Double): EconomyResponse {
        val manager = EduEconomy.instance.manager
        val fake = FakePlayer(playerName)
        manager.addCoins(fake, amount)
        return EconomyResponse(amount, manager.getCoins(fake), ResponseType.SUCCESS, null)
    }

    override fun depositPlayer(player: OfflinePlayer, amount: Double): EconomyResponse {
        val manager = EduEconomy.instance.manager
        val fake = FakePlayer(player)
        manager.addCoins(fake, amount)
        return EconomyResponse(amount, manager.getCoins(fake), ResponseType.SUCCESS, null)
    }

    override fun depositPlayer(playerName: String, wourldName: String, amount: Double): EconomyResponse {
        return depositPlayer(playerName, amount)
    }

    override fun depositPlayer(player: OfflinePlayer, wourldName: String, amount: Double): EconomyResponse {
        return depositPlayer(player, amount)
    }

    override fun format(amount: Double): String {
        return Extra.MONEY.format(amount)
    }

    override fun fractionalDigits(): Int {
        return 4
    }

    override fun getBalance(playerName: String): Double {
        val manager = EduEconomy.instance.manager
        val fake = FakePlayer(playerName)
        return manager.getCoins(fake)
    }

    override fun getBalance(player: OfflinePlayer): Double {
        val manager = EduEconomy.instance.manager
        val fake = FakePlayer(player)
        return manager.getCoins(fake)
    }

    override fun getBalance(playerName: String, worldName: String): Double {
        return getBalance(playerName)
    }

    override fun getBalance(player: OfflinePlayer, worldName: String): Double {
        return getBalance(player)
    }

    override fun getBanks(): List<String> {
        return EduEconomy.instance.manager
            .currency
            .keys
            .stream()
            .map { obj: FakePlayer -> obj.name }
            .collect(Collectors.toList())

    }

    override fun has(playerName: String, amount: Double): Boolean {
        return  EduEconomy.instance.manager.hasCoins(FakePlayer(playerName), amount)
    }

    override fun has(player: OfflinePlayer, amount: Double): Boolean {
        return  EduEconomy.instance.manager.hasCoins(FakePlayer(player), amount)
    }

    override fun has(playerName: String, worldName: String, amount: Double): Boolean {
        return  EduEconomy.instance.manager.hasCoins(FakePlayer(playerName), amount)
    }

    override fun has(player: OfflinePlayer, worldName: String, amount: Double): Boolean {
        return  EduEconomy.instance.manager.hasCoins(FakePlayer(player), amount)
    }

    override fun hasAccount(playerName: String): Boolean {
        return true
    }

    override fun hasAccount(player: OfflinePlayer): Boolean {
        return true
    }

    override fun hasAccount(playerName: String, worldName: String): Boolean {
        return true
    }

    override fun hasAccount(player: OfflinePlayer, worldName: String): Boolean {
        return true
    }

    override fun hasBankSupport(): Boolean {
        return true
    }

    override fun isBankMember(bankName: String, playerName: String): EconomyResponse {
        return EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun isBankMember(bankName: String, player: OfflinePlayer): EconomyResponse {
        return EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun isBankOwner(bankName: String, playerName: String): EconomyResponse {
        return EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun isBankOwner(bankName: String, player: OfflinePlayer): EconomyResponse {
        return EconomyResponse(0.0, 0.0, ResponseType.NOT_IMPLEMENTED, null)
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun withdrawPlayer(playerName: String, amount: Double): EconomyResponse {
        val manager = EduEconomy.instance.manager
        val fake = FakePlayer(playerName)
        manager.removeCoins(fake, amount)
        return EconomyResponse(amount, manager.getCoins(fake), ResponseType.SUCCESS, null)
    }

    override fun withdrawPlayer(player: OfflinePlayer, amount: Double): EconomyResponse {
        val manager = EduEconomy.instance.manager
        val fake = FakePlayer(player)
        manager.removeCoins(fake, amount)
        return EconomyResponse(amount, manager.getCoins(fake), ResponseType.SUCCESS, null)
    }

    override fun withdrawPlayer(playerName: String, worldName: String, amount: Double): EconomyResponse {
        return withdrawPlayer(playerName, amount)
    }

    override fun withdrawPlayer(player: OfflinePlayer, worldName: String, amount: Double): EconomyResponse {
        return withdrawPlayer(player, amount)
    }

    override fun getName(): String {
        return "EduEconomy"
    }
}