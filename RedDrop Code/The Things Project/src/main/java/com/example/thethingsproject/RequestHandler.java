package com.example.thethingsproject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class RequestHandler {
    private List<Request> requests;


    public RequestHandler() {
        requests = new ArrayList<Request>();

        try {
            retrieveRequests();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

//        //Dummy data
//        Request request1 = new Request("Rawalpindi", "Meezan Hospital, Street 3, Mudassir Avenue", "O+", true);
//        requests.add(request1);
//
//        Request request2 = new Request("Rawalpindi", "Pepsi Hospital, Street 6, Cola Avenue", "B-", true);
//        requests.add(request2);
//
//        Request request3 = new Request("Rawalpindi", "Coca Cola Hospital, Street 9, Pepsi Avenue", "O+", false);
//        requests.add(request3);
    }

    public void retrieveRequests() throws FileNotFoundException {
        File file = new File("C:\\Users\\DELL\\Downloads\\The Things Project\\src\\main\\java\\com\\example\\thethingsproject\\requests.txt");
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine())
        {
            String line = sc.nextLine();
            String[] requestInfo = line.split(",");
            Request request = new Request(requestInfo[0], requestInfo[1], requestInfo[2], Boolean.parseBoolean(requestInfo[3]));
            requests.add(request);
        }
    }

    public List<String> returnRequestsByCity(String city) {
        List<String> requestsByCity = new ArrayList<String>();
        for (Request request : requests) {
            if (request.getCity().equals(city)) {
                requestsByCity.add(request.getLocation());
            }
        }
        return requestsByCity;
    }

    public Request returnRequestByLocation(String location) {
        for (Request request : requests) {
            if (request.getLocation().equals(location)) {
                return request;
            }
        }
        return null;
    }

    public List<String> returnRequestsByAppointment()
    {
        List<String> requestsByAppointment = new ArrayList<String>();
        for (Request request : requests) {
            if (!request.getEmergency()) {
                requestsByAppointment.add(request.getLocation());
            }
        }
        return requestsByAppointment;
    }


}
