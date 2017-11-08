<?php 

declare(strict_types=1);

namespace Plexus\utils;

use Plexus\Main;

class Skin {

  public $skins = [];

  public function getSkinFromFile($filename){
    $im = imagecreatefrompng($filename);
    list($width, $height) = getimagesize($filename);
    $bytes = '';
  for($y = 0; $y < $height; $y++){
  for($x = 0; $x < $width; $x++){
    $argb = imagecolorat($im, $x, $y);
    $a = ((~((int) ($argb >> 24))) << 1) & 0xff;
    $r = ($argb >> 16) & 0xff;
    $g = ($argb >> 8) & 0xff;
    $b = $argb & 0xff;
    $bytes .= chr($r) . chr($g) . chr($b) . chr($a);
   }
  }
     //echo "BYTES=".strlen($bytes)."\n";
     return $bytes;
  }

  public function getRandSkin(){
    $rand = rand(1, 21);
  if(in_array($rand, $this->skins)){
    return $this->getRandSkin();
  } else {
    $this->skins[] = $rand;
    return $this->getSkinFromFile(Main::getInstance()->getDataFolder() . 'skins/' . $rand . '.png');
   }
  }
}