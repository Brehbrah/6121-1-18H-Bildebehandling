import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;

public class SpeileBilde_plugin implements PlugInFilter {
	ImagePlus imp;

	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		return DOES_ALL;
	}

	public void run(ImageProcessor ip) {
		int w = ip.getWidth();
		int h = ip.getHeight();

		for(int x = 0; x < w/2; x++) {
			for(int y = 0; y < h; y++) {

					int a = ip.getPixel(x, y);
					int b = ip.getPixel(w - x, y);

					// Fjerner venstre bildet, og beholder den til høyere
					ip.putPixel(x, y, b);

					// Beholder hele x & y verdien variabelen a fra det originale bildet
					// Fjerner høyre bildet, og flipper til venstre
					ip.putPixel(w - x, y, a);
			}
		}
	}

}
