package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Conjunto de Mandelbrot");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Crear un panel de tamaño fijo (800x800)
            JPanel panel = new JPanel();
            JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 32, 1));
            panel.add(spinner);
            JButton calcularButton = new JButton("Calcular");
            panel.add(calcularButton);

            // Crear una instancia de Usuario y pasar el panel
            Usuario usuario = new Usuario(panel);

            calcularButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int numWorkers = (int) spinner.getValue();
                    // Llamar al método de Usuario para calcular el Mandelbrot
                    usuario.calcularMandelbrot(numWorkers);
                }
            });

            frame.add(panel);
            frame.pack();
            frame.setVisible(true);
        });
    }
}