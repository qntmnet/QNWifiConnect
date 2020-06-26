package com.qntm.qnwificonnection;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;
import com.thanosfisherman.wifiutils.WifiUtils;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionErrorCode;
import com.thanosfisherman.wifiutils.wifiConnect.ConnectionSuccessListener;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyStore;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class Qnwificonnection {
    private final String USER_AGENT = "Mozilla/5.0";
    Context context;
    private static AsyncHttpClient client = new AsyncHttpClient();
   /* String TOKEN1 = "link-login";
    String TOKEN2 = "link-orig";
    String TOKEN3 = "error";
    String TOKEN4 = "chap-id";
    String TOKEN5 = "mac";
    String TOKEN6 = "identity";
    String TOKEN7 = "username";
    String TOKEN8 = "chap-challenge";
    String TOKEN9 = "link-orig-esc";
    String TOKEN10 = "link-login-only";
    String strinput1, strinput2, strinput3, strinput4, strinput5, strinput6, strinput7, strinput8, strinput9, strinput10;*/
    String username,password;
    public Qnwificonnection(Context context) {
        this.context = context;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
    }

    public void Connect(String SSID, String PASSWORD,String USERNAME,String USER_PASSWORD) {
        this.username = USERNAME;
        this.password = USER_PASSWORD;

        WifiUtils.withContext(context)
                .connectWith(SSID, PASSWORD)
                .setTimeout(40000)
                .onConnectionResult(successListener)
                .start();
    }

    private ConnectionSuccessListener successListener = new ConnectionSuccessListener() {
        @Override
        public void success() {
            //Toast.makeText(context, "SUCCESS!", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //connectionManager(strinput10,username,password);
                    try {
                         sendGet(username,password);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 1000);
        }

        @Override
        public void failed(@NonNull ConnectionErrorCode errorCode) {
            Toast.makeText(context, "Connection Fail" + errorCode.toString(), Toast.LENGTH_SHORT).show();
        }
    };

    private void sendGet(final String username, final String password) throws Exception {

        //String url = "https://developer.yupwifi.com/raajbagh/loginuser.php?link-login=&link-orig=&error=&chap-id=&mac=&identity=&chap-challenge=&link-login-only=loginuser.php?link-login=http://192.168.88.1/login?dst=http%3A%2F%2Fwww.peplink.com%2F&link-orig=http://www.peplink.com/&error=&chap-id=&mac=74:D4:35:C7:6B:2E&identity=raajbagh&chap-challenge=&link-login-only=http://192.168.88.1/login&un=802309&up=802309";
        //String url="https://developer.yupwifi.com/AutoConnectApp/connect.php";
        String url = "http://peplink.com";
        URL obj = new URL(url);
        final HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.addRequestProperty("Referer", "google.com");
        boolean redirect = false;
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        System.out.println("Redireting URL : " + con.getURL());
       ///System.out.println("Redireting URL : " + URLEncoder.encode(String.valueOf(con.getURL())));
       ///System.out.println("Redireting URL : " + URLDecoder.decode(String.valueOf(con.getURL())));

        final String challenge = con.getURL().toString();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine + "\n");
        }
        //String str=new Jsoup().parse(response.toString()).text()
        //System.out.println("html to text : "+Html.fromHtml(response.toString()).toString());

        in.close();

        //print result
        System.out.println(response.toString());
        /*System.out.println();

        Document dc = Jsoup.parse(response.toString());
        //Element element=dc.select("input[name*=link-orig").

        org.jsoup.nodes.Element element2 = dc.select("input[name*=" + TOKEN2).first();
        strinput2 = element2.attr("value");
        System.out.println("string input 2" + strinput2);

        org.jsoup.nodes.Element element1 = dc.select("input[name*=" + TOKEN1).first();
        strinput1 = element1.attr("value");
        System.out.println("string input 1" + strinput1);

        org.jsoup.nodes.Element element3 = dc.select("input[name*=" + TOKEN3).first();
        strinput3 = element3.attr("value");
        System.out.println("string input 3" + strinput3);

        org.jsoup.nodes.Element element4 = dc.select("input[name*=" + TOKEN4).first();
        strinput4 = element4.attr("value");
        System.out.println("string input 4" + strinput4);

        org.jsoup.nodes.Element element5 = dc.select("input[name*=" + TOKEN5).first();
        strinput5 = element5.attr("value");
        System.out.println("string input 5" + strinput5);


        org.jsoup.nodes.Element element6 = dc.select("input[name*=" + TOKEN6).first();
        strinput6 = element6.attr("value");
        System.out.println("string input 6" + strinput6);
        //preferences.setIdentity(strinput6);

        org.jsoup.nodes.Element element7 = dc.select("input[name*=" + TOKEN7).first();
        if (element7 != null)
            strinput7 = element7.attr("value");
        System.out.println("string input 7" + strinput7);

        org.jsoup.nodes.Element element8 = dc.select("input[name*=" + TOKEN8).first();
        strinput8 = element8.attr("value");
        System.out.println("string input 8" + strinput8);

        org.jsoup.nodes.Element element9 = dc.select("input[name*=" + TOKEN9).first();
        strinput9 = element9.attr("value");
        System.out.println("string input 9" + strinput9);

        org.jsoup.nodes.Element element10 = dc.select("input[name*=" + TOKEN10).first();
        strinput10 = element10.attr("value");
        System.out.println("string input 10" + strinput10);*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try{
                    JSONObject object=new JSONObject();

                    //user_id,trip_id,mode,latitude,longitude,distance,remark,lat_long_list

                    object.put("username",username);
                    object.put("password",password);
                    object.put("challenge",challenge);
                    Log.d("param",object.toString());

                    VerifyHash(object);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, 1000);
    }


    private void VerifyHash(JSONObject jsonObject) throws UnsupportedEncodingException {


        RequestParams params = new RequestParams();
        params.put("",jsonObject);
        // StringEntity entity = new StringEntity(params.toString());
        final ByteArrayEntity entity = new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        //  params.put("Password",jsonpassword);
        final String url= "https://wsmp.zencc.net/api/v1/verifyHash";
        Log.d("url",url);

        client.setTimeout(20*1000);
        client.post(context,url,entity,"application/json",new JsonHttpResponseHandler()
        {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("res",response.toString());

                try{

                    if(response.getInt("responseCode")==200){

                    /*  boolean issuccess = response.getBoolean("success");
                      if(issuccess){
                            JSONObject dataobject = response.getJSONObject("data");
                            String url = dataobject.getString("url");
                      }else{
                          Toast.makeText(context, response.getString("message"), Toast.LENGTH_LONG).show();
                      }*///
                        //Log.d("url",response.getString("url"));
                        Call_Login(response.getString("url"));

                    }else{
                        Toast.makeText(context, response.getString("message"), Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }



            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(context, "something went wrong please try again later", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void Call_Login(String url){

        Log.d("last_url",url);
        Httpclientclass.get(url,null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                Log.d("login_response",response.toString());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }
}
