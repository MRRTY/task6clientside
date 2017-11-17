package web;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestManager {
    private static final RequestManager requestManager = new RequestManager();
    private String currentDatabaseName = null;
    private String currentTableName = null;

    private final String USER_AGENT = "Mozilla/5.0";

    public void executeRequest(Request request) throws Exception {
        String[] commandLine = (request.getPath()+" "+request.getParams()).split(" ");
        switch (request.getType()){
            case GET:
                sendGet(request.getDomen(),commandLine);
                break;
            case PUT:
                sendPut(request.getDomen(),commandLine);
                break;
            case POST:
                sendPost(request.getDomen(),commandLine);
                break;
            case DELETE:
                sendDelete(request.getDomen(),commandLine);
                break;
            default: throw new RuntimeException();
        }
    }
    private String sendGet(String domen, String[] commandLine) throws Exception {
        String path = commandLine[0];
        StringBuilder params = new StringBuilder("?");
        for(int i = 1; i<commandLine.length;i++){
            params.append(commandLine[i]);
            if(i!=commandLine.length-1){
                params.append("&");
            }
        }
        String url = domen+path+params.toString();

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        return  response.toString();
    }

    private void sendPost(String domen, String[] commandLine) throws Exception {
        String path = commandLine[0];
        StringBuilder params = new StringBuilder();
        for(int i = 1; i<commandLine.length;i++){
            params.append(commandLine[i]);
            if(i!=commandLine.length-1){
                params.append("&");
            }
        }
        String url = domen+path;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = params.toString();

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    private void sendDelete(String domen, String[] commandLine) throws IOException {
        String path = commandLine[0];
        StringBuilder params = new StringBuilder("?");
        for(int i = 1; i<commandLine.length;i++){
            params.append(commandLine[i]);
            if(i!=commandLine.length-1){
                params.append("&");
            }
        }
        String url = domen+path+params.toString();
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setDoInput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setUseCaches (false);
        System.out.println("\nSending 'DELETE' request to URL : " + url);
        System.out.println("Response Code : " + connection.getResponseCode());

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line, responseText = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            responseText += line;
        }
        br.close();
        connection.disconnect();
    }

    private void sendPut(String domen, String[] commandLine) throws IOException {
        String path = commandLine[0];
        StringBuilder params = new StringBuilder("?");
        for(int i = 1; i<commandLine.length;i++){
            params.append(commandLine[i]);
            if(i!=commandLine.length-1){
                params.append("&");
            }
        }
        String url = domen+path+params.toString();
        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setDoInput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setUseCaches (false);
        System.out.println("\nSending 'PUT' request to URL : " + url);
        System.out.println("Response Code : " + connection.getResponseCode());

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line, responseText = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            responseText += line;
        }
        br.close();
        connection.disconnect();
    }


    public static RequestManager getInstance(){
        return requestManager;
    }
}
