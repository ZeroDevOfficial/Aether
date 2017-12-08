<?php

declare(strict_types=1);

namespace Aether\tasks;

use pocketmine\utils\TextFormat as C;
use pocketmine\scheduler\PluginTask;
use Aether\Main;

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
  foreach($this->getPlugin()->getUtils()->getGames() as $map => $game){
    $this->getPlugin()->games[$map] = $game;
  }
    $this->getPlugin()->info(C::AQUA .'Loaded '. C::DARK_PURPLE . implode(", ", array_keys($this->getPlugin()->games)) . C::AQUA .' Game(s)');
  foreach($this->getPlugin()->getUtils()->getCommands() as $key => $cmd){
    $this->getPlugin()->getServer()->getCommandMap()->register($key, $cmd);
  }
  $this->getPlugin()->info(C::AQUA .'Registered '. C::DARK_PURPLE . implode(", ", array_keys($this->getPlugin()->getUtils()->getCommands())) . C::AQUA .' Command(s)');
    $this->getPlugin()->getServer()->getScheduler()->scheduleRepeatingTask(new \Aether\tasks\games\gameHandler($this->getPlugin()), 20);
    $this->getPlugin()->getUtils()->removeEntities();
    $this->getPlugin()->info(C::AQUA .'has Loaded');
    $this->getPlugin()->hasLoaded(true);
  }
}