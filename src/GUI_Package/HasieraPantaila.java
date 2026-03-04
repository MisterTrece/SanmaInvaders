package GUI_Package;
import javax.swing.*;
import java.awt.*;

public class HasieraPantaila extends JFrame {

    public HasieraPantaila() {
        // Lehioaren oinarrizko konfigurazioa
        this.setTitle("Space Invaders");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600); 
        this.getContentPane().setBackground(Color.BLACK); 
        this.setLayout(new BorderLayout());

        // Goiko zatia
        JLabel lblTop = new JLabel("Press <Up-Down-Left-Right> to move, <M> to shoot", SwingConstants.CENTER);
        lblTop.setForeground(Color.WHITE); // letra kolorea
        lblTop.setFont(new Font("Monospaced", Font.PLAIN, 16)); // estiloa
        lblTop.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0)); //margina
        this.add(lblTop, BorderLayout.NORTH);

        // --- Erdiko zatia ---
        //ideia: bi lerro geroago agertzen den irudia src karpetan egotea

        ImageIcon icono = new ImageIcon("src/GUI_Package/logo.png"); 
        JLabel lblImagen = new JLabel(icono, SwingConstants.CENTER);
        this.add(lblImagen, BorderLayout.CENTER);

        //Beheko zatia
        JLabel lblBottom = new JLabel("* Press <1> <G> <B> <R> to play *", SwingConstants.CENTER);
        lblBottom.setForeground(Color.WHITE);
        lblBottom.setFont(new Font("Monospaced", Font.PLAIN, 18));
        lblBottom.setBorder(BorderFactory.createEmptyBorder(0, 0, 80, 0)); 
        this.add(lblBottom, BorderLayout.SOUTH);
    }

    //main tempotala
    public static void main(String[] args) {
        HasieraPantaila pantaila = new HasieraPantaila();
        pantaila.setLocationRelativeTo(null); 
    }
}