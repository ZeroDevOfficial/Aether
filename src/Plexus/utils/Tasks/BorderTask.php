<?php

namespace Plexus\utils\Tasks;

class BorderTask {
    
  /* { var } | plugin */
  private $plugin;
  
   /* { constructor } */
  public function __construct(\Plexus\Main $plugin){
    $this->plugin = $plugin;
  }
  
  /* { function } | returns plexus main file */
  public function getPlugin(){
    return $this->plugin;
  }
  
  /* { function } | move task */
  public function run(){
    $players = $this->getPlugin()->getServer()->getOnlinePlayers();
  foreach($players as $player){
    $spawn = $player->getLevel()->getSpawnLocation();
  if($player->getLevel()->getFolderName() === $this->getPlugin()->config()->spawn()){
  if(round($player->getPosition()->distance(new \pocketmine\math\Vector3($spawn->getX(), $spawn->getY(), $spawn->getZ()))) >= $this->getPlugin()->config()->border or $player->getY() <= 0){
    $this->getPlugin()->spawn($player);
    $player->addTitle($this->getPlugin()->lang()->border_reached, $this->getPlugin()->lang()->border_end_of_world, 50, 90, 40);
     }
    }
   } 
  }

}