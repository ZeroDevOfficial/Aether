package Aether.tasks;

import Aether.Main;
import cn.nukkit.scheduler.Task;

public class handlerTask extends Task {

    private Main getPlugin() {
        return Main.getInstance();
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
        return;
    }
}
