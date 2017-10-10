<?php 

namespace Data;

use pocketmine\utils\TextFormat as C;

/* 
 * Basic Lang File for the core;
 */

 class Lang 
{
  //>> Startup and Shutdown <<
  public $load = C::AQUA . "is Loading all necessary core components.";
  
   public $no_dev_mode = C::AQUA ."is Running in". C::GREEN ." Public". C::AQUA ." Mode.";
   public $dev_mode = C::AQUA ."is Running in". C::RED ." Development". C::AQUA ." Mode.";
   public $testing_mode = C::AQUA ."is Running in". C::YELLOW ." Testing" . C::AQUA ." Mode. Games, Tasks, and Events Will be Loaded.";
   public $events = C::AQUA ."Loading Events...";
   public $tasks = C::AQUA ."Loading Tasks...";
   public $games = C::AQUA ."Loading Games...";
   
   public $has_loaded = C::AQUA ."is Now". C::GREEN ." Online.";
   public $shutdown = C::AQUA ."is Now". C::RED ." Offline.";
   public $force_shutdown = C::RED ."Server is still running, Shutting Down Server.";
   
   //>> Server <<
   public $server_offline_name = C::RED ."Plexus". C::GRAY ." Offline";
   public $server_public_name = C::RED ."Plexus". C::AQUA ." Studio";
   public $server_dev_name = C::RED ."Plexus". C::AQUA ." Dev";

  //>> Other <<
  public $break = C::RESET . C::WHITE . "\n";
  public $loading_server = C::AQUA ."Server is still loading...";

  //>> Player Close Messages <<
  public $shutdown_message = C::AQUA ."Plexus Has closed, We will be back shortly.";
  public $is_not_op = C::AQUA ."Plexus is in Staff Only Mode.";
}