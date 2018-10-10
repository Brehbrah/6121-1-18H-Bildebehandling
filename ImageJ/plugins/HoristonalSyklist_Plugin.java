import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;

public class HoristonalSyklistHøyere_Plugin implements PlugInFilter {
	ImagePlus imp;

	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		return DOES_8G;
	}

	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();

		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {

				int getPx = ip.getPixel(x,y);

				ip.putPixel(x,y, getPx);
			}
		}
	}

}
