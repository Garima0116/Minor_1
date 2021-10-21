import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.io.FileWriter;
public class decrypt1 {
	

	public static void main(String args[]) throws IOException {
		
		
		BufferedImage  hidImage = null; // for importing image 
		
		File hImage; 
		ArrayList<Integer> hidR = new ArrayList<Integer>(); // for storing the rgb data
		ArrayList<Integer> hidG = new ArrayList<Integer>();
		ArrayList<Integer> hidB = new ArrayList<Integer>();

		
		try //Images import
		{
		
		 
		 hImage   = new File("H:/hp/eclipse/programs/Image Processing/src/Output Images/Image is hidden here.png");//path of image to be decrypted
		 hidImage = ImageIO.read(hImage);
		
		}
		catch(IOException e)
		{
			System.out.println("----  ERROR!!!!! ----\n");
			System.out.println("No Image Found\n");
			e.printStackTrace();
			System.out.println("\n\n-----  RESTART -----");
			System.exit(0);
		}
		
		
		int hiHeight = hidImage.getHeight();
		int hiWidth  = hidImage.getWidth();
		
		for(int y=0 ; y<hiHeight ; y++)		// running the loop to cover all pixels of the image to be hidden in a rowwise manner
		{
			for(int x=0 ; x<hiWidth ; x++)
			{
				Color c = new Color(hidImage.getRGB(x,y), true);// Color class will return the colour of that pixel specified by x,y coordinates
				int Red = (c.getRed());// gets red component from c
				int Green = (c.getGreen());// gets green component from c 
				int Blue = (c.getBlue());// gets blue component from c
				//add the components to array list
				hidR.add(Red);
				hidG.add(Green);
				hidB.add(Blue);
			//	System.out.println("Done"+num);
				//num++;
			}	
		}
		
		Integer[] RData = new Integer[hidR.size()];//r value in Integer 
		RData = hidR.toArray(RData);
		
		Integer[] GData = new Integer[hidG.size()];//g value in Integer
		GData = hidG.toArray(GData);
			
		Integer[] BData = new Integer[hidB.size()];//b value in integer
		BData = hidB.toArray(BData);
		
		String[] RHString = new String[hidR.size()];
		String[] GHString = new String[hidG.size()];
		String[] BHString = new String[hidB.size()];
		
		
		
		
		int n = 0;
		for(int y=0 ; y<hiHeight ; y++)
		{
			for(int x=0 ; x<hiWidth ; x++)
			{
				//file.write(n+".("+RData[n]+" "+GData[n]+" "+BData[n]+")\n");
				RHString[n] = Integer.toBinaryString(RData[n]);
				RData[n]= Integer.parseInt(RHString[n]);
				RHString[n] = String.format("%08d",RData[n]);
				
				
				GHString[n] = Integer.toBinaryString(GData[n]);
				GData[n]= Integer.parseInt(GHString[n]);
				GHString[n] = String.format("%08d",GData[n]);
				
				
				BHString[n] = Integer.toBinaryString(BData[n]);
				BData[n]= Integer.parseInt(BHString[n]);
				BHString[n] = String.format("%08d",BData[n]);
				
				
				
				
				RHString[n] = RHString[n].substring(4,8)+"0000";
				GHString[n] = GHString[n].substring(4,8)+"0000";
				BHString[n] = BHString[n].substring(4,8)+"0000";
				
				//converting binary string to decimal integer using Integer.parseInt() 
				RData[n]= Integer.parseInt(RHString[n],2);
				GData[n]= Integer.parseInt(GHString[n],2);
				BData[n]= Integer.parseInt(BHString[n],2);
				
				//file.write(n+".("+RData[n]+" "+GData[n]+" "+BData[n]+")\n");
				
				
				n++;

				

				
			}
		}
		
		n=0;
		for(int y=0 ; y<hiHeight ; y++)
		{
			for(int x=0 ; x<hiWidth ; x++) {
			
				Color newC = new Color(RData[n], GData[n],BData[n]);
				hidImage.setRGB(x,y,newC.getRGB());
				n++;
				
			}
		}
		try
		{
			File outputfile = new File("H:/hp/eclipse/programs/Image Processing/src/Reterieved Images/reterieved.jpg");// where to store the decrypted image
			ImageIO.write(hidImage, "jpg", outputfile);
			
			
			
		}
		catch(IOException e)
		{
			System.out.println("----  ERROR!!!!! ----");
			e.printStackTrace();
		}
	
System.out.println("Done");
	}
}
