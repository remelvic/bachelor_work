package org.example;

import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {
    public static void main(String[] args) {

        try{
            System.out.println(EmailSendViaAPI.sendSimpleMessage());
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}