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

  /* { function } | loads tasks, games, events, and other things*/
  public function load(){
    $this->getServer()->getPluginManager()->registerEvents(new \Plexus\Events($this), $this);
    $this->getServer()->getScheduler()->scheduleRepeatingTask(new \Plexus\utils\Tasks\BorderTask($this), 20);

    $s = new \Plexus\utils\Skin();
  foreach($this->config()->npcData as $key => $data){
    $skin = $s->getSkinFromFile($this->getDataFolder() . "skins/" . rand(1, 18) . ".png");
    //$skin = $s->getSkinFromFile($this->getDataFolder() . "skins/" . $data[0] . ".png");
    $npc = new \Plexus\utils\NPC($data[0], $data[1], $data[2], $data[3], $skin);
    $this->npc[$npc->getEid()] = $npc;
   }
   return;
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
}