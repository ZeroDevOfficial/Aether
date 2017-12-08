<?php

declare(strict_types=1);

namespace Aether\tasks\games;

use pocketmine\scheduler\PluginTask;
use Aether\Main;

class gameHandler extends PluginTask {

  private $plugin;

  public function __construct(Main $plugin){
    parent::__construct($plugin);
    $this->plugin = $plugin;
  }

  public function getPlugin() : Main {
    return $this->plugin;
  }

  public function onRun($tick) : void {
  if($this->getPlugin()->games != null){
  foreach($this->getPlugin()->games as $key => $game){
    $game->tick();
    }
   }
  } 
}