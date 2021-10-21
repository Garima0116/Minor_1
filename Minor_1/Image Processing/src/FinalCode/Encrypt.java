

import java.io.IOException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.String;
import javax.imageio.ImageIO;



public class Encrypt {
	
	private BufferedImage secretImage =null, targetImage=null;
	

	public Encrypt(mosaic obj, String secret) throws IOException {       
		
		
		targetImage=obj.outputMosaic;
		
		File  hImage;
		
		try //Images import
		{
		                 
         hImage = new File(secret);
		 secretImage = ImageIO.read(hImage);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		int[][][] targetRGB = removeLSB(targetImage);
		int[][][] secretRGB = removeLSB(secretImage);
		combineMSB(secretRGB, targetRGB);
		
	}
	
	
	
	
	
	// removing LSB from both images
	private int[][][] removeLSB(BufferedImage Image) {
		
		 
		int Height = Image.getHeight(); 
		int Width  = Image.getWidth();
		
		int[][][] rgb = new int[Width][Height][3];
		
		for(int y=0 ; y<Height ; y++)		
		{
			for(int x=0 ; x<Width ; x++)
			{
				int c = Image.getRGB(x,y);// c will return the colour of that pixel specified by x,y coordinates
				rgb[x][y][2] = (c>>4) & 0x0000000F;// gets red component from c
				rgb[x][y][1] = (c>>12)& 0x0000000F;// gets green component from c 
				rgb[x][y][0] = (c>>20)& 0x0000000F;// gets blue component from c
				
				
				
			}	
		}
		
		
		
		return(rgb);
	}
		
		
		
		
// combining MSB of both images with positioning secret image's MSB and target image's LSB
private void combineMSB(int[][][] secretRGB, int[][][] targetRGB) throws IOException {		

	int tWidth = targetRGB.length;
	int tHeight = targetRGB[0].length; 
	int[] rBlue = new int[tHeight*tWidth];
	int[] rGreen = new int[tHeight*tWidth];
	int[] rRed= new int[tHeight*tWidth];
	
	int num=0;
		for(int y=0 ; y<tHeight ; y++)		
		{
			for(int x=0 ; x<tWidth ; x++)
			{
				rBlue[num] = (targetRGB[x][y][2]<<4) | secretRGB[x][y][2];
				rGreen[num] = (targetRGB[x][y][1]<<12) | secretRGB[x][y][1]<<8;
				rRed[num] = (targetRGB[x][y][0]<<20) | secretRGB[x][y][0]<<16;
				
				num++;
			}	
		}
		
		new CreateImage(rRed,rGreen,rBlue,tHeight,tWidth);
		
		
		
		

}




}