package de.tekup.ds.ex.services;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

import de.tekup.ds.ex.Models.*;
import de.tekup.ds.ex.repositories.ClientRepository;
import de.tekup.ds.ex.repositories.MetRepository;
import de.tekup.ds.ex.repositories.TableRepository;
import de.tekup.ds.ex.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component 
public class ImplService implements Services {
@Autowired
    private ClientRepository clientRepository;
    @Autowired

    private MetRepository metRepository;
    @Autowired

    private TableRepository tableRepository;
    @Autowired

    private TicketRepository ticketRepository;

	public Plat PlatLePlusAchete(LocalDate start, LocalDate end)throws Exception{


        List<Ticket> ticketList = ticketRepository.findAll()
                .stream()
                .filter(star -> star.getDatetime().toLocalDate().isAfter(start) && star.getDatetime().toLocalDate().isBefore(end))
                .collect(Collectors.toList());


        List<Met> metList = new ArrayList<>();
        for (Ticket t:ticketList) {
            metList.addAll(t.getComposeTicket());
        }


        List<Plat> platList = new ArrayList<>();
        for (Met met : metList) {
            if (met instanceof Plat) {
                platList.add((Plat) met);
            }
        }
        return platList.stream()
                    .collect(Collectors.groupingBy(plat-> plat, Collectors.counting()))
                .entrySet()
                .stream().max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();

    }

    public Client ClientLePlusFidele()throws Exception{

        return clientRepository.findAll().stream()
            .max(Comparator.comparing(client -> client.getTickets().size()))
            .orElseThrow(()-> new Exception(""));
    }



   public Table TableLaPlusReserve() throws Exception {

	    return tableRepository.findAll().stream()
                .max(Comparator.comparing(table -> table.getTickets().size()))
                .orElseThrow(()-> new Exception(""));
   }


    public LocalDate JourLePlusReserve(Client client){

        List<Ticket> tickets = ticketRepository.findAll().stream()
                                                        .filter(ticket -> ticket.getClient().equals(client))
                                                        .collect(Collectors.toList());

       Map<LocalDate, Long> map = tickets.stream()
                                .collect(Collectors.groupingBy(ticket -> ticket.getDatetime().toLocalDate(), Collectors.counting()));


       return map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();

    }

    public double GetRevenueParMois() {
        List<Ticket> stars = ticketRepository.findAll()
                .stream()
                .filter(star -> star.getDatetime().toLocalDate().getMonth().equals(LocalDate.now().getMonth()))
                .collect(Collectors.toList());
return stars.stream().mapToDouble(p -> p.getAddition()+p.getTable().getSupplement()).sum();



    }

//    public double getrevenuparsemaine() {
//        WeekFields weekFields = WeekFields.of(Locale.getDefault());
//
//        List<Ticket> stars = ticketRepository.findAll()
//                .stream()
//                .filter(star -> star.getDatetime().toLocalDate().get(weekFields.weekOfWeekBasedYear()).equals(LocalDate.now().get(weekFields.weekOfWeekBasedYear())))
//                .collect(Collectors.toList());
//        return stars.stream().mapToDouble(p -> p.getAddition()+p.getTable().getSupplement()).sum();
//
//
//
//
//
//
//    }


    public double GetRevenuParJour() {
        List<Ticket> stars = ticketRepository.findAll()
                .stream()
                .filter(star -> star.getDatetime().toLocalDate().isEqual(LocalDate.now()))
                .collect(Collectors.toList());
        return stars.stream().mapToDouble(p -> p.getAddition()+p.getTable().getSupplement()).sum();





    }

    public double GetRevenuParPeriode(LocalDate dateBegin, LocalDate dateEnd) {
        List<Ticket> stars = ticketRepository.findAll()
                .stream()
                .filter(star -> star.getDatetime().toLocalDate().isAfter(dateBegin) && star.getDatetime().toLocalDate().isBefore(dateEnd))
                .collect(Collectors.toList());
        return stars.stream().mapToDouble(p -> p.getAddition()+p.getTable().getSupplement()).sum();




    }

}