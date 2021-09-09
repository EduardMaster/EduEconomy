package net.eduard.economy.core

import net.eduard.api.lib.database.annotations.*
import net.eduard.api.lib.database.api.DatabaseElement
import net.eduard.economy.EduEconomy
import java.sql.Timestamp

@TableName("economy_trades")
class EconomyTransaction : DatabaseElement {

    @ColumnPrimary
    var id = 0L
    @ColumnRelation
    lateinit var user : EconomyUser
    @ColumnSize(200)
    var reason = "Ganho"
    var changed = 0.0


    var madeAt = Timestamp(System.currentTimeMillis())

    override val sqlManager = EduEconomy.instance.sqlManager

}