package net.eduard.economy.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map.Entry;

import net.eduard.api.lib.manager.TimeManager;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.player.FakePlayer;
import net.eduard.economy.EduEconomy;
import net.eduard.economy.core.EconomyManager;

public class GeradorBackupBukkit extends TimeManager {
	public GeradorBackupBukkit() {
		setTime(60 * 5);
	}

	@Override
	public void run() { 
		EconomyManager m = EduEconomy.getInstance().getManager();
		StringBuilder stringBuilder = new StringBuilder();
		StringBuilder b = new StringBuilder();
		stringBuilder.append("INSERT INTO CONTAS VALUES ");
		boolean first = true;
		for (Entry<FakePlayer, Double> conta : m.getCurrency().entrySet()) {
			if (!first) {
				stringBuilder.append(", ");
			} else
				first = false;
			stringBuilder.append("(");
			String name = conta.getKey().getName();
			Double dinheiro = conta.getValue();
			stringBuilder.append("DEFAULT ,");
			stringBuilder.append("'" + name + "',");
			stringBuilder.append("'" + dinheiro + "')");
			b.append("" + name + " " + dinheiro);
		}
		SimpleDateFormat tempo = new SimpleDateFormat("MM-dd-hh-mm-ss");
		File pai = new File(EduEconomy.getInstance().getDataFolder(), "backups");
		pai.mkdirs();
		File file = new File(pai, "backup-" + tempo.format(Extra.getNow()) + ".txt");
		try {
			Files.write(file.toPath(), Arrays.asList(stringBuilder.toString()), Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		File dat = new File(EduEconomy.getInstance().getDataFolder(), "players.dat");
		try {
			Files.write(dat.toPath(), b.toString().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		EduEconomy.getInstance().getConfigs().set("backup", );
//		EduEconomy.getInstance().getConfigs().saveConfig();

	}

}
