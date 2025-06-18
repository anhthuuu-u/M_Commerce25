package xiiyuoo.com.k22411csampleproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xiiyuoo.com.adapters.TelephonyInforAdapter;
import xiiyuoo.com.models.TelephonyInfor;

public class TelephonyActivity extends AppCompatActivity {

    ListView lvTelephony;
    TelephonyInforAdapter adapter;
    List<TelephonyInfor> allContacts;
    private static final List<String> VIETTEL_PREFIXES = Arrays.asList("032", "033", "034", "035", "036", "037", "038", "039", "096", "097", "098", "086");
    private static final List<String> VINAPHONE_PREFIXES = Arrays.asList("081", "082", "083", "084", "085", "088", "091", "094", "099");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephony);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        getAllContacts();
        addEvents();
    }

    public void directCall(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    public void dialupCall(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }

    private void addEvents() {
        lvTelephony.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TelephonyInfor ti = adapter.getItem(position);
                makeAPhoneCall(ti);
            }
        });
    }

    private void makeAPhoneCall(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    private void addViews() {
        lvTelephony = findViewById(R.id.lvTelephonyInfor);
        adapter = new TelephonyInforAdapter(this, R.layout.item_telephony_infor);
        lvTelephony.setAdapter(adapter);
        allContacts = new ArrayList<>();
    }

    private void getAllContacts() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        while (cursor.moveToNext()) {
            int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameIndex);
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            String phone = cursor.getString(phoneIndex);

            // Normalize phone number (remove spaces, dashes, etc.)
            String normalizedPhone = phone.replaceAll("[^0-9]", "");
            if (normalizedPhone.startsWith("+84")) {
                normalizedPhone = "0" + normalizedPhone.substring(3);
            }
            String carrier = getCarrier(normalizedPhone);

            TelephonyInfor ti = new TelephonyInfor(name, phone, carrier);
            adapter.add(ti);
            allContacts.add(ti);
        }
        cursor.close();
    }

    private String getCarrier(String phone) {
        if (phone.length() >= 10) {
            String prefix = phone.substring(0, 3);
            if (VIETTEL_PREFIXES.contains(prefix)) {
                return "Viettel";
            } else if (VINAPHONE_PREFIXES.contains(prefix)) {
                return "VinaPhone";
            }
        }
        return "Others";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_telephony, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        adapter.clear();
        if (id == R.id.menu_filter_viettel) {
            for (TelephonyInfor ti : allContacts) {
                if (ti.getCarrier().equals("Viettel")) {
                    adapter.add(ti);
                }
            }
        } else if (id == R.id.menu_filter_vinaphone) {
            for (TelephonyInfor ti : allContacts) {
                if (ti.getCarrier().equals("VinaPhone")) {
                    adapter.add(ti);
                }
            }
        } else if (id == R.id.menu_filter_others) {
            for (TelephonyInfor ti : allContacts) {
                if (ti.getCarrier().equals("Others")) {
                    adapter.add(ti);
                }
            }
        }
        adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }
}