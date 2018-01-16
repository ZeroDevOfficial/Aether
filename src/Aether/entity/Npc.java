package Aether.entity;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.item.Item;
import cn.nukkit.level.Location;
import cn.nukkit.network.protocol.AddPlayerPacket;
import cn.nukkit.network.protocol.PlayerListPacket;
import cn.nukkit.network.protocol.RemoveEntityPacket;
import cn.nukkit.utils.TextFormat;

import java.util.UUID;

public class Npc {

    private Location pos;
    private String name;
    private Skin skin;

    private long eid;
    private UUID uuid;

    public Npc(Location pos, String name, Skin skin) {
        this.setPos(pos);
        this.setName(name);
        this.setSkin(skin);

        this.eid = Entity.entityCount++;
        this.uuid = UUID.randomUUID();
    }

    private Location getPos() {
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

    private Skin getSkin() {
        return this.skin;
    }

    private void setSkin(Skin skin) {
        this.skin = skin;
    }

    public long getId() {
        return this.eid;
    }

    public void spawnTo(Player player) {
        if (player.getLevel() == getPos().getLevel()) {
            AddPlayerPacket pk = new AddPlayerPacket();
            pk.uuid = this.uuid;
            pk.username = TextFormat.BOLD + "" + TextFormat.YELLOW + getName();
            pk.entityRuntimeId = getId();
            pk.entityUniqueId = getId();
            pk.x = (float) getPos().x + 0.50F;
            pk.y = (float) getPos().y;
            pk.z = (float) getPos().z + 0.50F;
            pk.yaw = (float) getPos().getYaw();
            pk.offset = (int) getPos().getYaw();
            pk.pitch = (float) getPos().getPitch();
            pk.item = Item.get(0);
            player.dataPacket(pk);

            PlayerListPacket pk2 = new PlayerListPacket();
            pk2.type = PlayerListPacket.TYPE_ADD;
            pk2.entries = new PlayerListPacket.Entry[]{
                    new PlayerListPacket.Entry(this.uuid, this.getId(), "Npc: " + getName(), getSkin())
            };
            player.dataPacket(pk2);
        }
    }

    public void despawnFrom(Player player) {
        RemoveEntityPacket pk = new RemoveEntityPacket();
        pk.eid = this.eid;
        player.dataPacket(pk);

        PlayerListPacket pk2 = new PlayerListPacket();
        pk2.type = PlayerListPacket.TYPE_REMOVE;
        pk2.entries = new PlayerListPacket.Entry[]{
                new PlayerListPacket.Entry(this.uuid)
        };
        player.dataPacket(pk2);
    }
}
