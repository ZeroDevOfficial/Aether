package Aether.events;

import Aether.AetherPlayer;
import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.utils.TextFormat;

public class gadgetEvents implements Listener {

    private Main plugin;

    public gadgetEvents(Main main) {
        setPlugin(main);
    }

    private Aether.Main getPlugin() {
        return plugin;
    }

    private void setPlugin(Main plugin) {
        this.plugin = plugin;
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
                        FormWindowSimple window = new FormWindowSimple("Your Profile",
                                TextFormat.GRAY + "---------------------------------\n" +
                                        TextFormat.YELLOW + "Display Name: " + TextFormat.AQUA + player.getDisplayName() + "\n" +
                                        TextFormat.GRAY + "\n" +
                                        TextFormat.YELLOW + "Kills: " + TextFormat.AQUA + ((AetherPlayer) player).kills + "\n" +
                                        TextFormat.YELLOW + "Deaths: " + TextFormat.AQUA + ((AetherPlayer) player).deaths + "\n" +
                                        TextFormat.GRAY + "\n" +
                                        TextFormat.YELLOW + "Last Login: " + TextFormat.AQUA + ((AetherPlayer) player).lastLogin + "\n" +
                                        TextFormat.GRAY + "---------------------------------\n"
                        );
                        window.addButton(new ElementButton("Close."));
                        player.showFormWindow(window);
                        break;
                    case "games":
                        FormWindowSimple games = new FormWindowSimple("Games", "Click a MiniGame");
                        games.addButton(new ElementButton("Cancel", new ElementButtonImageData("url", "https://i.imgur.com/PcJEnVy.png")));
                        games.addButton(new ElementButton(TextFormat.GRAY + "Skywars | Not Available Yet!", new ElementButtonImageData("url", "https://i.imgur.com/SfYBMNl.png")));
                        player.showFormWindow(games);
                        break;
                    case "leaper":
                        double x = player.getDirectionVector().x;
                        double z = player.getDirectionVector().z;
                        player.knockBack(player, 0, x, z, 0.85);
                        player.sendPopup(TextFormat.GREEN + "Leaped!");
                        break;
                }
            }
        }
    }
}
