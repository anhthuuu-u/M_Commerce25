package xiiyuoo.com.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class TelephonyInfor implements Serializable {
    private String name;
    private String phone;
    private String carrier;

    public TelephonyInfor() {
    }

    public TelephonyInfor(String name, String phone, String carrier) {
        this.name = name;
        this.phone = phone;
        this.carrier = carrier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @NonNull
    @Override
    public String toString() {
        return this.name + "\n" + this.phone + "\n" + this.carrier;
    }
}