
import java.io.IOException;

import java.awt.image.BufferedImage;
import java.io.File;

import java.lang.String;



import javax.imageio.ImageIO;


public class Decrypt {
	
	BufferedImage encryptedImage;
	
	Decrypt(String encrypted, String key) throws IOException {
		 File eImage;
		 eImage = new File(encrypted); 
		 encryptedImage = ImageIO.read(eImage);
		 
		 MatchKey matchKeyObj = new MatchKey(encryptedImage, key);
		 
		 if(matchKeyObj.status==true) {
			 
			 getSecretImage(encryptedImage);
		 }
		 else {
			 CreateImage result = new CreateImage(encryptedImage);
			
		 }
		 
	}
	
private void getSecretImage(BufferedImage encryptedImage) throws IOException {
	
			int num;
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
					int c = encryptedImage.getRGB(x,y);// c will return the colour of that pixel specified by x,y coordinates
					eBlue[num] = (c & 0x000001F)<<3;// gets red component from c
					eGreen[num] = (c & 0x00001F00)<<3;// gets green component from c 
					eRed[num] = (c & 0x001F0000)<<3;// gets blue component from c
					num++;
				}	
			}
			
			
			
			
			
		new CreateImage(eRed,eGreen,eBlue,eHeight,eWidth);
			
			
	System.out.println("Done");
		}
	}
