package com.restassuredtests.testDataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class DBValidation {
	

	public static HashMap<String, String> getTableDataUsingHashMap(Connection dbConnection, String keyId) throws SQLException{
		
		HashMap<String, String> hmap = new HashMap<String, String>();
		String queryString = "SELECT * FROM Test.TEST_TABLE WHERE Id = 'id'";
		queryString = queryString.replaceFirst("id",keyId);
		Statement statement = dbConnection.createStatement();
		ResultSet rsTableData = statement.executeQuery(queryString);
		while(rsTableData.next()) {
			hmap.put("TEST_FIELD", rsTableData.getString("TEST_FIELD"));
		}
		rsTableData.close();
		statement.close();
		return hmap;
	}
	
	public static ArrayList<String> getTableDataUsingArrayList(Connection dbConnection, String keyId) {
		
		String queryString = null;
		Statement statement = null;
		ResultSet rsTableData = null;
		String TEST_FIELD = null;
		ArrayList<String> ar = new ArrayList<String>();
		
		try {
			queryString = "SELECT * FROM Test.TEST_TABLE WHERE Id = 'id'";
			queryString = queryString.replaceFirst("id",keyId);
			statement = dbConnection.createStatement();
			rsTableData = statement.executeQuery(queryString);
			while(rsTableData.next()) {
				TEST_FIELD = rsTableData.getString(1);
				ar.add(TEST_FIELD);
			}
		}catch(SQLException e) {
			e.getMessage();
		}
		return ar;
	}
	
	public static boolean testMethod() {
		
		int timeOutMaxLimit = 0;
		int waitTime = 5000;
		int waitTimeOutPeriod = 300000;
		int timeout = waitTimeOutPeriod/waitTime;
		ArrayList<String> getTableDataUsingArrayList = null;		
			
		try {
			getTableDataUsingArrayList = getTableDataUsingArrayList(null, null);
			timeOutMaxLimit = 0;
			while(getTableDataUsingArrayList == null && timeOutMaxLimit++ < timeout) {
				getTableDataUsingArrayList = getTableDataUsingArrayList(null, null);
				Thread.sleep(waitTime);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
}


