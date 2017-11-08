<?php

declare(strict_types=1);

namespace Plexus\tasks;

use pocketmine\utils\TextFormat as C;

use Plexus\Main;

class ShowHideTask {
    
  private $plugin;
  
  public function __construct(Main $plugin){
    $this->plugin = $plugin;
  }
  
  public function getPlugin() : Main {
    return $this->plugin;
  }

  public function run() : void {
    $players = $this->getPlugin()->getServer()->getOnlinePlayers();
  foreach($players as $player){
  foreach($players as $p){
  if($player != $p){
  if(round($player->getPosition()->distance(new \pocketmine\math\Vector3($p->x, $p->y, $p->z))) <= 40){
    $player->showPlayer($p);
  } else {
    $player->hidePlayer($p);
      }
     }
    }
   }
  }
}