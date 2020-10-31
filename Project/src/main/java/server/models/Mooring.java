package server.models;

import globalmodels.ShipInformation;

public class Mooring implements Informative {

    private ShipInformation ship;

    public boolean isEmpty(){
        return ship==null;
    }

    public ShipInformation free(){
        var info = ship;
        ship = null;
        return info;
    }

    public ShipInformation getShip() {
        return ship;
    }

    public void setShip(ShipInformation ship) {
        this.ship = ship;
    }

    @Override
    public String getInfo() {
        if(isEmpty()){
            return "Причал пуст\n";
        }
        return String.format("Причал занят: %s\n", ship.getInfo());
    }
}
