package ie.trusthub.trusthub2.utils;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

public class ThubRestClientUsage {

    private static final String LOG_TAG = "TRUSTUB_APP";

    public void getUsers() throws JSONException {
//        ThubRestClient.get("v1/users/1", null, new AsyncHttpResponseHandler() {
        ThubRestClient.get("v1/session_users/3Y02Lm1DkcCX8lHV8P21Nv", null, new JsonHttpResponseHandler() {

//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
//                // called when response HTTP status is "200 OK"
//                Log.d(LOG_TAG, "ThubRestClientUsage::TwitterRestClient.onSuccess");
//                debugHeaders(LOG_TAG, headers);
//                debugStatusCode(LOG_TAG, statusCode);
//                debugResponse(LOG_TAG, new String(response));
//            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                // called when response HTTP status is "200 OK"
                Log.d(LOG_TAG, "ThubRestClientUsage::TwitterRestClient::getUsers::onSuccess");
                debugHeaders(LOG_TAG, headers);
                debugStatusCode(LOG_TAG, statusCode);
                debugResponse(LOG_TAG, response.toString());

                // extract the JSON object from the data element
                try {
                    JSONObject data = response.getJSONObject("data");
                    Log.d(LOG_TAG, "ThubRestClientUsage::TwitterRestClient::getUsers:: got user: " + data.get("name"));
                    Log.d(LOG_TAG, "ThubRestClientUsage::TwitterRestClient::getUsers:: got user token: " + data.get("token"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                // called when request fails
                Log.d(LOG_TAG, "ThubRestClientUsage::TwitterRestClient::getUsers::onFailure: " + errorResponse.toString());
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                Log.d(LOG_TAG, "ThubRestClientUsage::TwitterRestClient.onRetry");
            }

        });
    }

    public void updateUser() throws JSONException {

        // build up request to update user
        RequestParams requestParams = new RequestParams("user_session_data",
                "{\"name\":\"Joe Bloggs\",\"phone\":\"087 6543 210\",\"email\":\"test@test.com\",\"reg_no\":\"00-C-10513\",\"address_1\":\"Grafton Buildings\"," +
                        "\"address_2\":\"123 Grafton Street\",\"city\":\"Dublin\",\"postcode\":\"Dublin 2\",\"county\":\"Fermanagh\"}");

        // post the request
        ThubRestClient.patch("v1/session_users/3Y02Lm1DkcCX8lHV8P21Nv", requestParams, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                // called when response HTTP status is "200 OK"
                Log.d(LOG_TAG, "ThubRestClientUsage::TwitterRestClient::updateUser::onSuccess");
                debugHeaders(LOG_TAG, headers);
                debugStatusCode(LOG_TAG, statusCode);
                debugResponse(LOG_TAG, response.toString());

                // extract the JSON object from the data element
                try {
                    JSONObject data = response.getJSONObject("data");
                    Log.d(LOG_TAG, "ThubRestClientUsage::TwitterRestClient::updateUser:: got user: " + data.get("name"));
                    Log.d(LOG_TAG, "ThubRestClientUsage::TwitterRestClient::updateUser:: got user token: " + data.get("token"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                // called when request fails
                Log.d(LOG_TAG, "ThubRestClientUsage::TwitterRestClient::updateUser::onFailure: " + errorResponse.toString());
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
                Log.d(LOG_TAG, "ThubRestClientUsage::TwitterRestClient::updateUser::onRetry");
            }

        });
    }


    protected final void debugResponse(String TAG, String response) {
        if (response != null) {
            Log.d(TAG, "Response data:");
            Log.d(TAG, response);
        }
    }

    protected final void debugStatusCode(String TAG, int statusCode) {
        String msg = String.format(Locale.US, "Return Status Code: %d", statusCode);
        Log.d(TAG, msg);
    }

    protected final void debugHeaders(String TAG, Header[] headers) {
        if (headers != null) {
            Log.d(TAG, "Return Headers:");
            StringBuilder builder = new StringBuilder();
            for (Header h : headers) {
                String _h = String.format(Locale.US, "%s : %s", h.getName(), h.getValue());
                Log.d(TAG, _h);
                builder.append(_h);
                builder.append("\n");
            }
            Log.d(TAG, "Return Headers:" + builder.toString());
        }
    }
}
