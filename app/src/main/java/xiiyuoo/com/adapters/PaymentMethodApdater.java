package xiiyuoo.com.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.List;

import xiiyuoo.com.k22411csampleproject.R;
import xiiyuoo.com.models.PaymentMethod;

public class PaymentMethodApdater extends ArrayAdapter<PaymentMethod> {
    Activity context;
    int resource;

    Typeface typeface;
    private List<PaymentMethod> paymentMethods;
    public PaymentMethodApdater(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
        typeface=Typeface.createFromAsset(this.context.getAssets(),"fonts/TMC-Ong Do.TTF");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View item=inflater.inflate(this.resource,null);
        TextView txtPaymentMethodName=item.findViewById(R.id.txtPaymentMethodName);
        TextView txtPaymentMethodDescription=item.findViewById(R.id.txtPaymentMethodDescription);

        PaymentMethod pm=getItem(position);
        txtPaymentMethodName.setText(pm.getName());
        txtPaymentMethodDescription.setText(pm.getDescription());

        txtPaymentMethodName.setTypeface(this.typeface);

        return item;
    }

    @Override
    public void addAll(Collection<? extends PaymentMethod> collection) {
        super.addAll(collection);
        paymentMethods.addAll(collection);
        notifyDataSetChanged();
    }

}
