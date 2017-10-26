<?php

namespace Plexus\utils\Form;

abstract class Form {

  /* { var } | id */
  public $id;
  /* { var } | data */
  private $data = [];

  /* { constructor } */
  public function __construct($id) {
    $this->id = $id;
  }

  /* { function } | get id */ 
  public function getId() {
    return $this->id;
  }

  /* { function } | sends the form to the player */
  public function send($player){
    $pk = new \pocketmine\network\mcpe\protocol\ModalFormRequestPacket();
    $pk->formId = $this->id;
    $pk->formData = json_encode($this->data);
    $player->dataPacket($pk);
  }  

}