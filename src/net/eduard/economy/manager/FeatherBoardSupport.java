package net.eduard.economy.manager;

import be.maximvdw.featherboard.api.PlaceholderAPI;
import be.maximvdw.featherboard.api.PlaceholderAPI.PlaceholderRequestEvent;
import be.maximvdw.featherboard.api.PlaceholderAPI.PlaceholderRequestEventHandler;
import net.eduard.api.lib.modules.Extra;
import net.eduard.api.lib.modules.FakePlayer;
import net.eduard.economy.Main;

@SuppressWarnings("deprecation")
public class FeatherBoardSupport {

	public FeatherBoardSupport() {
		PlaceholderAPI.registerPlaceholder("dinheiro", new PlaceholderRequestEventHandler() {

			@Override
			public String onPlaceholderRequest(PlaceholderRequestEvent request) {
				try {
					if (request.getPlayer() != null) {
						return Extra.formatMoney2(
								Main.getInstance().getManager().getCoins(new FakePlayer(request.getPlayer())));
					}
					if (request.getOfflinePlayer() != null) {
						return Extra.formatMoney2(
								Main.getInstance().getManager().getCoins(new FakePlayer(request.getOfflinePlayer())));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				return "0";
			}
		});
	}

}
