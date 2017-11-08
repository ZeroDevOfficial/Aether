<?php

declare(strict_types=1);

namespace Plexus\entity;

use pocketmine\entity\Entity;
use pocketmine\entity\Human;
use pocketmine\level\Level;
use pocketmine\Player;

use Plexus\Main;

class Npc extends Human {

  public function __construct(\pocketmine\level\Level $level, \pocketmine\nbt\tag\CompoundTag $nbt){
    $skinData = new \Plexus\utils\Skin();
    $skin = new \pocketmine\entity\Skin('npc_skin', $skinData->getRandSkin(), "", "geometry.humanoid.custom", "");
    $this->setSkin($skin);
  parent::__construct($level, $nbt);
  }
}