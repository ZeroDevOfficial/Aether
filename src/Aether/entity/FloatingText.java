package Aether.entity;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.EntityMetadata;
import cn.nukkit.item.Item;
import cn.nukkit.level.Location;
import cn.nukkit.network.protocol.AddPlayerPacket;
import cn.nukkit.network.protocol.RemoveEntityPacket;
import cn.nukkit.utils.TextFormat;

import java.util.UUID;

public class FloatingText {

    private Location pos;
    private String name;
    private String text;

    private long eid;
    private UUID uuid;

    public FloatingText(Location pos, String name) {
        this.setPos(pos);
        this.setName(name);
        this.setText(name);
        this.eid = Entity.entityCount++;
        this.uuid = UUID.randomUUID();
    }

    public Location getPos() {
        return this.pos;
    }

    private void setPos(Location pos) {
        if (pos != null) {
            this.pos = pos;
        }
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private int getId() {
        return (int) this.eid;
    }

    public void sendText(Player player) {
        if (player.getLevel() == getPos().getLevel()) {
            AddPlayerPacket pk = new AddPlayerPacket();
            pk.uuid = this.uuid;
            pk.username = getText();
            pk.entityRuntimeId = getId();
            pk.entityUniqueId = getId();
            pk.x = (float) getPos().x + 0.50F;
            pk.y = (float) getPos().y - 0.75F;
            pk.z = (float) getPos().z + 0.50F;
            pk.yaw = (float) 0;
            pk.pitch = (float) 0;
            long flags = ((1L << Entity.DATA_FLAG_CAN_SHOW_NAMETAG) | (1L << Entity.DATA_FLAG_ALWAYS_SHOW_NAMETAG) | (1L << Entity.DATA_FLAG_IMMOBILE));
            pk.metadata = new EntityMetadata()
                    .putLong(Entity.DATA_FLAGS, flags)
                    .putString(Entity.DATA_NAMETAG, TextFormat.BOLD + "" + TextFormat.AQUA + getText())
                    .putLong(Entity.DATA_LEAD_HOLDER_EID, -1)
                    .putFloat(Entity.DATA_SCALE, 0.00f); //zero causes problems on debug builds?//idk if that is still true
            pk.item = Item.get(0);
            player.dataPacket(pk);
        }
    }

    public void despawnFrom(Player player) {
        RemoveEntityPacket pk = new RemoveEntityPacket();
        pk.eid = this.eid;
        player.dataPacket(pk);
    }
}
