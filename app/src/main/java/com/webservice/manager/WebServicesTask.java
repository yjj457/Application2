package com.webservice.manager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.yinjianjun.application.HttpUtil;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.HashMap;
import java.util.Map.Entry;



/**
 * Created by yinjianjun on 15-7-31.
 */
public class WebServicesTask extends AsyncTask<String, Integer, String> {

    final static String TAG = "LOGCAT";

    private Context mContext;

    public WebServicesTask() {

    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected String doInBackground(String... params) {

        //return getPwdByYjj(params[0]);
        return getPwdByName(params[0]);
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {

    }

    @Override
    protected void onPostExecute(String result) {

    }


    private String getPwdByYjj(String name) {
        Log.i(TAG,"参数是:" + name);
        String URL = "http://192.168.2.219:8080/cxfservice/users";
        String NAMESPACE = "http://manager.webservice.com/";
        String METHOD_NAME = "users";
        String SOAP_ACTION = "";
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("arg0",name);
        SoapObject soap = null;
        try{
            SoapObject rpc = new SoapObject(NAMESPACE,METHOD_NAME);
            if (params != null && params.size() > 0){
                for (Entry<String, Object> item : params.entrySet()){
                //    rpc.addProperty(item.getKey(),item.getValue().toString());
                }
            }
            Log.i(TAG,params.toString());
            Log.i(TAG,"参数加好了！！！");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.bodyOut = rpc;
            envelope.dotNet = false;
            envelope.setOutputSoapObject(rpc);
            HttpTransportSE ht = new HttpTransportSE(URL);
            ht.debug = true;
            Log.i(TAG, "call1！！！");
            ht.call(null, envelope);
            Log.i(TAG, "call2！！！");
            try{
                Log.i(TAG,"要来数据了！！！");
                soap = (SoapObject) envelope.getResponse();
                Log.i(TAG,"数据来了:" + soap.getProperty(0).toString());
                return "result:" + soap.getProperty(0).toString();
            }catch (Exception e){
                Log.i(TAG,"又要来数据了！！！");
                soap = (SoapObject) envelope.bodyIn;
                Log.i(TAG,"数据又来了:" + soap.getProperty(0).toString());
                return "result:" + soap.getProperty(0).toString();
            }

        }catch (Exception e){
            e.printStackTrace();
            return "result:...";
        }


    }

    private String  getPwdByName(String name){
        return HttpUtil.executeHttpGet();
    }

}
