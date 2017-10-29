<?php

namespace Plexus\utils\Tasks;

use pocketmine\utils\TextFormat as C;

class BorderTask {
    
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
   * Border
   * ===============================
   * - Task
   * ===============================
   */
  public function run(){
    $players = $this->getPlugin()->getServer()->getOnlinePlayers();
  foreach($players as $player){
    $spawn = $player->getLevel()->getSpawnLocation();
  if($player->getLevel()->getFolderName() === $this->getPlugin()->config()->spawn()){
    //$player->sendTip("Yaw:". $player->yaw);
  if($player->getY() <= 10 || $player->getY() >= 185){
    $player->addTitle(C::YELLOW ."Border is near!", C::RED ."if you proceed you will be teleported to spawn.", 50, 90, 40);
  }
  if($player->getY() <= 2 || $player->getY() >= 200){
    $this->getPlugin()->spawn($player);
    $player->addTitle(C::YELLOW ."Border Reached!", C::RED ."You have reached the end of the world.", 50, 90, 40);
    $player->setHealth(20);
     }
    }
   } 
  }
}