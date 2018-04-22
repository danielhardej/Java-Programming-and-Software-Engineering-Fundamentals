// write your code here
var img = new SimpleImage("duke_blue_devil.png");
print(img);


function swapPixelColor (pixel) {
    
    if ((pixel.getGreen() == 255) && (pixel.getRed() == 255) && (pixel.getBlue() == 255)) {
        // If white, do nothing
    }
    //else if ((pixel.getGreen() == 51) && (pixel.getRed() == 0) && (pixel.getBlue() == 227)) {
        // Swap blue pixels to yellow
    //    
    //}
    else {
        // Anything else, do nothing!
        pixel.setRed(255);
        pixel.setGreen(255);
        pixel.setBlue(0);
    }
    
}

for (var pixel of img.values()) {
    
    swapPixelColor(pixel);
    
}

print(img);