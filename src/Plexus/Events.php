<?php

namespace Plexus;

use pocketmine\event\Listener;
use pocketmine\network\mcpe\protocol\InteractPacket;

use pocketmine\utils\TextFormat as C;

class Events implements Listener {

  /** @var string | Plexus\Main */
  private $plugin;

  /* 
   * Constructor
   */
  public function __construct(\Plexus\Main $plugin){
    $this->plugin = $plugin;
  }

  /*
   * Plugin
   * ===============================
   * - Returns $this->plugin = \Plexus\Main;
   * ===============================
   */
  public function getPlugin(){
    return $this->plugin;
  }

  /*
   * Player Join Event $e
   * ===============================
   * - Sends the player to spawn
   * - Spawns npc's
   * ===============================
   */
  public function join(\pocketmine\event\player\PlayerJoinEvent $e){
    $e->setJoinMessage("");
    $player = $e->getPlayer();
  // Sends Player to Spawn
    $this->getPlugin()->spawn($player);
  // Creates Player Data
    $this->getPlugin()->player[$player->getName()] = new \Plexus\utils\PlayerData($this->getPlugin(), $player);
  // Spawns Npc's
  foreach($this->getPlugin()->npc as $eid => $npc){
    $npc->spawn($player);
   }
  }

  /* 
   * Player Move Event $e 
   * ===============================
   * - Npc looks at the player.
   * =============================== 
   */
  public function move(\pocketmine\event\player\PlayerMoveEvent $e){
    $player = $e->getPlayer();
  if($e->getTo()->distance($e->getFrom()) > 0.1) {
  // If they are at spawn
  if($player->getLevel()->getFolderName() === $this->getPlugin()->config()->spawn()){
  // If the player moves the npc will look at them.
  foreach($this->getPlugin()->npc as $eid => $npc){
    $npc->look($player);
     }
    }
   }
  }

  /*
   * InteractPacket 
   * ===============================
   * - UI When Looking at NPC 1-5 
   * ===============================
   *
	public function onPacketReceived(\pocketmine\event\server\DataPacketReceiveEvent $e){
    $player = $e->getPlayer();
    $pk = $e->getPacket();
  if($pk instanceof InteractPacket && $pk->action === InteractPacket::ACTION_MOUSEOVER && isset($this->getPlugin()->npc[$pk->target])){
    $npc = $this->getPlugin()->npc[$pk->target];
    $npc_games = array("NPC1", "NPC2", "NPC3", "NPC4", "NPC5");
  if(round($player->getPosition()->distance(new \pocketmine\math\Vector3($npc->x, $npc->y, $npc->z))) <= 3){
  // If the player looks at npc 1 - 5
  foreach($npc_games as $ng){
  if($npc->getName() === $ng){

      }
     }
    }
   }
  }*/

  /*
   * Player Quit Event $e
   * ===============================
   * - Despawns Npc's 
   * - Unsets player from player array
   * ================================ 
   */
  public function quit(\pocketmine\event\player\PlayerQuitEvent $e){
    $player = $e->getPlayer();
    $e->setQuitMessage("");
  foreach($this->getPlugin()->npc as $eid => $npc){
    $npc->remove($player);
  }
  if(isset($this->getPlugin()->player[$player->getName()])){
    $this->getPlugin()->player[$player->getName()]->save();
    unset($this->getPlugin()->player[$player->getName()]);
   }
  }
}
