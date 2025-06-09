package xiiyuoo.com.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import xiiyuoo.com.adapters.ProductAdapter;
import xiiyuoo.com.models.ListProduct;
import xiiyuoo.com.connectors.ProductConnector;
import xiiyuoo.com.connectors.SQLiteConnector;

public class AdvancedProductActivity extends AppCompatActivity {

    ListView lvAdvancedProduct;
    ProductAdapter adapter;
    ListProduct listProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_advanced_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvAdvancedProduct = findViewById(R.id.lvAdvancedProduct);
        adapter = new ProductAdapter(AdvancedProductActivity.this, R.layout.item_advanced_product);
        lvAdvancedProduct.setAdapter(adapter);

        // Mở cơ sở dữ liệu và lấy sản phẩm từ cơ sở dữ liệu
        SQLiteConnector sqLiteConnector = new SQLiteConnector(this);
        sqLiteConnector.openDatabase();
        ProductConnector productConnector = new ProductConnector();
        listProduct = productConnector.getAllProducts(sqLiteConnector.getDatabase());

        // Thêm tất cả sản phẩm vào adapter để hiển thị trong ListView
        adapter.addAll(listProduct.getProducts());
    }
}
