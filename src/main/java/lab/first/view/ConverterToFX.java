package lab.first.view;

import lab.first.view.model.AirshipFX;
import lab.first.view.model.ClientFX;
import lab.first.view.model.RouteFX;
import lab.first.view.model.TicketFX;
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

        return new Ticket(ticketFx.getId(), convertFxToModel(ticketFx.getAirship()), convertFxToModel(ticketFx.getRoute()));

    }
}
