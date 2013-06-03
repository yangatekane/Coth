package com.coth.webservice;

import android.content.Context;
import com.coth.ui.R;
import com.coth.utils.CothSocketFactory;
import com.coth.webservice.objects.ScriptureBasicDto;
import com.coth.webservice.objects.results.ScriptureDataResult;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanga on 2013/05/26.
 */
public class CothWebService {
    private static String PROTOCOL;
    private static String SERVER;
    private static int PORT;

    public CothWebService(Context context) {
        PROTOCOL = context.getString(R.string.PROTOCOL);
        SERVER = context.getString(R.string.SERVER);
        PORT = context.getResources().getInteger(R.integer.PORT);
    }

    private String makeRequest(URI path, String JSONRequest,Context context) throws Exception {
        // instantiates httpclient to make request
        HttpParams params = new BasicHttpParams();

        int timeoutConnection = 60000;
        HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);
        // Set the default socket timeout (SO_TIMEOUT)
        // in milliseconds which is the timeout for waiting for data.
        int timeoutSocket = 60000;
        HttpConnectionParams.setSoTimeout(params, timeoutSocket);

        HttpClient httpclient = getNewHttpClient(params);

        // url with the post data
        HttpPost httpost = new HttpPost(path);


        StringEntity se = new StringEntity(JSONRequest, "utf-8");
        // sets the post request as the resulting string
        httpost.setEntity(se);

        // sets a request header so the page receving the request will know what
        // to do with it
        httpost.setHeader("Accept", "application/json");
        httpost.setHeader("Content-type", "application/json; charset=utf-8");

        HttpResponse response = null;

        try {
            response = httpclient.execute(httpost);
        } catch (Exception e) {
            throw e;
        }

        if (response != null) {

            HttpEntity entity = response.getEntity();
            // Log.i("KoomaWebService", "Content-Length " +
            // entity.getContentLength());

            ByteArrayOutputStream baos = new ByteArrayOutputStream(
                    (int) entity.getContentLength());
            response.getEntity().writeTo(baos);

            String text = baos.toString("UTF-8");

			/*
			 * if (text.length() >= 106) { Log.i("KoomaWebService",
			 * "Area of error: " + text.substring(105)); }
			 * Log.i("KoomaWebService", "Length of text in string: " +
			 * text.length());
			 */

//			  Log.i("KoomaWebService", "::: " + text + " :::");

            return text;
        } else{
            throw new Exception("Unknown Error occured");
        }
    }
    private HttpClient getNewHttpClient(HttpParams params) {
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            SSLSocketFactory sf = new CothSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            SchemeRegistry registry = new SchemeRegistry();
            //registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("http", sf, 3000));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient(params);
        }
    }
    public ScriptureDataResult GetList(Context c) throws Exception {
        Map<String, Object> JSONMap = new HashMap<String, Object>();
        Gson gson = new Gson();
        String JSONRequest = gson.toJson(JSONMap);

        try {

            URI uri = URIUtils.createURI(PROTOCOL, SERVER, PORT,
                    "/users", null,
                    null);

            String response = makeRequest(uri, JSONRequest, c);
            ScriptureDataResult responseObj = gson.fromJson(response, ScriptureDataResult.class);

            return responseObj;

        } catch (Exception e) {
            throw e;
        }
    }
}
