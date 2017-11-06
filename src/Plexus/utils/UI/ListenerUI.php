<?php

declare(strict_types=1);

namespace Plexus\utils\UI;

use pocketmine\event\Listener;
use pocketmine\network\mcpe\protocol\ModalFormResponsePacket;
use pocketmine\network\mcpe\protocol\InteractPacket;

use pocketmine\Player;
use pocketmine\utils\TextFormat as C;

use Plexus\Main;

class ListenerUI implements Listener {

  private $plugin;

  public function __construct(Main $plugin){
    $this->plugin = $plugin;
  }

  public function getPlugin() : Main {
    return $this->plugin;
  }

  /*public function onPacketReceived(\pocketmine\event\server\DataPacketReceiveEvent $e){
    $player = $e->getPlayer();
    $pk = $e->getPacket();
  if($pk instanceof InteractPacket && $pk->action === InteractPacket::ACTION_MOUSEOVER && isset($this->getPlugin()->npc[$pk->target])){
    $npc = $this->getPlugin()->npc[$pk->target];
  if($npc->getName() === ""){
  }
  }
  }*/
}