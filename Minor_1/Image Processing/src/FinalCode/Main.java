
import java.io.IOException;
import java.util.Scanner;

public class Main {

	static String target, secret, encrypted, list, key;
	public static void main(String[] args) throws IOException {
		
		int choice ;
		
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Select the desired number from the menu:");
		System.out.println("\n-----------Menu-----------\n");
		System.out.println("        1. Encrypt an Image         ");
		System.out.println("        2. Decrypt an Image         ");
		System.out.println("        3. Exit                     ");
		
		
		choice = scan.nextInt();
		
		
		
		switch(choice) {
		
		case 1: 
			System.out.println("Enter the path of target image:");
			
			target = getInputPath();
			System.out.println("Enter the path of secret image:");
			secret =  getInputPath();
			System.out.println("Enter the path of the folder containing list images for constructing mosaic:");
			list =  getInputPath();
			
			mosaic mosaicObj = new mosaic(target,list);
			Encrypt encryptObj=new Encrypt(mosaicObj,secret);
			new GenerateKey(encryptObj.resultImage);
			break;
		
		case 2:
			System.out.println("Enter the path of hidden image:");
			scan.nextLine();
			encrypted = scan.nextLine();
			
			System.out.println("Enter the secret key:");
			key = scan.nextLine();
			new Decrypt(encrypted, key);
			break;
			
		case 3:
			System.out.println("End of execution.");
			System.exit(0);
		
		default:
			System.out.println("Wrong Choice............Terminating ");
			System.exit(1);
			
		}
		
		
	
	}	
	
	private static String getInputPath() {
		
		String path;
		Scanner scan = new Scanner(System.in);
		path = scan.nextLine();
		return(path);
	}
		
}

