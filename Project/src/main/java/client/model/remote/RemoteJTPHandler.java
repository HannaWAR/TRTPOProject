package client.model.remote;

import client.model.Ship;
import globalmodels.ShipInformation;
import jtp.JTPClient;
import jtp.JTPMessage;

import java.io.IOException;

import static utils.Constants.FINISHED;
import static utils.Constants.INCOMING;

public class RemoteJTPHandler implements RemoteHandler {//отвечает за отправку и получение смс

    private Ship ship;

    private JTPClient client;

    public RemoteJTPHandler(Ship ship) throws IOException {
        this.ship = ship;
        client = new JTPClient();
        initEndpoints();
        client.start();
    }

    private void initEndpoints() {

    }

    @Override
    public String getToken() {
        return client.getToken();
    }

    @Override
    public void incomingMessage(ShipInformation ship) {
        client.sendMessage(JTPMessage.createMessage(INCOMING, ship));
    }

    @Override
    public void loadFinished(ShipInformation info) {
        client.sendMessage(JTPMessage.createMessage(FINISHED, info));
    }

}
