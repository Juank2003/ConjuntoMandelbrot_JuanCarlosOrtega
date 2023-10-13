package org.example;

import javax.swing.*;

public class Usuario {
    private Mandelbrot mandelbrot;
    private JPanel panel;

    public Usuario(JPanel panel) {
        this.panel = panel;
        this.mandelbrot = new Mandelbrot(panel);
    }

    public void calcularMandelbrot(int numWorkers) {
        mandelbrot.pintaMandelbrot(numWorkers);
    }
}