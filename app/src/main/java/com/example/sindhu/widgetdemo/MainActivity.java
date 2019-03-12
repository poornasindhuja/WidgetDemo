package com.example.sindhu.widgetdemo;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=findViewById(R.id.list_view);
        String[] s=getResources().getStringArray(R.array.names_array);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,s);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, ""+parent.getItemAtPosition(position),
                        Toast.LENGTH_SHORT).show();
                preferences=getSharedPreferences(getPackageName(),MODE_PRIVATE);
                editor=preferences.edit();

                StringBuffer buffer=new StringBuffer();
                buffer.append(parent.getItemAtPosition(position));
                editor.putString("fruit",buffer.toString());
                Intent i=new Intent(MainActivity.this,SampleWidget.class);
                i.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                editor.apply();
                int[] ids= AppWidgetManager.getInstance(MainActivity.this)
                        .getAppWidgetIds(new ComponentName(getApplication(),SampleWidget.class));
                /*TextView tv=findViewById(R.id.wid_text);
                tv.setText(parent.getItemAtPosition(position).toString());
           */
                i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
                sendBroadcast(i);
            }
        });
    }
}
