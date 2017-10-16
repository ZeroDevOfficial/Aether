<?php

namespace Plexus;

use pocketmine\event\Listener;

class Events implements Listener 
{
  /* { var } | plugin */
  private $plugin;

  /* { constructor } */
  public function __construct(\Plexus\Main $plugin){
    $this->plugin = $plugin;
  }

  /* { function } | returns plexus main file */
  public function getPlugin(){
    return $this->plugin;
  }

  /* { function } | player pre login event */
  public function preLogin(\pocketmine\event\player\PlayerPreLoginEvent $e){
    $player = $e->getPlayer();
  if($this->getPlugin()->config()->staffOnly() === true){
  if($player->isOp() === false){
    $player->close("", $this->getPlugin()->lang()->is_not_op);
    } 
   } else {
    $this->getPlugin()->player[$player->getName()] = new \Plexus\utils\PlayerData($this->getPlugin(), $player);
   }
  }

  /* { function } | player join event */
  public function join(\pocketmine\event\player\PlayerJoinEvent $e){
    $e->setJoinMessage("");
    $player = $e->getPlayer();
    $rand = rand(1, 5);
    $spawns = $this->getPlugin()->config()->spawns;
    $sr = "spawn" . $rand; 
    $player->teleport(\pocketmine\Server::getInstance()->getLevelByName($this->getPlugin()->config()->spawn())->getSafeSpawn());
    $player->teleport(new \pocketmine\math\Vector3($spawns[$sr]['x'], 11, $spawns[$sr]['z']));
  }

  /* { function } | player quit event */
  public function quit(\pocketmine\event\player\PlayerQuitEvent $e){
    $player = $e->getPlayer();
    $e->setQuitMessage("");
  if(isset($this->getPlugin()->player[$player->getName()])){
    $this->getPlugin()->player[$player->getName()]->save();
    unset($this->getPlugin()->player[$player->getName()]);
   }
  }
}
