<?php

namespace Plexus\utils;

use pocketmine\entity\Entity;
use pocketmine\item\Item;

class NPC {

  /* { var } | name */ 
  private $name;

  /* { var } | message */ 
  //private $message;
  
  /* { var } | npc Spawn */
  public $x;
  public $y;
  public $z;

  /* { var } | skin */ 
  private $skin;

  /* { var } | yaw and pitch */
  private $yaw = 180;
  private $pitch = 0;

  /* { constructor } */
	public function __construct($name, $x, $y, $z, $skin){
    $this->name = $name;
  
    //$this->message = $message;

    $this->x = $x + 0.5;
    $this->y = $y;
    $this->z = $z + 0.5;

    $this->skin = $skin;
    
    $this->eid = Entity::$entityCount++;
    $this->uuid = \pocketmine\utils\UUID::fromRandom();
  }

  /* { function } | returns Entity ID  */
  public function getEid(){
    return $this->eid;
  }

  /* { function } | sends a message to the player */
  public function onInteract($player){
    return;//will add an inventory later, the send message thing seems bad fn
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
      Entity::DATA_FLAGS => [Entity::DATA_TYPE_LONG, (1 << Entity::DATA_FLAG_CAN_SHOW_NAMETAG) | (1 << Entity::DATA_FLAG_ALWAYS_SHOW_NAMETAG)],
      Entity::DATA_LEAD_HOLDER_EID => [Entity::DATA_TYPE_LONG, -1],
      Entity::DATA_SCALE => [Entity::DATA_TYPE_FLOAT, 1],
    ];

    $player->dataPacket($pk);

    $pk = new \pocketmine\network\mcpe\protocol\PlayerSkinPacket;
    $pk->uuid = $this->uuid;
    $pk->skin = new \pocketmine\entity\Skin("", $this->skin);
    $player->dataPacket($pk);
  }

  /* { function } | removes Npc */
  public function remove($player){
    $pk = new \pocketmine\network\mcpe\protocol\RemoveEntityPacket();
    $pk->entityUniqueId = $this->eid;

    $player->dataPacket($pk);
  }

  // code from fengberd helped me fix this.
  // https://github.com/fengberd/FNPC/blob/master/src/FNPC/npc/NPC.php#L171
  public function look($player){
    $pk = new \pocketmine\network\mcpe\protocol\MovePlayerPacket();
    $pk->entityRuntimeId = $this->eid;
  if(round($player->getPosition()->distance(new \pocketmine\math\Vector3($this->x, $this->y, $this->z))) <= 10){
    $x = $this->x - $player->x;
    $y = $this->y - $player->y;
    $z = $this->z - $player->z;
  if(sqrt($x * $x + $z * $z) == 0 || sqrt($x * $x + $z * $z + $y * $y) == 0){
    return true;
  }
    $yaw = asin($x / sqrt($x * $x + $z * $z)) / 3.14 * 180;
    $pitch = round(asin($y / sqrt($x * $x + $z * $z + $y * $y)) / 3.14 * 180);
  if($z > 0){
    $yaw =- $yaw + 180;
  }
    $pk->yaw = $yaw;
    $pk->headYaw = $yaw;
    $pk->pitch = $pitch;
  } else {
    $pk->yaw = $this->yaw;
    $pk->headYaw = $this->yaw;
    $pk->pitch = $this->pitch;
   }
   $pk->position = new \pocketmine\math\Vector3($this->x, $this->y + 1.62, $this->z);
   $player->dataPacket($pk);
  }
}
