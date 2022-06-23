package connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.mongodb.ConnectionString;

public class MyConnectionMongo {
	
private static final String PROPERTIES_FILE = "nosql.properties";
	
	public static ConnectionString uri() {
		FileInputStream fis = null;
		
		try {
			Properties props = new Properties();
			fis = new FileInputStream(PROPERTIES_FILE);
			props.load(fis);	
			String user = props.getProperty("user");
			String password = props.getProperty("password");
			String cluster = props.getProperty("cluster_name");
			String server_address = props.getProperty("server_address");
			String param1 = props.getProperty("param1");
			String param2 = props.getProperty("param2");
			
			String uri = "mongodb+srv://%s:%s@%s.%s/?%s&%s".formatted(user,password,cluster,server_address,
					param1,param2);
			return new ConnectionString(uri);
			
		}catch (FileNotFoundException e) {
			
		}catch (IOException e) {
			
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return null;
	}

}
