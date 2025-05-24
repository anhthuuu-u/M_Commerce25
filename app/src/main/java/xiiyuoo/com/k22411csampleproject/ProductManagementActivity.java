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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import xiiyuoo.com.models.Category;
import xiiyuoo.com.models.ListCategory;
import xiiyuoo.com.models.Product;

public class ProductManagementActivity extends AppCompatActivity {

    ListView lvProduct;
    ArrayAdapter<Category>adapterCategory;
    ArrayAdapter<Product>adapterProduct;
    ListCategory  listCategory;
    Spinner spinnerCategory;
//    ProductConnector connector;
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
        addEvents();
    }

    private void addEvents() {
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Category c=adapterCategory.getItem(i);
                displayProductsByCategory(c);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void displayProductsByCategory(Category c) {
        //xóa dlieu cũ trong listview đi:
        adapterProduct.clear();
        //nạp mới lại dữ liệu cho adapter;
        adapterProduct.addAll(c.getProducts());
    }

    private void addViews() {
        spinnerCategory=findViewById(R.id.spinnerCategory);
        adapterCategory=new ArrayAdapter<>(ProductManagementActivity.this, android.R.layout.simple_spinner_item);
        spinnerCategory.setAdapter(adapterCategory);

        listCategory=new ListCategory();
        listCategory.generate_sample_product_dataset();
        adapterCategory.addAll(listCategory.getCategories());

        lvProduct=findViewById(R.id.lvProduct);
        adapterProduct=new ArrayAdapter<>(
                ProductManagementActivity.this,
                android.R.layout.simple_list_item_1);
        lvProduct.setAdapter(adapterProduct);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.option_menu_product,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_add_product)
        {
            Toast.makeText(ProductManagementActivity.this,
                    "Mở màn hình thêm mới sản phẩm",
                    Toast.LENGTH_LONG).show();

        }
        else if(item.getItemId()==R.id.menu_update_product)
        {
            Toast.makeText(ProductManagementActivity.this,
                    "Cập nhật thông tin sản phẩm",
                    Toast.LENGTH_LONG).show();
            //Tìm hiểu: Firebase Cloud Message + push message
        }
        else if(item.getItemId()==R.id.menu_quality_control)
        {
            Toast.makeText(ProductManagementActivity.this,
                    "Kiểm tra số lượng sản phẩm",
                    Toast.LENGTH_LONG).show();
        }

        else if(item.getItemId()==R.id.menu_view_product)
        {
            Toast.makeText(ProductManagementActivity.this,
                    "Xem thông tin chi tiết sản phẩm",
                    Toast.LENGTH_LONG).show();
        }

        else if(item.getItemId()==R.id.menu_compare_product)
        {
            Toast.makeText(ProductManagementActivity.this,
                    "Xem thông tin so sánh các sản phẩm",
                    Toast.LENGTH_LONG).show();
        }

        else if(item.getItemId()==R.id.menu_add_product_category)
        {
            Toast.makeText(ProductManagementActivity.this,
                    "Theem danh mục sản phẩm",
                    Toast.LENGTH_LONG).show();
        }

        else if(item.getItemId()==R.id.menu_export_data)
        {
            Toast.makeText(ProductManagementActivity.this,
                    "Xuất dữ liệu",
                    Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }
}