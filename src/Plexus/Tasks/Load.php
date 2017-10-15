<?php

namespace Plexus\Tasks;

use pocketmine\scheduler\PluginTask;

class Load extends PluginTask {
    
  /* { var } | plugin */
  private $plugin;

  /* { constructor } */
  public function __construct(\Plexus\Main $plugin){
    parent::__construct($plugin);
    $this->plugin = $plugin;
  }
  
  /* { function } | returns plexus main file */
  public function getPlugin(){
    return $this->plugin;
  }
  
  /* { function } | time before a player can join */
  public function onRun($tick){
    $this->getPlugin()->cool_down--;
  if($this->getPlugin()->cool_down === 0){
    $this->getPlugin()->getServer()->getScheduler()->cancelTask($this->getTaskId());
   }
  }
}