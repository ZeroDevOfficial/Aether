<?php 
namespace Data;

use pocketmine\utils\TextFormat as C;

/* 
 * Config for Plexus Core.
 */

 class Config
{
  
  public $max_players = 60;
  public $border = 130;
  private $spawn = "hub";
  private $dev = true;
  private $testing = true;
  private $forceShutdown = true;
  private $staff_only = false;
  
  /* { function } | max players allowed on the server. */
  public function maxPlayers(){
    return $this->max_players;
  }

  /* { function } | border */
  public function getBorderSize(){
    return $this->border;
  }
  
  /* { function } | spawn/lobby name */
  public function spawn(){
    return $this->spawn;
  }
  
  /* { function } | developer mode */
  public function developerMode(){
    return $this->dev;
  }
  
  /* { function } | testing mode */
  public function testingMode(){
    return $this->testing;
  }
  
  /* { function } | force shutdown */
  public function forceShutdown(){
    return $this->forceShutdown;
  }
  
  /* { function } | staff only */
  public function staffOnly(){
    return $this->staff_only;
  }

  /* { function } | spawns */
  public $spawns = array(
    'spawn1' => array('x' => "-6", 'z' => "-7"),
    'spawn2' => array('x' => "10", 'z' => "-8"),
    'spawn3' => array('x' => "7", 'z' => "-3"),
    'spawn4' => array('x' => "3", 'z' => "6"),
    'spawn5' => array('x' => "-5", 'z' => "9"), 
  );
 
  /* { function } | npc's data */
  public $npcData = array(
    'npc' => array("welcome", '1045', '117', '-594'),
    'npc1' => array("NPC1", '1041', '116', '-629'),
    'npc2' => array("NPC2", '1044', '116', '-629'),
    'npc3' => array("NPC3", '1047', '116', '-629'),
    'npc4' => array("NPC4", '1050', '116', '-629'),
    'npc5' => array("NPC5", '1053', '116', '-629'),
  );
}
