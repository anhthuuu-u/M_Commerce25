package xiiyuoo.com.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import xiiyuoo.com.k22411csampleproject.R;
import xiiyuoo.com.models.OrderDetailsViewer;

public class OrderDetailsAdapter extends ArrayAdapter<OrderDetailsViewer> {
    Activity context;
    int resource;

    public OrderDetailsAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);

        TextView txtShowProductName = item.findViewById(R.id.txtShowProductName);
        TextView txtShowQuantity = item.findViewById(R.id.txtShowQuantity);
        TextView txtShowPrice = item.findViewById(R.id.txtShowPrice);
        TextView txtShowDiscount = item.findViewById(R.id.txtShowDiscount);
        TextView txtShowVAT = item.findViewById(R.id.txtShowVAT);
        TextView txtShowTotalValue = item.findViewById(R.id.txtShowTotalValue);

        OrderDetailsViewer od = getItem(position);
        txtShowProductName.setText(od.getProductName());
        txtShowQuantity.setText(String.valueOf(od.getQuantity()));
        txtShowPrice.setText(String.format("%.2f VNĐ", od.getPrice()));
        txtShowDiscount.setText(String.format("%.2f%%", od.getDiscount()));
        txtShowVAT.setText(String.format("%.2f%%", od.getVAT()));
        txtShowTotalValue.setText(String.format("%.2f VNĐ", od.getTotalValue()));

        return item;
    }
}