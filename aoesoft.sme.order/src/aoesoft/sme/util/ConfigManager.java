package aoesoft.sme.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class ConfigManager {
	private static Properties config;
	
	private ConfigManager(){
	}
	
	public static String getValue(String key){
		if(config == null){
			config = new Properties();
			InputStream is = null;
			try {
				is = new FileInputStream("etc/config.properties");
				config.load(new InputStreamReader(is));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} finally{
				if(is != null){
					try {
						is.close();
					} catch (IOException e) {
						is = null;
					}
				}
			}
		}
		
		return config.getProperty(key);
	}
}
