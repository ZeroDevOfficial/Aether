<?php

declare(strict_types=1);

namespace Plexus\entity;

use pocketmine\Player;
use pocketmine\item\Item;
use pocketmine\entity\Entity;

class FloatingNameTag extends Entity {

  protected $text;

  protected function initEntity(){
  parent::initEntity();
    $this->setNameTagVisible(true);
    $this->setNameTagAlwaysVisible(true);
    $this->setScale(0.01);
  }

  public function getText() : string {
    return $this->text;
  }

  public function setText(string $text) : void {
    $this->text = $text;
    $this->setNameTag($text);
  }

  public function spawnTo(Player $player) : void {
    $pk = new \pocketmine\network\mcpe\protocol\AddPlayerPacket();
    $pk->uuid = \pocketmine\utils\UUID::fromRandom();
    $pk->username = "";
    $pk->entityRuntimeId = $this->id;
    $pk->position = $this->asVector3();
    $pk->item = Item::get(Item::AIR, 0, 0);;
    $pk->metadata = $this->dataProperties;
    $player->dataPacket($pk);

    parent::spawnTo($player);
  }
}