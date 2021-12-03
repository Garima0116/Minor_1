import java.awt.image.BufferedImage;

public class GenerateKey extends Key {

	BufferedImage image ;
	
	GenerateKey(BufferedImage getKeyFrom){
		this.image = getKeyFrom;
		this.selectBlock(image);
		displayKey();
	}
	
	void displayKey(){
		String str = String.valueOf(key);
		System.out.println("Image has been hidden. The key is displayed only once. Store it safely.");
		System.out.println("Key : " + str);
	}
	
}
