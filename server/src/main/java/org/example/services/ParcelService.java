package org.example.services;

import org.example.dao.ParcelDAO;
import org.example.models.ClientNotificationSystem;
import org.example.models.Observer;
import org.example.models.Parcel;
import org.example.server.Server;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class ParcelService {
    private ParcelDAO parcelDAO;

    public ParcelService() {
        this.parcelDAO = new ParcelDAO();
    }

    public void trackParcel(String parcelID, Integer clientID, PrintWriter clientOut) {
        try {
            Parcel parcel = Server.getParcelById(Integer.parseInt(parcelID));
            if (parcel == null) {
                parcel = parcelDAO.getParcelById(Integer.parseInt(parcelID));
                if (parcel != null) {
                    Server.addOrUpdateParcel(parcel);
                }
            }

            if (parcel != null) {
                Observer observer = new ClientNotificationSystem();
                parcel.registerObserver(observer);
                clientOut.println("SUCCESS");
            } else {
                clientOut.println("FAIL");
            }
        } catch (SQLException e) {
            clientOut.println("ERROR");
            e.printStackTrace();
        }
    }

    public void testChangeParcelStatus(int parcelId, String newStatus) {
        Parcel parcel = Server.getParcelById(parcelId);
        if (parcel != null) {
            parcel.setStatus(newStatus);
            Server.addOrUpdateParcel(parcel);
            try {
                parcelDAO.updateParcelStatus(parcelId, newStatus);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
