package Aether.events;

import Aether.AetherPlayer;
import Aether.Main;
import Aether.form.form;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;

import java.net.InetSocketAddress;

public class gadgetEvents implements Listener {

    private Main getPlugin() {
        return Main.getInstance();
    }

    @EventHandler
    public void interact(cn.nukkit.event.player.PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Item item = event.getItem();
        if (item.hasCustomBlockData()) {
            if (item.getCustomBlockData().contains("HubItem")) {
                String customBlockData = item.getCustomBlockData().getString("HubItem");
                switch (customBlockData) {
                    case "profile":
                        player.showFormWindow(new form().profileWindow(player));
                        break;
                    case "games":
                        player.showFormWindow(new form().gamesWindow());
                        break;
                    case "lobbies":
                        player.showFormWindow(new form().lobbiesWindow(player));
                        break;
                    case "leaper":
                        if (player.y >= 140) {
                            player.sendPopup(TextFormat.RED + "Leaper height limit reached!");
                        } else {
                            double x = player.getDirectionVector().x;
                            double z = player.getDirectionVector().z;
                            player.knockBack(player, 0, x, z, 0.85);
                            player.sendPopup(TextFormat.GREEN + "Leaped!");
                        }
                        break;
                }
            }
        }
    }

    @EventHandler
    public void formRespond(cn.nukkit.event.player.PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        FormWindow window = event.getWindow();
        if (event.getResponse() == null) return;
        //Simple response
        if (window instanceof FormWindowSimple) {
            String title = ((FormWindowSimple) event.getWindow()).getTitle();
            String button = ((FormResponseSimple) event.getResponse()).getClickedButton().getText();
            if (!event.wasClosed()) {
                switch (title) {
                    case "Lobby Selector"://TODO add more lobbies
                        if (!getPlugin().getLobby().equals(button)) {
                            switch (button) {//doesnt work atm
                                case "Dev Lobby":
                                    player.transfer(new InetSocketAddress("aethernetwork.tk", 19132));
                                    break;
                                case "Lobby 1":
                                    player.transfer(new InetSocketAddress("aethernetwork.tk", 19132));
                                    break;
                            }
                        } else {
                            player.sendMessage(TextFormat.RED + "Silly your already in this lobby.");
                        }
                        break;
                    case "Your Profile":
                        switch (button) {
                            case "Settings.":
                                player.showFormWindow(new form().settingsWindow(player));
                                break;
                        }
                        break;
                }
            }
        }
        if (!event.wasClosed()) {
            if (window instanceof FormWindowCustom) {
                String title = ((FormWindowCustom) event.getWindow()).getTitle();
                switch (title) {
                    case "Settings.":
                        switch ((String) ((FormWindowCustom) event.getWindow()).getResponse().getResponse(0)) {
                            case "ant mode":
                                player.setScale(0.05F);
                                break;
                            case "0.50":
                                player.setScale(0.50F);
                                break;
                            case "1.00":
                                player.setScale(1.00F);
                                break;
                            case "1.50":
                                player.setScale(1.50F);
                                break;
                            case "giant mode":
                                player.setScale(5.00F);
                                break;
                        }
                        switch ((String) ((FormWindowCustom) event.getWindow()).getResponse().getResponse(1)) {
                            case "5 chunks":
                                player.setViewDistance(5);
                                break;
                            case "8 chunks":
                                player.setViewDistance(8);
                                break;
                        }
                        switch ((String) ((FormWindowCustom) event.getWindow()).getResponse().getResponse(2)) {
                            case "black":
                                ((AetherPlayer) player).setCape("black");
                                break;
                            case "gray":
                                ((AetherPlayer) player).setCape("gray");
                                break;
                            case "red":
                                ((AetherPlayer) player).setCape("red");
                                break;
                            case "blue":
                                ((AetherPlayer) player).setCape("blue");
                                break;
                            case "purple":
                                ((AetherPlayer) player).setCape("purple");
                                break;
                        }
                        break;
                }
            }
        }
    }
}
