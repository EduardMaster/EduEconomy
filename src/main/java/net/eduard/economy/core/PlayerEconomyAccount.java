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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerEconomyAccount that = (PlayerEconomyAccount) o;

        return playerName.equalsIgnoreCase(that.playerName);
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


}
