package xiiyuoo.com.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import xiiyuoo.com.connectors.ProductConnector;
import xiiyuoo.com.connectors.SQLiteConnector;
import xiiyuoo.com.models.Product;
import xiiyuoo.com.models.ListProduct;

public class ProductManagementActivity extends AppCompatActivity {

    ListView lvProduct;
    ArrayAdapter<Product> adapterProduct;
    ListProduct listProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvProduct = findViewById(R.id.lvProduct);
        adapterProduct = new ArrayAdapter<>(ProductManagementActivity.this, android.R.layout.simple_list_item_1);
        lvProduct.setAdapter(adapterProduct);

        SQLiteConnector sqLiteConnector = new SQLiteConnector(this);
        sqLiteConnector.openDatabase();
        ProductConnector productConnector = new ProductConnector();
        listProduct = productConnector.getAllProducts(sqLiteConnector.getDatabase());

        adapterProduct.addAll(listProduct.getProducts());  // Thêm sản phẩm vào adapter
    }

    private void addEvents() {
        lvProduct.setOnItemClickListener((adapterView, view, i, l) -> {
            Product product = adapterProduct.getItem(i);
            displayProductDetailActivity(product);
        });
    }

    private void displayProductDetailActivity(Product product) {
        Intent intent = new Intent(ProductManagementActivity.this, ProductDetailActivity.class);
        intent.putExtra("SELECTED_PRODUCT", product);
        startActivity(intent);
    }
}
