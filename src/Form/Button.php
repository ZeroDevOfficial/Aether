<?php

namespace Form;

class Button implements \JsonSerializable{
 
  const IMAGE_TYPE_PATH = "path";
  const IMAGE_TYPE_URL = "url";

  private $text;
  private $imageType;
  private $imagePath;

  public function __construct(string $text, ?string $imageType = null, ?string $imagePath = null){
    $this->text = $text;
    $this->imageType = $imageType;
    $this->imagePath = $imagePath;
  }
    
  public function getText() : string{
    return $this->text;
  }

  public function hasImage() : bool{
    return $this->imageType !== null and $this->imagePath !== null;
  }
    
  public function getImageType() : ?string{
    return $this->imageType;
  }

  public function getImagePath() : ?string{
    return $this->imagePath;
  }
    
  public function jsonSerialize(){
	$json = [
	  "text" => $this->text
	];
  if($this->hasImage()){
	$json["image"] = [
      "type" => $this->getImageType(),
      "data" => $this->getImagePath()
    ];
  }
    return $json;
  }
}