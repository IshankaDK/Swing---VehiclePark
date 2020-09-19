/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

/**
 *
 * @author Ishanka
 */
public class ParkedVehicle {
    private String vNum;
    private String type;
    private String owner;
    private String arrival;
    private String departure;
    private int cost;
    private String totParkTime;
    private int totFee;
    private String payMethod;

    public ParkedVehicle() {
    }
    public ParkedVehicle(String vNum) {
        this.vNum = vNum;
    }
     public ParkedVehicle(String vNum, String arrival) {
        this.vNum = vNum;
        this.arrival = arrival;
        
    }
    public ParkedVehicle(String totParkTime, int totFee) {
        this.totParkTime = totParkTime;
        this.totFee = totFee;
        
    }

    public ParkedVehicle(String vNum, String type, String owner, String arrival, String departure, int cost, String totParkTime, int totFee, String payMethod) {
        this.vNum = vNum;
        this.type = type;
        this.owner = owner;
        this.arrival = arrival;
        this.departure = departure;
        this.cost = cost;
        this.totParkTime = totParkTime;
        this.totFee = totFee;
        this.payMethod = payMethod;
    }
     
      public ParkedVehicle(String vNum,String arrival, String departure, String totParkTime, int totFee, String payMethod) {
        this.vNum = vNum;
        this.arrival = arrival;
        this.departure = departure;
        this.totParkTime = totParkTime;
        this.totFee = totFee;
        this.payMethod = payMethod;
    }
   

    public ParkedVehicle(String vNum,String type,String owner, String arrival) {
        this.vNum = vNum;
        this.type = type;
        this.owner = owner;
        this.arrival = arrival;
    }

     public ParkedVehicle(String vNum, String departure,int cost) {
        this.vNum = vNum;
        this.departure = departure;
        this.cost = cost;
    }
    public ParkedVehicle(String vNum, String departure,String method) {
        this.vNum = vNum;
        this.departure = departure;
        this.payMethod = method;
    }

    /**
     * @return the vNum
     */
    public String getvNum() {
        return vNum;
    }

    /**
     * @param vNum the vNum to set
     */
    public void setvNum(String vNum) {
        this.vNum = vNum;
    }

    /**
     * @return the arrival
     */
    public String getArrival() {
        return arrival;
    }

    /**
     * @param arrival the arrival to set
     */
    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    /**
     * @return the departure
     */
    public String getDeparture() {
        return departure;
    }

    /**
     * @param departure the departure to set
     */
    public void setDeparture(String departure) {
        this.departure = departure;
    }

    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * @return the totParkTime
     */
    public String getTotParkTime() {
        return totParkTime;
    }

    /**
     * @param totParkTime the totParkTime to set
     */
    public void setTotParkTime(String totParkTime) {
        this.totParkTime = totParkTime;
    }

    /**
     * @return the totFee
     */
    public int getTotFee() {
        return totFee;
    }

    /**
     * @param totFee the totFee to set
     */
    public void setTotFee(int totFee) {
        this.totFee = totFee;
    }

    /**
     * @return the payMethod
     */
    public String getPayMethod() {
        return payMethod;
    }

    /**
     * @param payMethod the payMethod to set
     */
    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    
 

    
   


    
}
