<?php 

namespace Plexus\utils;

use Plexus\Main;
use pocketmine\Player;

class PlayerData {

  /* { var } | plugin */
  public $plugin;
  /* { var } | player */
  public $player;
  /* { var } | kills */
  public $kills = 0;
  /* { var } | deaths */
  public $deaths = 0;

  /* { constructor } */
  public function __construct(Main $plugin, Player $player){
    $this->plugin = $plugin;
    $this->player = $player;
  if(!is_file($this->getConfig())){ 
    $this->create();
  } else {
    $config = yaml_parse_file($this->getConfig());
    $this->rank = $config["rank"];
    $this->last_login = $config["last_login"];
    $this->kills = $config["kills"];
    $this->deaths = $config["deaths"];
   }
  }

  public function getPlugin(){
    return $this->plugin;    
  }

  public function getConfig(){
    return $this->getPlugin()->getDataFolder() ."players/". strtolower($this->player->getName()) . ".yml";    
  }

  /* { function } | gets players kills */
  public function getKills(){
    return $this->kills; 
  }

  public function getDeaths(){
    return $this->deaths;
  }
 
  public function save(){
    yaml_emit_file($this->getConfig(), ["username" => strtolower($this->player->getName()), "kills" => $this->getKills(), "deaths" => $this->getDeaths()]);
  }

  public function create(){
    $this->kills = 0;
    $this->deaths = 0;
    $this->save();  
  }
}