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
    $e->getPlayer()->close(Main::PREFIX, C::RED .'Server Has Not Loaded yet.');
    $e->setCancelled(true);
    return;
   }
  }

  public function join(\pocketmine\event\player\PlayerJoinEvent $e) : void {
    $player = $e->getPlayer();
    $e->setJoinMessage('');
    $this->getPlugin()->getPlayer($player)->setup();
    $this->getPlugin()->games['gameTestWorld']->join($player);
  }

  public function quit(\pocketmine\event\player\PlayerQuitEvent $e) : void {
    $player = $e->getPlayer();
    $e->setQuitMessage('');
    $this->getPlugin()->getPlayer($player)->saveData();
  }
}