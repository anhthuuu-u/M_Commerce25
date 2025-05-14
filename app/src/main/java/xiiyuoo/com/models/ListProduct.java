package xiiyuoo.com.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ListProduct implements Serializable {
    private ArrayList<Product> products;

    public ListProduct() {
        products= new ArrayList<>();
    }

    public ArrayList<Product> getProducts()
    {
        return products;
    }

    public void setProducts(ArrayList<Product> products)
    {
        this.products = products;
    }

    public  void addProducts(Product p)
    {
        products.add(p);
    }

    public void generate_sample_dataset()
    {
        Random random=new Random();
        for (int i=1; i<=100; i++)
        {
            int id=i;
            String name ="Product "+i;
            int quantity = 0;
            double price;
            int cate_id;
            String description;
            for(int p=1;p<=100;p++)
            {
                quantity+=random.nextInt(3);

            }

            for(int p=1;p<=100;p++)
            {
                quantity+=random.nextInt(3);

            }
            // Tạo giá ngẫu nhiên cho sản phẩm (giá từ 10 đến 500)
            price = 10 + random.nextInt(491); // Tạo giá từ 10 đến 500

            // Tạo ID danh mục ngẫu nhiên (giả sử có một vài danh mục)
            cate_id = random.nextInt(5) + 1;
            // Tạo mô tả ngẫu nhiên
            description = "Description for " + name;

            // Tạo ID danh mục ngẫu nhiên (giả sử có một vài danh mục)
            cate_id = random.nextInt(5) + 1; // Giả sử có 5 danh mục, ID từ 1 đến 5


            Product p=new Product(id,name,quantity,price,cate_id,description);
            addProducts(p);

        }
    }
}
