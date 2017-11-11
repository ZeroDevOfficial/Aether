<?php 

declare(strict_types=1);

namespace Data;

use pocketmine\utils\TextFormat as C;

 class Config {

  /** Server Settings */
  private $spawn = "Alazar";
  private $forceShutdown = true;
  private $version = '0.1.3.8.8';
  
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

  public function commandArrayData() : array {
    $main = \Plexus\Main::getInstance();
    return array(
      'hub' => new \Plexus\commands\hubCommand($main),
      'welcome' => new \Plexus\commands\welcomeCommand($main),
      'xyz' => new \Plexus\commands\xyzCommand($main)
    );
  }

  public function taskArrayData() : array {
    $main = \Plexus\Main::getInstance();
    return array(
      'borderTask' => new \Plexus\tasks\BorderTask($main),
      'floatingTextTask' => new \Plexus\tasks\FloatingTextTask($main),
      'showHideTask' => new \Plexus\tasks\ShowHideTask($main),
      'xyzTask' => new \Plexus\tasks\xyzTask($main)
    );
  }

  public function floatingTextArrayData() : array {
    return array(
      // 'name' =        [$x, $y, $z, $temp_text];
      'welcome' => array(856, 82, 1148, 'welcome')
    );
  }

  public function npcArrayData() : array {
    return array(
      // 'name' =        [$x, $y, $z, $yaw, $pitch, $displayName, $name];
      'welcome' => array(852, 82, 1148, 0, 0, C::AQUA .'Welcome'),
      'npc1' => array(836, 84, 1174, 0, 0, C::YELLOW .'Shop'),
      'npc2' => array(826, 84, 1164, 90, 0, C::YELLOW .'Shop'),
      'npc3' => array(826, 84, 1128, 90, 0, C::YELLOW .'Shop'),
      'npc4' => array(836, 84, 1118, 180, 0, C::YELLOW .'Shop'),
      'npc5' => array(872, 84, 1118, 180, 0, C::YELLOW .'Shop'),
      'npc6' => array(882, 84, 1128, 270, 0, C::YELLOW .'Shop'),
      'npc7' => array(882, 84, 1164, 270, 0, C::YELLOW .'Shop'),
      'npc8' => array(872, 84, 1174, 0, 0, C::YELLOW .'Shop'),

      'parkour1' => array(815, 81, 1135, 180, 0, C::DARK_PURPLE .'Parkour'),
      'parkour2' => array(811, 81, 1135, 180, 0, C::DARK_PURPLE .'Parkour'),

      'lookout' => array(858, 153, 1104, 180, 0, C::AQUA .'Look at This Spawn'),
      'to_spawn' => array(872, 106, 1080, 270, 0, C::YELLOW .'Return to Spawn')
    );
  }
}
