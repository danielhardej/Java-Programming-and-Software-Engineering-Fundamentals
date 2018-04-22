// write your code here
var owenimage = new SimpleImage("astrachan.jpg");

function cropImage(image, width, height) {
    var croppedImage = new SimpleImage(width, height);
    try {
        for (var pixel of croppedImage.values()) {
            var x = pixel.getX();
            var y = pixel.getY();
            originalpx = image.getPixel(x,y);
            croppedImage.setPixel(x,y, originalpx);
        }
    }
    catch(err) {
        print("Error: Unable to crop image.")
    }
        
    return croppedImage;
}

var croppedimg = cropImage(owenimage, 2000, 300);
print(owenimage);
print(croppedimg);
