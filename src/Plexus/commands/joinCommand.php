<?php

declare(strict_types=1);

namespace Plexus\commands;

//Pocketmine
use pocketmine\Player;
use pocketmine\utils\TextFormat as C;
use pocketmine\command\Command;
use pocketmine\command\CommandSender;
use pocketmine\command\defaults\VanillaCommand;

//Plexus
use Plexus\Main;

class joinCommand extends VanillaCommand {
    
  private $plugin;

  public function __construct(Main $plugin){
    $this->plugin = $plugin;
    parent::__construct('join', 'sends a join game ui', '/join');
    $this->setPermission('plugins.command');
  }

  public function execute(CommandSender $sender, $alias, array $args) : bool {
  if($sender instanceof Player){
    $buttons = array(new \pocketmine\form\MenuOption('Game 1.'), new \pocketmine\form\MenuOption(C::GRAY .'Game 2.'), new \pocketmine\form\MenuOption('Quit!'));
    $sender->sendForm(new class('join a game', 'would you like to join?', $buttons) extends \pocketmine\form\MenuForm {
      public function onSubmit(Player $sender): ?\pocketmine\form\Form{
        $selectedOption = $this->getSelectedOption()->getText();
      if($selectedOption === 'Game 1.'){
        $main = \Plexus\Main::getInstance();
        $main->games['gameTestWorld']->join($sender);
      }
        return null;
      }
    }, true);
    return true;
  } else {
    $sender->sendMessage(C::RED .'Command must be run in-game!');
    return false;     
   }
  }
}