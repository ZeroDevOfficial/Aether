<?php 

namespace Plexus\utils;

use Plexus\Main;
use pocketmine\Player;

class PlayerData {

  /** @var string | Plexus\Main */
  public $plugin;
  /** @var string | Player Name */
  public $player;
  /** @var int | Kills */
  public $kills = 0;
  /** @var int | Deaths */
  public $deaths = 0;

  /* 
   * Constructor
   */
  public function __construct(Main $plugin, Player $player){
    $this->plugin = $plugin;
    $this->player = $player;
  if(!is_file($this->getConfig())){ 
    $this->create();
  } else {
    $config = yaml_parse_file($this->getConfig());

    $this->kills = $config["kills"];
    $this->deaths = $config["deaths"];
   }
  }

  /*
   * Plugin
   * ===============================
   * - Returns $this->plugin = \Plexus\Main;
   * ===============================
   */
  public function getPlugin(){
    return $this->plugin;    
  }

  /*
   * Config
   * ===============================
   * - Returns Config
   * ===============================
   */
  public function getConfig(){
    return $this->getPlugin()->getDataFolder() ."players/". strtolower($this->player->getName()) . ".yml";    
  }

  /* 
   * Kills
   * ===============================
   * - Gets players kills
   * ===============================
   */
  public function getKills(){
    return $this->kills; 
  }

  /* 
   * Deaths
   * ===============================
   * - Gets players deaths
   * ===============================
   */
  public function getDeaths(){
    return $this->deaths;
  }
 
  /* 
   * Save
   * ===============================
   * - Saves the Config
   * ===============================
   */
  public function save(){
    yaml_emit_file($this->getConfig(), ["username" => strtolower($this->player->getName()), "kills" => $this->getKills(), "deaths" => $this->getDeaths()]);
  }

  /* 
   * Create
   * ===============================
   * - Creates new player stats
   * ===============================
   */
  public function create(){
    $this->kills = 0;
    $this->deaths = 0;
    $this->save();  
  }
}