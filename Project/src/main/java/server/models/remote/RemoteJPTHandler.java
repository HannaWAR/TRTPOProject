package server.models.remote;

import globalmodels.ShipInformation;
import jtp.JTPServer;
import server.models.Port;

import java.io.IOException;

import static utils.Constants.FINISHED;
import static utils.Constants.INCOMING;

public class RemoteJPTHandler implements IRemoteHandler {

    private Port port;

    private JTPServer server;

    public RemoteJPTHandler(Port port) throws IOException {
        this.port = port;
        server = new JTPServer();
        initEndPoints();
        server.start();
    }

    private void initEndPoints() {//выставляет действия по некоторым событиям
        server.registerInputEndPoint(INCOMING, message ->
                onIncomingShip(message.getDBody(ShipInformation.class)));
        server.registerInputEndPoint(FINISHED , message -> {
            onLoadingFinished(message.getDBody(ShipInformation.class));
        });
    }

    @Override
    public void onIncomingShip(ShipInformation ship) {
        port.incomingShip(ship);
    }

    @Override
    public void onLoadingFinished(ShipInformation info) {
        port.loadedFinished(info);

    }
}
