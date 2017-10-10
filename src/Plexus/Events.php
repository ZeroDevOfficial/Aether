<?php

namespace Plexus;

use pocketmine\event\Listener;

class Events implements Listener 
{

  private $plugin;

  public function __construct(\Plexus\Main $plugin){
    $this->plugin = $plugin;
  }

  /** @return plugin */ 
  public function getPlugin(){
    return $this->plugin;
  }

  public function preLogin(\pocketmine\event\player\PlayerPreLoginEvent $e){
    $player = $e->getPlayer();
    $cooldown = $this->getPlugin()->cool_down;
  if($cooldown > 0){
    $player->close("", $this->getPlugin()->lang()->loading_server);
  } elseif($this->getPlugin()->config()->staffOnly() === true){
  if($player->isOp() === false){
    $player->close("", $this->getPlugin()->lang()->is_not_op);
    }
   } elseif($player->getXuid() !== ""){
    $this->getPlugin()->createPlayer($player);
   }
  }

  public function join(\pocketmine\event\player\PlayerJoinEvent $e){
    $e->setJoinMessage("");
    $player = $e->getPlayer();
    $rand = rand(1, 5);
    $spawns = $this->getPlugin()->config()->spawns;
    $sr = "spawn" . $rand; 
    $player->teleport(\pocketmine\Server::getInstance()->getLevelByName($this->getPlugin()->config()->spawn())->getSafeSpawn());
    $player->teleport(new \pocketmine\math\Vector3($spawns[$sr]['x'], 11, $spawns[$sr]['z']));
  }

  public function quit(\pocketmine\event\player\PlayerQuitEvent $e){
    $e->setQuitMessage("");
  }
}
