package xiiyuoo.com.connectors;

import java.util.ArrayList;

import xiiyuoo.com.models.Customer;
import xiiyuoo.com.models.ListCustomer;
import xiiyuoo.com.models.ListProduct;
import xiiyuoo.com.models.Product;

public class ProductConnector {

    ListProduct listProduct;
    public ProductConnector()
    {
        listProduct=new ListProduct();
        listProduct.generate_sample_dataset();;
    }
    public ArrayList<Product> get_all_products()
    {
        if(listProduct==null)
        {
            listProduct=new ListProduct();
            listProduct.generate_sample_dataset();
        }
        return listProduct.getProducts();
    }
    public ArrayList<Product> get_product_by_provider()
    {
        if(listProduct==null)
        {
            listProduct=new ListProduct();
            listProduct.generate_sample_dataset();
        }
        ArrayList<Product>results=new ArrayList<>();
        for (Product p: listProduct.getProducts())
        {

        }
        return results;
    }
}
