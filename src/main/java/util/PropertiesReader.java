package util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	public static String getProperties(String fileName, String name) {

		String value = null;
		Properties properties = new Properties();
		PropertiesReader reader = new PropertiesReader();
		try(InputStream input = reader.getClass().getClassLoader().getResourceAsStream(fileName)){
			properties.load(input);
			value = properties.getProperty(name);
		} catch (IOException e) {
			System.out.println("Can`t read properties file");
		}
		return value;
	}
}
