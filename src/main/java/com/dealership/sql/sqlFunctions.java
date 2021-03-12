package com.dealership.sql;

import com.dealership.car.car;
import com.dealership.car.offer;
import com.dealership.jdbc.ConnectionFactory;
import com.dealership.system.customer;
import com.dealership.system.employee;
import com.dealership.system.user;

import java.sql.*;

public class sqlFunctions {
    public static ConnectionFactory conn = new ConnectionFactory();

    public static user retrieveAccount(String username, String password){
        String SQL = "SELECT username, userpassword, firstname, lastname, accounttype"
                + " FROM accounts "
                + "WHERE username = '" + username + "'";
        user account = new user();

        try (Connection conn = ConnectionFactory.connect(); Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE))//Statement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,
                //ResultSet.CONCUR_UPDATABLE)) {
        {
            //pstmt.setString(1, username);
            //pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery(SQL);
            //rs.first();
            //System.out.println(rs.getString(1));

            //System.out.println(rs.getCharacterStream(1));
            //rs.first();
            //do{
                //if (rs.getString(5).equals("employee")){
                   //account = new user(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5));
            while(rs.next()) {
                account.setAccountType(rs.getString(5));
                account.setFirstName(rs.getString(3));
                account.setPassword(rs.getString(2));
                account.setUsername(rs.getString(1));
                account.setLastName((rs.getString(4)));
            }
                    return account;
               // }
                //else{
                 //   user account = new user(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5));
                 //   return account;
              //  }
            //}while (rs.next());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return account;
    }

