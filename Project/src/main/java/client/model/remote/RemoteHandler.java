package client.model.remote;

import globalmodels.ShipInformation;

public interface RemoteHandler {

    void incomingMessage(ShipInformation ship);

    void loadFinished(ShipInformation ship);

    String getToken();

}
