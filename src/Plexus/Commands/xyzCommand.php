<?php 

declare(strict_types=1);

namespace Plexus\Commands;

use pocketmine\Player;
use pocketmine\utils\TextFormat as C;
use pocketmine\command\Command;
use pocketmine\command\CommandSender;

use pocketmine\command\defaults\VanillaCommand;

use Plexus\Main;

class xyzCommand extends VanillaCommand {
    
  private $plugin;

  public function __construct(Main $plugin){
    $this->plugin = $plugin;
    parent::__construct('xyz', 'turns on xyz for player', '/xyz');
    $this->setPermission('plugins.command');
  }

  public function execute(CommandSender $sender, $alias, array $args) : bool {
  if($sender instanceof Player){
  switch($args[0]){
  case 'on':
  if(!isset($this->plugin->show_xyz[$sender->getName()])){
    $this->plugin->show_xyz[$sender->getName()] = $sender;
    $sender->sendMessage(C::DARK_PURPLE .'You have turned on '. C::YELLOW .'XYZ');
  } else {
    $sender->sendMessage(C::RED .'it seems you already have this on, try turning it off and on again!');
  }
  break;
  case 'off':
  if(isset($this->plugin->show_xyz[$sender->getName()])){
    unset($this->plugin->show_xyz[$sender->getName()]);
    $sender->sendMessage(C::DARK_PURPLE .'You have turned off '. C::YELLOW .'XYZ');
  } else {
    $sender->sendMessage(C::RED .'You have not turned this on yet!');
  }
  break;
  }
    return true;
  } else {
    $sender->sendMessage(C::RED .'Command must be run in-game!');
    return false;     
   }
  }
}