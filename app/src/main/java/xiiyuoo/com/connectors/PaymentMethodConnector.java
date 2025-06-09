package xiiyuoo.com.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import xiiyuoo.com.models.ListPaymentMethod;
import xiiyuoo.com.models.PaymentMethod;

public class PaymentMethodConnector {

    public ListPaymentMethod getAllPaymentMethods(SQLiteDatabase database) {
        ListPaymentMethod paymentmethods = new ListPaymentMethod();
        Cursor cursor = database.rawQuery("SELECT * FROM PaymentMethod", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            PaymentMethod p = new PaymentMethod();
            p.setId(id);
            p.setName(name);
            p.setDescription(description);
            paymentmethods.getPaymentMethods().add(p); // Thêm payment method vào danh sách
        }
        cursor.close();
        return paymentmethods;
    }
}
