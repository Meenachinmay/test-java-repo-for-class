package org.polarmeet.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstApiApplication.class, args);
	}

}

// They are basically connection type
// Http -> not a secure communication
// when you send data over the http connection - data is not encrypted automatically
// we never use this for production and where we need to send and receive data online

// Https -> secure communication
// Just opposite of Http connection.
// we always use this for secure data communication over the network.

// you never see payment website with http, it has to be on https.

// http -> local development is always on http.



// Creating a Hello world REST API.
// you can make some network calls to, create, update, read and delete your data over the network.
// Http calls --> network calls to a server from a client

// A Http call is a two way communication
// client send a request and get the response
// server receive the request and send back the response to the client

// What is the REST API endpoint?
// an endpoint is a URL where you API can be accessed by a client application.
// https://linkedin.com/profile/chinmay123 --> you are trying this url (route) to access some data
// https://linkedin.com/create/profile --> you are trying to create a profile over this url (route) to access some data

// client makes a Http request using few methods.
// Methods are -
// Http GET, POST, DELETE, PUT
// GET -> when you just want to read the data from the api (you can make this http call directly from the
// browser)
// POST -> when you want to save some data
// DELETE -> when you want to delete some data
// PUT -> when you want to update some data

// JSON response --> Java script object notation
// way to structure a data in a human readable form and send it over the network.
// This is one of the most used data structure to share over the network.