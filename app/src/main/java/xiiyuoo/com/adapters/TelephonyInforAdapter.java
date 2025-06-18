package xiiyuoo.com.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import xiiyuoo.com.k22411csampleproject.R;
import xiiyuoo.com.k22411csampleproject.SendSMSActivity;
import xiiyuoo.com.k22411csampleproject.TelephonyActivity;
import xiiyuoo.com.models.TelephonyInfor;

public class TelephonyInforAdapter extends ArrayAdapter<TelephonyInfor> {
    Activity context;
    int resource;

    public TelephonyInforAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);
        TextView txtTelephonyName = item.findViewById(R.id.txtTelephonyName);
        TextView txtTelephonyNumber = item.findViewById(R.id.txtTelephonyNumber);
        TextView txtCarrier = item.findViewById(R.id.txtCarrier);
        ImageView imgDirectCall = item.findViewById(R.id.imgCall);
        ImageView imgDialUp = item.findViewById(R.id.imgDialog);
        ImageView imgSendSms = item.findViewById(R.id.imgSendsms);

        TelephonyInfor ti = getItem(position);
        txtTelephonyName.setText(ti.getName());
        txtTelephonyNumber.setText(ti.getPhone());
        txtCarrier.setText(ti.getCarrier());

        imgDirectCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TelephonyActivity) context).directCall(ti);
            }
        });
        imgDialUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TelephonyActivity) context).dialupCall(ti);
            }
        });
        imgSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SendSMSActivity.class);
                intent.putExtra("TI", ti);
                context.startActivity(intent);
            }
        });
        return item;
    }
}