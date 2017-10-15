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
    $cooldown = $this->getPlugin()->cool_down;
  if($cooldown > 0){
    $player->close("", $this->getPlugin()->lang()->loading_server);
  } elseif($this->getPlugin()->config()->staffOnly() === true){
  if($player->isOp() === false){
    $player->close("", $this->getPlugin()->lang()->is_not_op);
    }
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
  /* { npc } | spawns all npc's on join */
  foreach($this->getPlugin()->getNpc() as $eid => $npc){
    $npc->spawn($player);
   }
  }

  /* { function } | player quit event */
  public function quit(\pocketmine\event\player\PlayerQuitEvent $e){
    $player = $e->getPlayer();
    $e->setQuitMessage("");

  /* { npc } | removes all npc's on player leave */
  foreach($this->getPlugin()->getNpc() as $eid => $npc){
    $npc->remove($player);
   }
  }

  /* { function } | player move event */
  public function move(\pocketmine\event\player\PlayerMoveEvent $e){
    $player = $e->getPlayer(); 

  /* { npc } | when a player moves the npc it will look at the player */
  foreach($this->getPlugin()->getNpc() as $eid => $npc){
    $npc->look($player, $this->getPlugin());
   }
  }
}
