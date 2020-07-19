package ar.edu.unq.epers.bichomon.backend.model.ubicacion;

public enum CostoCamino {

    tierra(1),
    mar(2),
    aire(5);

    private final int id;

    CostoCamino(int id) { this.id = id; }

    public int getValue() { return id; }

}