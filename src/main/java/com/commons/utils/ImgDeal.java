package com.commons.utils;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImgDeal {

	public static void zoomImageScale(File imageFile, String newPath, int newWidth) throws IOException {
        if(!imageFile.canRead()){
        	System.out.println("bunengread:"+imageFile.getName());
            return;
        }
        	
       BufferedImage bufferedImage = ImageIO.read(imageFile);
       if (null == bufferedImage) 
           return;
       
       int originalWidth = bufferedImage.getWidth();
       int originalHeight = bufferedImage.getHeight();
       double scale = (double)originalWidth / (double)newWidth;    // 缩放的比例
       
       int newHeight =  (int)(originalHeight / scale);

       zoomImageUtils(imageFile, newPath, bufferedImage, newWidth, newHeight);
   }  
	
	private static void zoomImageUtils(File imageFile, String newPath, BufferedImage bufferedImage, int width, int height)
            throws IOException{
        
         String suffix = "jpg";

        if(suffix != null && (suffix.trim().toLowerCase().endsWith("png") || suffix.trim().toLowerCase().endsWith("gif"))){
            BufferedImage to= new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
            Graphics2D g2d = to.createGraphics(); 
            to = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT); 
            g2d.dispose(); 
            
            g2d = to.createGraphics(); 
            Image from = bufferedImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING); 
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose(); 
            
            ImageIO.write(to, suffix, new File(newPath));
        }else{
            BufferedImage newImage = new BufferedImage(width, height, bufferedImage.getType());
            Graphics g = newImage.getGraphics();
            g.drawImage(bufferedImage, 0, 0, width, height, null);
            g.dispose();
            ImageIO.write(newImage, suffix, new File(newPath));
        }
	}
}
