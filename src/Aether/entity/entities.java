package Aether.entity;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.entity.EntityHuman;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;

public class entities {
	
  public Aether.Main plugin;
  public int npcs = 0;
  
  public entities(Aether.Main main){
    this.setPlugin(main);
  }

  public Aether.Main getPlugin(){
    return plugin;
  }

  public void setPlugin(Aether.Main plugin){
    this.plugin = plugin;
  }

  public void addNpc(Player player){
    final CompoundTag nbt = new CompoundTag();
    nbt.putList(new ListTag<DoubleTag>("Pos")
      .add(new DoubleTag("", player.x))
      .add(new DoubleTag("", player.y))
      .add(new DoubleTag("", player.z)));
    nbt.putList(new ListTag<DoubleTag>("Motion")
      .add(new DoubleTag("", 0))
      .add(new DoubleTag("", 0))
      .add(new DoubleTag("", 0)));
    nbt.putList(new ListTag<FloatTag>("Rotation")
      .add(new FloatTag("", 0))
      .add(new FloatTag("", 0)));
    nbt.putBoolean("ishuman", true);
    final EntityHuman entity = (EntityHuman) Entity.createEntity("Human", player.chunk, nbt);
    entity.spawnTo(player);
    getPlugin().npcs[npcs] = (EntityHuman) entity;
    npcs++;
  }
}
