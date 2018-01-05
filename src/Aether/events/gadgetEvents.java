package Aether.events;

import Aether.AetherPlayer;
import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.item.Item;
import cn.nukkit.potion.Effect;
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
                        games.addButton(new ElementButton(TextFormat.GRAY + "Skywars | Not Available Yet!"));
                        player.showFormWindow(games);
                        break;
                    case "lobbies":
                        FormWindowSimple lobbies = new FormWindowSimple("Lobby Selector", "Select a lobby");
                        lobbies.addButton(new ElementButton("Cancel", new ElementButtonImageData("url", "https://i.imgur.com/PcJEnVy.png")));
                        lobbies.addButton(new ElementButton("Lobby 1"));
                        player.showFormWindow(lobbies);
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
                        switch (button) {
                            case "Lobby 1":
                                player.addEffect(new Effect(Effect.INVISIBILITY, "Invisibility", 0, 0, 0).setDuration(100000));

                                player.setImmobile(true);
                                player.setEnableClientCommand(true);

                                player.getInventory().clearAll();
                                player.teleport(getPlugin().getDefaultLevel().getSafeSpawn());

                                new Aether.tasks.sendHub(player, false, TextFormat.YELLOW + "Welcome to Lobby 1", TextFormat.AQUA + player.getName()).runTaskLater(getPlugin(), 20);
                                break;
                        }
                        break;
                }
            }
        }
    }
}
