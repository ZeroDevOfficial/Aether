package Aether.entity;

import Aether.Main;
import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.data.Skin;
import cn.nukkit.item.Item;
import cn.nukkit.level.Location;
import cn.nukkit.math.Vector3;
import cn.nukkit.network.protocol.AddPlayerPacket;
import cn.nukkit.network.protocol.MovePlayerPacket;
import cn.nukkit.network.protocol.PlayerSkinPacket;
import cn.nukkit.network.protocol.RemoveEntityPacket;
import cn.nukkit.utils.TextFormat;

import java.io.File;
import java.util.UUID;

public class Npc {

    private Main plugin;
    private Location pos;
    private String name;
    private String skin;

    private long eid;
    private UUID uuid;

    public Npc(Main main, Location pos, String name, String skin) {
        setPlugin(main);
        this.setPos(pos);
        this.setName(name);
        this.setSkin(skin);
        eid = Entity.entityCount++;
        uuid = UUID.randomUUID();
    }

    private Aether.Main getPlugin() {
        return plugin;
    }

    private void setPlugin(Main plugin) {
        this.plugin = plugin;
    }

    public void setPos(Location pos) {
        if (pos != null) {
            this.pos = pos;
        }
    }

    public Location getPos() {
        return this.pos;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public String getSkin() {
        return this.skin;
    }

    public void spawnTo(Player player) {//need to fix issues with this before I will use it
        if (player.getLevel() == getPos().getLevel()) {
            AddPlayerPacket pk = new AddPlayerPacket();
            pk.uuid = this.uuid;
            pk.username = this.name;
            pk.entityRuntimeId = this.eid;
            pk.entityUniqueId = this.eid;
            pk.x = (float) getPos().x + 0.50F;
            pk.y = (float) getPos().y;
            pk.z = (float) getPos().z + 0.50F;
            pk.yaw = (float) getPos().getYaw();
            pk.pitch = (float) getPos().getPitch();
            pk.item = Item.get(0);
            player.dataPacket(pk);

            PlayerSkinPacket skin = new PlayerSkinPacket();
            skin.uuid = this.uuid;
            skin.skin = new Skin(new File(getPlugin().getDataFolder() + "/skins/" + getSkin() + ".png"));
            skin.skinName = "skin";
            skin.serializeName = "";
            skin.geometryModel = "Steve";
            skin.geometryData = "";
            player.dataPacket(skin);
        } else {
            getPlugin().info(TextFormat.RED + "[Npc Error] Could not spawn npc, Player and Npc are not in the same world!");
        }
    }

    public void seePlayer(Player player) {
        if (player.getLevel() != getPos().getLevel()) return;
        MovePlayerPacket pk = new MovePlayerPacket();
        pk.eid = this.eid;
        if (Math.round(player.getPosition().distance(new Vector3(getPos().x, getPos().y, getPos().z))) <= 10) {
            double x = getPos().x - player.x, y = getPos().y - player.y, z = getPos().z - player.z;
            double yaw = Math.asin(x / Math.sqrt(x * x + z * z)) / 3.14 * 180, pitch = Math.round(Math.asin(y / Math.sqrt(x * x + z * z + y * y)) / 3.14 * 180);
            if (z > 0) {
                yaw = -yaw + 180;
            }
            pk.yaw = (float) yaw;
            pk.headYaw = (float) yaw;
            pk.pitch = (float) pitch;
        } else {
            pk.yaw = (float) getPos().yaw;
            pk.headYaw = (float) getPos().yaw;
            pk.pitch = (float) getPos().pitch;
        }
        pk.x = (float) (getPos().x + 0.50F);
        pk.y = (float) (getPos().y + 1.62F);
        pk.z = (float) (getPos().z + 0.50F);
        player.dataPacket(pk);
    }

    public void despawnFrom(Player player) {
        RemoveEntityPacket pk = new RemoveEntityPacket();
        pk.eid = this.eid;
        player.dataPacket(pk);
    }
}
