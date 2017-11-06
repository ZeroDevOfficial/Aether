<?php

declare(strict_types=1);

namespace Plexus\utils\Tasks;

use pocketmine\utils\TextFormat as C;

use Plexus\Main;

class FloatingTextTask {
    
  private $plugin;
  private $x = 852;
  private $y = 84;
  private $z = 1144;
  
  public function __construct(Main $plugin){
    $this->plugin = $plugin;
    $this->text = new \pocketmine\level\particle\FloatingTextParticle(new \pocketmine\math\Vector3($this->x, $this->y, $this->z), "", C::BLACK .">======================<\n". C::DARK_PURPLE ."\nWelcome to ". C::YELLOW ."Plexus Studio");
  }
  
  public function getPlugin() : Main {
    return $this->plugin;
  }

  public function run() : void {
    $players = $this->getPlugin()->getServer()->getOnlinePlayers();
    $level = \pocketmine\Server::getInstance()->getLevelByName($this->getPlugin()->config()->spawn());
  foreach($players as $player){
  if(round($player->getPosition()->distance(new \pocketmine\math\Vector3($this->x, $this->y, $this->z))) <= 10){      
  if(isset($this->getPlugin()->player[$player->getName()])){
    $stats = $this->getPlugin()->player[$player->getName()];
    $this->text->setText(
    C::DARK_PURPLE ."Profile Username: ". C::YELLOW . $player->getName() ."\n".
    C::DARK_PURPLE ."Players Online: ". C::YELLOW . count($players) ."\n". 
    C::DARK_PURPLE ."Coins: ". C::YELLOW . $stats->getCoins() ."\n". 
    C::DARK_PURPLE ."Kills: ". C::YELLOW . $stats->getKills() ."\n". 
    C::DARK_PURPLE ."Deaths: ". C::YELLOW . $stats->getDeaths() ."\n\n". 
    C::BLACK .">======================<");
  } else {
    $this->text->setText(
      C::DARK_PURPLE ."Profile Username: ". C::YELLOW . $player->getName() ."\n".
      C::DARK_PURPLE ."Players Online: ". C::YELLOW . count($players) . "\n".
      C::DARK_PURPLE ."Coins: ". C::YELLOW ."Error Finding Stats.\n".
      C::DARK_PURPLE ."Kills: ". C::YELLOW ."Error Finding Stats.\n".
      C::DARK_PURPLE ."Deaths: ". C::YELLOW ."Error Finding Stats.\n".
      C::DARK_PURPLE ."K\D: ". C::YELLOW ."Error Finding Stats.\n\n". 
      C::BLACK .">======================<");
  }
  if($level) 
    $level->addParticle($this->text);
    }
   }
  }
}