import java.io.FileWriter;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.String;


import javax.imageio.ImageIO;

public class encrypt1 {
	public static void main(String args[]) throws IOException {
		
		
		
		int num=0;
		
		BufferedImage orImage = null, hidImage = null; // for importing image 
		File oImage, hImage; 
		ArrayList<Integer> hidR = new ArrayList<Integer>(); // for storing the rgb data
		ArrayList<Integer> hidG = new ArrayList<Integer>();
		ArrayList<Integer> hidB = new ArrayList<Integer>();

		ArrayList<Integer> orR = new ArrayList<Integer>();
		ArrayList<Integer> orG = new ArrayList<Integer>();
		ArrayList<Integer> orB = new ArrayList<Integer>();
		try //Images import
		{
		
		 oImage     = new File("H:/hp/eclipse/programs/Image Processing/src/Input Images/Target2.jpg");//path of image to be hidden
		 orImage = ImageIO.read(oImage);
		 hImage   = new File("H:/hp/eclipse/programs/Image Processing/src/Output Images/output.jpg");// path of image used for hiding
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
		
		int orHeight = orImage.getHeight(); // getting height and width of each image
		int orWidth  = orImage.getWidth();
		int hiHeight = hidImage.getHeight();
		int hiWidth  = hidImage.getWidth();
		
		for(int y=0 ; y<hiHeight ; y++)		// running the loop to cover all pixels of the image to be hidden in a rowwise manner
		{
			for(int x=0 ; x<hiWidth ; x++)
			{
				Color c = new Color(hidImage.getRGB(x,y), true);// Color class will return the colour of that pixel specified by x,y coordinates
				int Red = (int)(Math.floor(c.getRed()));// gets red component from c
				int Green = (int)(Math.floor(c.getGreen()));// gets green component from c 
				int Blue = (int)(Math.floor(c.getBlue()));// gets blue component from c
				//add the components to array list
				hidR.add(Red);
				hidG.add(Green);
				hidB.add(Blue);
			//	System.out.println("Done"+num);
				//num++;
			}	
		}
		
		for(int y=0 ; y<orHeight ; y++)		// running the loop to cover all pixels of the image to be hidden in a rowwise manner
		{
			for(int x=0 ; x<orWidth ; x++)
			{
				Color c = new Color(orImage.getRGB(x,y), true);// Color class will return the colour of that pixel specified by x,y coordinates
				int Red = (int)(Math.floor(c.getRed()));// gets red component from c
				int Green = (int)(Math.floor(c.getGreen()));// gets green component from c 
				int Blue = (int)(Math.floor(c.getBlue()));// gets blue component from c
				//add the components to array list
				orR.add(Red);
				orG.add(Green);
				orB.add(Blue);
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
		
		Integer[] oRData = new Integer[orR.size()];//r value in Integer 
		oRData = orR.toArray(oRData);
		
		Integer[] oGData = new Integer[orG.size()];//g value in Integer
		oGData = orG.toArray(oGData);
			
		Integer[] oBData = new Integer[orB.size()];//b value in integer
		oBData = orB.toArray(oBData);
		
		String[] RHString = new String[hidR.size()];
		String[] GHString = new String[hidG.size()];
		String[] BHString = new String[hidB.size()];
		
		String[] ROString = new String[orR.size()];
		String[] GOString = new String[orG.size()];
		String[] BOString = new String[orB.size()];

		
		
		
		int n = 0;
		String test;
		for(int y=0 ; y<orHeight ; y++)
		{
			for(int x=0 ; x<orWidth ; x++)
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
				
				
				ROString[n] = Integer.toBinaryString(oRData[n]);
				oRData[n]= Integer.parseInt(ROString[n]);
				ROString[n] = String.format("%08d",oRData[n]);
				
				
				GOString[n] = Integer.toBinaryString(oGData[n]);
				oGData[n]= Integer.parseInt(GOString[n]);
				GOString[n] = String.format("%08d",oGData[n]);
				
				
				BOString[n] = Integer.toBinaryString(oBData[n]);
				oBData[n]= Integer.parseInt(BOString[n]);
				BOString[n] = String.format("%08d",oBData[n]);
				
				
				test=n+".("+RHString[n]+" "+GHString[n]+" "+BHString[n]+")"+"\n";
				//file.write(test);
				test=n+".("+ROString[n]+" "+GOString[n]+" "+BOString[n]+")"+"\n";
				//file.write(test);
				
				RHString[n] = RHString[n].substring(0,4)+ROString[n].substring(0,4);
				GHString[n] = GHString[n].substring(0,4)+GOString[n].substring(0,4);
				BHString[n] = BHString[n].substring(0,4)+BOString[n].substring(0,4);
				//converting binary string to decimal integer using Integer.parseInt() 
				RData[n]= Integer.parseInt(RHString[n],2);
				GData[n]= Integer.parseInt(GHString[n],2);
				BData[n]= Integer.parseInt(BHString[n],2);
				

				
				n++;

				

				
			}
		}
		
		n=0;
		for(int y=0 ; y<orHeight ; y++)
		{
			for(int x=0 ; x<orWidth ; x++) {
			
				Color newC = new Color(RData[n], GData[n],BData[n]);
				orImage.setRGB(x,y,newC.getRGB());
				
				
				Color c = new Color(orImage.getRGB(x,y), true);// Color class will return the colour of that pixel specified by x,y coordinates
				int Red = (int)(Math.floor(c.getRed()));// gets red component from c
				int Green = (int)(Math.floor(c.getGreen()));// gets green component from c 
				int Blue = (int)(Math.floor(c.getBlue()));
				n++;
				
			}
		}
		try
		{
			File outputfile = new File("H:/hp/eclipse/programs/Image Processing/src/Output Images/Image is hidden here.png");// path where to store and name of image 
			ImageIO.write(orImage, "png", outputfile);
			
			
			
		}
		catch(IOException e)
		{
			System.out.println("----  ERROR!!!!! ----");
			e.printStackTrace();
		}
		
			
System.out.println("Done");
	}

}
