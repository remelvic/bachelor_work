package org.example;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class EmailSendViaAPI {
    

    public static JsonNode sendSimpleMessage() throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + DOMAIN_NAME + "/messages")
			.basicAuth("api", API_KEY.API_KEY)
                .queryString("from", "Excited User victorMailgunTest@test.com")
                .queryString("to", "remelvic@fel.cvut.cz")
                .queryString("subject", "GMAIL_TEST_SEND_19.11")
                .queryString("text", "Hi! How are u?")
                .asJson();
        return request.getBody();
    }

}
