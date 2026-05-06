package model;

public enum ZailtasunMaila {
    ERRAZA("Erraza", 4, 6, 6),
    NORMALA("Normala", 4, 8, 4),
    ZAILA("Zaila", 6, 10, 2);

    private final String izena;
    private final int etsaiMin;
    private final int etsaiMax;
    private final int etsaiMugimenduTick;

    ZailtasunMaila(String pIzena, int pEtsaiMin, int pEtsaiMax, int pEtsaiMugimenduTick) {
        this.izena = pIzena;
        this.etsaiMin = pEtsaiMin;
        this.etsaiMax = pEtsaiMax;
        this.etsaiMugimenduTick = pEtsaiMugimenduTick;
    }

    public String getIzena() {
        return izena;
    }

    public int getEtsaiMin() {
        return etsaiMin;
    }

    public int getEtsaiMax() {
        return etsaiMax;
    }

    public int getEtsaiMugimenduTick() {
        return etsaiMugimenduTick;
    }
}
