package xiiyuoo.com.models;

import java.io.Serializable;

public class Catagory implements Serializable {
    private  int id;
    private  String name;

    public Catagory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Catagory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cate "+ id + "\n" + name;
    }
}
