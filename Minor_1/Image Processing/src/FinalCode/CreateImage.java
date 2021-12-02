import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class CreateImage {
	
	BufferedImage result=null;
	
	CreateImage(BufferedImage encrypted) throws IOException{
		saveAs(encrypted);
		
	}

	CreateImage(int[] Red, int[] Green, int[] Blue, int height, int width) throws IOException{
	
		setRGB(Red,Green,Blue,height,width);
	
	}
	
	
	
	private void setRGB(int[] Red, int[] Green, int[] Blue, int height, int width) throws IOException {
		int pixel;
		
		result = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		int num=0;
		for(int y=0 ; y<height ; y++)		// running the loop to cover all pixels of the image to be hidden
		{
			for(int x=0 ; x<width ; x++)
			{
				pixel= 0xFF000000 | Red[num] | Green[num] | Blue[num];
				result.setRGB(x, y, pixel);
				num++;
			}	
		}
		
		saveAs(result);
	}
	
	 void saveAs(BufferedImage Result) throws IOException {
		
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Enter the path along with the name of the image to be saved: ");
			String save = scan.nextLine();
			
			File resultFile = new File(save);
			ImageIO.write(Result, "png", resultFile);
		}
		
		System.out.println("Yesss");
		
	}
	
}
