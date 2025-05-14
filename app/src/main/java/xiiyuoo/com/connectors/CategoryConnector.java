package xiiyuoo.com.connectors;

import java.util.ArrayList;

import xiiyuoo.com.models.Catagory;
import xiiyuoo.com.models.ListCategory;

public class CategoryConnector {
    private ListCategory listCategory;

    public CategoryConnector() {
        listCategory = new ListCategory();
        listCategory.gen_dataset(); // Sinh dữ liệu category
    }

    public ArrayList<Catagory> get_all_categories() {
        return listCategory.getCategories();
    }
}
