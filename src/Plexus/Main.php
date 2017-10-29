<?php

namespace Plexus;

use pocketmine\plugin\PluginBase;
use pocketmine\Server;

use pocketmine\utils\TextFormat as C;

class Main extends PluginBase {

  /** @var array */
  public $player = [];

  /** @var array */
  public $npc = [];
  /** @var array |*/
  public $skins = [];

  /** @var array | tasks */
  public $tasks = [];

  /** @var array | ui data */
  public $ui = [];
  /** @var int | ui number */
  public $uiCount = 0;

  /*
   * Plugin enable
   * ===============================
   * - Developer mode | bool
   * - Testing mode | bool
   * - Sets Server Name | developer or public
   * ===============================
   */ 
  public function onEnable(){
    $this->getLogger()->info(C::YELLOW ."Loading all necessary core components.");
  if($this->config()->developerMode() !== true){
    $this->getLogger()->info(C::AQUA ."is Running in". C::GREEN ." Public". C::AQUA ." Mode.");
    $this->getServer()->getNetwork()->setName(C::RED ."Plexus". C::AQUA ." Studio");
  } elseif($this->config()->developerMode() === true){
    $this->getLogger()->info(C::AQUA ."is Running in". C::RED ." Development". C::AQUA ." Mode.");
    $this->getServer()->getNetwork()->setName(C::RED ."Plexus". C::AQUA ." Dev");
  if($this->config()->testingMode() === true){
    $this->getLogger()->info( C::AQUA ."is Running in". C::YELLOW ." Testing" . C::AQUA ." Mode");
   }
  }
  if($this->load() === true){
    $this->getLogger()->info(C::AQUA ."is". C::GREEN ." Online");
  } else {
    $this->onDisable();
   }
  }

  /*
   * Load
   * ===============================
   * - Loads Events and Tasks
   * - Creates Npc's and UI's
   * ===============================
   */
  public function load(){
  try {
    $this->task["border"] = new \Plexus\utils\Tasks\BorderTask($this);
    $this->task["text"] = new \Plexus\utils\Tasks\FloatingTextTask($this);

    $this->getServer()->getPluginManager()->registerEvents(new \Plexus\Events($this), $this);
    $this->getServer()->getScheduler()->scheduleRepeatingTask(new \Plexus\utils\TaskHandler($this), 20);

  foreach($this->config()->npcData as $key => $data){
    $npc = new \Plexus\utils\NPC($data[0], $data[1], $data[2], $data[3], $this->getRandSkin());
    $this->npc[$npc->getEid()] = $npc;
  }
    return true;
  }
  catch(Exception $e){
    return false;  
   }
  }

  /*
   * Create UI 
   * ===============================
   * - Creates All UI used for the server. 
   * ===============================
   */
  public function createUI(){
  //TODO
  }

  /*
   * Random Skin 
   * ===============================
   * picks a number between $min and $max,
   * checks if that skin is being used,
   * if not returns skin. 
   * ===============================
   */
  public function getRandSkin(){
    $rand = rand(1, 21);
  if(in_array($rand, $this->skins)){
    return $this->getRandSkin();
  } else {
    $this->skins[] = $rand;
    $s = new \Plexus\utils\Skin();
    return $s->getSkinFromFile($this->getDataFolder() . "skins/" . $rand . ".png");
   }
  }

  /*
   * Spawn
   * ===============================
   * - Spawns the player at hub spawn
   * ===============================
   */
  public function spawn($player){
    $player->teleport(\pocketmine\Server::getInstance()->getLevelByName($this->config()->spawn())->getSafeSpawn());
  }
  
  /*
   * Config
   * ===============================
   * - Returns Config
   * ===============================
   */
  public function config(){
    return new \Data\Config(); 
  }

  /*
   * Disable
   * ===============================
   * - Kicks all online players
   * - If server is running it stops it
   * ===============================
   */
  public function onDisable(){
    $this->getLogger()->info(C::AQUA ."is". C::RED ." Offline.");
    $players = $this->getServer()->getOnlinePlayers();
  foreach($players as $player){
    $player->close("", C::AQUA ."Plexus Has closed, We will be back shortly.");
  }
  if($this->config()->forceShutdown() === true && $this->getServer()->isRunning() === true){
   $this->getLogger()->info(C::RED ."Server is still running, Shutting Down Server.");
   $this->getServer()->forceShutdown();
   }
  }
}