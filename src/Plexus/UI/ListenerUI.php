<?php

declare(strict_types=1);

namespace Plexus\UI;

use pocketmine\event\Listener;
use pocketmine\network\mcpe\protocol\ModalFormResponsePacket;
use pocketmine\network\mcpe\protocol\InteractPacket;

use pocketmine\Player;
use pocketmine\utils\TextFormat as C;

use Plexus\Main;

class ListenerUI implements Listener {

  private $plugin;
  private $id = [];

  public function __construct(Main $plugin){
    $this->plugin = $plugin;
  }

  public function getPlugin() : Main {
    return $this->plugin;
  }

  /*public function onPacketReceived(\pocketmine\event\server\DataPacketReceiveEvent $e){
    $player = $e->getPlayer();
    $pk = $e->getPacket();
  if($pk instanceof InteractPacket && $pk->action === InteractPacket::ACTION_MOUSEOVER && isset($this->getPlugin()->npc[$pk->target])){
    $npc = $this->getPlugin()->npc[$pk->target];
  if($npc->getName() === ""){
  }
  }
  }*/

  public function createUIArray(){
    $this->welcome();
    $this->shopMain();
  }

  public function welcome(){
    $id = $this->getRandId();
    $ui = new \Plexus\UI\SimpleUI($id);
    $ui->addTitle("Welcome");
    $ui->addButton('About', 1, 'https://i.imgur.com/TgOsY7i.png');
    $ui->addButton('Staff List', 1, 'https://i.imgur.com/eiA3BZ5.png');
    $ui->addButton('Changelog', 1, 'https://i.imgur.com/xUSenna.png');
    $ui->addButton('Cancel', 1, 'https://i.imgur.com/PcJEnVy.png');
    $this->getPlugin()->ui['welcome'] = $ui;
  }

  public function shopMain(){
    $id = $this->getRandId();
    $ui = new \Plexus\UI\SimpleUI($id);
    $ui->addTitle("Shop");
    $ui->addButton('Coming Soon', 1, 'https://i.imgur.com/lqBIiXL.png');
    $ui->addButton('Cancel', 1, 'https://i.imgur.com/PcJEnVy.png');
    $this->getPlugin()->ui['shop_main'] = $ui;
  }

  public function getRandId(){
    $rand = rand(1, 1000);
  if(in_array($rand, $this->id)){
    return self::getRandId();
  } else {
    $this->id[] = $rand;
    return $rand;
   }
  }
}