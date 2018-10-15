import ij.*;
import ij.process.*;
import ij.gui.*;
import java.util.*;
import java.awt.*;
import ij.plugin.filter.*;
import ij.process.*;
import java.lang.Math.*;
import java.awt.Color.*;

public class SobelOperatorColor_Plugin implements PlugInFilter {

    ImagePlus imp;
    int pixelX = 0;
    int pixelY = 0;
    Color color;

    public int setup(String arg, ImagePlus imp) {
        this.imp = imp;
        return DOES_8G;
    }

    public void run(ImageProcessor ip) {

        int sobelX[][] = {
            {-1, 0, 1},
            {-2, 0, 2},
            {-1, 0, 1}
        };

        int sobelY[][] = {
            {-1, -2, -1},
            {0, 0, 0},
            {1, 2, 1}
        };

        int width = ip.getWidth();
        int height = ip.getHeight();
        int resultat = 0;
        int resultat2 = 0;



        ImageProcessor ip2 = new ColorProcessor(width,height);

        for (int x = 1; x < width - 2; x++) {
            for (int y = 1; y < height - 2; y++) {
	               resultat = 0;
	               pixelX = 0;
	               pixelY = 0;
		             resultat2 =0;
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        int hentPx = ip.getPixel(x+i, y+j);
                        // kjøres gjennom konvelusjon
                        pixelX += sobelX[1+j][1+i] * hentPx;
                        pixelY += sobelY[1+j][1+i] * hentPx;
                    }
                }
                resultat = (int) Math.sqrt((pixelX * pixelX) + (pixelY * pixelY));
                resultat2 = (int)(Math.atan2(pixelY,pixelX)*180/Math.PI);
                color = (color.getHSBColor(resultat2, resultat, 50));
                ip2.setColor(color);
                ip2.drawPixel(x,y);
            }
        }
	         ImagePlus im = new ImagePlus("Sobel Strength",ip2);
           im.show();

    }
}
