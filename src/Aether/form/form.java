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
        ElementStepSlider scaleSteps = new ElementStepSlider("\nNote: changing scale is experiment.\nScale");
        if (player.isOp()) {
            scaleSteps.addStep("ant mode");
            scaleSteps.addStep("0.50");
            scaleSteps.addStep("1.00");
            scaleSteps.addStep("1.50");
            scaleSteps.addStep("giant mode");
            scaleSteps.setDefaultOptionIndex(2);
        } else {
            scaleSteps.addStep("0.50");
            scaleSteps.addStep("1.00");
            scaleSteps.addStep("1.50");
            scaleSteps.setDefaultOptionIndex(1);
        }

        window.addElement(scaleSteps);

        ElementStepSlider viewDistanceSteps = new ElementStepSlider("\nServer View Distance");
        viewDistanceSteps.addStep("5 chunks");
        viewDistanceSteps.addStep("8 chunks");
        viewDistanceSteps.setDefaultOptionIndex(1);
        window.addElement(viewDistanceSteps);

        ElementDropdown capeColor = new ElementDropdown("\nCape Color:");
        capeColor.addOption("black");
        capeColor.addOption("gray");
        capeColor.addOption("red");
        capeColor.addOption("blue");
        capeColor.addOption("purple");
        capeColor.setDefaultOptionIndex(0);
        window.addElement(capeColor);
        return window;
    }

    public FormWindowSimple gamesWindow() {
        FormWindowSimple window = new FormWindowSimple("Games", "Click a MiniGame");
        window.addButton(new ElementButton("Cancel", new ElementButtonImageData("url", "https://i.imgur.com/PcJEnVy.png")));
        window.addButton(new ElementButton(TextFormat.GRAY + "Skywars | Not Available Yet!"));
        return window;
    }

    public FormWindowSimple lobbiesWindow() {
        FormWindowSimple window = new FormWindowSimple("Lobby Selector", "Select a lobby");
        window.addButton(new ElementButton("Cancel", new ElementButtonImageData("url", "https://i.imgur.com/PcJEnVy.png")));
        window.addButton(new ElementButton("Lobby 1"));
        return window;
    }
}
