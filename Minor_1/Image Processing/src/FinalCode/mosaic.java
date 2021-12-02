
//final
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class mosaic
{


public static int xScale = 10;
public static int yScale = 10;
BufferedImage outputMosaic = null;



public mosaic(String targrtImagePath, String listFolderPath)
{
	System.out.println("Running...");
	try
	{
	//Importing image
		File targetFile = new File(targrtImagePath);
		BufferedImage targetImage = ImageIO.read(targetFile);
		
		int[][][] blockArray = mainImageBlockRGB(targetImage);

		BufferedImage[] listImages = getListImages(listFolderPath);

		int[][] listImageRGBs = listImagesRGB(listImages);

		
		int[][] matchArray = matchImageWithBlocks(blockArray, listImageRGBs);


		 outputMosaic = createResult(targetImage, listImages, matchArray);
				
		System.out.println("Done!!");
		
		
	}
	catch (IOException e)
	{
		e.printStackTrace();
	}
}
 





//FOR CALCULATING AVG RGB VALUE OF EACH BLOCK THAT THE IMAGE WILL BE DIVIDED IN
private int[][][] mainImageBlockRGB(BufferedImage imageSource) throws IOException 
{
	int width = imageSource.getWidth();
	int height = imageSource.getHeight();

	int[][] rgbArray = new int[width][height];
// GETTING RGB VALUES OF EACH PIXEL
	for (int x = 0; x < width; x++) {
		for (int y = 0; y < height; y++) {
			rgbArray[x][y] = imageSource.getRGB(x, y);
		}
	}
	
	int numberOfXBlocks = width / xScale; //For calculating the number of columns that will be there along the width
	int numberOfYBlocks = height / yScale;// for calculating number of rows along the column

	int[][][] blockArray = new int[numberOfXBlocks][numberOfYBlocks][3];
// FOR CALCULATING THE AVG RGB VALUE AT EACH BLOCK
	for (int i = 0; i < numberOfXBlocks; i++)	
		for (int j = 0; j < numberOfYBlocks; j++)
			{
				int value, sumR = 0, color;
				int sumG = 0;
				int sumB = 0;

				for (int x = 0; x < xScale; x++)
					for (int y = 0; y < yScale; y++)
					{
						color = rgbArray[i * xScale + x][j * yScale + y] ;
						value = color & 0x000000ff;
						sumB += value;
						value = (color>>8) & 0x000000ff;
						sumG += value;
						value = (color>>16) & 0x000000ff;
						sumR += (value);
					}
				int resolutionPerBlock = xScale * yScale;
				blockArray[i][j][0] = sumR/resolutionPerBlock;
				blockArray[i][j][1] = sumG/resolutionPerBlock;
				blockArray[i][j][2] = sumB/resolutionPerBlock;
			}

return blockArray;
}






// For reteriving the list images fro specifiedd path
private BufferedImage[] getListImages(String listFolderPath) throws IOException {
	
	File ImageFolder = new File(listFolderPath) ;
	File[] listImageFiles = ImageFolder.listFiles();
	BufferedImage[] listImages = new BufferedImage[listImageFiles.length]; 
	
	for(int i=0;i<listImageFiles.length;i++) 
	{
		
		listImages[i] = ImageIO.read(listImageFiles[i]);
	}
	
	return(listImages);
	
}








// for calculating avgRGb values of list iimages
private int[][] listImagesRGB(BufferedImage[] listImages) throws IOException
{
	int[][] listImageRGBs = new int[listImages.length][3];
	for (int i = 0; i < listImages.length; i++)
	{
		listImages[i]=resize(listImages[i]);
		
		int width = listImages[i].getWidth();
		int height = listImages[i].getHeight();

		int value, sumR = 0,color;
		int sumG = 0;
		int sumB = 0;

		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
			{
				color = listImages[i].getRGB(x, y);
				value = color & 0x000000ff;
				sumB += value;
				value = (color>>8) & 0x000000ff;
				sumG += value;
				value = (color>>16) & 0x000000ff;
				sumR += (value);
			}
		int resolution = width * height;
		listImageRGBs[i][0] = sumR/resolution;
		listImageRGBs[i][1] = sumG/resolution;
		listImageRGBs[i][2] = sumB/resolution;
	}
return listImageRGBs;
}











// for matching the specific block with the list image 
private int[][] matchImageWithBlocks(int[][][] blockArray, int[][] listImageRGBs)
{
	int[][] matchArray = new int[blockArray.length][blockArray[0].length];

	for (int j = 0; j < blockArray.length; j++)
			for (int k = 0; k < blockArray[0].length; k++)
			{
				int bestDiff = 0;
				for (int i = 0; i < listImageRGBs.length; i++)
				{
					
					int diff = (int)Math.sqrt(Math.pow(blockArray[j][k][0] - listImageRGBs[i][0], 2) + Math.pow(blockArray[j][k][1] - listImageRGBs[i][1], 2) + Math.pow(blockArray[j][k][2] - listImageRGBs[i][2], 2));
					if (bestDiff == 0 || (diff < bestDiff))
					{
						bestDiff = diff;
						matchArray[j][k] = i;
					}
				}
				
			}
return matchArray;
}










//creating the mosaic from matched pieces
private BufferedImage createResult(BufferedImage mainImage, BufferedImage[] listImages, int[][] matchArray) throws IOException
{
	
	
		int width = mainImage.getWidth() ;
		int height = mainImage.getHeight() ;
	
		BufferedImage imageResult = new BufferedImage(width, height, mainImage.getType());
	
		
		int XBlocks = width/xScale;
		int YBlocks = height/yScale;
	
		for (int i = 0; i < XBlocks; i++)
			for (int j = 0; j < YBlocks; j++)
			{
				int match = matchArray[i][j];
				for (int x = 0; x < xScale; x++)
					for (int y = 0; y < yScale; y++)
						imageResult.setRGB((i * xScale + x), (j * yScale + y), listImages[match].getRGB(x, y));
			}
	
	

	return(imageResult);
}





// scaling the list images down to match the block size
private  BufferedImage resize(BufferedImage img) {
    Image tmp = img.getScaledInstance( xScale,  yScale, Image.SCALE_SMOOTH);
    BufferedImage resized = new BufferedImage( xScale,  yScale, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = resized.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();
    return resized;
}
}
