package controller;

import model.Espazio;
import GUI_Package.HasieraPantaila;
import GUI_Package.Matrizea;

public class GameController {

    private Espazio modelo;
    private HasieraPantaila hasiera;
    private Matrizea joko;

    public static void main(String[] args) {
        new GameController().iniciar();
    }

    public void iniciar() {
        modelo = Espazio.getEspazioEMA();

        // Hasierako panela
        hasiera = new HasieraPantaila(this);
        hasiera.setVisible(true);
    }

    public void empezarPartida(String tipoNave) {
        // configurar depende del tipo de nave

        // Cerrar u ocultar hasierako panela
        hasiera.setVisible(false);

        // Crear la ventana del juego si aún no existe
        if (joko == null) {
            joko = new Matrizea(modelo, this);
        }
        joko.setVisible(true);
    }

    public void moverNave(int dx, int dy) {
    
    }

    public void disparar() {
        
    }
}