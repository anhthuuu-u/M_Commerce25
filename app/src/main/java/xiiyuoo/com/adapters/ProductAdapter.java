package xiiyuoo.com.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log; // Thêm import Log
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;  // Thêm Glide để tải hình ảnh

import java.net.URLEncoder;

import xiiyuoo.com.k22411csampleproject.R;
import xiiyuoo.com.models.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
    Activity context;
    int resource;

    public ProductAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource, null);
        ImageView imgProduct = item.findViewById(R.id.imgProduct);
        TextView txtProductId = item.findViewById(R.id.txtProductID);
        TextView txtProductName = item.findViewById(R.id.txtProductName);
        TextView txtProductQuantity = item.findViewById(R.id.txtProductQuantity);
        TextView txtProductPrice = item.findViewById(R.id.txtProductPrice);

        Product p = getItem(position);

        // Kiểm tra giá trị imageLink và tải hình ảnh bằng Glide
        String imageLink = p.getImageLink();
        Log.d("ImageLink", "Product ID: " + p.getId() + " ImageLink: " + imageLink);  // In ra giá trị của imageLink


        if (imageLink != null && !imageLink.isEmpty()) {
            Glide.with(context)
                    .load(imageLink)  // Lấy URL hình ảnh từ cơ sở dữ liệu
                    .into(imgProduct);  // Hiển thị hình ảnh vào ImageView
        } else {
            // Nếu không có link hình ảnh, có thể đặt hình ảnh mặc định
            imgProduct.setImageResource(R.mipmap.ic_product); // Thêm hình ảnh mặc định nếu cần
        }
//
//        String encodedImageLink = URLEncoder.encode(imageLink, "UTF-8");
//        Glide.with(context)
//                .load(encodedImageLink)
//                .into(imgProduct);



        // Gán các giá trị vào các TextView
        txtProductId.setText(String.valueOf(p.getId()));
        txtProductName.setText(p.getName());
        txtProductQuantity.setText(String.valueOf(p.getQuantity()));
        txtProductPrice.setText(p.getPrice() + "(VNĐ)");

        return item;
    }
}
