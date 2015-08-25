package ie.trusthub.trusthub2;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONException;

import java.util.List;

import ie.trusthub.trusthub2.utils.JacksonExample;
import ie.trusthub.trusthub2.utils.ThubRestClientUsage;
import ie.trusthub.trusthub2.utils.ThubUser;


public class HomeActivity extends Activity {

    private static final String TAG = "TRUSTUB_APP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // log the packages installed
        logPackages();

        // log the IMEI
        getPhoneDetails();

        // get contact details
        getContactDetails();

        // call twitter
        getUserData();

        // update user data
        updateUserData();

        // convert string to JSON object
        convertJsonObject();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loginToFacebook(View view) {
        Intent intent = new Intent(this, PhotoActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private void logPackages()
    {
        final PackageManager pm = getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            Log.d(TAG, "Installed package :" + packageInfo.packageName);
//            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
//            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
        }
    }

    private void getPhoneDetails()
    {
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String deviceid = telephonyManager.getDeviceId();
        Log.d(TAG, "Device IMEI :" + deviceid);

        int phoneType = telephonyManager.getPhoneType();
        Log.d(TAG, "Device phoneType :" + phoneType);

        String phoneNumber = telephonyManager.getLine1Number();
        Log.d(TAG, "Device phoneType :" + phoneNumber);
    }

    private void getContactDetails()
    {
        Cursor cursor =  managedQuery(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        int count = cursor.getCount();
        Log.d(TAG, "Device Number of contacts :" + count);
    }

    private void getUserData()
    {
        ThubRestClientUsage thubClient = new ThubRestClientUsage();
        try {
            Log.d(TAG, "getUserData");
            thubClient.getUsers();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void updateUserData()
    {
        ThubRestClientUsage thubClient = new ThubRestClientUsage();
        try {
            Log.d(TAG, "update ThubUser Data");
            thubClient.updateUser();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void convertJsonObject()
    {
        JacksonExample jacksonExample = new JacksonExample();
        ThubUser thubUser = jacksonExample.generateJsonFromString("{\"age\":30,\"name\":\"Joe Brown\",\"messages\":[\"msg 11\",\"msg 21\",\"msg 31\"]}");
        Log.d(TAG, "convertJsonObject: " + thubUser.toString());
    }
}
