<?php

namespace Plexus;

use pocketmine\plugin\PluginBase;
use pocketmine\Server;

class Main extends PluginBase
{

  public $has_loaded = false;
  public $cool_down = 10;

  public function onEnable(){
    $this->load();
  }

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

  public function load(){
    $this->getServer()->getNetwork()->setName($this->lang()->server_offline_name);
    $this->getLogger()->info($this->lang()->load);
  if($this->config()->developerMode() == true){
    $this->getLogger()->info($this->lang()->dev_mode);
  if($this->config()->testingMode() == true){
    $this->getServer()->getNetwork()->setName($this->lang()->server_dev_name);
    $this->getLogger()->info($this->lang()->testing_mode);
    $this->loadTEG();
  }
   } else {
    $this->getServer()->getNetwork()->setName($this->lang()->server_public_name);
    $this->getLogger()->info($this->lang()->no_dev_mode);
    $this->loadTEG();
   }
  }

  public function loadTEG(){
    $this->getServer()->getScheduler()->scheduleRepeatingTask(new \Plexus\Tasks\Load($this), 20);
    $this->getServer()->getPluginManager()->registerEvents(new \Plexus\Events($this), $this);
  }

  public function config(){
    return new \Data\Config(); 
  }

  public function lang(){
    return new \Data\Lang(); 
  }

}
?>