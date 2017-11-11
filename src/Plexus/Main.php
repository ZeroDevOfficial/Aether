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

  private static $instance = null;

  const BR = "\n";

  public function onEnable() : void {
    self::$instance = $this; 
    $this->getServer()->getLogger()->info(C::DARK_PURPLE .'Plexus '. C::AQUA .'v'. $this->config()->version() . C::DARK_PURPLE .' is Loading...');
  foreach($this->config()->taskArrayData() as $key => $task){
    $this->task[$key] = $task;
  }
    $this->getServer()->getLogger()->info(C::DARK_PURPLE .'Task(s) '. C::AQUA . implode(", ", array_keys($this->config()->taskArrayData())) . C::DARK_PURPLE .' have been Started');
    
    $this->getServer()->getScheduler()->scheduleRepeatingTask(new \Plexus\tasks\TaskHandler($this), 20);
    $this->getServer()->getPluginManager()->registerEvents(new \Plexus\UI\ListenerUI($this), $this);
    $this->getServer()->getPluginManager()->registerEvents(new \Plexus\Events($this), $this);
  foreach($this->config()->commandArrayData() as $key => $command){
    $this->getServer()->getCommandMap()->register($key, $command);
  }
    $this->getServer()->getLogger()->info(C::DARK_PURPLE .'Command(s) '. C::AQUA . implode(", ", array_keys($this->config()->commandArrayData())) . C::DARK_PURPLE .' have been Activated');

    $ui = new \Plexus\UI\ListenerUI($this);
    $ui->createUIArray();  
    $this->entity()->entityInit();
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
    $minMax = array('min' => array(779, 0, 1071), 'max' => array(930, 200, 1222));
  if(($x >= $minMax['min'][0] && $x <= $minMax['max'][0]) && ($y >= $minMax['min'][1] && $y <= $minMax['max'][1]) && ($z >= $minMax['min'][2] && $z <= $minMax['max'][2])){
    return true;
  } else {
    return false;
   }
  }

  public function discord(string $msg, string $type = '') : void {
  if($type != ''){
  if($type === 'chat'){
    $url = 'https://discordapp.com/api/webhooks/378667626748051456/yy0HcG-e9WDofsxgO2yu36Dz579MBSbLA2qhUzbqs80xPmNzlsgAbdF5r1rRKvliQunt';
  }
  if($type === 'server'){
    $url = 'https://discordapp.com/api/webhooks/378668960285196288/JLz9UDZrs3Ic86Hm6Lyke7MEiiF37Yn9L7U11QjpxMxWqOwHpBIN4fliydr736S8CWU_';
  }
    $ch = curl_init();
    curl_setopt_array($ch, array(
    CURLOPT_URL => $url,
    CURLOPT_RETURNTRANSFER => 1,
    CURLOPT_SSL_VERIFYHOST => 0,
    CURLOPT_SSL_VERIFYPEER => 0,
    CURLOPT_POSTFIELDS => json_encode(array(
    'content' => $msg,
    'username' => 'Yuki',
    'avatar_url' => '')))
    );
    curl_exec($ch);
   }
  }

  public function join(Player $player) : void {
    $this->player[$player->getName()] = new \Plexus\PlexusPlayer($this, $player);
    $this->spawn($player);
  foreach($this->ft as $key => $ft){//in case I add more later
    $ft->spawnTo($player);
  }
  foreach($this->npc as $npc){
    $npc->spawnTo($player);
   }
  }

  public function spawn(Player $player) : void {
    $player->teleport(\pocketmine\Server::getInstance()->getLevelByName($this->config()->spawn())->getSafeSpawn());
    $player->addTitle(C::DARK_PURPLE .'Welcome to Spawn.', C::YELLOW . $player->getName(), 50, 90, 40);
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