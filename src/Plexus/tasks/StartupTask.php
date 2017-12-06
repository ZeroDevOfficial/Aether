<?php

declare(strict_types=1);

namespace Plexus\tasks;

use pocketmine\utils\TextFormat as C;
use pocketmine\scheduler\PluginTask;
use Plexus\Main;

class StartupTask extends PluginTask {
    
  private $plugin;
  
  public function __construct(Main $plugin){
  parent::__construct($plugin);
    $this->plugin = $plugin;
  }

  public function getPlugin() : Main {
    return $this->plugin;
  }

  public function onRun($tick) : void {
    $this->getPlugin()->info(C::AQUA .'v'. $this->getPlugin()->getDescription()->getVersion() . C::DARK_PURPLE .' is Loading');
    $this->getPlugin()->info(C::AQUA .'has Loaded');
    $this->getPlugin()->hasLoaded(true);
  }


}