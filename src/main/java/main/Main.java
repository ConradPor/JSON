package main;

import flights.ReadAndCalc;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        ReadAndCalc tj = new ReadAndCalc();
        tj.readFile();
        System.out.println("Choose what you need to find?");
        System.out.println(" '1' - Cargo weight for requested flight.");
        System.out.println(" '2' - Baggage weight for requested flight.");
        System.out.println(" '3' - Total weight for requested flight.");
        System.out.println(" '4' - Number of flights departing from requested airport.");
        System.out.println(" '5' - Number of flights arriving to requested airport.");
        System.out.println(" '6' - Total number (pieces) of baggage arriving to requested airport.");
        System.out.println(" '7' - Total number (pieces) of baggage departing from requested airport.");
        Scanner in = new Scanner(System.in);
        String dates = tj.listOfDate();
        int action = in.nextInt();
        if (action == 1) {
            System.out.println("Enter flight number in format 'NNNN'");
            Scanner fn = new Scanner(System.in);
            int numberOfFlight = fn.nextInt();
            System.out.println("Enter date of flight in format 'YYYY-MM-DD'");
            System.out.println("Accesible dates: "+dates);
            Scanner dn = new Scanner(System.in);
            String date = dn.next();
            int nf = tj.findId(numberOfFlight, date);
            System.out.println("Number of flight ID: " + nf);
            if (nf > -1) {
                int cwk = tj.findCargoWeightInkilograms(nf);
                int cwp = tj.findCargoWeightInPounds(nf);
                System.out.println("Cargo weight for requested flight is equal: " + cwk + " Kg " + cwp + " lb.");
            }
            if (nf == -1) {
                System.out.println(" Searching flight not exist.");

            }
        }

        if (action == 2) {
            System.out.println("Enter flight number in format 'NNNN'");
            Scanner fn = new Scanner(System.in);
            int numberOfFlight = fn.nextInt();
            System.out.println("Enter date of flight in format 'YYYY-MM-DD'");
            System.out.println("Accesible dates: "+dates);
            Scanner dn = new Scanner(System.in);
            String date = dn.next();
            int nf = tj.findId(numberOfFlight, date);
            if (nf > -1) {
                int cwk = tj.findBaggageWeightInkilograms(nf);
                int cwp = tj.findBaggageWeightInPounds(nf);
                System.out.println("Baggage weight for requested flight is equal: " + cwk + " Kg " + cwp + " lb.");
            }
            if (nf == -1) {
                System.out.println(" Searching flight not exist.");

            }
        }

        if (action == 3) {
            System.out.println("Enter flight number in format 'NNNN'");
            Scanner fn = new Scanner(System.in);
            int numberOfFlight = fn.nextInt();
            System.out.println("Enter date of flight in format 'YYYY-MM-DD'");
            System.out.println("Accesible dates: "+dates);
            Scanner dn = new Scanner(System.in);
            String date = dn.next();
            int nf = tj.findId(numberOfFlight, date);
            System.out.println("ID number of this flight is: " + nf);
            if (nf > -1) {
                int cwk = tj.findCargoWeightInkilograms(nf);
                int cwp = tj.findCargoWeightInPounds(nf);
                int bwk = tj.findBaggageWeightInkilograms(nf);
                int bwp = tj.findBaggageWeightInPounds(nf);
                int totk = cwk + bwk;
                int totp = cwp + bwp;
                System.out.println("Total weight for requested flight is equal: " + totk + " Kg " + totp + " lb.");
            }
            if (nf == -1) {
                System.out.println("Searching flight not exist!!!");

            }
        }

        if (action == 4) {
            System.out.println("Enter airport number in format 'NNN', example: 'GDN' ");
            Scanner fn = new Scanner(System.in);
            String iataCode = fn.next();
            System.out.println("Enter date of flight in format 'YYYY-MM-DD'");
            System.out.println("Accesible dates: "+dates);
            Scanner dn = new Scanner(System.in);
            String date = dn.next();
            int nf = tj.checkDep(iataCode, date);
            if (nf > -1) {
                System.out.println("Flights number departing from this airport is/are :" + tj.findDepFlightNo(iataCode, date));
            }
            if (nf == -1) {
                System.out.println("Searching flight not exist!!!");
            }
        }

        if (action == 5) {
            System.out.println("Enter airport number in format 'NNN', example: 'GDN' ");
            Scanner fn = new Scanner(System.in);
            String iataCode = fn.next();
            System.out.println("Enter date of flight in format 'YYYY-MM-DD'");
            System.out.println("Accesible dates: "+dates);
            Scanner dn = new Scanner(System.in);
            String date = dn.next();
            int nf = tj.checkArr(iataCode, date);
            if (nf > -1) {
                System.out.println("Flights number arriving from this airport " +
                        "is/are :" + tj.findArrFlightNo(iataCode, date));
            }
            if (nf == -1) {
                System.out.println("Searching flight not exist!!!");
            }
        }

        if (action == 6) {
            System.out.println("Enter arrival airport number in format 'NNN', example: 'GDN' ");
            Scanner fn = new Scanner(System.in);
            String iataCode = fn.next();
            System.out.println("Enter date of flight in format 'YYYY-MM-DD'");
            System.out.println("Accesible dates: "+dates);
            Scanner dn = new Scanner(System.in);
            String date = dn.next();
            int nf = tj.checkArr(iataCode, date);
            if (nf > -1) {
                System.out.println("Total number (pieces) of baggage arriving to this airport is: " +
                         tj.findArrivedBaggage(iataCode, date));
            }
            if (nf == -1) {
                System.out.println("No luggage was found!!!");
            }
        }

        if (action == 7) {
            System.out.println("Enter departure airport number in format 'NNN', example: 'GDN' ");
            Scanner fn = new Scanner(System.in);
            String iataCode = fn.next();
            System.out.println("Enter departure date of flight in format 'YYYY-MM-DD'");
            System.out.println("Accesible dates: "+dates);
            Scanner dn = new Scanner(System.in);
            String date = dn.next();
            int nf = tj.checkDep(iataCode, date);
            if (nf > -1) {
                System.out.println("Total number (pieces) of baggage departing from this airport: " +
                        "is :" + tj.findDepartedBaggage(iataCode, date));
            }
            if (nf == -1) {
                System.out.println("No luggage was found!!!");
            }
        }
    }
}
