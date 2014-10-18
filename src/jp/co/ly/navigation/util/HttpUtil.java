package jp.co.ly.navigation.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    public static String getDistinations(String id) {
        
        String result = null;
        
        try {
            final URL url = new URL("URL" + id);
            HttpURLConnection connection  = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            
            byte[] buffer = new byte[1024];
            int size = 0;
            StringBuffer stringBuffer = new StringBuffer();
            while(true) {
                size = inputStream.read(buffer);
                if (size <= 0) {
                    break;
                }   
                stringBuffer.append(new String(buffer));
            }
            
            result = stringBuffer.toString();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
