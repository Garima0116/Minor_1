import java.awt.image.BufferedImage;
public class Key {

private int xScale = 10;
private int yScale = 10;
int passwordCharNo=20, blocks;
static int count=0;
char[] key = new char[ passwordCharNo];	
BufferedImage image ;
static int noOfYBlocks,noOfXBlocks;
	
	
	
	
	 void selectBlock(BufferedImage image ) {
		
		 this.image = image;
		 int num=0;

		noOfYBlocks= image.getHeight()/yScale;
		noOfXBlocks = image.getWidth()/xScale;
		blocks = noOfXBlocks*noOfYBlocks ;
		//System.out.println(blocks);
		int difference = (blocks)/passwordCharNo;
		int remainder = (blocks)%passwordCharNo;
		
		for(int i=1; i<blocks-remainder ;i=i+difference) {
		
			int  avg = blockAvgRGB(i);
			
			//System.out.println(i);
			//System.out.println(num+".done");
			//num++;
		}
	}

	 	int blockAvgRGB( int index){
		// System.out.println("Index"+index);
		 int avg;
		 int row = index / noOfXBlocks;
		 int column= index % noOfXBlocks;
		 int value = 0 ,sumR=0 , sumG=0, sumB = 0 ;
		 for (int x = 0; x < xScale; x++) {
				for (int y = 0; y < yScale; y++)
				{
					int color = image.getRGB(column*xScale+x,row*yScale+y);
					value = color & 0x000000ff;
					sumB += value;
					value = (color>>8) & 0x000000ff;
					sumG += value;
					value = (color>>16) & 0x000000ff;
					sumR += (value);
				}
		 }
		 int resolution = xScale*yScale;
		 avg = ((sumR/resolution)+(sumG/resolution)+(sumB/resolution))/3;
		 
		// System.out.println(avg);
			  generate(avg);
			  
		 
		return(value);
	}
	 

	  void generate(int avg) {

		// System.out.println(avg%26);
		 //System.out.println(avg%6);
		// key[count]=(char)avg;
			if(count%4==0)
				key[count] =(char)((avg%26)+97);
			else if(count%4==1)
				key[count]=(char)((avg%10)+48);
			else if(count%4==2)
			    key[count] =(char)((avg%26)+64);
			else
				key[count] =(char)((avg%15)+33);
			count++;				
		
	}
	
}

