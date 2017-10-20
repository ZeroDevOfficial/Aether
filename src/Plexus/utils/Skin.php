<?php 

namespace Plexus\utils;

class Skin {

    public function getSkinFromFile(string $filename) : string{
        $im = imagecreatefrompng($filename);
        list($width, $height) = getimagesize($filename);
        $bytes = "";
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

}