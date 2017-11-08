<?php 

declare(strict_types=1);

namespace Plexus;

use Plexus\Main;
use pocketmine\Player;

class PlexusPlayer {

  public $plugin;
  public $player;
  public $coins = 0;
  public $kills = 0;
  public $deaths = 0;

  public function __construct(Main $plugin, Player $player){
    $this->plugin = $plugin;
    $this->player = $player;
  if(!is_file($this->getConfig())){ 
    $this->create();
  } else {
    $config = yaml_parse_file($this->getConfig());
    $this->coins = $config['coins'];
    $this->kills = $config['kills'];
    $this->deaths = $config['deaths'];
   }
  }

  public function getPlugin() : Main {
    return $this->plugin;    
  }

  public function getConfig() : string {
    return $this->getPlugin()->getDataFolder() .'players/'. strtolower($this->player->getName()) . '.yml';    
  }

  public function getCoins() : int {
    return $this->coins; 
  }

  public function getKills() : int {
    return $this->kills; 
  }

  public function getDeaths() : int {
    return $this->deaths;
  }
 
  public function save() : void {
    yaml_emit_file($this->getConfig(), ['username' => strtolower($this->player->getName()), 'coins' => $this->getCoins(), 'kills' => $this->getKills(), 'deaths' => $this->getDeaths()]);
  }

  public function create() : void {
    $this->kills = 0;
    $this->deaths = 0;
    $this->save();
  }

  public function playSound(int $sound) : void {
    $pk = new \pocketmine\network\mcpe\protocol\LevelSoundEventPacket();
    $pk->sound = $sound;
    $pk->position = $this->player->getPosition();
    $pk->extraData = -1;
    $pk->pitch = 1;
    $this->player->dataPacket($pk);
  }
}