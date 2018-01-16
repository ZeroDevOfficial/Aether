package Aether.form;

import Aether.AetherPlayer;
import cn.nukkit.Player;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.element.ElementButtonImageData;
import cn.nukkit.form.element.ElementDropdown;
import cn.nukkit.form.element.ElementStepSlider;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import cn.nukkit.utils.TextFormat;

import java.util.Arrays;

public class form {

    public FormWindowSimple profileWindow(Player player) {
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
        window.addButton(new ElementButton("Close.", new ElementButtonImageData("url", "https://i.imgur.com/PcJEnVy.png")));
        window.addButton(new ElementButton("Settings."));
        return window;
    }

    public FormWindowCustom settingsWindow(Player player) {
        FormWindowCustom window = new FormWindowCustom("Settings.");
        if (player.isOp()) {
            window.addElement(new ElementStepSlider("\nNote: changing scale is experiment.\nScale", Arrays.asList("ant mode", "0.50", "1.00", "1.50", "giant mode"), 2));
        } else {
            window.addElement(new ElementStepSlider("\nNote: changing scale is experiment.\nScale", Arrays.asList("0.50", "1.00", "1.50"), 1));
        }
        window.addElement(new ElementStepSlider("\nServer View Distance", Arrays.asList("5 chunks", "8 chunks"), 1));
        window.addElement(new ElementDropdown("\nCape Color:", Arrays.asList("black", "gray", "red", "blue", "purple"), 0));
        return window;
    }

    public FormWindowCustom serverSettingsWindow(Player player) {
        return null;
    }

    public FormWindowSimple gamesWindow() {
        FormWindowSimple window = new FormWindowSimple("Games", "Click a MiniGame");
        window.addButton(new ElementButton("Cancel", new ElementButtonImageData("url", "https://i.imgur.com/PcJEnVy.png")));
        window.addButton(new ElementButton(TextFormat.GRAY + "Skywars | Not Available Yet!"));
        return window;
    }

    public FormWindowSimple lobbiesWindow(Player player) {
        FormWindowSimple window = new FormWindowSimple("Lobby Selector", "Select a lobby");
        window.addButton(new ElementButton("Cancel", new ElementButtonImageData("url", "https://i.imgur.com/PcJEnVy.png")));
        if (player.isOp()) {//TODO
            window.addButton(new ElementButton("Dev Lobby"));
        } else {
            window.addButton(new ElementButton("Lobby 1"));
        }
        return window;
    }
}
