package com.restassuredtests.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigPropertyReader {
	
	//Retrieve the values from the config.properties file
	private Properties properties;
	private final String configPropertyFilePath = "src//test//resources//config//config.properties";
	
	//Reading the config.properties file
	public ConfigPropertyReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(configPropertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("config.properties file not found at " + configPropertyFilePath);
		}
	}
	
	//Retrieving the name of the data sheet in the csv file
	public String getValue(String key) {
		String value = properties.getProperty(key);
		if(value != null) {
			return value;
		}
		else {
			System.out.println("Value not available in the config.properties file");
			return null;
		}
	}
}
