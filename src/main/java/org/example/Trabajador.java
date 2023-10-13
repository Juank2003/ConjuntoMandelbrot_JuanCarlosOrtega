package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Trabajador implements Runnable {
    public static final int ANCHO = 800;
    public static final int ALTO = 800;
    public static final int MAX_ITERACIONES = 1000;
    private int startY;
    private int endY;
    private int width;
    private BufferedImage image;

    public Trabajador(int startY, int endY, int width, BufferedImage image) {
        this.startY = startY;
        this.endY = endY;
        this.width = width;
        this.image = image;
    }

    @Override
    public void run() {
        // Cálculos de Mandelbrot para el rango de filas asignado
        for (int y = startY; y < endY; y++) {
            for (int x = 0; x < width; x++) {
                // Realiza el cálculo del conjunto de Mandelbrot y pinta el píxel en la imagen
                int color = calcularMandelbrot(x, y);
                image.setRGB(x, y, color);
            }
        }
    }

    public int calcularMandelbrot(int x, int y) {
        double zx = 0;
        double zy = 0;
        double cX = (x - ANCHO / 2.0) * 4.0 / ANCHO;
        double cY = (y - ALTO / 2.0) * 4.0 / ALTO;
        int iteraciones = 0;

        while (zx * zx + zy * zy < 4 && iteraciones < MAX_ITERACIONES) {
            double tmp = zx * zx - zy * zy + cX;
            zy = 2.0 * zx * zy + cY;
            zx = tmp;
            iteraciones++;

        }

        if (iteraciones < MAX_ITERACIONES){
            //Asignamos un color basado en las iteraciones del punto
            int r = (iteraciones % 8) * 32;
            int g = (iteraciones % 16) * 16;
            int b = (iteraciones % 32) * 8;
            return new Color(r, g, b).getRGB();
        }else{
            //El punto no escapa a la infinitud
            return Color.BLACK.getRGB();
        }
    }
}