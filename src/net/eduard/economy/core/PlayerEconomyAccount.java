package net.eduard.economy.core;

import net.eduard.api.lib.database.annotations.*;

import java.util.Objects;

@TableName("players_coins")
public class PlayerEconomyAccount {

    @ColumnPrimary
    private int id;
    @ColumnUnique
    @ColumnSize(16)
    @ColumnName("name")
    private String playerName;
    private double amount;
    transient boolean needUpdate;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerEconomyAccount account = (PlayerEconomyAccount) o;
        return Double.compare(account.amount, amount) == 0 &&
                Objects.equals(playerName, account.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName.toLowerCase());
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
        needUpdate = true;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void removeAmount(double amount) {
        setAmount(getAmount() - amount);
    }

    public void addAmount(double amount) {
        setAmount(getAmount() + amount);
    }

    public boolean needUpdate() {
        return needUpdate;
    }

    public void setNeedUpdate(boolean update) {
        needUpdate = update;
    }
}
