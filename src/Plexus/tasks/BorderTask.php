<?php

declare(strict_types=1);

namespace Plexus\tasks;

use pocketmine\utils\TextFormat as C;

use Plexus\Main;

class BorderTask {

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
  if(isset($this->getPlugin()->player[$player->getName()]) && $player->getLevel()->getFolderName() === $this->getPlugin()->config()->spawn()){
    $p = $this->getPlugin()->player[$player->getName()];
    $spawn = $player->getLevel()->getSpawnLocation();
    $y = $player->getY();
  switch(true){
  case(($y <= 1 || $y >= 200)):
    $player->addTitle(C::YELLOW .'Border Reached!', C::RED .'You have reached the end of the world.', 50, 90, 40);
    $p->playSound(46);//SOUND_EXPLODE
    $player->setHealth(20);
    $this->getPlugin()->spawn($player);
  break;
  case(($y <= 5 || $y >= 185)):
    $player->addTitle(C::YELLOW .'Border is near!', C::RED .'if you proceed you will be teleported to spawn.', 50, 90, 40);
    $p->playSound(73);//SOUND_DENY
  break;
     }
    }
   }
  }
}