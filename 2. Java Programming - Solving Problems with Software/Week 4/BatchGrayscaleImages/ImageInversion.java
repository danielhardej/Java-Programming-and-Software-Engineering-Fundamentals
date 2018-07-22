
/**
 * Write a description of ImageInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class ImageInversion {
    public ImageResource makeInversion(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int red = inPixel.getRed();
            int green = inPixel.getGreen();
            int blue = inPixel.getBlue();
            
            pixel.setRed(255-red);
            pixel.setGreen(255-green);
            pixel.setBlue(255-blue);
        }
        return outImage;
    }
    
    public void selectAndConvertMultiple() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource negImage = makeInversion(inImage);
            negImage.draw();
            String fileName = inImage.getFileName();
            String newFileName = fileName + "-negative";
            negImage.setFileName(newFileName);
            negImage.save();
            //saveNewGrayscaleImage(inImage, grayimage);
        }
    }
}
