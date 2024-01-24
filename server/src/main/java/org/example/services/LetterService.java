package org.example.services;

import org.example.dao.LetterDAO;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class LetterService {
    public void sendLetterInfo(String userID, PrintWriter clientOut) {
        LetterDAO letterDAO = new LetterDAO();
        try {
            List<String> letterInfoList = letterDAO.getLetterInfoByUserId(userID);
            StringBuilder letterInfo = new StringBuilder();
            for (String info : letterInfoList) {
                letterInfo.append(info);
            }
            System.out.println("List: " + letterInfo);
            clientOut.println(letterInfo);
        } catch (SQLException e) {
            clientOut.println("ERROR");
            e.printStackTrace();
        }
    }

}
