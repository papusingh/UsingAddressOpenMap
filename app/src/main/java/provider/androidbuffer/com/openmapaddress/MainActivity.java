package provider.androidbuffer.com.openmapaddress;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText etAddress;
    Button btn;
    Double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAddress = findViewById(R.id.etAddress);
        btn = findViewById(R.id.btn);
        onClickBtn();
    }

    private void onClickBtn(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLatLongFromAddress(etAddress.getText().toString());
            }
        });
    }

    private void getLatLongFromAddress(String strAddress){
        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocationName(strAddress,5);
            if (addresses != null){
                Address address = addresses.get(0);
                latitude = address.getLatitude();
                longitude = address.getLongitude();

                callMap();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void callMap(){
        String uri = String.format(Locale.ENGLISH,"geo:%f,%f",latitude,longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}
