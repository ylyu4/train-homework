package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String graph = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
        TrainStations trainStations = new TrainStations(graph);
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the number for the function you want to test: ");
        System.out.println("1: Calculate the route distances");
        System.out.println("2: Calculate the route numbers with a maximum stop for two stations");
        System.out.println("3: Calculate the route numbers with a fixed stop for two stations");
        System.out.println("4: Calculate the route length for the shortest route between two stations");
        System.out.println("5: Calculate the route numbers for two stations with limit distances");
        System.out.println("6: Calculate the route durations");
        System.out.println("7: Calculate the route numbers with a maximum durations for two stations");
        System.out.println("8: Calculate the route numbers with a fixed durations for two stations");
        System.out.println("9: Calculate the route durations for the quickest route between two stations");
        System.out.println("10: Calculate the route numbers for two stations with limit durations");
        System.out.println("Please use '-' to split your parameter!!!");
        while (sc.hasNext()) {
            Integer command = Integer.parseInt(sc.next());
            switch (command) {
                case 1 :
                    System.out.println(trainStations.calculateDistance(sc.next().split("-")));
                    break;
                case 2 :
                    System.out.println(trainStations.calculateRouteNumbersWithMaximumStop(sc.next().split("-")));
                    break;
                case 3 :
                    System.out.println(trainStations.calculateRouteNumbersWithFixedStop(sc.next().split("-")));
                    break;
                case 4 :
                    System.out.println(trainStations.calculateShortestRouteLength(sc.next().split("-")));
                    break;
                case 5 :
                    System.out.println(trainStations.calculateRouteNumbersWithMaximumDistance(sc.next().split("-")));
                    break;
                case 6 :
                    System.out.println(trainStations.calculateTripDuration(sc.next().split("-")));
                    break;
                case 7 :
                    System.out.println(trainStations.calculateRouteNumbersWithMaximumDuration(sc.next().split("-")));
                    break;
                case 8 :
                    System.out.println(trainStations.calculateRouteNumbersWithFixedDurations(sc.next().split("-")));
                    break;
                case 9 :
                    System.out.println(trainStations.calculateTheShortestDurationRoute(sc.next().split("-")));
                    break;
                case 10 :
                    System.out.println(trainStations.calculateRouteNumbersWithMaximumDurationLimit(sc.next().split("-")));
                    break;
                default : throw new RuntimeException();
            }
        }
    }
}