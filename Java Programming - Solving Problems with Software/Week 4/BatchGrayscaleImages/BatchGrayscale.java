
/**
 * Write a description of BatchGrayscale here.
 * 
 * @author Daniel Hardej
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class BatchGrayscale {
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
    
    public void saveNewGrayscaleImage(ImageResource inImage, ImageResource grayimage) {
        String fileName = inImage.getFileName();
        String newFileName = fileName+"-grayscale";
        grayimage.setFileName(newFileName);
        grayimage.save();
    }
    
    public void convertAndSaveMultiple() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource grayimage = makeGray(inImage);
            saveNewGrayscaleImage(inImage, grayimage);
        }
    } 
}
