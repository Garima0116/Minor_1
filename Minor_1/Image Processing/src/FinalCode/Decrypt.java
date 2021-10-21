
import java.io.IOException;

import java.awt.image.BufferedImage;
import java.io.File;

import java.lang.String;



import javax.imageio.ImageIO;


public class Decrypt {
	
	BufferedImage encryptedImage;
	
	Decrypt(String encrypted) throws IOException {
		File eImage;
		 eImage = new File(encrypted); 
		 encryptedImage = ImageIO.read(eImage);
		 getSecretImage(encryptedImage);
		 
	}
	
	
//	shifting the bits in order to retrieve the secret image 
private void getSecretImage(BufferedImage encryptedImage) throws IOException {
	
			int num;
			int eHeight = encryptedImage.getHeight(); 
			int eWidth  = encryptedImage.getWidth();
			
			
			int[] eBlue = new int[eHeight*eWidth]; 
			int[] eGreen = new int[eHeight*eWidth]; 
			int[] eRed = new int[eHeight*eWidth]; 
			
			
			
			num=0;
			for(int y=0 ; y<eHeight ; y++)		
			{
				for(int x=0 ; x<eWidth ; x++)
				{
					int c = encryptedImage.getRGB(x,y);// c will return the colour of that pixel specified by x,y coordinates
					eBlue[num] = (c & 0x000000F)<<4;// gets red component from c
					eGreen[num] = (c & 0x00000F00)<<4;// gets green component from c 
					eRed[num] = (c & 0x000F0000)<<4;// gets blue component from c
					num++;
				}	
			}
			
			
			
			
			
		new CreateImage(eRed,eGreen,eBlue,eHeight,eWidth);
			
			
		}
	}