    public static void createAccount(user u) {
        String SQL = "INSERT INTO public.accounts" +
                "(username, userpassword, firstname, lastname, accounttype)" +
                "VALUES(?, ?, ?, ?, ?);";

        try (Connection conn = ConnectionFactory.connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, u.getUsername());
            pstmt.setString(2, u.getPassword());
            pstmt.setString(3, u.getFirstName());
            pstmt.setString(4, u.getLastName());
            pstmt.setString(5, u.accountType);
            pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void storeCarIntoLot(car c) {
        String SQL = "INSERT INTO public.carlist" +
                "(vin, carmake, carmodel, caryear, miles, stickerprice, monthlypayment, remainingbalance, username)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = ConnectionFactory.connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(2, c.getCarMake());
            pstmt.setString(3, c.getCarModel());
            pstmt.setInt(4, c.getCarYear());
            pstmt.setInt(5, c.getMiles());
            pstmt.setInt(1, c.getVIN());
            pstmt.setDouble(6, c.getStickerPrice());
            pstmt.setDouble(7, c.getMonthlyPayment());
            pstmt.setDouble(8, c.getRemainingBalance());
            pstmt.setString(9, c.getUsername());

            pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //Done
    public static int countCars(){
        String SQL = "SELECT *" +
                "FROM public.carlist;";

        try (Connection conn = ConnectionFactory.connect(); PreparedStatement pstmt = conn.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = pstmt.executeQuery();
            rs.last();

            return rs.getRow();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public static car[] retrieveAllCarsIntoArray(){
        String SQL = "SELECT * " +
                "FROM public.carlist;";

        int arraySize = countCars();
        car[] lot = new car[arraySize];

        try (Connection conn = ConnectionFactory.connect(); Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = pstmt.executeQuery(SQL);

            int i = 0;
            rs.first();
            do {
                car c = new car(rs.getString(3), rs.getString(2), rs.getInt(4),
                        rs.getInt(5), rs.getInt(1), rs.getDouble(6),
                        rs.getDouble(7), rs.getDouble(8),
                        retrieveAllOffersOnCar(rs.getInt(1)), retrieveAllPaymentsOnCar(rs.getInt(1)), rs.getString(9));
                lot[i] = c;
                //System.out.println(lot[i].toString());
                i++;
            } while (rs.next());
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return lot;
    }

    public static void acceptOffer(int vin, double offer){
        String SQL2 = "SELECT username "
                + "FROM public.offers "
                + "WHERE vin=" + vin + " AND offer=" + offer + ";";

        try (Connection conn2 = ConnectionFactory.connect(); Statement pstmt2 = conn2.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs2 = pstmt2.executeQuery(SQL2);

            rs2.first();
            String tempUsername = rs2.getString(1);

            String SQL = "UPDATE public.carlist "
                    + "SET username='" + tempUsername
                    + "' WHERE vin=" + vin + ";";

            try (Connection conn = ConnectionFactory.connect(); Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                pstmt.executeUpdate(SQL);
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        rejectAllOtherOffers(vin);
    }

    public static void rejectAllOtherOffers(int vin){
        String SQL = "DELETE "
                + "FROM public.offers "
                + "WHERE vin=" + vin + ";";

        try (Connection conn = ConnectionFactory.connect(); Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            pstmt.executeUpdate(SQL);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeAllOtherPayments(int vin){
        String SQL = "DELETE "
                + "FROM public.finance "
                + "WHERE vin=" + vin + ";";

        try (Connection conn = ConnectionFactory.connect(); Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE)) {
            pstmt.executeUpdate(SQL);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void rejectOffer(double offer){
        String SQL = "DELETE "
                + "FROM public.offers "
                + "WHERE offer=" + offer + ";";

        try (Connection conn = ConnectionFactory.connect(); Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            pstmt.executeUpdate(SQL);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean tableIsEmpty(String tableName){
        String SQL = "SELECT COUNT(*) " +
                "FROM public." + tableName + ";";

        try (Connection conn = ConnectionFactory.connect(); Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            ResultSet rs = pstmt.executeQuery(SQL);

            rs.first();

            if (rs.getInt(1) == 0){
                return true;
            }
            else
                return false;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    //Done
    public static float[] retrieveAllOffersOnCar(int vin){
        String SQL = "SELECT * FROM public.offers\n" +
                "WHERE vin=" + vin + ";";


        try (Connection conn = ConnectionFactory.connect(); Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            ResultSet rs = pstmt.executeQuery(SQL);

            if (rs.first() == false){
                return null;
            }

            rs.last();
            int size = rs.getRow();
            rs.first();

            float[] offers = new float[size];
            int i = 0;

            do {
                offers[i] = rs.getFloat(2);
                i++;
            } while (rs.next());

            return offers;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    //Done
    public static float[] retrieveAllPaymentsOnCar(int vin){
        String SQL = "SELECT * FROM public.finance\n" +
                "WHERE vin=" + vin + ";";
        try (Connection conn = ConnectionFactory.connect(); Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){
            ResultSet rs = pstmt.executeQuery(SQL);

            if (rs.first() == false){
                return null;
            }

            rs.last();
            int size = rs.getRow();
            rs.first();

            float[] payments = new float[size];
            int i = 0;

            do {
                payments[i] = rs.getFloat(2);
                i++;
            } while (rs.next());

            return payments;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void storeOffer(int vin, String username, double offer){
        String SQL = "INSERT INTO public.offers" +
                "(vin, offer, username)" +
                "VALUES(?, ?, ?);";

        try (Connection conn = ConnectionFactory.connect(); PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(3, username);
            pstmt.setInt(1, vin);
            pstmt.setDouble(2, offer);
            pstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeCarFromLot(int vin){
        String SQL = "DELETE FROM public.carlist\n" +
                "WHERE vin=" + vin + ";";

        try (Connection conn = ConnectionFactory.connect();  Statement pstmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            rejectAllOtherOffers(vin);
            removeAllOtherPayments(vin);
            pstmt.executeUpdate(SQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean storeAccount(user u){
        String SQL = "INSERT INTO accounts (username, password, firstName, lastName, accountType)"
                + "VALUES (?, ?, ?, ?, ?)";

        return false;
    }
}
