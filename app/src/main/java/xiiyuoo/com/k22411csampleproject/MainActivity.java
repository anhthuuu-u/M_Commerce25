package xiiyuoo.com.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView imgEmployee;
    TextView txtEmployee;

    ImageView imgCustomer, imgProduct, imgOrder, imgPaymentMethod, imgTelephony, imgBlog, imgTransport, imgReport;
    TextView txtCustomer, txtProduct, txtOrder, txtPaymentMethod, txtTelephony, txtBlog, txtTransport, txtReport;

    ImageView imgAdvancedProduct;
    TextView txtAdvancedProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        addView();
        addEvents();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addEvents() {
        imgAdvancedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedProductManagementActivity();
            }
        });

        txtAdvancedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvancedProductManagementActivity();
            }
        });

        imgEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gọi code mở màn hình quản trị nhân sự
                openEmployeeManagementActivity();
            }
        });
        txtEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gọi code mở màn hình quản trị nhân sự
                openEmployeeManagementActivity();
            }
        });

        imgCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerManagementActivity();
            }
        });
        txtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCustomerManagementActivity();
            }
        });
        imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProductManagementActivity();
            }
        });

        txtProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProductManagementActivity();
            }
        });

        imgPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentMethodActivity();
            }
        });
        txtPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPaymentMethodActivity();
            }
        });
        imgOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderViewerActivity();
            }
        });
        txtOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrderViewerActivity();
            }
        });
        imgTelephony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTelephonyActivity();
            }
        });
        txtTelephony.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTelephonyActivity();
            }
        });
        imgBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBlogManagementActivity();
            }
        });
        txtBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBlogManagementActivity();
            }
        });
        imgTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransportManagementActivity();
            }
        });
        txtTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransportManagementActivity();
            }
        });
        imgReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReportManagementActivity();
            }
        });
        txtReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReportManagementActivity();
            }
        });
    }

    private void openAdvancedProductManagementActivity() {
        Intent intent=new Intent(MainActivity.this, AdvancedProductActivity.class);
        startActivity(intent);
    }

    void openEmployeeManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, EmployeeManagementActivity.class);
        startActivity(intent);
    }

    void openCustomerManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, CustomerManagementActivity.class);
        startActivity(intent);
    }
    void openProductManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, ProductManagementActivity.class);
        startActivity(intent);
    }
//    void openOrderManagementActivity()
//    {
//        Intent intent=new Intent(MainActivity.this, OrderManagementActivity.class);
//        startActivity(intent);
//    }
    void openPaymentMethodActivity()
    {
        Intent intent=new Intent(MainActivity.this, PaymentMethodActivity.class);
        startActivity(intent);
    }

    void openOrderViewerActivity()
    {
        Intent intent=new Intent(MainActivity.this, OrdersViewerActivity.class);
        startActivity(intent);
    }

    void openTelephonyActivity()
    {
        Intent intent=new Intent(MainActivity.this,TelephonyActivity.class);
        startActivity(intent);
    }
    void openBlogManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, BillManagementActivity.class);
        startActivity(intent);
    }
    void openTransportManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, TransportManagementActivity.class);
        startActivity(intent);
    }
    void openReportManagementActivity()
    {
        Intent intent=new Intent(MainActivity.this, ReportManagementActivity.class);
        startActivity(intent);
    }
    private void addView() {
        imgEmployee = findViewById(R.id.imgEmployee);
        txtEmployee = findViewById(R.id.txtEmployee);

        imgCustomer = findViewById(R.id.imgCustomer);
        txtCustomer = findViewById(R.id.txtCustomer);

        imgProduct = findViewById(R.id.imgProduct);
        txtProduct = findViewById(R.id.txtProduct);



        txtPaymentMethod = findViewById(R.id.txtPaymentMethod);
        imgPaymentMethod = findViewById(R.id.imgPaymentMethod);

        txtOrder = findViewById(R.id.txtOrder);
        imgOrder = findViewById(R.id.imgOrder);

        imgTelephony = findViewById(R.id.imgTelephony);
        txtTelephony = findViewById(R.id.txtTelephony);

        imgBlog = findViewById(R.id.imgBlog);
        txtBlog = findViewById(R.id.txtBlog);

        imgTransport = findViewById(R.id.imgTransport);
        txtTransport = findViewById(R.id.txtTransport);

        imgReport = findViewById(R.id.imgReport);
        txtReport = findViewById(R.id.txtReport);

        imgAdvancedProduct = findViewById(R.id.imgAdvancedProduct);
        txtAdvancedProduct = findViewById(R.id.txtAdvancedProduct);
    }
}