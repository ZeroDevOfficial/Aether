<?php

declare(strict_types=1);

namespace Plexus\tasks;

use pocketmine\scheduler\PluginTask;

use Plexus\Main;

class TaskHandler extends PluginTask {
    
  private $plugin;

  public function __construct(Main $plugin){
    parent::__construct($plugin);
    $this->plugin = $plugin;
  }
  
  public function getPlugin() : Main {
    return $this->plugin;
  }
  
  public function onRun($tick) : void {
  foreach($this->getPlugin()->task as $key => $task){
    $task->run();
   }
  } 

}