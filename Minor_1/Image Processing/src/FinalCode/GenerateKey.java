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
		System.out.println("Store the key safely as it is displayed only once.");
		System.out.println("Key : " + str);
	}
	
}
