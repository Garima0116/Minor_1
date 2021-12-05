import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import javax.imageio.ImageIO;

public class MSE {
	public static void main(String args[]) throws IOException {
	File original = new File("H:/hp/eclipse/programs/Image Processing/src/Input Images/Target2.jpg");
	File retrieved = new File("C:/Users/hp/Desktop/test/retrieved.png");
	
	BufferedImage originalImage = ImageIO.read(original);
	BufferedImage retrievedImage = ImageIO.read(retrieved);
	double sumR = 0, sumB=0, sumG=0;
	int oHeight = originalImage.getHeight();
	int oWidth = originalImage.getWidth();
	
	int rHeight = retrievedImage.getHeight();
	int rWidth = retrievedImage.getWidth();
	
	if(rHeight!=oHeight || rWidth!=oWidth)
	{
		System.out.println("Error!!!Dimensions of images must be same.");
		System.exit(0);
	}
	
	
	for (int x = 0; x < oWidth; x++) {
		for (int y = 0; y < oHeight; y++) {
			int o =originalImage.getRGB(x, y);
			int r =retrievedImage.getRGB(x, y);
			double c = Math.pow(originalImage.getRGB(x, y) - retrievedImage.getRGB(x, y),2) ;
			
			sumR =sumR+Math.pow((o & 0x000000FF) - (r & 0x000000FF),2);
			sumG =sumG+Math.pow((o>>8 & 0x000000FF) - (r>>8 & 0x000000FF),2);
			sumB =sumB+Math.pow((o>>16 & 0x000000FF) - (r>>16 & 0x000000FF),2);
		}
		
		
	}
	
	double errorR = sumR/(oHeight*oWidth);
	double errorG = sumG/(oHeight*oWidth);
	double errorB = sumB/(oHeight*oWidth);
	System.out.println("MSE Red: "+errorR);
	System.out.println("MSE Green: "+errorG);
	System.out.println("MSE Blue: "+errorB);
}
}