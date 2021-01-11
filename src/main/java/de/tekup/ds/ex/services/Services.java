package de.tekup.ds.ex.services;

import de.tekup.ds.ex.Models.Client;
import de.tekup.ds.ex.Models.Plat;
import de.tekup.ds.ex.Models.Table;

import java.time.LocalDate;

public interface Services {
    Plat PlatLePlusAchete(LocalDate start, LocalDate end)throws Exception;
    Client ClientLePlusFidele()throws Exception;
    LocalDate JourLePlusReserve(Client client) throws Exception;
    double GetRevenueParMois();
    Table TableLaPlusReserve()throws Exception;
    double GetRevenuParJour();
    double GetRevenuParPeriode(LocalDate dateBegin, LocalDate dateEnd);
}
