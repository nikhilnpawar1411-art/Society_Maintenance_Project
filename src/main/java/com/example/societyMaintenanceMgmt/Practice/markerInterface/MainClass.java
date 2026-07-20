package com.example.societyMaintenanceMgmt.Practice.markerInterface;

public class MainClass {
    public static void main(String[] args) {
        ClassA ca=new ClassA();

        if(ca instanceof TestMarkerInterface){
            System.out.println("Its marker interface");
        }else{
            System.out.println("Not a marker interface");
        }
    }
}
