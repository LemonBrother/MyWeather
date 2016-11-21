package mustdo.liumin.com.myweather.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Locale;

import mustdo.liumin.com.myweather.R;
import mustdo.liumin.com.myweather.WeatherRequest;
import mustdo.liumin.com.myweather.utils.MyUtils;

public class MainActivity extends Activity {

    public Button btn;
    public TextView showtv;
    public String returncode;
    public TextToSpeech mSpeech;
    public String todaystring;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    //MyWeather mw = (MyWeather) msg.obj;    //use this word where use  the JsonParser
                    WeatherRequest wr = (WeatherRequest)msg.obj;
                    showtv.setText(showtv.getText()+"\n"+wr.toString());
                    todaystring = wr.toString();
                    speaktoday();
                    break;
                case 1:
                    showtv.setText(showtv.getText()+"\n"+msg.obj.toString());
            }
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.btn);
        showtv = (TextView)findViewById(R.id.showtv);

        mSpeech = new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status == TextToSpeech.SUCCESS) {
                    int result = mSpeech.setLanguage(Locale.CHINESE);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("lanageTag", "not use");
                    } else {


                        mSpeech.speak("该起床了....\n   起床了...... \n  床了......  \n   了",
                                TextToSpeech.QUEUE_FLUSH, null);


                    }

                }}
        });



        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showtv.setText("the button is clicked  \n");
                myrequest();


                showtv.setText("\n");
            }
        });

    }

    private  void myrequest(){
        new Thread(
                new Runnable() {
            @Override
            public void run() {

                try {
                    URL url = new URL("http://api.map.baidu.com/telematics/v3/weather?" +
                            "location="+URLEncoder.encode("沈阳","UTF-8")+"&output=json" +
                            "&ak=5HiuQwdjeHUU6f24igqoAdNMDGwdGri7" +
                            "&mcode=BF:78:9A:BC:54:94:83:56:52:85:D4:4F:75:F7:D3:50:52:36:56:A6;" +
                            "mustdo.liumin.com.testweather");
                    URLConnection conn = url.openConnection();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));


                    String jsonstring = "";
                    String templine = null;
                    try {


                        while ((templine = reader.readLine()) != null) {

                            jsonstring += templine;
                            Log.v("LMTAG",templine);
                        }
                        returncode = jsonstring;
                        Log.v("LMTAG",returncode);

                        //new JsonParser().parseJSONObjectOrJSONArray(jsonstring,handler);

                        java.lang.reflect.Type type = new TypeToken<WeatherRequest>() {}.getType();
                        WeatherRequest wr = (WeatherRequest)MyUtils.parserFromJson(type, jsonstring);
                        Message temp = new Message();
                        temp.obj = wr;
                        temp.what = 0;
                        handler.sendMessage(temp);
                        Log.v("LMTAG",wr.getStatus()+wr.getResults().get(0).getIndex().get(0).getTitle());




                    } finally {

                        reader.close();

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Message m = new Message();
                    m.what = 1;
                    m.obj = "get the internet failed";
                    handler.sendMessage(m);
                }

            }}).start();
    }




    private void speaktoday(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                mSpeech.speak(todaystring,TextToSpeech.QUEUE_FLUSH,null);
            }
        }).start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mSpeech != null){
            mSpeech.stop();
            mSpeech.shutdown();
        }



    }
}
