// write your code here
var bImg = new SimpleImage("dinos.png");
var fImg = new SimpleImage("drewRobert.png");

function applyBackgroundImage(fImg, bImg) {
    
    var newImg = new SimpleImage(fImg.getWidth(), fImg.getHeight());
    
    for (var pixel of fImg.values()) {
        
        var x = pixel.getX();
        var y = pixel.getY();
        
        if (pixel.getGreen() > pixel.getRed() + pixel.getBlue()) {
            var bgPixel = bImg.getPixel(x, y);
            newImg.setPixel(x, y, bgPixel);
        }
        else {
            newImg.setPixel(x, y, pixel);
        }
        
    }
    
    return newImg;
}

print(applyBackgroundImage(fImg, bImg));
