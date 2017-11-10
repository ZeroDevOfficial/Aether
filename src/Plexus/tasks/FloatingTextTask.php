<?php

declare(strict_types=1);

namespace Plexus\tasks;

use pocketmine\utils\TextFormat as C;

use Plexus\Main;

class FloatingTextTask {
    
  private $plugin;
  private $tick = 0;
  
  public function __construct(Main $plugin){
    $this->plugin = $plugin;
  }
  
  public function getPlugin() : Main {
    return $this->plugin;
  }

  public function run() : void {
    $this->discordUpdate();
    $ft = $this->getPlugin()->ft['welcome'];
    $players = $this->getPlugin()->getServer()->getOnlinePlayers();
    $text = C::BLACK .'>======================<' . 
    Main::BR . Main::BR. C::DARK_PURPLE .'Welcome to '. C::AQUA .'Plexus Studio'. 
    Main::BR . C::DARK_PURPLE ."Players Online: ". C::AQUA . count($players) . C::BLACK . 
    Main::BR  . Main::BR .">======================<";
  foreach($players as $player){
  if(round($player->getPosition()->distance(new \pocketmine\math\Vector3($ft->x, $ft->y, $ft->z))) <= 10){
    $ft->setText($text);
    }
   }
  }

  public function discordUpdate(){
    $players = count($this->getPlugin()->getServer()->getOnlinePlayers());
  if($players >= 1){
    $this->tick++;
  if($this->tick === 3600){//EveryHour //3600
    $type = 'server';
    $br = "\n";
    $p = ' players: "'. $players .'"';
    $msg = '```css'. $br .'[========================]'. $br . $br .'Plexus Studio(){'. $br . $p . $br .'}' . $br . $br .'[========================]'. $br .'```';
    $this->getPlugin()->discord($msg, $type);
    $this->tick = 0;
    }
   }
  }
}