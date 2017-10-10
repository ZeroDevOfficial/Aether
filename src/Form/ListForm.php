<?php

namespace Form;

use pocketmine\Player;

abstract class ListForm extends Form{
  protected $title;
  protected $content;
  private $buttons;

  public function __construct(string $title, string $text, Button ...$buttons){
    $this->title = $title;
	  $this->content = $text;
	  $this->buttons = $buttons;
  }
    
  public function getType() : string{
    return Form::TYPE_LIST;
  }
    
  public function getButton(int $position) : ?Button{
    return $this->buttons[$position] ?? null;
  }

  public function handleResponse(Player $player, $data) : void{
  if($data === null){
	$this->onClose($player);
  } elseif(is_int($data)){
  if(!isset($this->buttons[$data])){
    throw new \RuntimeException($player->getName() . " selected an option that doesn't seem to exist ($data)");
  }
    $this->onSubmit($player, $data);
  } else {
    throw new \UnexpectedValueException("Expected int or NULL, got " . gettype($data));
   }
  }
    
  public function onClose(Player $player) : void{}

  abstract public function onSubmit(Player $player, int $selectedOption) : void;
  
  public function serializeFormData() : array{
    return [
	  "title" => $this->title,
	  "content" => $this->content,
	  "buttons" => $this->buttons
	];
  }
}