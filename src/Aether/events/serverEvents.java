package Aether.events;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;

public class serverEvents implements Listener {

    @EventHandler
    public void weather(cn.nukkit.event.weather.WeatherChangeEvent event) {
        event.setCancelled(true);
    }
}
