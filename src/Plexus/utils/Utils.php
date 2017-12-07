<?php

namespace Plexus\utils;

/**
 * Main utils (not plugin main)
 * 
 * will have some rand things in here for now
 */

class Utils {

  private $events = [];

  public function removeEntities(){
    $main = \Plexus\Main::getInstance();
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
    $main = \Plexus\Main::getInstance();
    return array(
    'gameTestWorld' => new \Plexus\tasks\games\gameTest($main, 'gameTestWorld', 12, 120, 60)
    );
  }
  
  public function getEvents(){
    $main = \Plexus\Main::getInstance();
    return array(
      'playerEvents' => new \Plexus\events\playerEvents($main)
    );
  }
}