<?php 

declare(strict_types=1);

namespace Plexus\commands;

use pocketmine\Player;
use pocketmine\utils\TextFormat as C;
use pocketmine\command\Command;
use pocketmine\command\CommandSender;

use pocketmine\command\defaults\VanillaCommand;

use Plexus\Main;

class hubCommand extends VanillaCommand {
    
  private $plugin;

  public function __construct(Main $plugin){
    $this->plugin = $plugin;
    parent::__construct('hub', 'sends a player back to spawn', '/hub');
    $this->setPermission('plugins.command');
  }

  public function execute(CommandSender $sender, $alias, array $args) : bool {
  if($sender instanceof Player){
    $this->plugin->spawn($sender);
    $sender->addTitle(C::DARK_PURPLE .'Welcome back to Spawn.', C::YELLOW . $sender->getName(), 50, 90, 40);
    return true;
  } else {
    $sender->sendMessage(C::RED .'Command must be run in-game!');
    return false;     
   }
  }
}