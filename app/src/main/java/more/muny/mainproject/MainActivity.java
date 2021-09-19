package more.muny.mainproject;

import android.content.Intent;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<courseValute> d;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        d=data();
        StateAdapter.OnStateClickListener stateClickListener = new StateAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(courseValute courseValute, int position) {
                Intent i = new Intent(MainActivity.this,colculator.class);

                i.putExtra("name",d.get(position).getName());
                i.putExtra("course",d.get(position).getCource().toString());
                startActivity(i);
            }
        };
        StateAdapter adapter = new StateAdapter(this,data(),stateClickListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = getResources().getDimensionPixelOffset(R.dimen.re_2_gap);
            }
        });
    }


    //////////////////////////////////////JSON parsing//////////////////////////////////////////////
    public static ArrayList<courseValute> data(){
        final String[]vaalute={"AUD","AZN","GBP","AMD","BYN","BGN","BRL","HUF","HKD","DKK","USD","EUR","INR","KZT","CAD","KGS","CNY","MDL","NOK","PLN","RON","XDR","TJS","TRY","TMT","UZS","UAH","CZK","SEK","CHF","ZAR","KRW","JPY"};
        ArrayList<String>course = new ArrayList<>();
        ArrayList<String>name = new ArrayList<>();
        ArrayList<courseValute>dataValue = new ArrayList<>();
        DowlandJSONTask task = new DowlandJSONTask();
        try {
            String jsonObject =  task.execute("https://www.cbr-xml-daily.ru/daily_json.js").get();
            JSONObject mJSONObject = new JSONObject(jsonObject);
            JSONObject valute = mJSONObject.getJSONObject("Valute");

            for (int i = 0; i <= vaalute.length - 1; i++ ){
                JSONObject  concretValute = valute.getJSONObject(vaalute[i]);
              Double dauble=  concretValute.getDouble("Value");
             String value= dauble.toString();
                course.add(value);
                name.add(concretValute.getString("Name"));
                dataValue.add(new courseValute(name.get(i),course.get(i)));
            }
            return dataValue;
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } catch (ExecutionException executionException) {
            executionException.printStackTrace();
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
        }

        return null;
    }//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>JSON parsing>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    ////////////////////////////// Dowland DATA json /////////////////////////////////////////////
    private static class DowlandJSONTask extends AsyncTask<String,Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            URL url =null;
            HttpURLConnection urlConnection = null;
            StringBuilder result = new StringBuilder();
            try {
                url = new URL(strings[0]);
                urlConnection=(HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader =  new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line =reader.readLine();
                while(line != null){
                    result.append(line);
                    line = reader.readLine();
                }
                return   result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(urlConnection !=null){
                    urlConnection.disconnect();
                }
            }
            return null;
        }
    }//      >>>>>>>>>>>>>>>>>>>>>>>Dowland DATA json>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
