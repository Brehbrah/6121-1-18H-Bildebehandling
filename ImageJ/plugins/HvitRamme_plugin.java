import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;

public class HvitRamme_plugin implements PlugInFilter {
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

				// Definere hvor og til bildet skal settes for å lage en ramme
				if(x < 20 || w-x < 20 || y < 20 || h-y < 20) {
					ip.putPixel(x, y, 255);
				}
			}
		}
	}
}
