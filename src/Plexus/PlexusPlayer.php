<?php

namespace Plexus;

//Pocketmine
use pocketmine\Player;
use pocketmine\utils\TextFormat as C;

//Plexus
use Plexus\Main;

class PlexusPlayer {

  private $player;
  private $plugin;
  private $config = null;

  private $name = '';
  private $logins = 0;
  private $lastLoggedIn = 'never';
  private $coins = 0;
  private $kills = 0;
  private $deaths = 0;

  public function __construct(Player $player, Main $plugin){
    $this->player = $player;
    $this->plugin = $plugin;

    $this->name = $this->player->getName();
  }

  public function getPlugin() : Main {
    return $this->plugin;
  }

  public function setup() : void {
  if(is_dir($this->getPlugin()->getDataFolder())){
  if(is_dir($this->getPlugin()->getDataFolder() .'/players/')){
    $player_file = $this->getPlugin()->getDataFolder() .'/players/'. strtolower($this->name) .'.json';
  if(file_exists($player_file)){
    $this->config = new \pocketmine\utils\Config($player_file, \pocketmine\utils\Config::JSON);
  if($this->checkConfig() === true){
    $this->updatePlayerVar();
  } else {
    $this->getNewDefaultConfig();
  }
  } else {
    $this->config = new \pocketmine\utils\Config($player_file, \pocketmine\utils\Config::JSON, $this->getDefaultConfig());
    $this->config->save();
    $this->setup();
   }
  } else {
    @mkdir($this->getPlugin()->getDataFolder() .'/players/');
    $this->setup();
  }
  } else {
    @mkdir($this->getPlugin()->getDataFolder());
    $this->setup();
  }
    $this->loggedIn();
  }

  private function updatePlayerVar() : void { 
    $this->logins = $this->config->get('logins');
    $this->coins = $this->config->get('coins');
    $this->kills = $this->config->get('kills');
    $this->deaths = $this->config->get('deaths');
    $this->saveData();
  }

  private function getDefaultConfig() : array {
    return array(
      'account-version' => $this->getPlugin()->getDescription()->getVersion(),
      'username' => $this->name,
      'logins' => 0,
      'last-logged-in' => array(date('h:i'), date('Y-m-d')),
      'coins' => 0,
      'kills' => 0,
      'deaths' => 0,
    );
  }

  private function getNewDefaultConfig() : void {
  //this is so if I add new things in the future it will update the config
    $new = array(
      'account-version' => $this->getPlugin()->getDescription()->getVersion(),
      'username' => $this->name,
      'logins' => $this->config->get('logins'),
      'last-logged-in' => array(date('h:i'), date('Y-m-d')),
      'coins' => $this->config->get('coins'),
      'kills' => $this->config->get('kills'),
      'deaths' => $this->config->get('deaths'),
    );
  foreach($new as $key => $i){
    $this->config->set($key, $i);
  }
    $this->config->save();
    $this->updatePlayerVar();
  }

  private function checkConfig() : bool {
  if($this->config->get('account-version') === $this->getPlugin()->getDescription()->getVersion()){
    return true;  
  } else {
    return false;
   }
  }

  public function saveData() : void {
    $this->config->set('username', $this->name);
    $this->config->set('logins', $this->logins);
    $this->config->set('last-logged-in', $this->lastLoggedIn);
    $this->config->set('coins', $this->coins);
    $this->config->set('kills', $this->kills);
    $this->config->set('deaths', $this->deaths);
    $this->config->save();
  }

  public function loggedIn(){
    $this->logins++;
    $this->lastLoggedIn = array(date('h:i'), date('Y-m-d'));
    $this->saveData();
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