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
  try {
    self::$instance = $this; 
    $this->getServer()->getLogger()->info(C::DARK_PURPLE .'==================================');
    $this->getServer()->getLogger()->info(C::YELLOW .'Plexus v'. $this->config()->version() .' is Loading...');
    $this->task['border'] = new \Plexus\utils\Tasks\BorderTask($this);
    $this->task['text'] = new \Plexus\utils\Tasks\FloatingTextTask($this);
    $this->task['show_hide'] = new \Plexus\utils\Tasks\ShowHideTask($this);
    $this->task['xyz'] = new \Plexus\utils\Tasks\xyzTask($this);

    $this->getServer()->getScheduler()->scheduleRepeatingTask(new \Plexus\utils\Tasks\TaskHandler($this), 20);
    $this->getServer()->getLogger()->info(C::YELLOW .'Tasks have Loaded!');
   
    $this->getServer()->getPluginManager()->registerEvents(new \Plexus\Events($this), $this);
    $this->getServer()->getPluginManager()->registerEvents(new \Plexus\utils\UI\ListenerUI($this), $this);
    $this->getServer()->getLogger()->info(C::YELLOW .'Events have been registered!');
  
    $this->getServer()->getCommandMap()->register('hub', new \Plexus\Commands\hubCommand($this));
    $this->getServer()->getCommandMap()->register('welcome', new \Plexus\Commands\welcomeCommand($this));
    $this->getServer()->getCommandMap()->register('xyz', new \Plexus\Commands\xyzCommand($this));
    $this->getServer()->getLogger()->info(C::YELLOW .'Commands have been registered!');

    $this->entity()->entityInit();
    $this->getServer()->getLogger()->info(C::YELLOW .'Entities have been registered!');

    $this->hasLoaded = true;
    $this->getServer()->getLogger()->info(C::YELLOW .'Everything has Loaded, Plexus is now Online!');
    $this->getServer()->getLogger()->info(C::DARK_PURPLE .'==================================');
  }
  catch(Exception $e){
    $this->getLogger()->info(C::RED ."Plugin has Failed to Load due to $e");
    $this->forceShutdown();
   }
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

  public function join(Player $player) : void {
    $this->player[$player->getName()] = new \Plexus\utils\PlexusPlayer($this, $player);
    $this->spawn($player);
    $this->ft['welcome']->spawnTo($player);
  }

  public function spawn(Player $player) : void {
    $player->teleport(\pocketmine\Server::getInstance()->getLevelByName($this->config()->spawn())->getSafeSpawn());
  }

  public function onDisable() : void {
    $this->getLogger()->info(C::AQUA .'is'. C::RED .' Offline.');
    $this->entity()->entitiesRemove();
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