package Aether.events;

import Aether.Main;
import Aether.entity.Npc;
import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.server.DataPacketReceiveEvent;
import cn.nukkit.form.window.FormWindowModal;
import cn.nukkit.inventory.transaction.data.UseItemOnEntityData;
import cn.nukkit.network.protocol.InventoryTransactionPacket;

import java.util.Map;

public class entityEvents implements Listener {

    private Main plugin;

    public entityEvents(Main main) {
        this.setPlugin(main);
    }

    private Aether.Main getPlugin() {
        return plugin;
    }

    private void setPlugin(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onNpcHit(DataPacketReceiveEvent event) {
        if (event.getPacket() instanceof InventoryTransactionPacket) {
            InventoryTransactionPacket pk = (InventoryTransactionPacket) event.getPacket();
            if (pk.transactionType != InventoryTransactionPacket.TYPE_USE_ITEM_ON_ENTITY)
                return;
            Player player = event.getPlayer();
            UseItemOnEntityData entityData = (UseItemOnEntityData) pk.transactionData;
            for (Map.Entry<String, Npc> npc : getPlugin().npc.entrySet()) {
                if (entityData.entityRuntimeId == npc.getValue().getId()) {
                    if (npc.getValue().getName().contains("Test")) {
                        FormWindowModal form = new FormWindowModal("", "Game '" + npc.getValue().getName() + "' Coming Soon!", "Ok", "Cancel");
                        player.showFormWindow(form);
                    }
                }
            }
        }
    }
}
