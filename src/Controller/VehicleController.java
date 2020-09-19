/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Menu.Bus;
import Menu.Car;
import Menu.Lorry;
import Menu.ParkedSlot;
import Menu.ParkedVehicle;
import Menu.Van;
import Menu.Vehicle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Ishanka
 */
public class VehicleController {
    public static boolean saveVehicle(Vehicle vehicle) throws SQLException, ClassNotFoundException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("insert into vehicle values (?,?,?)");
        stm.setObject(3, vehicle.getType());
        stm.setObject(1, vehicle.getvNum());
        stm.setObject(2, vehicle.getOwner());
        return stm.executeUpdate()>0;
    }
   
    
    public static Vehicle searchVehicle(String vNum) throws SQLException, ClassNotFoundException{
         PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("select * from vehicle where VehicleNumber = ? ");
         stm.setObject(1, vNum);
         ResultSet rst = stm.executeQuery();
         if(rst.next()){
             Vehicle vehicle = new Vehicle(rst.getString("VehicleNumber"),rst.getString("ownerName"),rst.getString("vehicletype"));
             return vehicle;
         }
         return null;
    }
    public static boolean savePark(ParkedVehicle parkedvehicle) throws SQLException, ClassNotFoundException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("insert into parkingdetails values (?,?,?,?,?,?,?)");
        stm.setObject(1, parkedvehicle.getvNum());
        stm.setObject(2, parkedvehicle.getArrival());
        stm.setObject(3, parkedvehicle.getDeparture());
        stm.setObject(4, parkedvehicle.getCost());
        stm.setObject(5, parkedvehicle.getTotParkTime());
        stm.setObject(6, parkedvehicle.getTotFee());
        stm.setObject(7, parkedvehicle.getPayMethod());
        return stm.executeUpdate()>0;
    }
    public static ParkedVehicle searchParkedVehicle(String vNum) throws SQLException, ClassNotFoundException{
         String sql ="select distinct vehicle.VehicleNumber,OwnerName,VehicleType,parkingdetails.Arrival from Vehicle,parkingdetails where vehicle.vehiclenumber=? and parkingdetails.vehiclenumber=? and parkingdetails.costperhour=0.00;";
         PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
         stm.setObject(1, vNum);
         stm.setObject(2, vNum);
         ResultSet rst = stm.executeQuery();
         if(rst.next()){
             ParkedVehicle vehicle = new ParkedVehicle(rst.getString("VehicleNumber"),rst.getString("vehicletype"),rst.getString("ownerName"),rst.getString("Arrival"));
             return vehicle;
         }
         return null;
    }
    public static boolean giveSlot(String type) throws SQLException, ClassNotFoundException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("update parkingslot set availableslot = (availableslot-1) where vehicletype = ?");
        stm.setObject(1, type);
        return stm.executeUpdate()>0;
       
    }
    public static boolean calculateFee(ParkedVehicle parkedVehicle) throws SQLException, ClassNotFoundException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("update parkingdetails set departure = ? ,costperhour=? , parkedtime =(timediff(departure,arrival)),parkingfee=(timediff(departure,arrival)*costperhour/10000) where vehiclenumber = ? and parkingfee=0.00 ");
       // PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement("select parkedtime,parkingfee from parkingdetails where vehiclenumber = ?;");
        stm.setObject(1, parkedVehicle.getDeparture());
        stm.setObject(2, parkedVehicle.getCost());
        stm.setObject(3, parkedVehicle.getvNum());
        return stm.executeUpdate()>0;
       
    }
     public static ParkedVehicle calculateFee1(String vNum,String departure) throws SQLException, ClassNotFoundException{
       // PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("update parkingdetails set departure = ? ,costperhour=? , parkedtime =(timediff(departure,arrival)),parkingfee=(timediff(departure,arrival)*costperhour/10000) where vehiclenumber = ? ");
        PreparedStatement stm1 = DBConnection.getInstance().getConnection().prepareStatement("select parkedtime,parkingfee from parkingdetails where vehiclenumber = ? and departure =?;");
      
        stm1.setObject(1, vNum);
        stm1.setObject(2,departure);
        ResultSet rst = stm1.executeQuery();
        if(rst.next()){
             ParkedVehicle vehicle = new ParkedVehicle(rst.getString("parkedtime"),rst.getInt("parkingfee"));
             return vehicle;
         }
        return null;
    }
     public static boolean saveLeave(ParkedVehicle parkedvehicle) throws SQLException, ClassNotFoundException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Update parkingdetails set paymentmethod=? where vehiclenumber= ? and departure=?");
        stm.setObject(2, parkedvehicle.getvNum());
        stm.setObject(1, parkedvehicle.getPayMethod());
        stm.setObject(3, parkedvehicle.getDeparture());
        return stm.executeUpdate()>0;
    }
    public static boolean releaseSlot(String type) throws SQLException, ClassNotFoundException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("update parkingslot set availableslot = (availableslot+1) where vehicletype = ?");
        stm.setObject(1, type);
        return stm.executeUpdate()>0;
       
    }
      public static ParkedVehicle checkPayment(String vNum) throws SQLException, ClassNotFoundException{
         String sql ="select distinct vehicle.VehicleNumber,OwnerName,VehicleType,parkingdetails.Arrival,departure,costperhour,parkedtime,parkingfee,paymentmethod from Vehicle,parkingdetails where vehicle.vehiclenumber=? and parkingdetails.vehiclenumber=? order by arrival desc limit 1";
         PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(sql);
         stm.setObject(1, vNum);
         stm.setObject(2, vNum);
         ResultSet rst = stm.executeQuery();
         while(rst.next()){
             ParkedVehicle vehicle = new ParkedVehicle(rst.getString("VehicleNumber"),rst.getString("vehicletype"),rst.getString("ownerName"),rst.getString("Arrival"),rst.getString("departure"),rst.getInt("costperhour"),rst.getString("parkedtime"),rst.getInt("parkingfee"),rst.getString("paymentmethod"));
             return vehicle;
         }
         return null;
    }
    public static ArrayList<Vehicle>getAllVehicles(int index ) throws ClassNotFoundException, SQLException{
       
        ArrayList <Vehicle>vehicleList=new ArrayList<>();
            if(index==0){
             ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select vehiclenumber,ownername from vehicle where vehicletype = 'Car'");
             while(rst.next()){
             Vehicle vehicle = new Car(rst.getString("vehiclenumber"),rst.getString("ownername"));vehicleList.add(vehicle);}
            }
            if(index==1){
             ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select vehiclenumber,ownername from vehicle where vehicletype = 'Van'");   
             while(rst.next()){
             Vehicle   vehicle = new Van(rst.getString("vehiclenumber"),rst.getString("ownername"));vehicleList.add(vehicle);}
            }
            if(index==2){
             ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select vehiclenumber,ownername from vehicle where vehicletype = 'Bus'");
             while(rst.next()){
             Vehicle vehicle = new Bus(rst.getString("vehiclenumber"),rst.getString("ownername"));vehicleList.add(vehicle);}
            }
            if(index==3){
             ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select vehiclenumber,ownername from vehicle where vehicletype = 'Lorry'");
             while(rst.next()){
             Vehicle vehicle = new Lorry(rst.getString("vehiclenumber"),rst.getString("ownername"));vehicleList.add(vehicle);}
            }
        return vehicleList;
    }  
    
    public static ArrayList<ParkedVehicle>getAllParks() throws ClassNotFoundException, SQLException{
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * From parkingdetails");
        ArrayList <ParkedVehicle>parkedVehiclesList=new ArrayList<>();
        while(rst.next()){
             ParkedVehicle parkedVehicle = new ParkedVehicle(rst.getString("vehiclenumber"),rst.getString("Arrival"),rst.getString("departure"),rst.getString("parkedtime"),rst.getInt("parkingfee"),rst.getString("paymentmethod"));
            parkedVehiclesList.add(parkedVehicle);
        }
        return parkedVehiclesList;
    }
     public static ArrayList<ParkedVehicle>getAllParks(String vNum) throws ClassNotFoundException, SQLException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Select * From parkingdetails where vehiclenumber=?");
        stm.setObject(1, vNum);
        ResultSet rst = stm.executeQuery();
        ArrayList <ParkedVehicle>parkedVehiclesList=new ArrayList<>();
        while(rst.next()){
             ParkedVehicle parkedVehicle = new ParkedVehicle(rst.getString("vehiclenumber"),rst.getString("Arrival"),rst.getString("departure"),rst.getString("parkedtime"),rst.getInt("parkingfee"),rst.getString("paymentmethod"));
            parkedVehiclesList.add(parkedVehicle);
        }
        return parkedVehiclesList;
    } 
    public static ArrayList<ParkedSlot>getAllSlot() throws ClassNotFoundException, SQLException{
        PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement("Select * From parkingslot");
        ResultSet rst = stm.executeQuery();
        ArrayList <ParkedSlot>parkedSlotList=new ArrayList<>();
        while(rst.next()){
            ParkedSlot parkedSlot = new ParkedSlot(rst.getString("vehicletype"),rst.getInt("availableslot") );
            parkedSlotList.add(parkedSlot);
        }
        return parkedSlotList;
    } 
}
