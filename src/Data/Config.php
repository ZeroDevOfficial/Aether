<?php 
namespace Data;

use pocketmine\utils\TextFormat as C;

/* 
 * Config for Plexus Core.
 */

 class Config
{
  
  public $max_players = 60;
  private $spawn = "hub";
  private $dev = true;
  private $testing = true;
  private $forceShutdown = true;
  private $staff_only = false;

  private $npc_data = [];
  
  /** @return int, max server players */
  public function maxPlayers(){
    return $this->max_players;
  }
  
  /** @return string, spawn name */
  public function spawn(){
    return $this->spawn;
  }
  
  /** @return bool, dev mode */
  public function developerMode(){
    return $this->dev;
  }
  
  /** @return bool, testing mode */
  public function testingMode(){
    return $this->testing;
  }
  
  /** @return bool, force shutdown */
  public function forceShutdown(){
    return $this->forceShutdown;
  }
  
  /** @return bool, staff only */
  public function staffOnly(){
    return $this->staff_only;
  }

  /** @return array, spawns */
  public $spawns = array(
    'spawn1' => array('x' => "-6", 'z' => "-7"),
    'spawn2' => array('x' => "10", 'z' => "-8"),
    'spawn3' => array('x' => "7", 'z' => "-3"),
    'spawn4' => array('x' => "3", 'z' => "6"),
    'spawn5' => array('x' => "-5", 'z' => "9"), 
  );
  
  public function npcData($plugin){
    return array(
      'npc1' => array(3, 11, 13, "Test"),
      'npc2' => array(1, 11, 13, "Test2"),
      );
  }
}
