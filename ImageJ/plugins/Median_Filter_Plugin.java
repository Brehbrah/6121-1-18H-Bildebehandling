import ij.*;
import ij.process.*;
import ij.gui.*;
import java.awt.*;
import ij.plugin.filter.*;
import java.util.*;

public class Median_Filter_Plugin implements PlugInFilter {

    ImagePlus imp;
    public int setup(String arg, ImagePlus imp) {
        this.imp = imp;
        return DOES_8G;     }

    public void run(ImageProcessor ip) {
        int heigth = ip.getHeight();
        int width = ip.getWidth();
        int filterStrH; //Horisontal størrelse
        int filterStrV; // Vertikal størrelse

        ImageProcessor nyttBilde = new ByteProcessor(width, heigth);

        GenericDialog dialog = new GenericDialog("Filter størrelse");
        //Standard 3x3 allerede skrivd inn i dialogviduet
        dialog.addNumericField("Vertikal: ", 3, 0);
        filterStrV = (int) dialog.getNextNumber();
        dialog.addNumericField("Horisontal: ", 3, 0);
        filterStrH = (int) dialog.getNextNumber();
        dialog.showDialog();

        int vertStart = (int) (filterStrV / 2.0);
        int horStart = (int) (filterStrH / 2.0);

        //Går gjennom bilde horisontalt og tar alle nye midlertidige verdier og lagrer de midlertidig.
        //Dvs. filter verdiene
        for (int y = vertStart; y < heigth - filterStrV; y++) {
            for (int x = horStart; x < width - filterStrH; x++) {
                int pikselVerdi = 0;
                int horPos = x - horStart;

                for (int i = horPos; i < horPos + filterStrH; i++) {

									int median;

									pikselVerdi = pikselVerdi + ip.getPixel(i, y);

									ArrayList <Integer> arrayVertikal = new ArrayList<Integer>();
							   	//	arrayVertikal.add(pikselVerdi);
									int arraySort = Collections.sort(arrayVertikal.add(pikselVerdi));

									if(arraySort % 2 == 0 ) {
										median = arraySort[arraySort.length/2] + arraySort[arraySort.length/2 - 1]/2;
									} else {
										median = arraySort[arraySort.length/2];
									}
                }
            }
        }

        //Går gjennom bilde vertikalt og tar alle nye  verdier og oppdaterer bildet.
        //Dvs. filter verdiene
        for (int x = horStart; x < width - filterStrH; x++) {
            for (int y = vertStart; y < heigth - filterStrV; y++) {
                int pikselVerdi = 0;
                int vertPos = y - vertStart;
                for (int i = vertPos; i < vertPos + filterStrV; i++) {
                    pikselVerdi = pikselVerdi + nyttBilde.getPixel(x, i);
                }
                int nyVerdi = (int) (pikselVerdi / filterStrV + 0.5);
                ip.putPixel(x, y, nyVerdi);
            }
        }
    }
}
