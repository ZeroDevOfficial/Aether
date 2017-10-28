<?php

namespace Plexus\utils\UI;

use pocketmine\network\mcpe\protocol\ModalFormRequestPacket;

class SimpleUI extends UI {

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
   * Add Title
   * ===============================
   * - Adds a title to the UI
   * ===============================
   */
  public function addTitle($title){
    $this->data["title"] = $title;
  }

  /* 
   * Add Content
   * ===============================
   * - Adds Content to the UI
   * ===============================
   */
  public function addContent($content){
    $this->data["content"] = $content;
  }
  
  /* 
   * Add Button
   * ===============================
   * - Adds a button to the UI
   * ===============================
   */
  public function addButton($text, $imageType = -1, $imagePath = ""){
    $content = ["text" => $text];
  if($imageType !== -1){
    $content["image"]["type"] = $imageType === 0 ? "path" : "url";
    $content["image"]["data"] = $imagePath;
  }
    $this->data["buttons"][] = $content;
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