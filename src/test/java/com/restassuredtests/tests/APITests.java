package com.restassuredtests.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.restassuredtests.util.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class APITests {

	private static Response response = null;
	private static int statusCode = 0;
	private static String contentType = null;
	private static String firstName = null;
	private static String lastName = null;
	private static String personId = null;
	private static String timeStamp = null;
		
	//builds a new report using the html template 
    private static ExtentHtmlReporter htmlReporter;
    
    //helps to generate the logs in test report.  	
    private static ExtentReports extentReports = null;
    private static ExtentTest test = null;   
    
    @BeforeClass
    public static void startTest() {
    	
    	// initialize the HtmlReporter
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"\\test-output\\API_Tests_Report.html");
        
        //initialize ExtentReports and attach the HtmlReporter
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }	
    
    @AfterTest
    public void tearDown() {
    	//to write or update test information to reporter
    	extentReports.flush();
    }
	
	@Test(priority=1)
	public static void verifyDetailsOfAllPeople() {
				
		test = extentReports.createTest("verifyDetailsOfAllPeople", "Verify details of all people");
		
		try {
			//Executing request to get details of all people
			response = RequestResource.getDetailsOfAllPeople();
			//Displaying response body
			System.out.println("Response body is : " + response.getBody().prettyPrint());			
			test.log(Status.INFO, "Response body is : " + response.getBody().prettyPrint());
			//Retrieving response code
			statusCode = response.getStatusCode();
			//Retrieving content type
			contentType = response.getContentType();
			//Retrieving first name
			firstName = response.jsonPath().getString("fname[1]");
			//Retrieving last name
			lastName = response.jsonPath().getString("lname[1]");
			//Retrieving person id
			personId = response.jsonPath().getString("person_id[1]");
			//Retrieving timestamp
			timeStamp = response.jsonPath().getString("timestamp[1]");
			//Verifying status code in response
			test.log(Status.INFO, "Verifying status code in response");
			Assert.assertTrue(statusCode == 200,
					"Request was not successful with response code as " + statusCode);
			System.out.println("Request is successful with response code as " + statusCode);
			test.log(Status.PASS, "Request is successful with response code as " + statusCode);
			//Verifying content type in response
			test.log(Status.INFO, "Verifying content type in response");
			Assert.assertTrue(contentType.equalsIgnoreCase("application/json"),
					"Request was not successful with content type as " + contentType);
			System.out.println("Request is successful with content type as " + contentType);
			test.log(Status.PASS, "Request is successful with content type as " + contentType);
			//Verifying details of user at index 1
			test.log(Status.INFO, "Verifying details of user and index 1");
			Assert.assertTrue(firstName.equalsIgnoreCase("Bunny"),
					"Request was not successful and the first name retrieved is " + firstName);
			System.out.println("Request is successful and the first name retrieved is " + firstName);
			test.log(Status.PASS, "Request is successful and the first name retrieved is " + firstName);
			Assert.assertTrue(lastName.equalsIgnoreCase("Easter"),
					"Request was not successful and the last name retrieved is " + lastName);
			System.out.println("Request is successful and the last name retrieved is " + lastName);
			test.log(Status.PASS, "Request is successful and the last name retrieved is " + lastName);
			Assert.assertTrue(personId.equalsIgnoreCase("3"),
					"Request was not successful and the person id retrieved is " + personId);
			System.out.println("Request is successful and the person id retrieved is " + personId);
			test.log(Status.PASS, "Request is successful and the person id retrieved is " + personId);
			Assert.assertTrue(timeStamp.equalsIgnoreCase("2021-04-27T02:30:06.099273"),
					"Request was not successful and the timestamp retrieved is " + timeStamp);
			System.out.println("Request is successful and the first timestamp retrieved is " + timeStamp);
			test.log(Status.PASS, "Request is successful and the timestamp retrieved is " + timeStamp);

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			RestAssured.reset();
		}
	}

	@Test(priority=2)
	public static void verifyDetailsOfOnePerson() {

		test = extentReports.createTest("verifyDetailsOfOnePerson", "Verify details of one person");

		try {
			//Hardcoded person id 2 to test the function
			personId = "2";
			//Executing request to get details of all people
			response = RequestResource.getDetailsOfOnePerson(personId);
			//Displaying response body
			System.out.println("Response body is : " + response.getBody().prettyPrint());
			test.log(Status.INFO, "Response body is : " + response.getBody().prettyPrint());
			//Retrieving response code
			statusCode = response.getStatusCode();
			//Retrieving content type
			contentType = response.getContentType();
			//Retrieving first name
			firstName = response.jsonPath().getString("fname");
			//Retrieving last name
			lastName = response.jsonPath().getString("lname");
			//Retrieving person id
			personId = response.jsonPath().getString("person_id");
			//Retrieving timestamp
			timeStamp = response.jsonPath().getString("timestamp");
			//Verifying status code in response
			test.log(Status.INFO, "Verifying status code in response");
			Assert.assertTrue(statusCode == 200,
					"Request was not successful with response code as " + statusCode);
			System.out.println("Request is successful with response code as " + statusCode);
			test.log(Status.PASS, "Request is successful with response code as " + statusCode);
			//Verifying content type in response
			test.log(Status.INFO, "Verifying content type in response");
			Assert.assertTrue(contentType.equalsIgnoreCase("application/json"),
					"Request was not successful with content type as " + contentType);
			System.out.println("Request is successful with content type as " + contentType);
			test.log(Status.PASS, "Request is successful with content type as " + contentType);
			//Verifying details of user at index 1
			test.log(Status.INFO, "Verifying details of user and index 1");
			Assert.assertTrue(firstName.equalsIgnoreCase("Kent"),
					"Request was not successful and the first name retrieved is " + firstName);
			System.out.println("Request is successful and the first name retrieved is " + firstName);
			test.log(Status.PASS, "Request is successful and the first name retrieved is " + firstName);
			Assert.assertTrue(lastName.equalsIgnoreCase("Brockman"),
					"Request was not successful and the last name retrieved is " + lastName);
			System.out.println("Request is successful and the last name retrieved is " + lastName);
			test.log(Status.PASS, "Request is successful and the last name retrieved is " + lastName);
			Assert.assertTrue(personId.equalsIgnoreCase("2"),
					"Request was not successful and the person id retrieved is " + personId);
			System.out.println("Request is successful and the person id retrieved is " + personId);
			test.log(Status.PASS, "Request is successful and the person id retrieved is " + personId);
			Assert.assertTrue(timeStamp.equalsIgnoreCase("2021-04-27T02:30:06.099273"),
					"Request was not successful and the timestamp retrieved is " + timeStamp);
			System.out.println("Request is successful and the first timestamp retrieved is " + timeStamp);
			test.log(Status.PASS, "Request is successful and the timestamp retrieved is " + timeStamp);

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			RestAssured.reset();
		}
	}

	@Test(priority=3)
	public static void verifyCreatingPerson() {

		test = extentReports.createTest("verifyCreatingPerson", "Verify creating new person");

		try {
			//Hard coding fistName and lastName values to verify the scenario
			firstName = "TestUser1firstName";
			lastName = "TestUser1lastName";
			//Executing request to create new person
			response = RequestResource.postCreateNewPerson(firstName,lastName);
			//Displaying response body
			System.out.println("Response body is : " + response.getBody().prettyPrint());
			test.log(Status.INFO, "Response body is : " + response.getBody().prettyPrint());
			//Retrieving response code
			statusCode = response.getStatusCode();
			//Retrieving content type
			contentType = response.getContentType();
			//Verifying status code in response
			test.log(Status.INFO, "Verifying status code in response");
			Assert.assertTrue(statusCode == 204,
					"Request was not successful with response code as " + statusCode);
			System.out.println("Request is successful with response code as " + statusCode);
			test.log(Status.PASS, "Request is successful with response code as " + statusCode);
			//Verifying content type in response
			test.log(Status.INFO, "Verifying content type in response");
			Assert.assertTrue(contentType.equalsIgnoreCase("application/json"),
					"Request was not successful with content type as " + contentType);
			System.out.println("Request is successful with content type as " + contentType);
			test.log(Status.PASS, "Request is successful with content type as " + contentType);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			RestAssured.reset();
		}
	}

	@Test(priority=4)
	public static void verifyUpdatingExistingPerson() {

		test = extentReports.createTest("verifyUpdatingExistingPerson", "Verify updating existing person");

		try {
			//Hard coding firstName, lastName and personId values to verify the scenario
			personId = "6";
			firstName = "TestUser2fistName";
			lastName = "TestUser2lastName";
			//Executing request to update existing person details
			response = RequestResource.putUpdateExistingPerson(personId,firstName,lastName);
			//Displaying response body
			System.out.println("Response body is : " + response.getBody().prettyPrint());
			test.log(Status.INFO, "Response body is : " + response.getBody().prettyPrint());
			//Retrieving response code
			statusCode = response.getStatusCode();
			//Retrieving content type
			contentType = response.getContentType();
			//Verifying status code in response
			test.log(Status.INFO, "Verifying status code in response");
			Assert.assertTrue(statusCode == 200,
					"Request was not successful with response code as " + statusCode);
			System.out.println("Request is successful with response code as " + statusCode);
			test.log(Status.PASS, "Request is successful with response code as " + statusCode);
			//Verifying content type in response
			test.log(Status.INFO, "Verifying content type in response");
			Assert.assertTrue(contentType.equalsIgnoreCase("application/json"),
					"Request was not successful with content type as " + contentType);
			System.out.println("Request is successful with content type as " + contentType);
			test.log(Status.PASS, "Request is successful with content type as " + contentType);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			RestAssured.reset();
		}
	}

	@Test(priority=5)
	public static void verifyDeletingExistingPerson() {

		test = extentReports.createTest("verifyDeletingExistingPerson", "Verify deleting existing person");

		try {
			//Hard coding personId value to verify the scenario
			personId = "6";
			//Executing request to update existing person details
			response = RequestResource.deleteExistingPerson(personId);
			//Displaying response body
			System.out.println("Response body is : " + response.getBody().prettyPrint());
			test.log(Status.INFO, "Response body is : " + response.getBody().prettyPrint());
			//Retrieving response code
			statusCode = response.getStatusCode();
			//Retrieving content type
			contentType = response.getContentType();
			//Verifying status code in response
			test.log(Status.INFO, "Verifying status code in response");
			Assert.assertTrue(statusCode == 200,
					"Request was not successful with response code as " + statusCode);
			System.out.println("Request is successful with response code as " + statusCode);
			test.log(Status.PASS, "Request is successful with response code as " + statusCode);
			//Verifying content type in response
			test.log(Status.INFO, "Verifying content type in response");
			Assert.assertTrue(contentType.equalsIgnoreCase("text/html; charset=utf-8"),
					"Request was not successful with content type as " + contentType);
			System.out.println("Request is successful with content type as " + contentType);
			test.log(Status.PASS, "Request is successful with content type as " + contentType);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			RestAssured.reset();
		}
	}
	
}
