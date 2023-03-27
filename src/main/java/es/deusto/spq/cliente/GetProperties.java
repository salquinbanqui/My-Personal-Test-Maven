package es.deusto.spq.cliente;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetProperties {

	String result = "";
	InputStream inputStream;
	
	public String getUrl() throws IOException {
		try {
			Properties p = new Properties();
			String pFilename ="config.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(pFilename);
		
			if (inputStream != null) {
				p.load(inputStream);
			} else {
				throw new FileNotFoundException(" fichero property" + "'" + pFilename + "'" + " no encontrado en el classpath");
			}
			String url = p.getProperty("url");
			result = url;
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}finally {
			inputStream.close();
		}
		
		return result;
		
	}

}
