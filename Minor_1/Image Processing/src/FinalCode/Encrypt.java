package Minor;



import java.io.IOException;

import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.String;
import javax.imageio.ImageIO;



public class Encrypt {
	
	private BufferedImage secretImage =null, targetImage=null;
	public BufferedImage resultImage=null;

	public Encrypt(mosaic obj, String secret) throws IOException {       
		
		
		targetImage=obj.outputMosaic;
		
		File  hImage;
		
		try //Images import
		{
		                 
         hImage = new File(secret);// path of the image 
		 secretImage = ImageIO.read(hImage);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		int[][][] targetRGB = removeLSBTarget(targetImage);
		int[][][] secretRGB = removeLSBSecret(secretImage);
		combineMSB(secretRGB, targetRGB);
		
	}
	
	
	
	
	
	
	private int[][][] removeLSBTarget(BufferedImage Image) {
		
		 
		int Height = Image.getHeight(); // getting height and width of each image
		int Width  = Image.getWidth();
		
		int[][][] rgb = new int[Width][Height][3];
		
		for(int y=0 ; y<Height ; y++)		// running the loop to cover all pixels of the image to be hidden in a rowwise manner
		{
			for(int x=0 ; x<Width ; x++)
			{
				int c = Image.getRGB(x,y);// c will return the colour of that pixel specified by x,y coordinates
				/*rgb[x][y][2] = (c>>4) & 0x0000000F;// gets red component from c
				rgb[x][y][1] = (c>>12)& 0x0000000F;// gets green component from c 
				rgb[x][y][0] = (c>>20)& 0x0000000F;*/// gets blue component from c
				rgb[x][y][2] = (c) & 0x000000E0;// gets blur component from c
				rgb[x][y][1] = (c) & 0x0000E000;// gets green component from c 
				rgb[x][y][0] = (c) & 0x00E00000;// gets red component from c
				
				
			}	
		}
		
		//Image = createPixels(Red, Green, Blue, Height, Width);
		
		return(rgb);
	}

	
	private int[][][] removeLSBSecret(BufferedImage Image) {
		
		 
		int Height = Image.getHeight(); // getting height and width of each image
		int Width  = Image.getWidth();
		
		int[][][] rgb = new int[Width][Height][3];
		
		for(int y=0 ; y<Height ; y++)		// running the loop to cover all pixels of the image to be hidden in a rowwise manner
		{
			for(int x=0 ; x<Width ; x++)
			{
				int c = Image.getRGB(x,y);// c will return the colour of that pixel specified by x,y coordinates
				/*rgb[x][y][2] = (c>>4) & 0x0000000F;// gets red component from c
				rgb[x][y][1] = (c>>12)& 0x0000000F;// gets green component from c 
				rgb[x][y][0] = (c>>20)& 0x0000000F;*/// gets blue component from c
				rgb[x][y][2] = (c>>3) & 0x0000001F;// gets blue component from c
				rgb[x][y][1] = (c>>3)& 0x00001F00;// gets green component from c 
				rgb[x][y][0] = (c>>3)& 0x001F0000;// gets red component from c
				
				
			}	
		}
		
		//Image = createPixels(Red, Green, Blue, Height, Width);
		
		return(rgb);
	}

		
		
		

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
				rBlue[num] = (targetRGB[x][y][2]) | secretRGB[x][y][2];
				rGreen[num] = (targetRGB[x][y][1]) | secretRGB[x][y][1];
			    rRed[num] = (targetRGB[x][y][0]) | secretRGB[x][y][0];
				
				num++;
			}	
		}
		
		CreateImage temp = new CreateImage(rRed,rGreen,rBlue,tHeight,tWidth);
		resultImage = temp.result;
		
		
		
}




}