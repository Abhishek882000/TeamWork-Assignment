package com.abhishek.teamworktask;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUs_Fragment extends Fragment {
    TextView emailLabel,PhoneLabel,phoneNumberTextView,emailTextView;
    String number = "9873138505";

    public ContactUs_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_contact_us, container, false);

        emailLabel = myView.findViewById(R.id.emailLabel);
        PhoneLabel = myView.findViewById(R.id.phoneLabel);
        phoneNumberTextView = myView.findViewById(R.id.phoneNumberTextView);
        emailTextView = myView.findViewById(R.id.emailTextView);

        emailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                startActivity(emailIntent);
            }
        });

        phoneNumberTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:9873138505"));
//                startActivity(callIntent);

                Uri u = Uri.parse("tel:" + number);

                // Create the intent and set the data for the
                // intent as the phone number.
                Intent i = new Intent(Intent.ACTION_DIAL, u);

                try
                {
                    // Launch the Phone app's dialer with a phone
                    // number to dial a call.
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    // show() method display the toast with
                    // exception message.
                    Toast.makeText(getActivity(), "Security error", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        return myView;
    }
}
