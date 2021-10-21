import java.io.File;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class mosaic{
	
	static int sclHeight=10, sclWidth=10
			;
	
	
	
		
	public static void main(String  args[]) throws IOException {
		
	
		
		File Target = new File("H:/hp/eclipse/programs/Image Processing/src/Input Images/Target.jpg"); //Image whose file has to be created
		
		BufferedImage  TargetImage = null;
		
		try {
		TargetImage = ImageIO.read(Target);
		//System.out.println("Successful");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		mosaic obj = new mosaic();
		BufferedImage[] listImages = obj.getListImages();
		
		int[] ListAvgRGB = new int[listImages.length];
		
		ListAvgRGB = obj.listImagesAvgBrightness(listImages);
		
		
		for(int i=0;i<listImages.length;i++)
		{
			listImages[i]=obj.resize(listImages[i]);
		}
		
		
		
	
		 obj.CreateMosaic(TargetImage,listImages,ListAvgRGB);
		 
		 System.out.println("done");
	
		
	}
	
	
	
	
	// getting list images
	public BufferedImage[] getListImages() throws IOException {
		
		File ImageFolder = new File("H:/hp/eclipse/programs/Image Processing/src/listImages") ;//path of the folder with images
		File[] listImageFiles = ImageFolder.listFiles();
		BufferedImage[] listImages = new BufferedImage[listImageFiles.length]; 
		
		for(int i=0;i<listImageFiles.length;i++) {
			
			listImages[i] = ImageIO.read(listImageFiles[i]);
		}
		
		return(listImages);
		
	}

	
	
	//resizing the list images
	private static BufferedImage resize(BufferedImage img) {
	    Image tmp = img.getScaledInstance(sclWidth, sclHeight, Image.SCALE_SMOOTH);
	    BufferedImage resized = new BufferedImage(sclWidth, sclHeight, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2d = resized.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();
	    return resized;
	}
	
	

	
	
// calculating avgRGB value of the list images	
	
	public int[] listImagesAvgBrightness(BufferedImage[] listImages) throws IOException {
		int n=0;
		int[] intAvgRGB = new int[listImages.length];
		
		for(int i=0;i<listImages.length;i++) {
		
			
			int red = 0;
			int green = 0;
			int blue = 0;
			int pixelCount = 0;
			
			
			for (int y = 0; y < listImages[n].getHeight(); y++)
			{
			    for (int x = 0; x < listImages[n].getWidth(); x++)
			    {
			        Color c = new Color(listImages[n].getRGB(x, y));
			        
			    
			      pixelCount++;
			        red += c.getRed();
			        green += c.getGreen();
			        blue += c.getBlue();
			     
			        
			        
			    }
			}
		
			
			intAvgRGB[n]=  getIntFromColor(red/pixelCount, green/pixelCount, blue/pixelCount);
		
			 n++;
		}
		
		return(intAvgRGB);
}
	
	

	
//getting integer value for color	
public int getIntFromColor(int Red, int Green, int Blue){
	    Red = (Red << 16) & 0x00FF0000; //Shift red 16-bits and mask out other stuff
	    Green = (Green << 8) & 0x0000FF00; //Shift Green 8-bits and mask out other stuff
	    Blue = Blue & 0x000000FF; //Mask out anything not blue.

	    return 0xFF000000 | Red | Green | Blue; //0xFF000000 for 100% Alpha
}	
	
	
	

	


//calculating the avg rgb values of the blocks of target image	anf comparing the same with the list images to find appropriate one to fit in. 
public BufferedImage CreateMosaic(BufferedImage Target, BufferedImage[] listImages, int[] listImagesAvgRGB ) throws IOException {
		int num=0,n=0;
		
		int rows = Target.getWidth()/sclWidth;
		int columns = Target.getHeight()/sclHeight;
		
		
		int[][] intAvgRGB = new int[rows][columns];
		for (int y= 0; y < columns; y++) 
		{
			for (int x=0; x < rows; x++)
			
			{
				int red = 0;
				int green = 0;
				int blue = 0;
				int pixelCount = 0;
				
				BufferedImage output = Target.getSubimage(x*sclWidth,y*sclHeight,sclWidth,sclHeight);

				for (int j = 0; j < sclHeight; j++) 
				{
					for (int i = 0; i < sclWidth; i++)
						{
						 Color c = new Color(output.getRGB(i, j));
					        
						    //    System.out.println(n+"."+c);
						      	pixelCount++;
						        red += c.getRed();
						        green += c.getGreen();
						        blue += c.getBlue();
						        
						}
				}
				
				intAvgRGB[x][y] =  getIntFromColor(red/pixelCount, green/pixelCount, blue/pixelCount);
				
				int index = getIndex(intAvgRGB[x][y],listImagesAvgRGB);
				
				
				for(int j=0;j<listImages[index].getHeight();j++) {
					
					for(int i=0;i<listImages[index].getWidth();i++) {
						Color c = new Color(listImages[index].getRGB(i,j), true);
						
						Target.setRGB(x*sclWidth+i,y*sclHeight+j,c.getRGB());
						
					}
				}
				
					
			}
		}
		
		File outputfile = new File("H:/hp/eclipse/programs/Image Processing/src/Output Images/output.jpg");// path where to store and name of image 
		ImageIO.write(Target, "jpg", outputfile);
		
		return(Target);
		
	}
	
	
	
	
	
//getting the index for comparison	
public int getIndex(int value, int[] arr) {
		
		
		int index = 0;
		int absoluteDifference = Math.abs(arr[index] - value);
		
		if (absoluteDifference == 0)
			return index;
		for (int i = 1; i < arr.length; i++)
		{
			int CurrentDifference = Math.abs(arr[i] - value);
			if (CurrentDifference == 0)
				return i;
			else if (CurrentDifference < absoluteDifference)
			{
				index = i;
				absoluteDifference = CurrentDifference;
			}
 
		}
		
		//  index will be closest to target
		return index;
	}




}
