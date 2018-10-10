import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;

public class SeperabeltMiddelVerdi_Plugin implements PlugInFilter {
	ImagePlus imp;

	public int setup(String arg, ImagePlus imp) {
		this.imp = imp;
		return DOES_8G;
	}

	public void run(ImageProcessor ip) {

		int filterVertikal = 3;
		int filterHorisontal = 3;

		GenericDialog dialog = new GenericDialog("Filter størrelse");
		//Standard 3x3 allerede skrivd inn i dialogviduet
		dialog.addNumericField("Vertikal: ", 3, 0);
		filterVertikal = (int) dialog.getNextNumber();
		dialog.addNumericField("Horisontal: ", 3, 0);
		filterHorisontal = (int) dialog.getNextNumber();
		dialog.showDialog();

		int w = ip.getWidth();
		int h = ip.getHeight();

		int horStart = (int) filterVertikal/2;
		int verStart = (int) filterHorisontal/2;

		int sumRamme = filterVertikal * filterHorisontal;

		// Starte å filtrere horisontal linje først
		for(int x = horStart; x < w - filterVertikal; x++) {
			for(int y = verStart; y < h - filterHorisontal; y++) {
				for(int i = 0; i < filterVertikal; i++) {
					int hentPx = i + ip.getPixel(i, x);
					int settPx = (int) hentPx/ sumRamme;
					ip.putPixel(x, y, settPx);
				}
			}
		}

		// Etter det, så filtrere vi vertikal linje
		for(int y = verStart; y < h - filterHorisontal; y++) {
			for(int x = horStart; x < w -filterVertikal; x++) {
				for(int i = 0; i < filterHorisontal; i++) {
					int hentPx = i + ip.getPixel(i, y);
					int settPx = (int) hentPx / sumRamme;
					ip.putPixel(x, y, settPx);
				}
			}
		}
	}
}
