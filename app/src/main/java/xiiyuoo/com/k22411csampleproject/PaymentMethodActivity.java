package xiiyuoo.com.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import xiiyuoo.com.adapters.PaymentMethodApdater;
import xiiyuoo.com.connectors.PaymentMethodConnector;
import xiiyuoo.com.connectors.SQLiteConnector;
import xiiyuoo.com.models.ListPaymentMethod;

public class PaymentMethodActivity extends AppCompatActivity {

    ListView lvPaymentMethod;
    PaymentMethodApdater adapter;
    ListPaymentMethod lpm;
    PaymentMethodConnector paymentMethodConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_method);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvPaymentMethod=findViewById(R.id.lvPaymentMethod);
        adapter=new PaymentMethodApdater(PaymentMethodActivity.this,R.layout.item_paymentmethod);
        lvPaymentMethod.setAdapter(adapter);
        paymentMethodConnector = new PaymentMethodConnector();

        // Mở kết nối với cơ sở dữ liệu
        SQLiteConnector sqLiteConnector = new SQLiteConnector(this);
        sqLiteConnector.openDatabase();

        // Lấy tất cả phương thức thanh toán từ cơ sở dữ liệu
        lpm = paymentMethodConnector.getAllPaymentMethods(sqLiteConnector.getDatabase());

        // Thêm các payment methods vào adapter
        adapter.addAll(lpm.getPaymentMethods());

//        lpm=new ListPaymentMethod();
//        lpm.gen_payments_method();
//        adapter.addAll(lpm.getPaymentMethods());
    }
}