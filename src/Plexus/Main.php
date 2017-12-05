<?php

declare(strict_types=1);

namespace Plexus;

//Pocketmine
use pocketmine\Server;
use pocketmine\plugin\PluginBase;
use pocketmine\event\Listener;
use pocketmine\utils\TextFormat as C;
use pocketmine\entity\Entity;

//Plexus
use Plexus\utils\Utils;

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
    $this->getServer()->getScheduler()->scheduleDelayedTask(new \Plexus\tasks\StartupTask($this), 20);
    $this->getUtils()->removeEntities();
  }

  public static function getInstance() : Main {
    return self::$instance;
  }

  public function getUtils() : Utils {
  if($this->utils !== null){
    return $this->utils;
  } else {
    $this->utils = new \Plexus\utils\Utils($this);
    return $this->utils;
   }
  }

  public function hasLoaded(bool $hasLoaded) : void {
    $this->hadLoaded = (bool)$hasLoaded;
  }

  public function info(string $msg){
    $this->getServer()->getLogger()->info('| '. self::PREFIX . C::WHITE .C::RESET .' | '. $msg);
  }

  public function registerEvent(\pocketmine\event\Listener $event) : void {
    $this->getServer()->getPluginManager()->registerEvents($event, $this);
  }

  public function onDisable() : void {
    $this->getUtils()->removeEntities();
    $this->info(C::AQUA .'is'. C::RED .' Offline.');
  }
}