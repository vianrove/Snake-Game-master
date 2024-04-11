package src;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Set a maximum length of 10 characters for the JTextField and prevent blank names
        ((PlainDocument) tf1.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (string == null) {
                    return;
                }
                String text = fb.getDocument().getText(0, fb.getDocument().getLength());
                if (text.trim().isEmpty() && string.trim().isEmpty()) {
                    return; // Prevents inserting spaces if the text field is empty
                }
                if (text.length() + string.length() <= 10) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (text == null) {
                    return;
                }
                String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
                if (currentText.trim().isEmpty() && text.trim().isEmpty()) {
                    return; // Prevents replacing spaces if the text field is empty
                }
                int newLength = currentText.length() - length + text.length();
                if (newLength <= 10) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        b1 = new JButton("save");
        b1.setBounds(45, 45, 100, 20);

        b1.addActionListener(this);
        f.add(label);
        f.add(tf1);
        f.add(b1);

        f.setSize(200, 120);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s1 = tf1.getText().trim(); // Trim to remove leading and trailing spaces
        if (!s1.isEmpty() && s1.length() <= 10) {
            puntaje.putName(s1);
            puntaje.saveNewScore();
            JOptionPane.showMessageDialog(null, "¡Guardado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            ((JFrame) SwingUtilities.getWindowAncestor(tf1)).dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un nombre de hasta 10 caracteres y no vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
