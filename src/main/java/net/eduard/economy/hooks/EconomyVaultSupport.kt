package net.eduard.economy.hooks

import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.economy.EconomyResponse
import net.eduard.api.lib.modules.FakePlayer
import net.milkbowl.vault.economy.EconomyResponse.ResponseType
import net.eduard.api.lib.modules.Extra
import net.eduard.economy.EduEconomyPlugin
import org.bukkit.OfflinePlayer
import java.util.stream.Collectors

class EconomyVaultSupport : Economy {
    override fun bankBalance(bankName: String): EconomyResponse {
        val manager = EduEconomyPlugin.instance.manager
        val fakePlayer = FakePlayer(bankName)
        return EconomyResponse(0.0, manager.getCoins(fakePlayer), ResponseType.SUCCESS, null)
    }

    override fun bankDeposit(bankName: String, amount: Double): EconomyResponse {
        val manager = EduEconomyPlugin.instance.manager
        val fake = FakePlayer(bankName)
        return EconomyResponse(amount, manager.getCoins(fake), ResponseType.SUCCESS, null)
    }

    override fun bankHas(bankName: String, amount: Double): EconomyResponse {
        val manager = EduEconomyPlugin.instance.manager
        val fakePlayer = FakePlayer(bankName)
        return if (manager.getCoins(fakePlayer) >= amount) EconomyResponse(
            amount,
            manager.getCoins(fakePlayer),
            ResponseType.SUCCESS,
            null
        ) else EconomyResponse(amount, manager.getCoins(fakePlayer), ResponseType.FAILURE, null)
    }

    override fun bankWithdraw(bankName: String, amount: Double): EconomyResponse {
        val manager = EduEconomyPlugin.instance.manager
        val fakePlayer = FakePlayer(bankName)
        return EconomyResponse(amount, manager.getCoins(fakePlayer), ResponseType.SUCCESS, null)
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
        val manager = EduEconomyPlugin.instance.manager
        val fakePlayer = FakePlayer(bankName)
        manager.currency.remove(fakePlayer)
        return EconomyResponse(0.0, 0.0, ResponseType.SUCCESS, null)
    }

    override fun depositPlayer(playerName: String, amount: Double): EconomyResponse {
        val manager = EduEconomyPlugin.instance.manager
        val fakePlayer = FakePlayer(playerName)
        manager.addCoins(fakePlayer, amount)
        return EconomyResponse(amount, manager.getCoins(fakePlayer), ResponseType.SUCCESS, null)
    }

    override fun depositPlayer(player: OfflinePlayer, amount: Double): EconomyResponse {
        val manager = EduEconomyPlugin.instance.manager
        val fakePlayer = FakePlayer(player)
        manager.addCoins(fakePlayer, amount)
        return EconomyResponse(amount, manager.getCoins(fakePlayer), ResponseType.SUCCESS, null)
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
        val manager = EduEconomyPlugin.instance.manager
        val fakePlayer = FakePlayer(playerName)
        return manager.getCoins(fakePlayer)
    }

    override fun getBalance(player: OfflinePlayer): Double {
        val manager = EduEconomyPlugin.instance.manager
        val fakePlayer = FakePlayer(player)
        return manager.getCoins(fakePlayer)
    }

    override fun getBalance(playerName: String, worldName: String): Double {
        return getBalance(playerName)
    }

    override fun getBalance(player: OfflinePlayer, worldName: String): Double {
        return getBalance(player)
    }

    override fun getBanks(): List<String> {
        return EduEconomyPlugin.instance.manager
            .currency
            .keys
            .stream()
            .map { fakePlayer: FakePlayer -> fakePlayer.name }
            .collect(Collectors.toList())

    }

    override fun has(playerName: String, amount: Double): Boolean {
        return  EduEconomyPlugin.instance.manager.hasCoins(FakePlayer(playerName), amount)
    }

    override fun has(player: OfflinePlayer, amount: Double): Boolean {
        return  EduEconomyPlugin.instance.manager.hasCoins(FakePlayer(player), amount)
    }

    override fun has(playerName: String, worldName: String, amount: Double): Boolean {
        return  EduEconomyPlugin.instance.manager.hasCoins(FakePlayer(playerName), amount)
    }

    override fun has(player: OfflinePlayer, worldName: String, amount: Double): Boolean {
        return  EduEconomyPlugin.instance.manager.hasCoins(FakePlayer(player), amount)
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
        val manager = EduEconomyPlugin.instance.manager
        val fakePlayer = FakePlayer(playerName)
        manager.removeCoins(fakePlayer, amount)
        return EconomyResponse(amount, manager.getCoins(fakePlayer), ResponseType.SUCCESS, null)
    }

    override fun withdrawPlayer(player: OfflinePlayer, amount: Double): EconomyResponse {
        val manager = EduEconomyPlugin.instance.manager
        val fakePlayer = FakePlayer(player)
        manager.removeCoins(fakePlayer, amount)
        return EconomyResponse(amount, manager.getCoins(fakePlayer), ResponseType.SUCCESS, null)
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