package com.mt.entity;


import java.io.Serializable;
import java.util.Objects;

public class SizeProductKey implements Serializable {
	private static final long serialVersionUID = 1L;
    private String Productid;
    private String Sizeid;

    // Constructor mặc định
    public SizeProductKey() {}

    public SizeProductKey(String Productid, String Sizeid) {
        this.Productid = Productid;
        this.Sizeid = Sizeid;
    }

    // Getter và Setter
    public String getProductid() {
        return Productid;
    }

    public void setProductid(String Productid) {
        this.Productid = Productid;
    }

    public String getSizeid() {
        return Sizeid;
    }

    public void setSizeid(String Sizeid) {
        this.Sizeid = Sizeid;
    }

    // Phương thức equals và hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeProductKey that = (SizeProductKey) o;
        return Objects.equals(Productid, that.Productid) &&
                Objects.equals(Sizeid, that.Sizeid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Productid, Sizeid);
    }
}
