<?php

declare(strict_types=1);

namespace Plexus;

//Pocketmine
use pocketmine\Server;
use pocketmine\plugin\PluginBase;
use pocketmine\event\Listener;
use pocketmine\utils\TextFormat as C;
use pocketmine\entity\Entity;
use pocketmine\Player;

//Plexus
use Plexus\utils\Utils;
use Plexus\PlexusPlayer;

/**
 * @author ZeroDevOfficial
 * 
 * @since 0.0.1
 * 
 * Plexus Core was Developed by ZeroDevOfficial
 * This Core is property of Plexus, Nobody is allowed to distribute, Modify, or Use
 * The Core without ZeroDevOfficial's Permission
 * 
 */

class Main extends PluginBase {

  public $hadLoaded = false;
  public $players = [];
  public $utils = null;

  private static $instance = null;

  public const PREFIX = C::YELLOW .'»'. C::DARK_PURPLE .' Plexus '. C::YELLOW .'«';

  public function onEnable() : void {
    self::$instance = $this;
    $this->hasLoaded(false);
    $this->registerEvents();
    $this->getServer()->getScheduler()->scheduleDelayedTask(new \Plexus\tasks\StartupTask($this), 20);
    $this->getUtils()->removeEntities();
  }

  public static function getInstance() : Main {
    return self::$instance;
  }

  public function registerEvents() : void {
    $regEvents = [];
    $events = $this->getUtils()->getEvents();
  foreach($events as $name => $event){
  if($event instanceof \pocketmine\event\Listener){
    $this->getServer()->getPluginManager()->registerEvents($event, $this);
    $regEvents[$name] = $event;
    }
   }
    $this->info(C::AQUA .'Loaded '. C::DARK_PURPLE . implode(", ", array_keys($regEvents)));
    unset($regEvents);
  }

  public function getPlayer(Player $player) : PlexusPlayer {
  if(!isset($this->player[$player->getName()])){
    $this->player[$player->getName()] = new PlexusPlayer($player, $this);
  }  
    return $this->player[$player->getName()];
  }

  public function getUtils() : Utils {
  if($this->utils === null){
    $this->utils = new \Plexus\utils\Utils($this);
  }
    return $this->utils;
  }

  public function hasLoaded(bool $hasLoaded) : void {
    $this->hadLoaded = (bool)$hasLoaded;
  }

  public function info(string $msg){
    $this->getServer()->getLogger()->info('| '. self::PREFIX . C::WHITE .C::RESET .' | '. $msg);
  }

  public function onDisable() : void {
    $this->getUtils()->removeEntities();
    $this->info(C::AQUA .'is'. C::RED .' Offline.');
  }
}