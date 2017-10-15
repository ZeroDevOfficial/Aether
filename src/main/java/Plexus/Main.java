package BladeMC;

import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase{
{
  /* { var bool } | has server loaded true or false */ 
  public static has_loaded = false;
  /* { var int } | time before players can join */ 
  public static cool_down = 5;
  /* { var array } | npc data */ 
  public static npc = "gay";
  /* { function } | plugin enable */ 
  public void onEnable(){
  load();
  }
  
  /* { function } | plugin disable */
  public void isable(){
    $this->getLogger()->info($this->lang()->shutdown);
    $players = $this->getServer()->getOnlinePlayers();
  foreach($players as $player){
    $player->close("", $this->lang()->shutdown_message);
  }
  if($this->config()->forceShutdown() === true && $this->getServer()->isRunning() === true){
   $this->getLogger()->info($this->lang()->force_shutdown);
   $this->getServer()->forceShutdown();
   }
  }
  
  /* { function } | loads tasks, games, events, and other things*/
  public function loadTEG(){
    $this->getServer()->getScheduler()->scheduleRepeatingTask(new \Plexus\Tasks\Load($this), 20);
    $this->getServer()->getPluginManager()->registerEvents(new \Plexus\Events($this), $this);
  /* { npc } | creates spawn npc's */ 
  foreach($this->config()->npcData($this) as $key => $data){
    //Ex. | array(-5, 11, 11, "Test");
    $npc = new \Plexus\NPC($data[0], $data[1], $data[2], $data[3]);
    $this->npc[$npc->getEid()] = $npc;  
   }
  }
  /* { function } | returns config file for plugin */
  public function config(){
    return new \Data\Config(); 
  }
 
  /* { function } | returns the lang file for the plugin */
  public function lang(){
    return new \Data\Lang(); 
  }
 
  /* { function } | returns the npc array */
  public function getNpc(){
    return $this->npc;
  }
}
?>
