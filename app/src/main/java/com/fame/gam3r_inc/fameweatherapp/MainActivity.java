package com.fame.gam3r_inc.fameweatherapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity implements AsyncResponse{

    EditText cityname;
    String city;
    JSONArray jsonArray;
    String main,desc,min,max,temp;
    TextView cityTV,mainTV,descTV,minTV,maxTV,tempTV;
    Button tryAgainbtn;
    JSONObject JO,jsonArray2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityname=findViewById(R.id.editText);
        cityTV=findViewById(R.id.ciryTV);
        mainTV=findViewById(R.id.mainTV);
        descTV=findViewById(R.id.descTV);
        minTV=findViewById(R.id.minTV);
        maxTV=findViewById(R.id.maxTV);
        tempTV=findViewById(R.id.tempTV);

        tryAgainbtn=findViewById(R.id.button2);
    }



    public void weather(View v){
        city=cityname.getText().toString();

        BackgroundTask backgroundTask=new BackgroundTask(this);
        backgroundTask.execute(city);




    }

    @Override
    public void processFinish(String output) {

        try {

            JSONObject reader = new JSONObject(output);
            String cod = reader.getString("cod");

            if(cod.equals("200")) {
                jsonArray = reader.getJSONArray("weather");
                JO = jsonArray.getJSONObject(0);
                main = JO.getString("main");
                desc = JO.getString("description");
                jsonArray2 = reader.getJSONObject("main");
                min=jsonArray2.getString("temp_min");
                max=jsonArray2.getString("temp_max");
                temp=jsonArray2.getString("temp");
                double dmin = Double.parseDouble(min);
                double dmax = Double.parseDouble(max);
                double dtemp = Double.parseDouble(temp);
                Integer itemp=(int)dtemp-273;
                Integer tempmin=(int)dmin-273;
                Integer tempmax=(int)dmax-273;
                temp=itemp.toString();
                min=tempmin.toString();
                max=tempmax.toString();
                cityTV.setText("City : " +city);
                mainTV.setText("main : " +main);
                descTV.setText("Description : " +desc);
                tempTV.setText("Temp : " +temp);
                minTV.setText("Temp_min : " +min);
                maxTV.setText("Temp_max : " +max);
                tryAgainbtn.setVisibility(View.VISIBLE);
            }
            else{
                Toast.makeText(this,"Sorry City name is not found" , Toast.LENGTH_LONG).show();
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void tryAgain(View V){
        cityTV.setText("City :");
        mainTV.setText("main :");
        descTV.setText("Description :");
        tempTV.setText("Temp :");
        minTV.setText("Temp_min :");
        maxTV.setText("Temp_max :");
        cityname.setText("");
        tryAgainbtn.setVisibility(View.INVISIBLE);
    }
}
