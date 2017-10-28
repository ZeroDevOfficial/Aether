<?php

namespace Plexus\utils;

use pocketmine\scheduler\PluginTask;

class TaskHandler extends PluginTask {
    
  /** @var string | Plexus\Main */
  private $plugin;

  /* 
   * Constructor
   */
  public function __construct(\Plexus\Main $plugin){
    parent::__construct($plugin);
    $this->plugin = $plugin;
  }
  
  /*
   * Returns Plugin
   * ===============================
   * - Returns $this->plugin = \Plexus\Main;
   * ===============================
   */
  public function getPlugin(){
    return $this->plugin;
  }
  
  /*
   * On Task Run\Tick
   * ===============================
   * - Runs All the Tasks
   * =============================== 
   */
  public function onRun($tick){
  foreach($this->getPlugin()->task as $key => $task){
    $task->run();
   }
  } 

}