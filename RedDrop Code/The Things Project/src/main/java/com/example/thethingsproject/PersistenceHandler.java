package com.example.thethingsproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PersistenceHandler {

    private List<Donor> donors;
    private List<Reaction> reactions;

    public PersistenceHandler() {
        donors = new ArrayList<Donor>();
        reactions = new ArrayList<Reaction>();

        try {
            retrieveDonors();
            retrieveReactions();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        displayDonors();
        displayReactions();



//        //Dummy data
//        Donor donor1 = new Donor("pepsi", "cocacola", "B-");
//        donors.add(donor1);
//
//        Donor donor2 = new Donor("cocacola", "pepsi", "O+");
//        donors.add(donor2);
//
//        //Dummy data
//        Reaction reaction1 = new Reaction("pepsi", "Nausea and vomiting", "12/12/2020");
//        reactions.add(reaction1);

    }

    public void retrieveDonors() throws FileNotFoundException {
        File file = new File("C:\\Users\\DELL\\Downloads\\The Things Project\\src\\main\\java\\com\\example\\thethingsproject\\donors.txt");
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] donorInfo = line.split(",");
            Donor donor = new Donor(donorInfo[0], donorInfo[1], donorInfo[2]);
            donors.add(donor);
        }

        System.out.println("Donors retrieved");
        System.out.println(donors);

    }

    public void retrieveReactions() throws FileNotFoundException {
        File file = new File("C:\\Users\\DELL\\Downloads\\The Things Project\\src\\main\\java\\com\\example\\thethingsproject\\reactions.txt");
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] reactionInfo = line.split(",");
            Reaction reaction = new Reaction(reactionInfo[0], reactionInfo[1], reactionInfo[2]);
            reactions.add(reaction);
        }

        System.out.println("Reactions retrieved");
        System.out.println(reactions);

    }

    public Donor getDonor(String username) {
        for (Donor donor : donors) {
            if (donor.getUsername().equals(username)) {
                return donor;
            }
        }
        return null;
    }

    public Reaction checkForReaction(String username) {
        for (Reaction reaction : reactions) {
            if (reaction.getUsername().equals(username)) {
                return reaction;
            }
        }
        return null;
    }

    public void addAppointment(String username, String location, String date) throws IOException {
        File file = new File("C:\\Users\\DELL\\Downloads\\The Things Project\\src\\main\\java\\com\\example\\thethingsproject\\appointments.txt");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(username + "," + location + "," + date);
        pw.close();
    }

    public void displayDonors() {
        for (Donor donor : donors) {
            System.out.println(donor.getUsername());
            System.out.println(donor.getPassword());
            System.out.println(donor.getBloodGroup());
        }
    }


    public void displayReactions() {
        for (Reaction reaction : reactions) {
            System.out.println(reaction.getUsername());
            System.out.println(reaction.getReaction());
            System.out.println(reaction.getDate());
        }
    }
}
