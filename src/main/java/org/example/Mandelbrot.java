package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Mandelbrot {
    private JPanel panel;
    private BufferedImage image;


    public Mandelbrot(JPanel panel) {
        this.panel = panel;
        panel.setPreferredSize(new Dimension(800, 800));

        // Escucha el evento de cambio de tamaño del panel para obtener el ancho y el alto
        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = panel.getWidth();
                int height = panel.getHeight();
                image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            }
        });
    }

    public void pintaMandelbrot(int numWorkers) {
        // Dividir el trabajo en filas o columnas, dependiendo de tu elección
        int rows = panel.getHeight() / numWorkers;
        ExecutorService executorService = Executors.newFixedThreadPool(numWorkers);

        for (int i = 0; i < numWorkers; i++) {
            int startY = i * rows;
            int endY = (i + 1) * rows;

            // Crea un trabajador para calcular una parte del conjunto
            Runnable worker = new Trabajador(startY, endY, panel.getWidth(), image);
            executorService.execute(worker);
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Renderiza la imagen en el panel
        Graphics g = panel.getGraphics();
        g.drawImage(image, 0, 0, null);
    }
}