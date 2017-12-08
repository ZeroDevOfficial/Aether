<?php

namespace Aether\utils;

class Utils {

  private $events = [];

  public function removeEntities(){
    $main = \Aether\Main::getInstance();
    $removed = 0;
    $level = $main->getServer()->getLevelByName('hub');
  if($level instanceof \pocketmine\level\Level){
  foreach($level->getEntities() as $entity){
  if(!($entity instanceof \pocketmine\Player)){
  if(count($level->getEntities()) >= 1){
    $level->removeEntity($entity);
    $removed++;
    }
   }
  }
  if($removed != 0){
    $main->info(C::DARK_PURPLE .'Removed '. C::YELLOW . $removed . C::DARK_PURPLE .' Entities!');
   }
  }
    unset($removed);
  }

  public function getGames(){
    $main = \Aether\Main::getInstance();
    return array(
    'gameTestWorld' => new \Aether\tasks\games\sw($main, 'gameTestWorld', 12, 60, 60)
    );
  }
  
  public function getEvents(){
    $main = \Aether\Main::getInstance();
    return array(
      'playerEvents' => new \Aether\events\playerEvents($main)
    );
  }

  public function getCommands(){
    $main = \Aether\Main::getInstance();
    return array(
      'join' => new \Aether\commands\joinCommand($main)
    );
  }
}