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

  public function __construct(Player $player, Main $plugin){
    $this->player = $player;
    $this->plugin = $plugin;
  }
}