<?php

declare(strict_types=1);

namespace Plexus\events;

use pocketmine\event\Listener;
use pocketmine\Player;
use pocketmine\utils\TextFormat as C;

use Plexus\Main;

class playerEvents implements Listener {

  private $plugin;
    
  public function __construct(Main $plugin){
    $this->plugin = $plugin;
  }
    
  public function getPlugin() : Main {
    return $this->plugin;
  }
  
  public function preJoin(\pocketmine\event\player\PlayerPreLoginEvent $e) : void {
  if($this->getPlugin()->hadLoaded !== true){
    $e->setCancelled(true);
    return;
  }
  }

  public function join(\pocketmine\event\player\PlayerJoinEvent $e) : void {
    $player = $e->getPlayer();
    $e->setJoinMessage('');
    $this->createPlayer($player);
  }

  public function createPlayer(Player $player) : void {
  if(!isset($this->getPlugin()->players[$player->getName()])){
    $this->getPlugin()->players[$player->getName()] = new \Plexus\PlexusPlayer($player, $this->getPlugin());
    $this->getPlugin()->info(C::AQUA .'Creating Player: '. C::YELLOW . $player->getName());
   }
  }

  public function quit(\pocketmine\event\player\PlayerQuitEvent $e) : void {
    $player = $e->getPlayer();
    $e->setQuitMessage('');
  if(!isset($this->getPlugin()->players[$player->getName()])){//TODO
    $this->getPlugin()->info(C::AQUA .'Saving Player Data: '. C::YELLOW . $player->getName());
   }
  }
}