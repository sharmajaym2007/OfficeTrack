package jaysharma.officetrack;

import android.content.Context;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "jaysharma.office.track.FUCKING_EXTRA_MESSAGE";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        populateConfiguredWifiSpinner();


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void populateConfiguredWifiSpinner() {


        List list = getSSID();

        // Create a dropdown spinner
        Spinner wifiListSpinner = (Spinner) findViewById(R.id.configuredWifiSpinner);
        ArrayAdapter<String> wifiListAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        wifiListAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wifiListSpinner.setAdapter(wifiListAdapter);
    }

    /**
     * Get the list of all configured Wifi Network and retrieves SSID of all networks
     *
     * @return List of SSID's
     */
    private List getSSID() {
        List<String> list = new ArrayList<>();
        WifiManager wifiManager = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);
        List<WifiConfiguration> configuredNetworkList = wifiManager.getConfiguredNetworks();
        for (int i = 0; i < configuredNetworkList.size(); i++) {
            String currentSSID = configuredNetworkList.get(i).SSID.replace("\"", "");
            list.add(currentSSID);
        }
        return list;
    }

    /**
     * Called when the user clicks the Send button
     */
//    public void sendMessage(View view) {
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_message);
//        String message = editText.getText().toString();
//
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Dashboard Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://jaysharma.officetrack/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Dashboard Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://jaysharma.officetrack/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
