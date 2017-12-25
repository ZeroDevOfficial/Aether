package Aether.events;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;

public class serverEvents implements Listener {

    private Aether.Main plugin;

    public serverEvents(Aether.Main main) {
        this.setPlugin(main);
    }

    public Aether.Main getPlugin() {
        return plugin;
    }

    public void setPlugin(Aether.Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void weather(cn.nukkit.event.weather.WeatherChangeEvent event) {
        event.setCancelled(true);
    }
}
