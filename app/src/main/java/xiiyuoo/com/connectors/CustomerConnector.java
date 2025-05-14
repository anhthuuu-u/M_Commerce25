package xiiyuoo.com.connectors;

import java.util.ArrayList;

import xiiyuoo.com.models.Customer;
import xiiyuoo.com.models.ListCustomer;

public class CustomerConnector {
    ListCustomer listCustomer;
    public CustomerConnector()
    {
        listCustomer=new ListCustomer();
        listCustomer.generate_sample_dataset();;
    }
    public ArrayList<Customer> get_all_customers()
    {
        if(listCustomer==null)
        {
            listCustomer=new ListCustomer();
            listCustomer.generate_sample_dataset();
        }
        return listCustomer.getCustomers();
    }
    public ArrayList<Customer> get_customer_by_provider(String provider)
    {
        if(listCustomer==null)
        {
            listCustomer=new ListCustomer();
            listCustomer.generate_sample_dataset();
        }
        ArrayList<Customer>results=new ArrayList<>();
        for (Customer c: listCustomer.getCustomers())
        {
            if(c.getPhone().startsWith(provider))
            {
                results.add(c);
            }
        }
        return results;
    }
}
