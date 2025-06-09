package xiiyuoo.com.models;

import java.io.Serializable;
import java.util.ArrayList;

import xiiyuoo.com.k22411csampleproject.R;

public class ListProduct implements Serializable {
    private ArrayList<Product> products;
    private ArrayList<Category> categories; // Thêm thuộc tính categories

    public ListProduct() {
        products = new ArrayList<>();
        categories = new ArrayList<>();
    }

    // Thêm phương thức getCategories() trả về danh sách danh mục
    public ArrayList<Category> getCategories() {
        return categories;
    }

    // Phương thức này sẽ thêm các danh mục vào trong danh sách categories
    public void addCategory(Category category) {
        categories.add(category);
    }

    // Thêm sản phẩm vào danh sách
    // Phương thức trả về danh sách sản phẩm, đảm bảo kiểu trả về là ArrayList<Product>
    public ArrayList<Product> getProducts() {
        return products;
    }
    // Thêm sản phẩm vào danh sách
    public void addProduct(Product product) {
        products.add(product);
    }

    // Bạn có thể thêm dữ liệu mẫu vào danh mục hoặc sản phẩm tại đây
    public void generate_sample_product_dataset() {
        // Ví dụ tạo sản phẩm
        Product p1 = new Product(1, "Coca Cola", 100, 10.0, 1, "Soft drink", "1");
        // Và tạo danh mục
        Category c1 = new Category(1, "Soft Drinks",1);
        c1.addProduct(p1); // Thêm sản phẩm vào danh mục
        categories.add(c1); // Thêm danh mục vào danh sách categories

        // Tiếp tục với các sản phẩm và danh mục khác
    }
}
