package xiiyuoo.com.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import xiiyuoo.com.connectors.CustomerConnector;
import xiiyuoo.com.connectors.SQLiteConnector;
import xiiyuoo.com.models.Customer;
import xiiyuoo.com.models.ListCustomer;

public class CustomerManagementActivity extends AppCompatActivity {

    ListView lvCustomer;
    ArrayAdapter<Customer> adapter;
    CustomerConnector connector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        addEvents();
    }

    private void addEvents() {
        lvCustomer.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*Customer selected=adapter.getItem(i);
                adapter.remove(selected);*/
                Customer c=adapter.getItem(i);
                displayCustomerDetailActivity(c);
                return false;
            }
        });
        /*lvCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Customer c=adapter.getItem(i);
                displayCustomerDetailActivity(c);
            }
        });*/
    }

    private void displayCustomerDetailActivity(Customer c) {
        Intent intent=new Intent(CustomerManagementActivity.this,CustomerDetailActivity.class);
        intent.putExtra("SELECTED_CUSTOMER",c);
        startActivity(intent);
    }

    private void addViews() {
        lvCustomer=findViewById(R.id.lvCustomer);
        adapter=new ArrayAdapter<>(
                CustomerManagementActivity.this,
                android.R.layout.simple_list_item_1
        );
        connector=new CustomerConnector();
        // Fixing the issue: you need to get customers from the database and update the list
        SQLiteConnector sqLiteConnector = new SQLiteConnector(this);
        sqLiteConnector.openDatabase();

        ListCustomer lc = connector.getAllCustomers(sqLiteConnector.getDatabase()); // Correct method to get customers
        adapter.addAll(lc.getCustomers()); // Add the customers from the list
        lvCustomer.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_customer,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_newcustomer)
        {
            Toast.makeText(CustomerManagementActivity.this,
                    "Mở màn hình thêm mới khách hàng",
                    Toast.LENGTH_LONG).show();
            Intent intent=new Intent(CustomerManagementActivity.this,CustomerDetailActivity.class);
            //đóng gói và đặt mã request code là 300
            startActivityForResult(intent,300);
        }
        else if(item.getItemId()==R.id.menu_broadcastadvertising)
        {
            Toast.makeText(CustomerManagementActivity.this,
                    "Gửi quảng cáo hàng loạt tới khách hàng",
                    Toast.LENGTH_LONG).show();
            //Tìm hiểu: Firebase Cloud Message + push message
        }
        else if(item.getItemId()==R.id.menu_help)
        {
            Toast.makeText(CustomerManagementActivity.this,
                    "Mở Website hướng dẫn sử dụng",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //trường hợp xử lí cho NEW CUSTOMER ta chỉ quan tâm 300 và 500 do ta định nghĩa
        if (requestCode==300 && resultCode==500)
        {
            //Lấy gói tin ra:
            Customer c=(Customer) data.getSerializableExtra("NEW_CUSTOMER");
            process_save_customer(c);
        }
    }

    private void process_save_customer(Customer c)
    {
        boolean result=connector.isExist(c);
        if (result==true)
        {
            //tức l customer này đã tồn tại trong hệ thống
            //họ có nhu cầu sửa các thông tin khác, ví dụ:
            //Địa chỉ, payment method...
            //Địa chỉ, payment method...
            //Sinh viên tự xử lí trường hợp sửa thông tin
        }
        else
        {
            //là thêm mới Customer vì chưa tồn tại
            connector.addCustomer(c);
            adapter.clear();
            adapter.addAll(connector.get_all_customers());
        }
    }
}