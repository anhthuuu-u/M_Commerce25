package xiiyuoo.com.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import xiiyuoo.com.adapters.OrderDetailsAdapter;
import xiiyuoo.com.connectors.OrderDetailsConnector;
import xiiyuoo.com.connectors.SQLiteConnector;
import xiiyuoo.com.models.OrderDetailsViewer;

public class OrderDetailsActivity extends AppCompatActivity {

    ListView lvOrderDetails;
    OrderDetailsAdapter adapter;
    TextView txtShowOrderID, txtShowTotalOrderValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
        loadData();
    }

    private void addViews() {
        lvOrderDetails = findViewById(R.id.lvOrderDetails);
        txtShowOrderID = findViewById(R.id.txtShowOrderID);
        txtShowTotalOrderValue = findViewById(R.id.txtShowTotalOrderValue);
        adapter = new OrderDetailsAdapter(this, R.layout.item_order_detail);
        lvOrderDetails.setAdapter(adapter);
    }

    private void loadData() {
        int orderId = getIntent().getIntExtra("ORDER_ID", -1);
        txtShowOrderID.setText(String.valueOf(orderId));

        SQLiteConnector connector = new SQLiteConnector(this);
        OrderDetailsConnector odc = new OrderDetailsConnector();
        ArrayList<OrderDetailsViewer> dataset = odc.getOrderDetailsByOrderId(connector.openDatabase(), orderId);
        adapter.addAll(dataset);

        double totalOrderValue = 0;
        for (OrderDetailsViewer od : dataset) {
            totalOrderValue += od.getTotalValue();
        }
        txtShowTotalOrderValue.setText(String.format("%.2f VNĐ", totalOrderValue));
    }
}