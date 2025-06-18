package xiiyuoo.com.k22411csampleproject;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import xiiyuoo.com.adapters.TelephonyInforAdapter;
import xiiyuoo.com.models.TelephonyInfor;

public class TelephonyActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSIONS = 100;
    ListView lvTelephony;
    TelephonyInforAdapter adapter;
    List<TelephonyInfor> allContacts;
    private static final List<String> VIETTEL_PREFIXES = Arrays.asList("032", "033", "034", "035", "036", "037", "038", "039", "096", "097", "098", "086");
    private static final List<String> VINAPHONE_PREFIXES = Arrays.asList("081", "082", "083", "084", "085", "088", "091", "094", "099");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TelephonyActivity", "onCreate started");
        EdgeToEdge.enable(this);
        try {
            setContentView(R.layout.activity_telephony);
            Log.d("TelephonyActivity", "setContentView done");
            Toolbar toolbar = findViewById(R.id.toolbar);
            if (toolbar == null) {
                Log.e("TelephonyActivity", "Toolbar is null - check activity_telephony.xml for ID 'toolbar'");
                Toast.makeText(this, "Error: Toolbar not found", Toast.LENGTH_LONG).show();
                finish();
                return;
            }
            setSupportActionBar(toolbar);
            Log.d("TelephonyActivity", "Toolbar set");
        } catch (Exception e) {
            Log.e("TelephonyActivity", "Error in Toolbar setup: " + e.getMessage(), e);
            Toast.makeText(this, "Error initializing UI: " + e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Check permissions
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.READ_CONTACTS,
                    android.Manifest.permission.CALL_PHONE
            }, REQUEST_PERMISSIONS);
        } else {
            initializeViewsAndData();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            initializeViewsAndData();
        } else {
            Log.e("TelephonyActivity", "Permissions denied");
            Toast.makeText(this, "Permissions required to access contacts and make calls", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void initializeViewsAndData() {
        addViews();
        getAllContacts();
        addEvents();
    }

    public void directCall(TelephonyInfor ti) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Uri uri = Uri.parse("tel:" + ti.getPhone());
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(uri);
            try {
                startActivity(intent);
            } catch (Exception e) {
                Log.e("TelephonyActivity", "Error making direct call: " + e.getMessage(), e);
                Toast.makeText(this, "Unable to make call: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Call permission not granted", Toast.LENGTH_SHORT).show();
        }
    }

    public void dialupCall(TelephonyInfor ti) {
        Uri uri = Uri.parse("tel:" + ti.getPhone());
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        try {
            startActivity(intent);
        } catch (Exception e) {
            Log.e("TelephonyActivity", "Error opening dialer: " + e.getMessage(), e);
            Toast.makeText(this, "Unable to open dialer: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
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
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Uri uri = Uri.parse("tel:" + ti.getPhone());
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(uri);
            try {
                startActivity(intent);
            } catch (Exception e) {
                Log.e("TelephonyActivity", "Error making call: " + e.getMessage(), e);
                Toast.makeText(this, "Unable to make call: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Call permission not granted", Toast.LENGTH_SHORT).show();
        }
    }

    private void addViews() {
        lvTelephony = findViewById(R.id.lvTelephonyInfor);
        if (lvTelephony == null) {
            Log.e("TelephonyActivity", "ListView is null - check activity_telephony.xml for ID 'lvTelephonyInfor'");
            Toast.makeText(this, "Error: ListView not found", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
        adapter = new TelephonyInforAdapter(this, R.layout.item_telephony_infor);
        lvTelephony.setAdapter(adapter);
        allContacts = new ArrayList<>();
    }

    private void getAllContacts() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            Log.e("TelephonyActivity", "READ_CONTACTS permission not granted");
            Toast.makeText(this, "Cannot access contacts without permission", Toast.LENGTH_LONG).show();
            return;
        }
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            Log.e("TelephonyActivity", "Cursor is null - unable to access contacts");
            Toast.makeText(this, "Unable to access contacts", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            while (cursor.moveToNext()) {
                int nameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String name = nameIndex != -1 ? cursor.getString(nameIndex) : "Unknown";
                String phone = phoneIndex != -1 ? cursor.getString(phoneIndex) : "";
                String carrier = getCarrier(phone); // Sử dụng số gốc thay vì số đã chuẩn hóa
                TelephonyInfor ti = new TelephonyInfor(name, phone, carrier);
                adapter.add(ti);
                allContacts.add(ti);
            }
        } catch (Exception e) {
            Log.e("TelephonyActivity", "Error reading contacts: " + e.getMessage(), e);
            Toast.makeText(this, "Error reading contacts: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            cursor.close();
        }
    }

    private String getCarrier(String phone) {
        if (phone == null || phone.length() < 3) {
            return "Others";
        }
        String prefix;
        // Kiểm tra số bắt đầu bằng +84
        if (phone.startsWith("+84") && phone.length() >= 5) {
            prefix = phone.substring(3, 6); // Lấy 3 chữ số sau +84 (ví dụ: +84032 -> 032)
        } else if (phone.length() >= 3) {
            prefix = phone.substring(0, 3); // Lấy 3 chữ số đầu nếu không phải +84
        } else {
            return "Others";
        }
        if (VIETTEL_PREFIXES.contains(prefix)) {
            return "Viettel";
        } else if (VINAPHONE_PREFIXES.contains(prefix)) {
            return "VinaPhone";
        }
        return "Others";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        try {
            getMenuInflater().inflate(R.menu.menu_telephony, menu);
            Log.d("TelephonyActivity", "Menu inflated successfully");
            return true;
        } catch (Exception e) {
            Log.e("TelephonyActivity", "Error inflating menu: " + e.getMessage(), e);
            Toast.makeText(this, "Error loading menu", Toast.LENGTH_LONG).show();
            return false;
        }
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
            Log.d("TelephonyActivity", "Filtered Viettel contacts");
        } else if (id == R.id.menu_filter_vinaphone) {
            for (TelephonyInfor ti : allContacts) {
                if (ti.getCarrier().equals("VinaPhone")) {
                    adapter.add(ti);
                }
            }
            Log.d("TelephonyActivity", "Filtered VinaPhone contacts");
        } else if (id == R.id.menu_filter_others) {
            for (TelephonyInfor ti : allContacts) {
                if (ti.getCarrier().equals("Others")) {
                    adapter.add(ti);
                }
            }
            Log.d("TelephonyActivity", "Filtered Other contacts");
        }
        adapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }
}