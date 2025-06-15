package xiiyuoo.com.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import xiiyuoo.com.models.PaymentMethod;

public class PaymentMethodConnector {

    public ArrayList<PaymentMethod> getAllPaymentMethods(SQLiteDatabase database) {
        ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM PaymentMethod", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            PaymentMethod p = new PaymentMethod();
            p.setId(id);
            p.setName(name);
            p.setDescription(description);
            paymentMethods.add(p);
        }
        cursor.close();
        return paymentMethods;
    }
}