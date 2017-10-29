<?php 
namespace Data;

use pocketmine\utils\TextFormat as C;

/* 
 * Config for Plexus Core.
 */

 class Config
{
  
  /** @var string | hub name */
  private $spawn = "Alazar";
  private $dev = true;
  /** @var bool | Testing mode */
  private $testing = true;
  /** @var bool | Force shutdown */
  private $forceShutdown = true;
  /** @var bool | Staff only */
  private $staff_only = false;
  /** @var array | Npc's data */
  public $npcData = array(
    'npc0' => array(C::AQUA ."Welcome", '857', '82', '1143', '180', '0'),
    'npc1' => array("NPC1", '872', '84', '1118', '180', '0'),
    'npc2' => array("NPC2", '882', '84', '1128', '270', '0'),
    'npc3' => array("NPC3", '882', '84', '1164', '270', '0'),
    'npc4' => array("NPC4", '872', '84', '1174', '0', '0'),
    'npc5' => array("NPC5", '836', '84', '1174', '0', '0'),
    'npc6' => array("NPC6", '826', '84', '1164', '70', '0'),
    'npc7' => array("NPC7", '826', '84', '1128', '70', '0'),
    'npc8' => array("NPC8", '836', '84', '1118', '180', '0'),
  );
  
  /*
   * Spawn
   * ===============================
   * - Returns spawn/lobby name
   * ===============================
   */
  public function spawn(){
    return $this->spawn;
  }
  
  /*
   * Developer Mode
   * ===============================
   * - Returns dev = true || false
   * ===============================
   */
  public function developerMode(){
    return $this->dev;
  }
  
  /*
   * Testing Mode
   * ===============================
   * - Returns testing = true || false
   * ===============================
   */
  public function testingMode(){
    return $this->testing;
  }
  
  /*
   * ForceShutdown
   * ===============================
   * - Returns forceShutdown = true || false
   * ===============================
   */
  public function forceShutdown(){
    return $this->forceShutdown;
  }
  
  /*
   * StaffOnly
   * ===============================
   * - Returns staff = true || false
   * ===============================
   */
  public function staffOnly(){
    return $this->staff_only;
  }

}
