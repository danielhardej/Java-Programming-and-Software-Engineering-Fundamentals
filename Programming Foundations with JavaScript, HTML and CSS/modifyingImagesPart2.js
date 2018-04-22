// write your code here
var img = new SimpleImage("drewgreen.png");
print(img);


function swapRedGreen (pixel) {
    
    var redVal = pixel.getRed();
    var greenVal = pixel.getGreen();
    
    pixel.setRed(greenVal);
    pixel.setGreen(redVal);
    
}

for (var pixel of img.values()) {
    
    swapRedGreen(pixel);
    
}

print(img);