
import java.io.FileWriter;
import java.io.IOException;

import java.awt.image.BufferedImage;
import java.io.File;

import java.lang.String;


import javax.imageio.ImageIO;
import java.util.Scanner;

public class encryptBit {
	

	
	
	
	
	
	
	
	

	
	public static void main(String args[]) throws IOException {
		
	
               
		
		int num=0;
		int Alpha=255;
		BufferedImage orImage = null, hidImage = null, resultantImage=null; // for importing image 
		File oImage, hImage, rImage;
		
		
        
        
        
       
        
		
		try //Images import
		{
		
		 
                 
         oImage = new File("H:/hp/eclipse/programs/Image Processing/src/Input Images/Target2.jpg");// path of the image to be encrypted(secret image)
		 orImage = ImageIO.read(oImage);

		                  
         hImage = new File("H:/hp/eclipse/programs/Image Processing/src/Output Images/output.jpg");// path of the image used for hiding(target image which os output from mosaic)
		 hidImage = ImageIO.read(hImage);
		
		}
		catch(Exception e)
		{
			System.out.println("----  ERROR!!!!! ----\n");
			System.out.println("No Image Found\n");
			e.printStackTrace();
			System.out.println("\n\n-----  RESTART -----");
			System.exit(0);
		}
		
		int orHeight = orImage.getHeight(); // getting height and width of each image
		int orWidth  = orImage.getWidth();
		int hiHeight = hidImage.getHeight();
		int hiWidth  = hidImage.getWidth();
		
		int[] hBlue = new int[hiHeight*hiWidth]; 
		int[] hGreen = new int[hiHeight*hiWidth]; 
		int[] hRed = new int[hiHeight*hiWidth]; 
		num=0;
		for(int y=0 ; y<hiHeight ; y++)		// running the loop to cover all pixels of the image to be hidden in a rowwise manner
		{
			for(int x=0 ; x<hiWidth ; x++)
			{
				int c = hidImage.getRGB(x,y);// Color class will return the colour of that pixel specified by x,y coordinates
				hBlue[num] = (c>>4) & 0x0000000F;// gets red component from c
				hGreen[num] = (c>>12)& 0x0000000F;// gets green component from c 
				hRed[num] = (c>>20)& 0x0000000F;// gets blue component from c
				
				num++;
			}	
		}
		
		int[] oBlue = new int[orHeight*orWidth]; 
		int[] oGreen = new int[orHeight*orWidth]; 
		int[] oRed = new int[orHeight*orWidth]; 
		
		num=0;
		for(int y=0 ; y<orHeight ; y++)		// running the loop to cover all pixels of the image to be hidden in a rowwise manner
		{
			for(int x=0 ; x<orWidth ; x++)
			{
				int c = orImage.getRGB(x,y);// Color class will return the colour of that pixel specified by x,y coordinates
				 oBlue[num] = (c>>4) & 0x0000000F;// gets red component from c
				 oGreen[num] = (c>>12) & 0x0000000F;// gets green component from c 
				 oRed[num] = (c>>20)& 0x0000000F;// gets blue component from c
				
				num++;
			}	
		}
		
		int[] rBlue = new int[orHeight*orWidth]; 
		int[] rGreen = new int[orHeight*orWidth]; 
		int[] rRed = new int[orHeight*orWidth];
		
		num=0;
		for(int y=0 ; y<hiHeight ; y++)		
		{
			for(int x=0 ; x<hiWidth ; x++)
			{
				rBlue[num] = (hBlue[num]<<4) | oBlue[num];
				rGreen[num] = (hGreen[num]<<12) | oGreen[num]<<8;
				rRed[num] = (hRed[num]<<20) | oRed[num]<<16;
				
				num++;
			}	
		}
		
		resultantImage=createPixels(rRed,rGreen,rBlue,hiHeight,hiWidth);
		
		try
		{
			File outputfile = new File("H:/hp/eclipse/programs/Image Processing/src/Output Images/Image is hidden here.png"
					+ "");// where to store the decrypted image
			ImageIO.write(resultantImage, "png", outputfile);
			
			
			
		}
		catch(IOException e)
		{
			System.out.println("----  ERROR!!!!! ----");
			e.printStackTrace();
		}
		
System.out.println("Done");
	}








public static BufferedImage createPixels(int[] Red, int[] Green, int[] Blue, int height, int width) {
	int pixel;
	
	BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	int num=0;
	for(int y=0 ; y<height ; y++)		// running the loop to cover all pixels of the image to be hidden in a rowwise manner
	{
		for(int x=0 ; x<width ; x++)
		{
			pixel= 0xFF000000 | Red[num] | Green[num] | Blue[num];
			result.setRGB(x, y, pixel);
			num++;
		}	
	}
	
	return(result);
}

}