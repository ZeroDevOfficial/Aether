<?php

declare(strict_types=1);

namespace Plexus\utils\Tasks;

use pocketmine\utils\TextFormat as C;

use Plexus\Main;

class xyzTask {

  private $plugin;

  public function __construct(Main $plugin){
    $this->plugin = $plugin;
  }
  
  public function getPlugin() : Main {
    return $this->plugin;
  }

  public function run() : void {
    $players = $this->getPlugin()->getServer()->getOnlinePlayers();
  foreach($players as $player){
  if(isset($this->getPlugin()->show_xyz[$player->getName()])){
    $x = round($player->x, 2);
    $y = round($player->y, 2);
    $z = round($player->z, 2);
    $yaw = round($player->yaw, 2);
    $pitch = round($player->pitch, 2);
    $player->sendPopup(C::DARK_PURPLE .'x: '. C::YELLOW . $x . C::DARK_PURPLE .' | y: '. C::YELLOW . $y . C::DARK_PURPLE .' | z: '. C::YELLOW . $z . C::DARK_PURPLE .' | yaw: '. C::YELLOW . $yaw . C::DARK_PURPLE .' | pitch: '. C::YELLOW . $pitch);
    }
   }
  }  
}