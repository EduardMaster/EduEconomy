package net.eduard.economy.core;

import net.eduard.api.lib.database.annotations.ColumnName;
import net.eduard.api.lib.database.annotations.ColumnPrimary;
import net.eduard.api.lib.database.annotations.ColumnSize;
import net.eduard.api.lib.database.annotations.ColumnUnique;

public class PlayerEconomyAccount {

    @ColumnPrimary
    private int id;
    @ColumnUnique
    @ColumnSize(16)
    @ColumnName("name")
    private String playerName;
    private double amount;


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
