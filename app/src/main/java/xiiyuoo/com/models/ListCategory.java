package xiiyuoo.com.models;

import java.util.ArrayList;

public class ListCategory {
    private ArrayList<Catagory> categories;

    public ListCategory()
    {
        categories= new ArrayList<>();
    }

    public ArrayList<Catagory> getCategories()
    {
        return categories;
    }

    public void setCategories(ArrayList<Catagory> categories)
    {
        this.categories = categories;
    }

    public  void addCategory(Catagory c)
    {
        categories.add(c);
    }
    public void gen_dataset()
    {
        Catagory c1 = new Catagory();
        c1.setName("Electronics");
        c1.setId(1);
        addCategory(c1); // Thêm vào danh sách

        Catagory c2 = new Catagory();
        c2.setName("Clothing & Apparel");
        c2.setId(2);
        addCategory(c2); // Thêm vào danh sách

        Catagory c3 = new Catagory();
        c3.setName("Home Appliances");
        c3.setId(3);
        addCategory(c3); // Thêm vào danh sách

        Catagory c4 = new Catagory();
        c4.setName("Beauty & Personal Care");
        c4.setId(4);
        addCategory(c4); // Thêm vào danh sách

        Catagory c5 = new Catagory();
        c5.setName("Sports & Outdoors");
        c5.setId(5);
        addCategory(c5); // Thêm vào danh sách

    }
}
