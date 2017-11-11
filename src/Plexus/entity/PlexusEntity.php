<?php

declare(strict_types=1);

namespace Plexus\entity;

use Plexus\Main;

use pocketmine\entity\Entity;
use pocketmine\nbt\tag\StringTag;
use pocketmine\nbt\tag\CompoundTag;
use pocketmine\nbt\tag\DoubleTag;
use pocketmine\nbt\tag\FloatTag;
use pocketmine\nbt\tag\ListTag;

use pocketmine\utils\TextFormat as C;

class PlexusEntity {

  private $plugin;
    
  public function __construct(Main $plugin){
    $this->plugin = $plugin;
  }

  public function getPlugin() : Main {
    return $this->plugin;
  }

  public function entityInit() : void {
    Entity::registerEntity(\Plexus\entity\FloatingNameTag::class, true);
    Entity::registerEntity(\Plexus\entity\Npc::class, true);
    $this->removeEntities();//if any didnt despawn they will be removed now
  foreach($this->getPlugin()->config()->floatingTextArrayData() as $key => $data){
    $pos = $this->position($data[0], $data[1], $data[2]);
    $this->addFloatingNameTag($pos, $data[3]);
  }
  foreach($this->getPlugin()->config()->npcArrayData() as $key => $data){
    $pos = $this->position($data[0], $data[1], $data[2]);
    $this->addNpc($pos, $data[3], $data[5], $data[5]);
  }
    $this->getPlugin()->getServer()->getLogger()->info(C::DARK_PURPLE .'Spawned Entities');
  }

  public function addFloatingNameTag(\pocketmine\level\Position $pos, string $text) : void {
    $nbt = new CompoundTag;
    $nbt->Pos = new ListTag("Pos", [
      new DoubleTag("", $pos->x + 0.5),
      new DoubleTag("", $pos->y + 2),
      new DoubleTag("", $pos->z + 0.5)
    ]);
    $nbt->Motion = new ListTag("Motion", [
      new DoubleTag("", 0),
      new DoubleTag("", 0),
      new DoubleTag("", 0)
    ]);
    $nbt->Rotation = new ListTag("Rotation", [
      new FloatTag("", 0),
      new FloatTag("", 0)
    ]);
    $entity = Entity::createEntity("FloatingNameTag", $pos->level, $nbt);
  if($entity instanceof \Plexus\entity\FloatingNameTag){
    $entity->setText($text);
    $entity->spawnToAll();
    $this->getPlugin()->ft[$text] = $entity;
   }
  }

  public function addNpc(\pocketmine\level\Position $pos, $yaw, $pitch, string $displayName) : void {
    $nbt = new CompoundTag;
    $nbt->Pos = new ListTag("Pos", [
      new DoubleTag("", $pos->x + 0.5),
      new DoubleTag("", $pos->y),
      new DoubleTag("", $pos->z + 0.5)
    ]);
    $nbt->Motion = new ListTag("Motion", [
      new DoubleTag("", 0),
      new DoubleTag("", 0),
      new DoubleTag("", 0)
    ]);
    $nbt->Rotation = new ListTag("Rotation", [
      new FloatTag("", intval($yaw)),
      new FloatTag("", intval($pitch))
    ]);
    $nbt->Name = new StringTag("Name", $displayName);
    $npc = Entity::createEntity("Npc", $pos->level, $nbt);
  if($npc instanceof \Plexus\entity\Npc){
    $npc->setNameTag($displayName);
    $npc->setDefaultYawPitch($yaw, $pitch);
    $npc->spawnToAll();
    $this->getPlugin()->npc[] = $npc;
   }
  }

  public function removeEntities() : void {
    $level = $this->getPlugin()->getServer()->getLevelByName($this->getPlugin()->config()->spawn());
  foreach($level->getEntities() as $entity){
  if($entity instanceof \Plexus\entity\Npc || $entity instanceof \Plexus\entity\FloatingNameTag){
    $level->removeEntity($entity);
   }
  }
    $this->getPlugin()->getServer()->getLogger()->info(C::RED .'Removed Entities!');
  }

  public function position(int $x, int $y, int $z){
    return new \pocketmine\level\Position($x, $y, $z, $this->getPlugin()->getServer()->getLevelByName($this->getPlugin()->config()->spawn()));
  }
}