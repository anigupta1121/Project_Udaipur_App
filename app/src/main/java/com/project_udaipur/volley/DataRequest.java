package com.project_udaipur.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class DataRequest {
    private Context context;

    public DataRequest(Context ctx)
    {
        context=ctx;
    }

    public void getjsonarraypost(String url, JSONArray jsonArray, final DataCallback callback)
    {
        JsonRequest<JSONArray> req= new JsonRequest<JSONArray>(Request.Method.POST, url, jsonArray.toString(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        callback.onResponse(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse!=null)
                    callback.onError(error.toString(),new Integer(error.networkResponse.statusCode));
                else
                    callback.onError(error.toString(),new Integer(-1));
            }
        }) {
            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString =
                            new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new JSONArray(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        };

        RequestQueue mrequestqueue= VolleySingleton.getInstance(context).getRequestQueue();
        req.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mrequestqueue.add(req);
    }

public void getjsonarraypost(String url, JSONObject object, final DataCallback callback)
    {
        JsonRequest<JSONArray> req= new JsonRequest<JSONArray>(Request.Method.POST, url, object.toString(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        callback.onResponse(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse!=null)
                    callback.onError(error.toString(),new Integer(error.networkResponse.statusCode));
                else
                    callback.onError(error.toString(),new Integer(-1));
            }
        }) {
            @Override
            protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString =
                            new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new JSONArray(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        };

        RequestQueue mrequestqueue= VolleySingleton.getInstance(context).getRequestQueue();
        req.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mrequestqueue.add(req);
    }



    public void getjsonarray(String url, final DataCallback callback){
        JsonArrayRequest req= new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        callback.onResponse(response);
                    }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse!=null)
                    callback.onError(error.toString(),new Integer(error.networkResponse.statusCode));
                else
                    callback.onError(error.toString(),new Integer(-1));
            }
        });
        RequestQueue mrequestqueue= Volley.newRequestQueue(context);
        mrequestqueue.add(req);
    }


    public void checkuserdata(String url, JSONObject userdata, final DataCallback callback) {
        JsonObjectRequest req= new JsonObjectRequest(Request.Method.POST, url, userdata,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onResponse(new JSONArray().put(response));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse!=null)
                    callback.onError(error.toString(),new Integer(error.networkResponse.statusCode));
                else
                    callback.onError(error.toString(),new Integer(-1));
            }
        });

        RequestQueue mrequestqueue= VolleySingleton.getInstance(context).getRequestQueue();
        mrequestqueue.add(req);
    }

    public void Stringrequestpost(String url, final String data, final DataCallback callback)
    {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {JSONObject jsonObject = new JSONObject(response);
                } catch (JSONException ignored) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError.networkResponse!=null)
                    callback.onError(volleyError.toString(),new Integer(volleyError.networkResponse.statusCode));
                else
                    callback.onError(volleyError.toString(),new Integer(-1));
            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("preference", data);
                return headers;
            }

            @Override
            public Priority getPriority() {
                return Priority.IMMEDIATE;
            }
        };
        RequestQueue mrequestqueue= VolleySingleton.getInstance(context).getRequestQueue();
        mrequestqueue.add(stringRequest);
    }
    public void jsonOjectpost(String url, JSONObject userdata, final DataCallback callback) {
        JsonObjectRequest req= new JsonObjectRequest(Request.Method.POST, url, userdata,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onResponse(new JSONArray().put(response));

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse!=null)
                    callback.onError(error.toString(),new Integer(error.networkResponse.statusCode));
                else
                    callback.onError(error.toString(),new Integer(-1));
            }
        });

        RequestQueue mrequestqueue= Volley.newRequestQueue(context);
        mrequestqueue.add(req);
    }
    public void  getjsonOjectpost(String url, JSONObject userdata, final DataCallback callback) {
        JsonObjectRequest req= new JsonObjectRequest(Request.Method.POST, url, userdata,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onResponse(response);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error.networkResponse!=null)
                    callback.onError(error.toString(),new Integer(error.networkResponse.statusCode));
                else
                    callback.onError(error.toString(),new Integer(-1));
            }
        });

        RequestQueue mrequestqueue= VolleySingleton.getInstance(context).getRequestQueue();
        mrequestqueue.add(req);
    }


    public void getjsonOjectpost(String url, JSONArray jsonArray, final DataCallback callback) {

        JsonRequest<JSONObject> req= new JsonRequest<JSONObject>(Request.Method.POST, url, jsonArray.toString(),
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        callback.onResponse(response);
                        Log.d("RESPONSECONTACT",response.toString());

                    }
                }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                if(error.networkResponse!=null)
                    callback.onError(error.toString(),new Integer(error.networkResponse.statusCode));
                else
                    callback.onError(error.toString(),new Integer(-1));
            }
        })
        {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString =
                            new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new JSONObject(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }
        };

        RequestQueue mrequestqueue= VolleySingleton.getInstance(context).getRequestQueue();
        req.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mrequestqueue.add(req);
    }


}
