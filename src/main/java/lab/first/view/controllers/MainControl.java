package lab.first.view.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lab.first.dao.FactoryDAO;

import static lab.first.view.controllers.Util.toScene;

public class MainControl {

    public FactoryDAO factoryDAO = FactoryDAO.getInstance();

    @FXML
    private void mainRoutesButtonAction(ActionEvent event) throws Exception {
        toScene("route/list_routes.fxml", "List Routes");
    }

    @FXML
    private void mainClientsButtonAction(ActionEvent event) throws Exception {
        toScene("client/list_clients.fxml", "List Clients");
    }

    @FXML
    private void mainTicketsButtonAction(ActionEvent event) throws Exception {
        toScene("ticket/list_tickets.fxml", "List Tickets");
    }

}
