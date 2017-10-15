<?php

namespace Plexus;

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
		$pk->yaw = $this->yaw;
		$pk->pitch = $this->pitch;
	  $pk->metadata = [
      Entity::DATA_FLAGS => [Entity::DATA_TYPE_LONG, 
      (1 << Entity::DATA_FLAG_CAN_SHOW_NAMETAG) | 
      (1 << Entity::DATA_FLAG_ALWAYS_SHOW_NAMETAG)],
      Entity::DATA_LEAD_HOLDER_EID => [Entity::DATA_TYPE_LONG, -1],
      Entity::DATA_SCALE => [Entity::DATA_TYPE_FLOAT, 1],
    ];

    $player->dataPacket($pk);
  }

  /* { function } | removes Npc */
  public function remove($player){
    $pk = new \pocketmine\network\mcpe\protocol\RemoveEntityPacket();
    $pk->entityUniqueId = $this->eid;

    $player->dataPacket($pk);
  }

  /* this function look was borrowed from onebone's npc plugin, some things have been changed */
  /* { function } | when a player moves the npc it will look at the player  */
	public function look($player, $plugin){
    $pk = new \pocketmine\network\mcpe\protocol\MovePlayerPacket();
    $pk->entityRuntimeId = $this->eid;
  if(round($player->getPosition()->distance(new \pocketmine\math\Vector3($this->x, $this->y, $this->z))) <= 5){
  if($this->yaw === -1 and $player !== null){
    $xdiff = $player->x - $this->x;
    $zdiff = $player->z - $this->z;
    $angle = atan2($zdiff, $xdiff);
    $pk->yaw = (($angle * 180) / M_PI) - 90;
  } else {
    $pk->yaw = $this->yaw;
  }
  if($this->pitch === -1 and $player !== null){
    $ydiff = $player->y - $this->y;
    $vec = new \pocketmine\math\Vector2($this->x, $this->z);
    $dist = $vec->distance($player->x, $player->z);
    $angle = atan2($dist, $ydiff);
    $pk->pitch = (($angle * 180) / M_PI) - 90;
  } else {
    $pk->pitch = $this->pitch;
   }
  } else {
    $pk->pitch = -1;
    $pk->bodyYaw = -1;    
  }
    $pk->position = new \pocketmine\math\Vector3($this->x, $this->y + 1.62, $this->z);
    $pk->bodyYaw = $pk->yaw;
		$player->dataPacket($pk);
	}
}
