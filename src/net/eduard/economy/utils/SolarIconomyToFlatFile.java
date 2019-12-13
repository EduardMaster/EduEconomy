package net.eduard.economy.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import net.eduard.api.lib.manager.DBManager;

public class SolarIconomyToFlatFile {
	public static void main(String[] args) {
		DBManager db = new DBManager();
		db.setUseSQLite(true);
		db.setDatabase("F:\\Tudo\\Infos\\Trabalhos\\database.db");
		db.openConnection();
		File f = new File("F:\\\\Tudo\\\\Infos\\\\Trabalhos\\\\players.dat");
		try {
			f.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ResultSet query = db.select("select * from solaryeconomy");
		try {

			ResultSetMetaData metaTable = query.getMetaData();
			StringBuilder builder = new StringBuilder();
			for (int colunaId = 1; colunaId < metaTable.getColumnCount(); colunaId++) {
				System.out.println(metaTable.getColumnName(colunaId));
			}
			while (query.next()) {
				builder.append(query.getString("name"));
				builder.append(" ");
				builder.append(query.getDouble("valor"));
				builder.append('\n');
				StringBuilder b = new StringBuilder();
				for (int x = 1; x < metaTable.getColumnCount(); x++) {
					b.append(" ");
					b.append(query.getObject(x));
				}
				System.out.println(b.toString());
			}
			try {
				Files.write(f.toPath(), builder.toString().getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("foi");
		db.closeConnection();
	}
}
