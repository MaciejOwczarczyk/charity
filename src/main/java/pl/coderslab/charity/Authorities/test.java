package pl.coderslab.charity.Authorities;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

public class test {
    public static void main(String[] args) throws Exception {

        System.out.println(getCountries("po", 1000));
    }

    public static int getCountries(String s, int p) throws Exception {
        String url = "https://jsonmock.hackerrank.com/api/countries/search?name=" + s;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        int counter = 0;

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();


            JSONObject jsonObject = new JSONObject(response.toString());

            JSONArray jsonArray = (JSONArray) jsonObject.get("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                String temp = jsonArray.getJSONObject(i).getString("population");
                int tempLong = Integer.parseInt(temp);
                if (tempLong > p) {
                    counter = counter + 1;
                }
            }
        } else {
            System.out.print("GET request not worked");
        }

        return counter;
    }

}
