package ie.trusthub.trusthub2.utils;

import android.util.Log;

import org.apache.http.Header;
import org.json.*;

import com.loopj.android.http.*;

public class TwitterRestClientUsage {

    private static final String TAG = "TRUSTUB_APP";

    public void getPublicTimeline() throws JSONException {
        Log.d(TAG, "TwitterRestClientUsage::TwitterRestClient.get");
        TwitterRestClient.get("statuses/public_timeline.json", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try {
                    // Pull out the first event on the public timeline
                    JSONObject firstEvent = response;
                    String tweetText = null;

                    tweetText = firstEvent.getString("text");

                    // Do something with the response
                    System.out.println(tweetText);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                try {
                    // Pull out the first event on the public timeline
                    JSONObject firstEvent = (JSONObject) timeline.get(0);
                    String tweetText = null;

                    tweetText = firstEvent.getString("text");

                    // Do something with the response
                    System.out.println(tweetText);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Log.d(TAG, "TwitterRestClientUsage::TwitterRestClient.get end");
    }

}
