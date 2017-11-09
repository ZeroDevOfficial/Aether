<?php

declare(strict_types=1);

namespace Plexus\UI;

use pocketmine\Player;
use pocketmine\network\mcpe\protocol\ModalFormRequestPacket;

abstract class UI {

  public $id;
  private $data = [];
  public $player;

  public function __construct($id){
    $this->id = $id;
  }

  public function getId() : int {
    return $this->id;
  }

  public function send(Player $player) : void {
    $pk = new ModalFormRequestPacket();
    $pk->formId = $this->id;
    $pk->formData = json_encode($this->data);
    $player->dataPacket($pk);
  }
}