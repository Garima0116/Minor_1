import java.awt.image.BufferedImage;

public class MatchKey extends Key {

	BufferedImage image ;
	boolean status;
	
	MatchKey(BufferedImage getKeyFrom, String inputKey){
		this.image = getKeyFrom;
		this.selectBlock(image);
		
		this.getStatus(inputKey);
	}
	
	private void getStatus(String inputKey) {
		
		String secretKey = String.valueOf(key);
		if(inputKey.equals(secretKey)) {
			status=true;
		}
		else {
			status=false;
		}
		
	}
	
	
	
}
