package br.com.concretesolutions.kappuccino;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RuntimePermissionActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 123;
    private TextView result;
    private static final String TAG = "RuntimePermission";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runtime_permission);

        Button btn = findViewById(R.id.btn_request_permission);
        result = findViewById(R.id.txt_permission_result);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showContacts(view);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    result.setText("PERMISSION GRANTED");

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    result.setText("PERMISSION DENIED");
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    public void showContacts(View v) {
        Log.i(TAG, "Show contacts button pressed. Checking permissions.");

        // Verify that all required contact permissions have been granted.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            // Contacts permissions have not been granted.
            Log.i(TAG, "Contact permissions has NOT been granted. Requesting permissions.");
            requestContactsPermissions();

        } else {

            // Contact permissions have been granted. Show the contacts fragment.
            Log.i(TAG,
                    "Contact permissions have already been granted. Displaying contact details.");
        }
    }

    /**
     * Requests the Contacts permissions.
     * If the permission has been denied previously, a SnackBar will prompt the user to grant the
     * permission, otherwise it is requested directly.
     */
    private void requestContactsPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        // BEGIN_INCLUDE(contacts_permission_request)
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                Manifest.permission.READ_CONTACTS)) {
//
//            // Provide an additional rationale to the user if the permission was not granted
//            // and the user would benefit from additional context for the use of the permission.
//            // For example, if the request has been denied previously.
//            Log.i(TAG,
//                    "Displaying contacts permission rationale to provide additional context.");
//
//            // Display a SnackBar with an explanation and a button to trigger the request.
//            Snackbar.make(mLayout, R.string.permission_contacts_rationale,
//                    Snackbar.LENGTH_INDEFINITE)
//                    .setAction(R.string.ok, new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            ActivityCompat
//                                    .requestPermissions(MainActivity.this, PERMISSIONS_CONTACT,
//                                            REQUEST_CONTACTS);
//                        }
//                    })
//                    .show();
//        } else {
//            // Contact permissions have not been granted yet. Request them directly.
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},
//                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//        }
        // END_INCLUDE(contacts_permission_request)
    }
}
