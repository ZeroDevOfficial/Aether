<?php

namespace Plexus;

use pocketmine\plugin\PluginBase;
use pocketmine\Server;

class Main extends PluginBase
{

  /* { var array } | player data */ 
  public $player = [];
  /* { var array } | npc's */ 
  public $npc = [];
  /* { var array } | skins */
  public $skins = [];
  /* { var array } | tasks */
  public $tasks = [];

  /* { function } | plugin enable */ 
  public function onEnable(){
    $this->getLogger()->info($this->lang()->load);
  if($this->config()->developerMode() == true){
    $this->getLogger()->info($this->lang()->dev_mode);
  if($this->config()->testingMode() == true){
    $this->getServer()->getNetwork()->setName($this->lang()->server_dev_name);
    $this->getLogger()->info($this->lang()->testing_mode);
    $this->load();
  }
   } else {
    $this->getServer()->getNetwork()->setName($this->lang()->server_public_name);
    $this->getLogger()->info($this->lang()->no_dev_mode);
    $this->load();
   }
  }

  /* { function } | loads tasks, events, and other things*/
  public function load(){
  //Events
    $this->getServer()->getPluginManager()->registerEvents(new \Plexus\Events($this), $this);
  
  //Tasks
    $this->task["border"] = new \Plexus\utils\Tasks\BorderTask($this);
    $this->getServer()->getScheduler()->scheduleRepeatingTask(new \Plexus\utils\TaskHandler($this), 20);

  //Npc's
  foreach($this->config()->npcData as $key => $data){
    $npc = new \Plexus\utils\NPC($data[0], $data[1], $data[2], $data[3], $this->getRandSkin());
    $this->npc[$npc->getEid()] = $npc;
   }
  }

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

  /* { function } | teleports the player to spawn */
  public function spawn($player){
    $player->teleport(\pocketmine\Server::getInstance()->getLevelByName($this->config()->spawn())->getSafeSpawn());
  }

  /* { function } | returns config file for plugin */
  public function config(){
    return new \Data\Config(); 
  }
 
  /* { function } | returns the lang file for the plugin */
  public function lang(){
    return new \Data\Lang(); 
  }

  /* { function } | plugin disable */
  public function onDisable(){
    $this->getLogger()->info($this->lang()->shutdown);
    $players = $this->getServer()->getOnlinePlayers();
  foreach($players as $player){
    $player->close("", $this->lang()->shutdown_message);
  }
  if($this->config()->forceShutdown() === true && $this->getServer()->isRunning() === true){
   $this->getLogger()->info($this->lang()->force_shutdown);
   $this->getServer()->forceShutdown();
   }
  }
}