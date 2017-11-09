<?php

declare(strict_types=1);

namespace Plexus\UI;

use pocketmine\Player;
use pocketmine\network\mcpe\protocol\ModalFormRequestPacket;

class SimpleUI extends UI {

  public $id;
  private $data = [];
  private $content = '';
  public $player;

  public function __construct($id){
  parent::__construct($id);
     $this->id = $id;
    $this->data["type"] = "form";
    $this->data["title"] = "";
    $this->data["content"] = $this->content;
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

  public function addTitle($title){
    $this->data["title"] = $title;
  }

  public function addContent($content){
    $this->data["content"] = $content;
  }

  public function addButton($text, $imageType = -1, $imagePath = ""){
    $content = ["text" => $text];
  if($imageType !== -1){
    $content["image"]["type"] = $imageType === 0 ? "path" : "url";
    $content["image"]["data"] = $imagePath;
  }
    $this->data["buttons"][] = $content;
  }
}