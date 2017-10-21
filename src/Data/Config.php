<?php 
namespace Data;

use pocketmine\utils\TextFormat as C;

/* 
 * Config for Plexus Core.
 */

 class Config
{
  
  public $max_players = 60;
  public $border = 100;
  private $spawn = "hub";
  private $dev = true;
  private $testing = true;
  private $forceShutdown = true;
  private $staff_only = false;
  
  /* { var } | max players allowed on the server. */
  public function maxPlayers(){
    return $this->max_players;
  }

  /* { var } | border */
  public function getBorderSize(){
    return $this->border;
  }
  
  /* { var } | spawn/lobby name */
  public function spawn(){
    return $this->spawn;
  }
  
  /* { var } | developer mode */
  public function developerMode(){
    return $this->dev;
  }
  
  /* { var } | testing mode */
  public function testingMode(){
    return $this->testing;
  }
  
  /* { var } | force shutdown */
  public function forceShutdown(){
    return $this->forceShutdown;
  }
  
  /* { var } | staff only */
  public function staffOnly(){
    return $this->staff_only;
  }

  /* { var } | spawns */
  public $spawns = array(
    'spawn1' => array('x' => "-6", 'z' => "-7"),
    'spawn2' => array('x' => "10", 'z' => "-8"),
    'spawn3' => array('x' => "7", 'z' => "-3"),
    'spawn4' => array('x' => "3", 'z' => "6"),
    'spawn5' => array('x' => "-5", 'z' => "9"), 
  );
 
  /* { var } | npc's data */
  public $npcData = array(
    'npc1' => array("NPC1", "Hello", '6', '12', '13'),
    'npc2' => array("NPC2", "Hello", '1', '12', '13'),
    'npc3' => array("NPC3", "Hello", '-3', '12', '13'),
  );
}
