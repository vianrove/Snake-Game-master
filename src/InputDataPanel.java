package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputDataPanel implements ActionListener {
    JLabel label;
    JTextField tf1;
    JButton b1;
    Score puntaje;
    InputDataPanel(Score score) {
        puntaje = score;
        JFrame f = new JFrame();
        f.getContentPane().setBackground(new Color(26, 100, 14, 1));
        label = new JLabel("Digite Apodo:");
        label.setBounds(45, 5, 100, 20);
        tf1 = new JTextField();
        tf1.setBounds(45, 25, 100, 20);

        b1 = new JButton("save");
        b1.setBounds(45, 45, 100, 20);

        b1.addActionListener(this);
        f.add(label);
        f.add(tf1);
        f.add(b1);

        // Cambiar el color de fondo
        //f.getContentPane().setBackground(new Color(26, 100, 14, 1));

        f.setSize(200, 120);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s1 = tf1.getText();
        puntaje.putName(s1);
        puntaje.saveNewScore();
        // Muestra un cuadro de diálogo
        JOptionPane.showMessageDialog(null, "¡Guardado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        // Cierra el JFrame asociado al InputDataPanel
        ((JFrame) SwingUtilities.getWindowAncestor(tf1)).dispose();

    }
}
