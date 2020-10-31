package server.models.remote;

import globalmodels.ShipInformation;

public interface IRemoteHandler {

    void onIncomingShip(ShipInformation ship);

    void onLoadingFinished(ShipInformation info);
}
