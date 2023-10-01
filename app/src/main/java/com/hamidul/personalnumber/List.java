package com.hamidul.personalnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SharedMemory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class List extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    ListView listView;
    ArrayList <HashMap<String,String>> arrayList = new ArrayList<>();
    HashMap <String,String> hashMap;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.WHITE);
        setContentView(R.layout.list);

        listView = findViewById(R.id.listView);

        Data();

        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

        sharedPreferences = getSharedPreferences(getString(R.string.app_name),MODE_PRIVATE);

        String name = sharedPreferences.getString("name","Default");

        Toast.makeText(this, "Welcome "+name, Toast.LENGTH_SHORT).show();

    }//onCrate======================================================================================

    public class MyAdapter extends BaseAdapter {
        LayoutInflater layoutInflater;
        LinearLayout layItem;
        TextView tvName,tvMobile,tvPosition;
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            int x = position+1;

            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = layoutInflater.inflate(R.layout.item,parent,false);

            layItem = myView.findViewById(R.id.layItem);
            tvName = myView.findViewById(R.id.tvName);
            tvMobile = myView.findViewById(R.id.tvMobile);
            tvPosition = myView.findViewById(R.id.tvPosition);

            HashMap <String,String> hashMap1 = arrayList.get(position);

            String sName = hashMap1.get("name");
            String sMobile = hashMap1.get("mobile");

            tvName.setText(sName);
            tvMobile.setText(sMobile);
            tvPosition.setText(""+x);

            layItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toastMessage("Hello "+sName);
                }
            });

            return myView;
        }
    }

    private void Data () {

        hashMap = new HashMap<>();
        hashMap.put("name","S.M.Hamidul");
        hashMap.put("mobile","01611683142");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name","S.M.Ujjal");
        hashMap.put("mobile","01745623598");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name","Mahadi Hasan");
        hashMap.put("mobile","01784875091");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name","Ajad Shikder");
        hashMap.put("mobile","01758586208");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name","S.M.Hamidul");
        hashMap.put("mobile","01611683142");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name","S.M.Hamidul");
        hashMap.put("mobile","01611683142");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name","S.M.Hamidul");
        hashMap.put("mobile","01611683142");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name","S.M.Hamidul");
        hashMap.put("mobile","01611683142");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name","S.M.Hamidul");
        hashMap.put("mobile","01611683142");
        arrayList.add(hashMap);

    }

    public void toastMessage (String message){
        if (toast!=null) toast.cancel();
        toast = Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}//AppCompatActivity================================================================================