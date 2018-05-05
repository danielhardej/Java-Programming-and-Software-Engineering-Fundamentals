
/**
 * Write a description of GrayscaleConvert here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class GrayscaleConvert {
    public ImageResource makeGray(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int red = inPixel.getRed();
            int green = inPixel.getGreen();
            int blue = inPixel.getBlue();
            int avg = (red + green + blue)/3;
            
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        return outImage;
    }
    
    public void convertMultiple() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource grayimg = makeGray(inImage);
            grayimg.draw();
        }
    }
    
    
    
    public void testMakeGray() {
        ImageResource ir = new ImageResource();
        ImageResource grayimg = makeGray(ir);
        grayimg.draw();
    }
}
