package com.restassuredtests.util;

import com.aventstack.extentreports.Status;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

public class RequestResource {
	
	private static Response response = null;
	public static ConfigPropertyReader configPropertyReader = new ConfigPropertyReader();
	public static String baseURL = configPropertyReader.getValue("baseURL");

	//Method to retrieve details of all people
	public static Response getDetailsOfAllPeople() {
		//Retrieve base URL in config.properties
		RestAssured.baseURI = baseURL;
		//Print the URL with the specific endpoint to the console
		System.out.println("GET - " + RestAssured.baseURI + "api/people");
		//Retrieve the response and return it to the test method in the APITests
		response = RestAssured.given().contentType("application/json").when().get("api/people");
		return response;
	}

	//Method to retrieve details of one person
	public static Response getDetailsOfOnePerson(String personId){
		//Retrieve base URL in config.properties
		RestAssured.baseURI = baseURL;
		//Print the URL with the specific endpoint to the console
		System.out.println("GET - " + RestAssured.baseURI + "api/people/" + personId);
		//Retrieve the response and return it to the test method in the APITests
		response = RestAssured.given().contentType("application/json").when().get("api/people/" + personId);
		return response;
	}

	//Method to create a person
	public static Response postCreateNewPerson(String firstName, String lastName){
		//Create json request body
		JSONObject createNewPersonRequestBody = createNewPersonRequestBody(firstName,lastName);
		System.out.println("Response body is : " + createNewPersonRequestBody.toString());
		//Retrieve base URL in config.properties
		RestAssured.baseURI = baseURL;
		//Print the URL with the specific endpoint to the console
		System.out.println("POST - " + RestAssured.baseURI + "api/people");
		//Retrieve the response and return it to the test method in the APITests
		response = RestAssured.given().contentType("application/json").body(createNewPersonRequestBody.toString()).when().post("api/people");
		return response;
	}

	//Method to update details of an existing person
	public static Response putUpdateExistingPerson(String personId,String firstName, String lastName){
		//Create json request body
		JSONObject createNewPersonRequestBody = createNewPersonRequestBody(firstName,lastName);
		System.out.println("Response body is : " + createNewPersonRequestBody.toString());
		//Retrieve base URL in config.properties
		RestAssured.baseURI = baseURL;
		//Print the URL with the specific endpoint to the console
		System.out.println("PUT - " + RestAssured.baseURI + "api/people/" + personId);
		//Retrieve the response and return it to the test method in the APITests
		response = RestAssured.given().contentType("application/json").body(createNewPersonRequestBody.toString()).when().put("api/people/" + personId);
		return response;
	}

	//Method to delete an existing person
	public static Response deleteExistingPerson(String personId){

		//Retrieve base URL in config.properties
		RestAssured.baseURI = baseURL;
		//Print the URL with the specific endpoint to the console
		System.out.println("DELETE - " + RestAssured.baseURI + "api/people/" + personId);
		//Retrieve the response and return it to the test method in the APITests
		response = RestAssured.given().contentType("text/html; charset=utf-8").when().delete("api/people/" + personId);
		return response;
	}

	//Method to create json body
	public static JSONObject createNewPersonRequestBody(String firstName, String lastName){

		JSONObject newPersonJSONRequestBody = new JSONObject();
		newPersonJSONRequestBody.put("fname",firstName);
		newPersonJSONRequestBody.put("lname",lastName);
		return newPersonJSONRequestBody;
	}
}
