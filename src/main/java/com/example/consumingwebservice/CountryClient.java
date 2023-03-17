package com.example.consumingwebservice;

//import com.example.consumingwebservice.wsdl.StudentDetailsRequest;
//import com.example.consumingwebservice.wsdl.StudentDetailsResponse;
import com.example.wsdl.StudentDetailsRequest;
import com.example.wsdl.StudentDetailsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;



public class CountryClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

    public StudentDetailsResponse getCountry(String country) {

        StudentDetailsRequest request = new StudentDetailsRequest();
        request.setName(country);

        log.info("Requesting location for " + country);

        StudentDetailsResponse response = (StudentDetailsResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/countries", request,
                        new SoapActionCallback(
                                "http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));

        return response;
    }

}