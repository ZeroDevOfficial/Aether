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
    $pos = $this->position(856, 82, 1144);
    $this->addFloatingNameTag($pos, '');
  foreach($this->getPlugin()->config()->npcArrayData() as $key => $data){
    $pos = $this->position($data[0], $data[1], $data[2]);
    $this->addNpc($pos, $data[3]);
   }
  }

  public function addFloatingNameTag(\pocketmine\level\Position $pos, string $text) : void {
    $entity = Entity::createEntity("FloatingNameTag", $pos->level, new CompoundTag("", [
    new ListTag("Pos", [
      new DoubleTag("", $pos->x + 0.5),
      new DoubleTag("", $pos->y + 2),
      new DoubleTag("", $pos->z + 0.5)
    ]),
    new ListTag("Motion", [
      new DoubleTag("", 0),
      new DoubleTag("", 0),
      new DoubleTag("", 0)
    ]),
    new ListTag("Rotation", [
      new FloatTag("", 0),
      new FloatTag("", 0)
    ])]));
  if($entity instanceof \Plexus\entity\FloatingNameTag){
    $entity->setText($text);
    $entity->spawnToAll();
    $this->getPlugin()->entity['floatingText'] = $entity;
   }
  }

  public function addNpc(\pocketmine\level\Position $pos, string $name) : void {
    $npc = Entity::createEntity("Npc", $pos->level, new CompoundTag("", [
    new ListTag("Pos", [
      new DoubleTag("", $pos->x + 0.5),
      new DoubleTag("", $pos->y),
      new DoubleTag("", $pos->z + 0.5)
    ]),
    new ListTag("Motion", [
      new DoubleTag("", 0),
      new DoubleTag("", 0),
      new DoubleTag("", 0)
    ]),
    new ListTag("Rotation", [
      new FloatTag("", 0),
      new FloatTag("", 0)
    ])]));
  if($npc instanceof \Plexus\entity\Npc){
    $npc->setNameTag($name);
    $npc->spawnToAll();
    $this->getPlugin()->npc[$npc->getId()] = $npc;
   }
  }

  public function position(int $x, int $y, int $z){
    return new \pocketmine\level\Position($x, $y, $z, $this->getPlugin()->getServer()->getLevelByName($this->getPlugin()->config()->spawn()));
  }

  public function entitiesRemove(){
    $level = $this->getPlugin()->getServer()->getLevelByName($this->getPlugin()->config()->spawn());
  foreach($this->getPlugin()->npc as $id => $npc){
    $level->removeEntity($npc);
  }
    $level->removeEntity($this->getPlugin()->entity['floatingText']);

    $this->getPlugin()->getServer()->getLogger()->info(C::YELLOW .'Removed Entities');
  }
}