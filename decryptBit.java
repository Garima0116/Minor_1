

import java.io.IOException;

import java.awt.image.BufferedImage;
import java.io.File;

import java.lang.String;



import javax.imageio.ImageIO;
import java.util.Scanner;

public class decryptBit {
	
	
	public static void main(String args[]) throws IOException {
	

       Scanner scan= new Scanner(System.in);
		
		int num=0;
		
		BufferedImage encryptedImage = null,  decryptedImage=null; // for importing image 
		File eImage, dImage;
		
		System.out.println("Enter the path of the encrypted image: ");
        String encrypted=scan.nextLine();

       
        
      
        
		
		try //Images import
		{
		
		 
                 
         eImage = new File(encrypted); 
		 encryptedImage = ImageIO.read(eImage);

		                  
       
		
		}
		
		
		catch(Exception e)
		{
			System.out.println("----  ERROR!!!!! ----\n");
			System.out.println("No Image Found\n");
			e.printStackTrace();
			System.out.println("\n\n-----  RESTART -----");
			System.exit(0);
		}
		
		int eHeight = encryptedImage.getHeight(); // getting height and width of each image
		int eWidth  = encryptedImage.getWidth();
		
		
		int[] eBlue = new int[eHeight*eWidth]; 
		int[] eGreen = new int[eHeight*eWidth]; 
		int[] eRed = new int[eHeight*eWidth]; 
		num=0;
		for(int y=0 ; y<eHeight ; y++)		// running the loop to cover all pixels of the image to be hidden in a rowwise manner
		{
			for(int x=0 ; x<eWidth ; x++)
			{
				int c = encryptedImage.getRGB(x,y);// Color class will return the colour of that pixel specified by x,y coordinates
				eBlue[num] = (c & 0x000000F)<<4;// gets red component from c
				eGreen[num] = (c & 0x00000F00)<<4;// gets green component from c 
				eRed[num] = (c & 0x000F0000)<<4;// gets blue component from c
				
				num++;
			}	
		}
		
		
		
		
		
		decryptedImage=createPixels(eRed,eGreen,eBlue,eHeight,eWidth);
		System.out.println("Enetr the path of the image to be saved  : ");
        String saveThis=scan.nextLine();
		
        try
		{
			File outputfile = new File(saveThis);// where to store the decrypted image
			ImageIO.write(decryptedImage, "png", outputfile);
				
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
