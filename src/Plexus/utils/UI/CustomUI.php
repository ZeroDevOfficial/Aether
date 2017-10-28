<?php

//TODO

namespace Plexus\utils\UI;

use pocketmine\network\mcpe\protocol\ModalFormRequestPacket;

class CustomUI extends UI {

  /** @var int */
  public $id;
  /** @var array */
  private $data = [];
  /** @var string */
  public $player;

  /* 
   * Constructor
   */
  public function __construct($id) {
  parent::__construct($id);
    $this->id = $id;
    $this->data = [
      "type" => "custom_form", "title" => "", "content" => []
    ];
  }

  /* 
   * getId
   * ===============================
   * - Returns Id  
   * ===============================
   */
  public function getId(){
    return $this->id;
  }

  /* 
   * send
   * ===============================
   * - sends UI to Player
   * ===============================
   */
  public function send($player){
    $pk = new ModalFormRequestPacket();
    $pk->formId = $this->id;
    $pk->formData = json_encode($this->data);
    $player->dataPacket($pk);
  }
}