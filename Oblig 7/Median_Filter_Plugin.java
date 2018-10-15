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
        int filterStrH = 5; //Horisontal størrelse
        int filterStrV = 5; // Vertikal størrelse
        ArrayList <Integer> arrayVertikal = new ArrayList<Integer>();


        int totalPxVekter = 0;
        int[][] fasteVekterArray =
        {
          {1,1,1,1,1},
  				{1,1,2,1,1},
  				{1,2,3,2,1},
  				{1,1,2,1,1},
  				{1,1,1,1,1}
        };

        ImageProcessor nyttBilde = new ByteProcessor(width, heigth);


        int vertStart = (int) (fasteVekterArray.length / 2.0);
        int horStart = (int) (fasteVekterArray[0].length / 2.0);

        //Går gjennom bilde horisontalt og tar alle nye midlertidige verdier og lagrer de midlertidig.
        //Dvs. filter verdiene
        for (int y = vertStart; y < heigth - filterStrV; y++) {
            for (int x = horStart; x < width - filterStrH; x++) {
              // Vekt matrisen
              for(int m = -vertStart; m < vertStart; m++) {
                for(int n = -horStart; n < horStart; n++) {

                  //int i = ip.getPixel(x+m,y+n);

                  int filterVerdi = fasteVekterArray[vertStart+m][horStart+n];
                  int horPos = x - horStart;
                  int pikselVerdi = ip.getPixel(x+m, y+n);

                  for (int i = 0; i < filterVerdi; i++) {

                    arrayVertikal.add(pikselVerdi);

                  }
                }
              }
              int median = 2;
              Collections.sort(arrayVertikal);
              int nyVerdi = arrayVertikal.get(((arrayVertikal.size()-1)/median));
              nyttBilde.putPixel(x, y, nyVerdi);
              arrayVertikal.clear();
            }
        }
        ImagePlus im = new ImagePlus("Median filter",nyttBilde);
	      im.show();
    }
}
