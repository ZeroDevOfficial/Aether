<?php

namespace Plexus\tasks\games;

//Pocketmine
use pocketmine\Player;
use pocketmine\utils\TextFormat as C;

//Plexus
use Plexus\Main;

class sw {

  private $plugin;
  private $mapName;
  private $players;
  private $slots;
  private $pts;

  //Time
  private $time;
  private $countDown;
  private $maxGameTime;

  //Game Status
  private $pvpStatus;
  private $gameStatus;

  private const COUNTDOWN = 0;
  private const RUNNING = 1;
  private const STOPPING = 3;

  private const NO_PVP = 0;
  private const PVP = 1;

  private const PREFIX = C::GRAY .'['. C::AQUA . C::BOLD .'SkyWars'. C::RESET . C::GRAY .'] '. C::WHITE;
    
  public function __construct(Main $plugin, string $mapName, int $slots, int $countDown, int $maxGameTime){
    $this->plugin = $plugin;
    $this->mapName = $mapName;
    $this->players = [];
    $this->slots = $slots;//so I can do 12 or 24 depending on the map
    $this->pts = 1;//will change that later
    $this->time = 0;
    $this->countDown = $countDown;
    $this->maxGameTime = $maxGameTime;
    $this->pvpStatus = self::NO_PVP;
    $this->gameStatus = self::COUNTDOWN;
  }

  public function getPlugin() : Main {
    return $this->plugin;
  }

  public function getGameStatus(){
    return $this->gameStatus;
  }

  public function join(Player $player){
  if($player instanceof Player){
  if($this->players !== $this->slots){
  if(!isset($this->players[$player->getName()])){
    $level = $this->getPlugin()->getServer()->getLevelByName('Waiting lobby map');
  if($level instanceof \pocketmine\level\Level){
    $player->teleport(new \pocketmine\level\Position(66, 114, -1467, $level));
    $this->players[$player->getName()] = $player;
  foreach($this->players as $name => $p){
    $p->sendMessage(self::PREFIX . C::DARK_PURPLE . $player->getName() . C::AQUA .' has joined the game'. C::WHITE .' | '. C::DARK_PURPLE . count($this->players) . C::AQUA .'/'. C::DARK_PURPLE . $this->slots);
   }
  } else {
    $player->sendMessage(self::PREFIX . C::RED .'Sorry but something went wrong here :(');
   }
  } else {
    $player->sendMessage(self::PREFIX . C::RED .'You are already in this game');
  }
  } else {
    $player->sendMessage(self::PREFIX . C::RED .'Sorry but this game is full.');
    }
   }
  }

  public function quit(Player $player, bool $alert, bool $teleport){
  if($player instanceof Player){
    unset($this->players[$player->getName()]);
  if($teleport === true){
    $player->teleport($this->getPlugin()->getServer()->getDefaultLevel()->getSafeSpawn());
  }
  if($alert === true){
  foreach($this->players as $name => $p){
    $p->sendMessage(self::PREFIX . C::DARK_PURPLE . $player->getName() . C::AQUA .' has left the game'. C::WHITE .' | '. C::DARK_PURPLE . count($this->players) . C::AQUA .'/'. C::DARK_PURPLE . $this->slots);
     }
    }
   }
  }

  public function tick(){
  if($this->getGameStatus() === self::COUNTDOWN || $this->getGameStatus() === self::RUNNING){
    $this->time++;
  }
  if($this->getGameStatus() === self::COUNTDOWN && $this->time >= $this->countDown){
    $this->start();
  }
  if($this->getGameStatus() === self::RUNNING && $this->time >= $this->maxGameTime){
  foreach($this->players as $name => $p){
  foreach($this->getPlugin()->getServer()->getDefaultLevel()->getPlayers() as $pl){
    $p->sendMessage(self::PREFIX . C::DARK_PURPLE . $this->mapName . C::AQUA  .' has ended, no winners!');
  }
    $p->sendMessage(self::PREFIX . C::AQUA .'No Winners!');
    $this->getPlugin()->getPlayer($p)->playSound(57);
    $this->stop();
   }
  }
  if($this->getGameStatus() === self::COUNTDOWN){
  foreach($this->players as $name => $p){
  if(count($this->players) >= $this->pts){
    $p->sendPopup(self::PREFIX . C::AQUA . 'starts in ' . C::DARK_PURPLE . date('i:s', ($this->countDown - $this->time)));
  if($this->time % 30 === 0){
    $p->sendMessage(self::PREFIX . C::AQUA . 'starts in ' . C::DARK_PURPLE . date('i:s', ($this->countDown - $this->time)));
    $this->getPlugin()->getPlayer($p)->playSound(70);  
  }
  if(($this->countDown - $this->time) <= 10){
    $this->getPlugin()->getPlayer($p)->playSound(70);
   } 
  } else {
    $p->sendPopup(C::RED .'Need '. C::AQUA . $this->pts . C::RED .' players to start.');
    $this->time = 0;
    }
   }
  }
  if($this->getGameStatus() === self::RUNNING){
  foreach($this->players as $name => $p){
  if(count($this->players) >= $this->pts){
    $p->sendPopup(self::PREFIX . C::DARK_PURPLE . date('i:s', ($this->maxGameTime - $this->time)) . C::AQUA .' until match ends.');
  if($this->time % 30 === 0){
    $p->sendMessage(self::PREFIX . C::DARK_PURPLE . date('i:s', ($this->maxGameTime - $this->time)) . C::AQUA .' until match ends.');
    $this->getPlugin()->getPlayer($p)->playSound(70);  
  }
  if(($this->maxGameTime - $this->time) <= 20){
    $this->getPlugin()->getPlayer($p)->playSound(70);
   }
  } else { 
    $this->stop();
     }
    }
   }
  }

  private function start(){
    $this->gameStatus = self::RUNNING;
    $this->time = 0;
  foreach($this->players as $name => $p){
    $level = $this->getPlugin()->getServer()->getLevelByName($this->mapName);
  if($level instanceof \pocketmine\level\Level){
    $p->addTitle(C::AQUA . 'let the game begin!', '', 60, 20);
    $this->getPlugin()->getPlayer($p)->playSound(55);
    $p->setHealth($p->getMaxHealth());
    $p->teleport($level->getSafeSpawn());
    $this->getPlugin()->getPlayer($p)->playSound(57);
  } else {
    $this->quit($p, false, true);
    }
   }
  }

  private function stop(){
    $this->gameStatus = self::STOPPING;
    $this->getPlugin()->info(self::PREFIX . C::AQUA .'Stopping '. C::DARK_PURPLE . $this->mapName . C::AQUA  .'!');
  foreach($this->players as $name => $p){
    $this->quit($p, false, true);
  }
    $this->players = [];
    $this->time = 0;
    $this->gameStatus = self::COUNTDOWN;
    $this->getPlugin()->info(self::PREFIX . C::DARK_PURPLE . $this->mapName . C::AQUA  .' has been reset!');
  }
}