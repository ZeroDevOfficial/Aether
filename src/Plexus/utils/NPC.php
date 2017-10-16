<?php

namespace Plexus\utils;

use pocketmine\entity\Entity;
use pocketmine\item\Item;

class NPC {

  /* { var } | npc Spawn */
  private $x;
  private $y;
  private $z;
  private $yaw = -1;
  private $pitch = -1;

  /* { var } | name */ 
  private $name;


  /* { constructor } */
	public function __construct($x, $y, $z, $name){
    $this->eid = Entity::$entityCount++;
    $this->x = $x;
    $this->y = $y;
    $this->z = $z;
    $this->name = $name;
    $this->uuid = \pocketmine\utils\UUID::fromData();
  }

  /* { function } | returns Entity ID  */
  public function getEid(){
    return $this->eid;
  }

  /* { function } | spawn's npc to player */
  public function spawn($player){
    $pk = new \pocketmine\network\mcpe\protocol\AddPlayerPacket();
		$pk->uuid = $this->uuid;
		$pk->username = $this->name;
		$pk->entityRuntimeId = $this->eid;
    $pk->position = new \pocketmine\math\Vector3($this->x, $this->y, $this->z);
		$pk->item = Item::get(Item::AIR, 0, 0);
    $pk->pitch = $this->pitch;
    $pk->yaw = $this->yaw;
	  $pk->metadata = [
      Entity::DATA_FLAGS => [Entity::DATA_TYPE_LONG, 
      (1 << Entity::DATA_FLAG_CAN_SHOW_NAMETAG) | 
      (1 << Entity::DATA_FLAG_ALWAYS_SHOW_NAMETAG)],
      Entity::DATA_LEAD_HOLDER_EID => [Entity::DATA_TYPE_LONG, -1],
    ];

    $player->dataPacket($pk);
  }

  /* { function } | removes Npc */
  public function remove($player){
    $pk = new \pocketmine\network\mcpe\protocol\RemoveEntityPacket();
    $pk->entityUniqueId = $this->eid;

    $player->dataPacket($pk);
  }
}
