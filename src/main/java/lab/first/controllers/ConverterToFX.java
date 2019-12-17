package lab.first.controllers;

import lab.first.controllers.FXModels.AirshipFX;
import lab.first.controllers.FXModels.ClientFX;
import lab.first.controllers.FXModels.RouteFX;
import lab.first.controllers.FXModels.TicketFX;
import lab.first.model.Airship;
import lab.first.model.Client;
import lab.first.model.Route;
import lab.first.model.Ticket;

public class ConverterToFX {

    public RouteFX convertToFx(Route route) {
        return new RouteFX(route);
    }

    public Route convertFxToModel(RouteFX routeFx) {
        return new Route(routeFx.getId(), routeFx.getStartPoint(), routeFx.getEndPoint());
    }

    public AirshipFX convertToFx(Airship airship) {
        return new AirshipFX(airship);
    }

    public Airship convertFxToModel(AirshipFX airshipFx) {
        return new Airship(airshipFx.getId(), airshipFx.getModel(), airshipFx.getNumberOfSeat());
    }

    public ClientFX convertToFx(Client client) {
        return new ClientFX(client);
    }

    public Client convertFxToModel(ClientFX clientFx) {
        return new Client(clientFx.getId(), clientFx.getFirstName(), clientFx.getMiddleName(), clientFx.getLastName(), clientFx.getTickets());
    }

    public TicketFX convertToFx(Ticket ticket) {
        return new TicketFX(ticket);
    }

    public Ticket convertFxToModel(TicketFX ticketFx) {
        return new Ticket(ticketFx.getId(), convertFxToModel(ticketFx.getAirship()), ticketFx.getRouteList());
    }
}
