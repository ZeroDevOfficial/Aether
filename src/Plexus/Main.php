<?php

namespace Plexus;

use pocketmine\plugin\PluginBase;
use pocketmine\Server;

class Main extends PluginBase
{

  /* { var array } | local player data */ 
  public $player = [];
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

  /* { function } | picks a random spawn for the player */
  public function spawn($player){
    $rand = rand(1, 5);
    $spawns = $this->config()->spawns;
    $sr = "spawn" . $rand; 
    $player->teleport(\pocketmine\Server::getInstance()->getLevelByName($this->config()->spawn())->getSafeSpawn());
    $player->teleport(new \pocketmine\math\Vector3($spawns[$sr]['x'], 11, $spawns[$sr]['z']));
  }

  /* { function } | loads tasks, games, events, and other things*/
  public function load(){
    $this->getServer()->getPluginManager()->registerEvents(new \Plexus\Events($this), $this);

  foreach($this->config()->npcData as $key => $data){
    $s = new \Plexus\utils\Skin();
    $skin = $s->getSkinFromFile($this->getDataFolder() . "skins/" . $data[0] . ".png");
    $npc = new \Plexus\utils\NPC($data[0], $data[1], $data[2], $data[3], $skin);
    $this->npc[$npc->getEid()] = $npc;  
   }
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