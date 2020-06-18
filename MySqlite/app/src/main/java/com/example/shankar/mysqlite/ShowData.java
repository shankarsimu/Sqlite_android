package com.example.shankar.mysqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ShowData extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showdata_list);
         Bundle extra = getIntent().getExtras();
        if(extra != null){
            try {
                JSONArray jArr = new JSONArray(extra.getString("extra"));
                loadData(jArr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    }

    private void loadData(JSONArray jArry){
        HashMap<String, String> loanmapTemp = null;
        JSONObject jOBjPlt = null;
        String[] from = new String[]{"user_nm", "email_id", "gender", "password"};
        int[] to = new int[]{R.id.userName, R.id.emailId, R.id.gender, R.id.password};
        ArrayList<HashMap<String, String>> listLoanMap = new ArrayList<HashMap<String, String>>();
        for(int i = 0;i<jArry.length();i++) {
            try {
                jOBjPlt = new JSONObject(jArry.getString(i));
                loanmapTemp = jObj2Map(jOBjPlt);
                listLoanMap.add(loanmapTemp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        SimpleAdapter lstadpLT = new SimpleAdapter(this, listLoanMap,
                R.layout.showdata, from, to);
        ListView listdata = (ListView) findViewById(R.id.lst_data);
        listdata.setAdapter(lstadpLT);
        listdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long arg3) { } });
    }
    public static HashMap<String, String> jObj2Map(JSONObject object)
            throws JSONException {
        HashMap<String, String> map = new HashMap<String, String>();
        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            String value = object.getString(key);
            map.put(key, value);
        }
        return map;
    }
}
