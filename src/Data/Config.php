<?php 

declare(strict_types=1);

namespace Data;

use pocketmine\utils\TextFormat as C;

 class Config {

  /** Server Settings */
  private $spawn = "Alazar";
  private $forceShutdown = true;
  private $version = '0.1.3.8.1';
  
  public function spawn() : string {
    return $this->spawn;
  }
  public function forceShutdown() : bool {
    return $this->forceShutdown;
  }
  public function version() : string {
    return $this->version;
  }
  
  public function info() : string {
    return C::YELLOW ."Plexus is a faction Server created by ZeroDevOfficial and Andrep0617\n\nThe server is running on PocketMine-MP\n\nPlugins developed by @ZeroDevOfficial";
  }
  
  public function staffList() : string {
    return C::YELLOW ."Owner:\n- ZeroDevOfficial\n\nHead Admin:\n- Andrep0617\n\nAdmins:\n- TheRoyalBlock\n\n\n\n". C::RED ."DO NOT ASK TO BE STAFF\n";
  }

  public function npcArrayData() : array {
    return array(
      // 'name' =        [$x, $y, $z, $yaw, $pitch, $displayName, $name];
      'welcome' => array(852, 82, 1148, 0, 0, C::AQUA .'Welcome', 'welcome'),
      'npc1' => array(836, 84, 1174, 0, 0, C::YELLOW .'Shop', 'shop')
    );
  }
}
