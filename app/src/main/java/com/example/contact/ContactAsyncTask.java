package com.example.contact;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Collections;


public class ContactAsyncTask extends AsyncTask<String, String, ArrayList<UserModel>> {

        private ProgressDialog pDialog;
        private Context mContext;
        private final String URL_PHP = "http://139.162.152.157/contactlist.php";
        private ContactListAdapter mAdapter;

        public ContactAsyncTask(Context context,ContactListAdapter adapter){
            mContext = context;
            mAdapter = adapter;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(mContext);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();

        }

        @Override
        protected ArrayList<UserModel> doInBackground(String... args) {
            JsonParser jParser = new JsonParser();

            // Getting User Details from URL
            ArrayList<UserModel> userList = jParser.getJSONFromUrl(URL_PHP);

            Collections.sort(userList);

            return userList;
        }


        @Override
        protected void onPostExecute(ArrayList<UserModel> list) {
            pDialog.dismiss();


            //if particulat ID is deleted by user and again received from server then we are removing that item in list
            try {

                for(int i=0; i<list.size(); i++){

                    if(AppPreferences.getBoolean(list.get(i).getId(),mContext)==true){
                        list.remove(i);
                    }

                }
            }
            catch(Exception e){
                e.printStackTrace();
            }


            if(mContext!=null){
                mAdapter.updateList(list);
            }

        }
}