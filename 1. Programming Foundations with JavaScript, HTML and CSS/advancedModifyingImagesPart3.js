var img = new SimpleImage("smallpanda.png");

function setBlack (pixel) {
    pixel.setRed(0);
    pixel.setBlue(0);
    pixel.setGreen(0);
}

function addBorder (image, borderThickness) {
    
    for (var pixel of image.values()) {
        
        var x = pixel.getX();
        var y = pixel.getY();
        
        if (x <= borderThickness || x >= image.getWidth()-borderThickness) {
            setBlack(pixel);
        }
        
        if (y <= borderThickness || y >= image.getHeight()-borderThickness) {
            setBlack(pixel);
        }
    }
    
}

var BORDER_THICKNESS = 5

addBorder(img, BORDER_THICKNESS);

print (img);
