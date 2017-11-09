<?php

declare(strict_types=1);

namespace Plexus\tasks;

use pocketmine\utils\TextFormat as C;

use Plexus\Main;

class FloatingTextTask {
    
  private $plugin;
  
  public function __construct(Main $plugin){
    $this->plugin = $plugin;
  }
  
  public function getPlugin() : Main {
    return $this->plugin;
  }

  public function run() : void {
    $ft = $this->getPlugin()->ft['welcome'];
    $players = $this->getPlugin()->getServer()->getOnlinePlayers();
    $text = C::BLACK .'>======================<' . 
    Main::BR . Main::BR. C::DARK_PURPLE .'Welcome to '. C::AQUA .'Plexus Studio'. 
    Main::BR . C::DARK_PURPLE ."Players Online: ". C::AQUA . count($players) . C::BLACK . 
    Main::BR  . Main::BR .">======================<";
    $ft->setText($text);
  }
}