package GUI_Package;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.KonexioDB;

import java.io.BufferedReader;
import java.sql.Connection;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.JLabel;

public class PuntuazioTabla extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private KonexioDB c = new KonexioDB();
    private JTextArea textArea;
    private JLabel lblAtzera;
    private static PuntuazioTabla nPuntuazioTabla;

    String punt = String.format("%-20s %-20s %12s\n",
            "IZENA", "ONTZIMOTA", "PUNTUAK");

    private PuntuazioTabla() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 740, 423);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/GUI_Package/Izarrak.png")));
        background.setBounds(0, 0, 740, 423);
        c.konektatu();

        punt += "------------------------------------------------------\n";
        punt += c.puntuazioakErakutsi();

        textArea = new JTextArea();
        textArea.setBounds(72, 51, 600, 260);
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        textArea.setOpaque(false);
        textArea.setForeground(Color.WHITE);
        textArea.setEditable(false);
        textArea.setCaretColor(Color.WHITE);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textArea.setText(punt);

        contentPane.add(textArea);

        JLabel tituloa = new JLabel("PUNTUAZIOAK");
        tituloa.setForeground(Color.WHITE);
        tituloa.setFont(new Font("Monospaced", Font.PLAIN, 25));
        tituloa.setBounds(0, 10, 740, 30);
        tituloa.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(tituloa);
        lblAtzera = new JLabel("Sakatu <B> atzera joateko");
        lblAtzera.setForeground(Color.WHITE);
        lblAtzera.setFont(new Font("Consolas", Font.BOLD, 18));
        lblAtzera.setHorizontalAlignment(SwingConstants.CENTER);
        lblAtzera.setBounds(0, 340, 740, 30);
        contentPane.add(lblAtzera);
        lblAtzera.setVisible(false);
        contentPane.add(background);
        contentPane.setComponentZOrder(background, contentPane.getComponentCount() - 1);

        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_B) {
                    dispose();
                }
            }
        });

        setFocusable(true);

        Timer blinkTimer = new Timer(750, e -> {
            lblAtzera.setVisible(!lblAtzera.isVisible());
        });
        blinkTimer.start();
    }

    public static PuntuazioTabla getPuntuazioTabla() {
        if (nPuntuazioTabla == null) {
            nPuntuazioTabla = new PuntuazioTabla();
        }
        return nPuntuazioTabla;
    }
}