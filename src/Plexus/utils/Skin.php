<?php 

declare(strict_types=1);

namespace Plexus\utils;

class Skin {

  public $skins = [];

  public function getSkinFromFile(string $filename) : int {
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
     imagedestroy($im);
     return $bytes;
  }

  public function getRandSkin(\Plexus\Main $plugin) : string {
    $rand = rand(1, 21);
  if(in_array($rand, $this->skins)){
    return $this->getRandSkin($plugin);
  } else {
    $this->skins[] = $rand;
    return $this->getSkinFromFile($plugin->getDataFolder() . 'skins/' . $rand . '.png');
   }
  }
}