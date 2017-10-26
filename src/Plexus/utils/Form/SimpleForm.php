<?php

namespace Plexus\utils\Form;

class SimpleForm extends Form {

  const IMAGE_TYPE_PATH = 0;
  const IMAGE_TYPE_URL = 1;

  /* { var } | id */
  public $id;
  /* { var } | data */
  private $data = [];
  /* { var } | content */
  private $content = "";

  /* { constructor } */
  public function __construct($id) {
  parent::__construct($id);
    $this->data["type"] = "form";
    $this->data["title"] = "";
    $this->data["content"] = $this->content;
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

  /* { function } | sets the form title */
  public function setTitle($title){
    $this->data["title"] = $title;
  }

  /* { function } | sets the form content */
  public function setContent($content){
    $this->data["content"] = $content;
  }

  /* { function } | adds buttons */
  public function addButton($text, $imageType = -1, $imagePath = ""){
    $content = ["text" => $text];
  if($imageType !== -1){
    $content["image"]["type"] = $imageType === 0 ? "path" : "url";
    $content["image"]["data"] = $imagePath;
  }
  $this->data["buttons"][] = $content;
  }
}