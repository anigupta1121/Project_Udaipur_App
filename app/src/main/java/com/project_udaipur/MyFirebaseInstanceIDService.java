package com.project_udaipur;

import android.util.Log;


import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.project_udaipur.volley.DataCallback;
import com.project_udaipur.volley.DataManager;

import org.json.JSONArray;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
       /* Log.d("token",token);
        DataManager dataManager = new DataManager(this);
        DataCallback callback = new DataCallback() {
            @Override
            public void onError(String message, Integer... statuscode) {
                super.onError(message, statuscode);

            }

            @Override
            public void onError(String message) {
                super.onError(message);
            }

            @Override
            public void onResponse(JSONArray response) {
                super.onResponse(response);
                Log.d("Response", response.toString());

            }
        };
        dataManager.sendToken(token,callback);*/
    }
}