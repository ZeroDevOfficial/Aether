<?php

namespace Plexus;

use pocketmine\event\Listener;
use pocketmine\network\mcpe\protocol\InteractPacket;

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
  } else {
      $this->getPlugin()->player[$player->getName()] = new \Plexus\utils\PlayerData($this->getPlugin(), $player);
    }
   } 
  }

  /* { function } | player join event */
  public function join(\pocketmine\event\player\PlayerJoinEvent $e){
    $e->setJoinMessage("");
    $player = $e->getPlayer();
    $this->getPlugin()->spawn($player);
  foreach($this->getPlugin()->npc as $id => $npc){
    $npc->spawn($player);
   }
  }

  /* { function } | player move event */
  public function move(\pocketmine\event\player\PlayerMoveEvent $e){
    $player = $e->getPlayer();
  if($e->getTo()->distance($e->getFrom()) > 0.1) {
    $spawn = $player->getLevel()->getSpawnLocation();
  foreach($this->getPlugin()->npc as $id => $npc){
    $npc->look($player);
  }
  if(round($player->getPosition()->distance(new \pocketmine\math\Vector3($spawn->getX(), $spawn->getY(), $spawn->getZ()))) >= $this->getPlugin()->config()->border or $player->getY() <= 0){
    $this->getPlugin()->spawn($player);
    $player->addTitle($this->getPlugin()->lang()->border_reached, $this->getPlugin()->lang()->border_end_of_world, 50, 90, 40);
    }
   }
  }

  /* { function } | player quit event */
  public function quit(\pocketmine\event\player\PlayerQuitEvent $e){
    $player = $e->getPlayer();
    $e->setQuitMessage("");
  foreach($this->getPlugin()->npc as $id => $npc){
    $npc->remove($player);
  }
  if(isset($this->getPlugin()->player[$player->getName()])){
    $this->getPlugin()->player[$player->getName()]->save();
    unset($this->getPlugin()->player[$player->getName()]);
   }
  }

  /* { function } | packet received event */
	public function onPacketReceived(\pocketmine\event\server\DataPacketReceiveEvent $e){
    $player = $e->getPlayer();
    $pk = $e->getPacket();
  if($pk instanceof InteractPacket && $pk->action === InteractPacket::ACTION_MOUSEOVER){
  if(isset($this->getPlugin()->npc[$pk->target])){
    $npc = $this->getPlugin()->npc[$pk->target];
    $npc->onInteract($player);
    }
   }
  }
}
