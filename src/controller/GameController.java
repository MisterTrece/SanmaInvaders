package controller;

import javax.swing.Timer;

import model.Espazio;
import GUI_Package.HasieraPantaila;
import GUI_Package.Matrizea;

public class GameController {

    private Espazio modelo;
    private HasieraPantaila hasiera;
    private Matrizea joko;

    private Timer tiroTimer;

    public static void main(String[] args) {
        new GameController().iniciar();
    }

    public void iniciar() {
        modelo = Espazio.getEspazioEMA();

        // Hasierako panela
        hasiera = new HasieraPantaila(this);
        hasiera.setLocationRelativeTo(null);
        hasiera.setVisible(true);
    }

    public void empezarPartida(String tipoNave) {
        // configurar depende del tipo de nave

        // Cerrar u ocultar hasierako panela
        hasiera.setVisible(false);

        // Crear la ventana del juego si no existe
        if (joko == null) {
            joko = new Matrizea(modelo, this);
        }
        joko.setLocationRelativeTo(null);
        joko.setVisible(true);

        if (tiroTimer == null) {
            tiroTimer = new Timer(50, e -> modelo.moverTiros());
        }
        tiroTimer.start();
    }

    public void moverNave(int dx, int dy) {
        modelo.moverNave(dx, dy);
    }

    public void tiro() {
        modelo.tiro();
    }
}