<?php

namespace Plexus\utils\Tasks;

use pocketmine\utils\TextFormat as C;

class FloatingTextTask {
    
  /** @var string | Plexus\Main */
  private $plugin;
  
  /* 
   * Constructor
   */
  public function __construct(\Plexus\Main $plugin){
    $this->plugin = $plugin;
    $this->text = new \pocketmine\level\particle\FloatingTextParticle(new \pocketmine\math\Vector3(852, 84, 1144), "", C::GRAY .">======================<\n". C::DARK_PURPLE ."\nWelcome to ". C::AQUA ."Plexus Studio");
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
   * Floating Text
   * ===============================
   * - Task
   * ===============================
   */
  public function run(){
    $players = $this->getPlugin()->getServer()->getOnlinePlayers();
    $level = \pocketmine\Server::getInstance()->getLevelByName($this->getPlugin()->config()->spawn());
    $this->text->setText(C::DARK_PURPLE ."Players Online: ". C::AQUA . count($players) . C::GRAY ."\n\n>======================<");
  if($level) 
    $level->addParticle($this->text);
  }
}