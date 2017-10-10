<?php

namespace pocketmine\form;

use pocketmine\Player;

abstract class ModalForm extends Form{
  
  private $title;
  private $content;
  private $button1;
  private $button2;
	
  public function __construct(string $title, string $text, string $yesButtonText = "gui.yes", string $noButtonText = "gui.no"){
    $this->title = $title;
	$this->content = $text;
	$this->button1 = $yesButtonText;
	$this->button2 = $noButtonText;
  }
	
  public function getType() : string{
    return self::TYPE_MODAL;
  }
	
  public function getYesButtonText() : string{
    return $this->button1;
  }

  public function getNoButtonText() : string{
    return $this->button2;
  }

  final public function handleResponse(Player $player, $data) : void{
  if(!is_bool($data)){
    throw new \UnexpectedValueException("Expected bool, got " . gettype($data));
  }
    $this->onSubmit($player, $data);
  }

  abstract public function onSubmit(Player $player, bool $responseValue) : void;

  public function serializeFormData() : array{
    return [
	  "title" => $this->title,
	  "content" => $this->content,
	  "button1" => $this->button1,
	  "button2" => $this->button2
	];
  }
}