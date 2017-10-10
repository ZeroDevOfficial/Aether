<?php

namespace Plexus\Tasks;

use pocketmine\scheduler\PluginTask;

class Load extends PluginTask {
    
  private $plugin;

  public function __construct(\Plexus\Main $plugin){
    parent::__construct($plugin);
    $this->plugin = $plugin;
  }
  
  /** @return plugin */ 
  public function getPlugin(){
    return $this->plugin;
  }

  public function onRun($tick){
    $this->getPlugin()->cool_down--;
    //$this->getPlugin()->getLogger()->info($this->getPlugin()->cool_down);  
  if($this->getPlugin()->cool_down === 0){
    //$this->getPlugin()->getLogger()->info("Stopping Task.");  
    $this->getPlugin()->getServer()->getScheduler()->cancelTask($this->getTaskId());
   }
  }
}