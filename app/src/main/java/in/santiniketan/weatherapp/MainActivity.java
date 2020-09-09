package in.santiniketan.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import in.santiniketan.weatherapp.Retrofit.Apiclient;
import in.santiniketan.weatherapp.Retrofit.Apiinterface;
import in.santiniketan.weatherapp.Retrofit.Example;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView search;
    TextView  temptext, desctext, humiditytext;
    EditText textfield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search = findViewById(R.id.search);
        temptext = findViewById(R.id.text_temp);
        desctext = findViewById(R.id.desc_text);
        humiditytext = findViewById(R.id.humidity_text);
        textfield =findViewById(R.id.textfield);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getWeatherData(textfield.getText().toString().trim());
            }
        });
    }

    private  void getWeatherData(String name){

        Apiinterface apiinterface = Apiclient.getClient().create(Apiinterface.class);

        Call<Example> call = apiinterface.getWeatherData(name);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

              temptext.setText("Temp"+" "+response.body().getMain().getTemp()+" C");
              desctext.setText("Feels_Like :"+response.body().getMain().getFeels_like());
              humiditytext.setText("Humidity :"+response.body().getMain().getHumidity());
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }
}
