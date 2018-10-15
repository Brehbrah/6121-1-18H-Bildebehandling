import java.util.Arrays;

import ij.ImagePlus;
import ij.plugin.filter.PlugInFilter;
import ij.process.ImageProcessor;

public class Weighted_Median implements PlugInFilter {
	
	public int setup(String arg, ImagePlus imp) {
		return DOES_8G;
	}
	public void run(ImageProcessor ip) {
		int width = ip.getWidth();
		int height = ip.getHeight();
				
		int [][] filter = {
				{1,1,1,1,1},
				{1,1,2,1,1},
				{1,2,3,2,1},
				{1,1,2,1,1},
				{1,1,1,1,1}
		};
		int totalPiksel = 0;
		for (int[] rad : filter) for (int tall : rad) totalPiksel += tall;
		
		int [] pikselArray = new int[totalPiksel];
		int medianArrayIndex = 0;
		int halfHeight = filter.length/2;
		int halfWidth = filter[0].length/2;
		
		ImageProcessor kopi = ip.duplicate();
		
		for (int y = 0; y <= height-filter.length; y++) {
			for (int x = 0; x <= width-filter[0].length; x++) {
				medianArrayIndex = 0;
				
				for (int m = 0; m < filter.length; m++) {
					for (int n = 0; n < filter[0].length; n++) {
				
						int filterVerdi = filter[n][m];
						int bildeVerdi = kopi.getPixel(x+n, y+m);
						
						for (int k = 0; k < filterVerdi; k++) {
                            pikselArray[medianArrayIndex++] = bildeVerdi;
						
					}
				}
				}
				Arrays.sort(pikselArray);
				ip.putPixel(x+halfWidth, y+halfHeight, pikselArray[pikselArray.length/2]);
			}
		}
		
		
	}
	

}
