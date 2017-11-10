<?php

declare(strict_types=1);

namespace Plexus;

use pocketmine\plugin\PluginBase;
use pocketmine\Server;

use Data\Config;

use pocketmine\utils\TextFormat as C;
use pocketmine\Player;

use pocketmine\entity\Entity;
use Plexus\entity\PlexusEntity;

class Main extends PluginBase {

  public $player = [];
  public $ft = [];
  public $npc = [];
  public $tasks = [];
  public $ui = [];
  public $show_xyz = [];
  public $hasLoaded = false;

  private static $instance = null;

  const BR = "\n";
  
  public function onEnable() : void {
    self::$instance = $this; 
    $this->getServer()->getLogger()->info(C::DARK_PURPLE .'Plexus '. C::AQUA .'v'. $this->config()->version() . C::DARK_PURPLE .' is Loading...');
  foreach($this->config()->taskArrayData() as $key => $task){
    $this->task[$key] = $task;
    $this->getServer()->getLogger()->info(C::YELLOW .'Task '. C::AQUA . $key . C::YELLOW .' has loaded');
  }
    $this->getServer()->getScheduler()->scheduleRepeatingTask(new \Plexus\tasks\TaskHandler($this), 20);
    $this->getServer()->getPluginManager()->registerEvents(new \Plexus\UI\ListenerUI($this), $this);
    $this->getServer()->getPluginManager()->registerEvents(new \Plexus\Events($this), $this);
  foreach($this->config()->commandArrayData() as $key => $command){
    $this->getServer()->getCommandMap()->register($key, $command);
    $this->getServer()->getLogger()->info(C::YELLOW .'Command '. C::AQUA . $key . C::YELLOW .' has loaded');
  }
    $ui = new \Plexus\UI\ListenerUI($this);
    $ui->createUIArray();  
    $this->entity()->entityInit();
    $this->hasLoaded = true;
    $this->getServer()->getLogger()->info(C::DARK_PURPLE .'Everything has Loaded, Plexus is now Online!');
  }

  public static function getInstance(){
    return self::$instance;
  }

  public function config() : Config {
    return new Config();
  }

  public function entity() : PlexusEntity {
    return new PlexusEntity($this);
  }

  public function atSpawn(Player $player) : bool {
    $x = $player->x;
    $y = $player->y;
    $z = $player->z;
    $minX = 778; $maxX = 929;
    $minY = 0; $maxY = 200;
    $minZ = 1222; $maxZ = 1071;
  if(($x >= $maxX && $x <= $minX) || ($y >= $maxY & $y <= $minY) || ($z >= $maxZ & $z <= $minZ)){
    return true;
  } else {
    return false;
   }
  } 

  public function join(Player $player) : void {
    $this->player[$player->getName()] = new \Plexus\PlexusPlayer($this, $player);
    $this->spawn($player);
    $this->ft['welcome']->spawnTo($player);
  }

  public function spawn(Player $player) : void {
    $player->teleport(\pocketmine\Server::getInstance()->getLevelByName($this->config()->spawn())->getSafeSpawn());
  }

  public function onDisable() : void {
    $this->getLogger()->info(C::AQUA .'is'. C::RED .' Offline.');
    $this->entity()->removeEntities();
    $players = $this->getServer()->getOnlinePlayers();
  foreach($players as $player){
    $player->close('', C::AQUA .'Plexus Has closed, We will be back shortly.');
  }
    $this->forceShutdown();
  }

  public function forceShutdown(){
  if($this->config()->forceShutdown() === true && $this->getServer()->isRunning() === true){
    $this->getLogger()->info(C::RED .'Server is still running, Shutting Down Server.');
    $this->getServer()->forceShutdown();
   }   
  }
  
}