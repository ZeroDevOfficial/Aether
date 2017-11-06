<?php 

declare(strict_types=1);

namespace Plexus\Commands;

use pocketmine\Player;
use pocketmine\command\Command;
use pocketmine\command\CommandSender;

use pocketmine\command\defaults\VanillaCommand;

use Plexus\Main;

class welcomeCommand extends VanillaCommand {
    
  private $plugin;

  public function __construct(Main $plugin){
    $this->plugin = $plugin;
    parent::__construct('welcome', 'testing cmd for ui', '/welcome');
    $this->setPermission('plugins.command');
  }

  public function execute(CommandSender $sender, $alias, array $args) : bool {
  if($sender instanceof Player){
    $sender->sendMessage('Welcome '. $sender->getName());
    return true;
  } else {
    $sender->sendMessage(C::RED .'Command must be run in-game!');
    return false;     
   }
  }
}