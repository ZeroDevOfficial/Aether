<?php 
namespace Data;

use pocketmine\utils\TextFormat as C;

/* 
 * Config for Plexus Core.
 */

 class Config
{
  
  public $border = 130;
  private $spawn = "hub";
  private $dev = true;
  private $testing = true;
  private $forceShutdown = true;
  private $staff_only = false;

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
 
  /* { var } | npc's data */
  public $npcData = array(
    'npc' => array(C::AQUA ."Welcome", '2', '23', '-2'),
    'npc1' => array("NPC1", '-4', '25', '-22'),
    'npc2' => array("NPC2", '4', '25', '-22'),
    'npc3' => array("NPC3", '4', '25', '-13'),
    'npc4' => array("NPC4", '-4', '25', '-13'),
  );
}
