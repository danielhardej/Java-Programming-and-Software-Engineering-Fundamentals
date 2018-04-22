// write your code here
// write your code here
var owenimage = new SimpleImage("astrachan.jpg");
var duvallimage = new SimpleImage("duvall.jpg");

function cropImage(image, width, height) {
    var croppedImage = new SimpleImage(width, height);
    
    for (var pixel of croppedImage.values()) {
        var x = pixel.getX();
        var y = pixel.getY();
        originalpx = image.getPixel(x,y);
        croppedImage.setPixel(x,y, originalpx);
  
    }
    return croppedImage;
}

function clearBits(colorval) {
    var newColorVal = Math.floor(colorval/16)*16;
    return newColorVal;
}

function chop2hide(image) {
    // For the image that will be hiding another image
    for (var pixel of image.values()) {
        var newRed = clearBits(pixel.getRed());
        pixel.setRed(newRed);
        pixel.setGreen(clearBits(pixel.getGreen()));
        pixel.setBlue(clearBits(pixel.getBlue()));
    }
    return image;
}

function shift(image) {
    // For the  image that will be hidden
    for (var pixel of image.values() ) {
        pixel.setRed(pixel.getRed() / 16 );
        pixel.setGreen(pixel.getGreen() / 16);
        pixel.setBlue(pixel.getBlue() / 16);
    }
    return image;
}

function combine(show, hide) {
    var combinedImage = new SimpleImage(show.getWidth(), show.getHeight());
    
    for (var pixel of combinedImage.values()) {
        var x = pixel.getX();
        var y = pixel.getY();
        
        var showpx = show.getPixel(x, y);
        var hidepx = hide.getPixel(x, y);
        
        pixel.setRed(showpx.getRed() + hidepx.getRed());
        pixel.setGreen(showpx.getGreen() + hidepx.getGreen());
        pixel.setBlue(showpx.getBlue() + hidepx.getBlue());
    }
    return combinedImage;
}

function newPV(p, v) {
    if (p + v > 255) {
        print("Error: RGB color vals more than 255");
    }
    else {
        
    }
}

// Hiding Duvall in Owen
print(owenimage);
var owencropped = cropImage(owenimage, duvallimage.getWidth(), duvallimage.getHeight());
var owenChopped = chop2hide(owencropped);

var duvallShifted = shift(duvallimage);
var combinedImage = combine(owenChopped, duvallShifted);
print(combinedImage);
