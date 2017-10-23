<?php

namespace Plexus\utils;

use pocketmine\scheduler\PluginTask;

class TaskHandler extends PluginTask {
    
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
  
  /* { function } | move task */
  public function onRun($tick){
  foreach($this->getPlugin()->task as $key => $task){
    $task->run();
   }
  } 

}