var img = new SimpleImage("rodger.png");
print(img);

for (var pixel of img.values()) {
    
    var width = img.getWidth();
    var xVal = pixel.getX();
    
    if (xVal < width/3) {
        pixel.setRed(255);
    }
    else if ((xVal >= width/3) && (xVal <= 2*width/3)) {
        pixel.setGreen(255);
    }
    else if (xVal > 2*width/3) {
        pixel.setBlue(255);
    }
    
}

print(img);