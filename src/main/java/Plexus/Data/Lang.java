package Plexus.Data;

import cn.nukkit.utils.TextFormat;

import java.util.*;

public class Lang 
{

  public static String eng(String lang){
  switch(lang){
  case "offline":
    return TextFormat.AQUA +"is "+ TextFormat.RED +" Offline."; 
  case "online":
    return TextFormat.AQUA +"is "+ TextFormat.GREEN +" Online";
   }
   return "Lang not found";
  }
}
