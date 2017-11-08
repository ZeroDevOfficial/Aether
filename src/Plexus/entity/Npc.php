<?php

declare(strict_types=1);

namespace Plexus\entity;

use pocketmine\entity\Entity;
use pocketmine\entity\Human;
use pocketmine\level\Level;
use pocketmine\Player;

use Plexus\Main;

class Npc extends Human {

  public $defaultYaw = 0;
  public $defaultPitch = 0;

  public function __construct(\pocketmine\level\Level $level, \pocketmine\nbt\tag\CompoundTag $nbt){
    $skinData = new \Plexus\utils\Skin();
    $skin = new \pocketmine\entity\Skin('npc_skin', $skinData->getRandSkin(), "", "geometry.humanoid.custom", "");
    $this->setSkin($skin);
  parent::__construct($level, $nbt);
  }

  public function setDefaultYawPitch($yaw, $pitch){
  if($yaw != '' || $pitch != ''){
    $this->defaultYaw = $yaw;
    $this->defaultPitch = $pitch;
  } else {
    echo "you need to include both Yaw and Pitch in Npc.php\n";
   }
  }

  public function resetYawPitch(){
    $this->yaw = $this->defaultYaw;
    $this->pitch = $this->defaultYaw;
  }

  public function getNamedTag(){
    return $this->namedtag;
  }

  public function look($player){
    $pk = new \pocketmine\network\mcpe\protocol\MovePlayerPacket();
    $pk->entityRuntimeId = $this->getId();
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
    $this->resetYawPitch();
   }
   $pk->position = new \pocketmine\math\Vector3($this->x, $this->y + 1.62, $this->z);
   $player->dataPacket($pk);
  }
}