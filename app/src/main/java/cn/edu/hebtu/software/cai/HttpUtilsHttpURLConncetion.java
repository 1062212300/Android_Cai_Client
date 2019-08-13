package cn.edu.hebtu.software.cai;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class HttpUtilsHttpURLConncetion {
    public static String BASE_URL = "http://10.7.89.236:8080/";
    public static String getContextByHttp(String urlStr, Map<String, String> parms) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(true);

            OutputStream outputStream = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(getStringFromOutput(parms));
            writer.flush();
            writer.close();
            outputStream.close();
            if(connection.getResponseCode()== HttpsURLConnection.HTTP_OK){
                BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String temp;
                while((temp=reader.readLine())!=null){
                    sb.append(temp);
                }
                reader.close();
            }else {
                return "connection error"+connection.getResponseCode();
            }
            connection.disconnect();
        } catch(Exception e){
            e.printStackTrace();
        }
        return sb.toString();
    }
    private static String getStringFromOutput(Map<String,String> map) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(isFirst)
                isFirst = false;
            else
                sb.append("&");
            sb.append(URLEncoder.encode(entry.getKey(),"UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode(entry.getValue(),"UTF-8"));
        }
        return sb.toString();
    }
}
