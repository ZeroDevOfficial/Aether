<?php

namespace Form;

use pocketmine\Player;

abstract class Form implements \JsonSerializable{

  const TYPE_MODAL = "modal";
  const TYPE_LIST = "form";
  const TYPE_CUSTOM_FORM = "custom_form";
 
  abstract public function getType() : string;

  abstract public function handleResponse(Player $player, $data) : void;

  final public function jsonSerialize() : array{
    $jsonBase = [
	  "type" => $this->getType()
	];
	return array_merge($jsonBase, $this->serializeFormData());
  }
    
  abstract protected function serializeFormData() : array;
}