package com.example.contact;


import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class JsonParser {

    //JSON Node Names
    private static final String TAG_USER = "result";
    private static final String TAG_ID = "uid";
    private static final String TAG_NAME = "name";



    public ArrayList<UserModel> getJSONFromUrl(String url) {
        InputStream inputStream = null;
        String result = "";
        HttpURLConnection conn = null;

        try {
            URL newurl = new URL(url);
            conn = (HttpURLConnection) newurl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("token","c149c4fac72d3a3678eefab5b0d3a85a");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            inputStream = new BufferedInputStream(conn.getInputStream());

            // convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            conn.disconnect();

        }

        String json = result.toString();

        Log.e("JSON Parser Data", "Json parsing data " + json);

        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(json);
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return getUserDetails(jsonObject);

    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;
    }


    JSONArray user;

    public ArrayList<UserModel> getUserDetails(JSONObject jsonObject){

        ArrayList<UserModel>  userList = new ArrayList<UserModel>();
        try{

            user = jsonObject.getJSONArray(TAG_USER);
            JSONObject c = user.getJSONObject(0);

            for (int i = 0; i < user.length(); i++) {
                JSONObject jo = user.getJSONObject(i);

                UserModel userModel = new UserModel();
                userModel.setName(jo.getString(TAG_NAME));
                userModel.setId(jo.getString(TAG_ID));
                userList.add(userModel);


            }
        }
        catch (JSONException e){

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return userList;
    }

}