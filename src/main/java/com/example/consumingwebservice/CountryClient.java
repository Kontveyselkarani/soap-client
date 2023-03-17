package com.example.consumingwebservice;

//import com.example.consumingwebservice.wsdl.StudentDetailsRequest;
//import com.example.consumingwebservice.wsdl.StudentDetailsResponse;
import com.example.wsdl.StudentDetailsRequest;
import com.example.wsdl.StudentDetailsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
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

    public ResponseEntity<StudentDetailsResponse> getCountryV2(String country) {
        RestTemplate restTemplate = new RestTemplate();
        StudentDetailsRequest request = new StudentDetailsRequest();
        request.setName(country);

        HttpEntity<StudentDetailsRequest> entity = new HttpEntity<>(new StudentDetailsRequest());
        entity.getBody().setName("deneme");

        log.info("Requesting location for " + country);

        ResponseEntity<StudentDetailsResponse> response =restTemplate.exchange("http://localhost:8080/ws/countries", HttpMethod.POST ,entity, StudentDetailsResponse.class);

//        StudentDetailsResponse response = (StudentDetailsResponse) getWebServiceTemplate()
//                .marshalSendAndReceive("http://localhost:8080/ws/countries", request,
//                        new SoapActionCallback(
//                                "http://spring.io/guides/gs-producing-web-service/GetCountryRequest"));

        return response;
    }


//    public ResponseEntity<CelsiusToFahrenheitResponse> getFahrenheit(String country) {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders header = new HttpHeaders();
//        header.setContentType(MediaType.TEXT_XML);
//        HttpEntity<CelsiusToFahrenheit> entity = new HttpEntity<>(new CelsiusToFahrenheit(),header);
//        entity.getBody().setCelsius(country);
//        ResponseEntity<CelsiusToFahrenheitResponse> response = restTemplate.exchange("https://www.w3schools.com/xml/tempconvert.asmx", HttpMethod.POST, entity, CelsiusToFahrenheitResponse.class);
//
//
//        return response;
//    }


}