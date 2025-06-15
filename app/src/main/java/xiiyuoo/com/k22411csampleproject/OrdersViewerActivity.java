package xiiyuoo.com.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import xiiyuoo.com.adapters.OrdersViewerAdapter;
import xiiyuoo.com.connectors.OrdersViewerConnector;
import xiiyuoo.com.connectors.SQLiteConnector;
import xiiyuoo.com.models.OrdersViewer;

public class OrdersViewerActivity extends AppCompatActivity {

    ListView lvOrdersView;
    OrdersViewerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_orders_viewer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvOrdersView = findViewById(R.id.lvOrdersViewer);
        adapter = new OrdersViewerAdapter(this, R.layout.item_ordersviewer);
        lvOrdersView.setAdapter(adapter);

        SQLiteConnector connector = new SQLiteConnector(this);
        OrdersViewerConnector ovc = new OrdersViewerConnector();
        ArrayList<OrdersViewer> dataset = ovc.getAllOrdersViewers(connector.openDatabase());
        adapter.addAll(dataset);

        lvOrdersView.setOnItemClickListener((parent, view, position, id) -> {
            OrdersViewer ov = adapter.getItem(position);
            Intent intent = new Intent(OrdersViewerActivity.this, OrderDetailsActivity.class);
            intent.putExtra("ORDER_ID", ov.getId());
            startActivity(intent);
        });
    }
}