package Aether.tasks;

import Aether.Main;
import cn.nukkit.scheduler.Task;

public class handlerTask extends Task {
    private Main plugin;

    public handlerTask(Main main) {
        setPlugin(main);
    }

    public Aether.Main getPlugin() {
        return plugin;
    }

    public void setPlugin(Aether.Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onRun(int tick) {
        for (handlerTask task : getPlugin().tasks.values()) {
            if (task != null) {
                task.tick();
            }
        }
    }

    //this is so I can extend this and @Override it, and run it
    public void tick() {
    }
}
