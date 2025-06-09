package xiiyuoo.com.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import xiiyuoo.com.models.ListProduct;
import xiiyuoo.com.models.Product;

public class ProductConnector {
    ListProduct listProduct;

    public ProductConnector() {
        listProduct = new ListProduct();
    }

    public ListProduct getAllProducts(SQLiteDatabase database) {
        listProduct = new ListProduct();
        Cursor cursor = database.rawQuery("SELECT * FROM Product", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int quantity = cursor.getInt(2);
            double price = cursor.getDouble(3);
            int cate_id = cursor.getInt(4);
            String description = cursor.getString(5);
            String imageLink = cursor.getString(6);  // Lấy cột imageLink thay vì image_id

            // In ra giá trị imageLink để kiểm tra
            Log.d("ImageLink", "Fetched ImageLink: " + imageLink);

            // Khởi tạo sản phẩm mới với imageLink
            Product product = new Product(id, name, quantity, price, cate_id, description, imageLink);

            // Thêm sản phẩm vào danh sách
            listProduct.getProducts().add(product);
        }
        cursor.close();
        return listProduct;
    }

}
