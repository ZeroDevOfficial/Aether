<?php

declare(strict_types=1);

namespace Plexus;

use pocketmine\event\Listener;
use pocketmine\Player;
use pocketmine\utils\TextFormat as C;

use Plexus\Main;

class Events implements Listener {

  private $plugin;

  public function __construct(Main $plugin){
    $this->plugin = $plugin;
  }

  public function getPlugin() : Main {
    return $this->plugin;
  }

  public function join(\pocketmine\event\player\PlayerJoinEvent $e) : void {
    $e->setJoinMessage('');
    $player = $e->getPlayer();
    $this->getPlugin()->join($player);
  }

  public function quit(\pocketmine\event\player\PlayerQuitEvent $e) : void {
    $player = $e->getPlayer();
    $e->setQuitMessage('');
  if(isset($this->getPlugin()->player[$player->getName()])){
    $this->getPlugin()->player[$player->getName()]->save();
    unset($this->getPlugin()->player[$player->getName()]);
   }
  }

  public function onEntityDamage(\pocketmine\event\entity\EntityDamageEvent $e){
    $entity = $e->getEntity();
  if($entity instanceof \Plexus\entity\FloatingNameTag){
    $e->setCancelled(true);
    return;
  }
  if(!$e instanceof \pocketmine\event\entity\EntityDamageByEntityEvent){
     return;
  }
    $damager = $e->getDamager();
  if(!$damager instanceof \pocketmine\Player) {
     return;
  }
  if($entity instanceof \Plexus\entity\Npc){
    $e->setCancelled(true);
  if(isset($this->getPlugin()->npc[$entity->getId()])){
    $damager->sendMessage("Y u hit me");
    }
   }
  }
/*
  public function move(\pocketmine\event\player\PlayerQuitEvent $e) : void {
    $player = $e->getPlayer();
  foreach($this->getPlugin()->npc as $id => $npc){
    $npc->lookAt($player);
   }
  }*/
}