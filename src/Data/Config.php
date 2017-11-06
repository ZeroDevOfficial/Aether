<?php 

declare(strict_types=1);

namespace Data;

use pocketmine\utils\TextFormat as C;

/* 
 * Config for Plexus Core.
 */

 class Config {

  /** Server Settings */
  private $spawn = "Alazar";
  private $forceShutdown = true;
  private $version = '0.1.3.7';

  /* Lang */
  public $info = C::YELLOW ."Plexus is a faction Server created by ZeroDevOfficial and Andrep0617\n\nThe server is running on PocketMine-MP\n\nPlugins developed by @ZeroDevOfficial";
  public $staffList = C::YELLOW ."Owner:\n- ZeroDevOfficial\n\nHead Admin:\n- Andrep0617\n\nAdmins:\n- TheRoyalBlock\n\n\n\n". C::RED ."DO NOT ASK TO BE STAFF\n";
  public $changelog = C::AQUA ."Update v0.1.3.7:". C::YELLOW ."\n- UI Changes\n- Added Stats to Floating Text\n- Floating Text no longer flickers\n- Finished Parkour\n- Started on World Protection.\n\n\n\n";

  public function spawn() : string {
    return $this->spawn;
  }

  public function forceShutdown() : bool {
    return $this->forceShutdown;
  }

  public function version() : string {
    return $this->version;
  }
}
