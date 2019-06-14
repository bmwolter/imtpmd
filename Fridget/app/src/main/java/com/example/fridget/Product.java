package com.example.fridget;

import com.google.gson.annotations.SerializedName;

public class Product {
    public int id;

    @SerializedName("product_naam")
    public String productNaam;

    @SerializedName("ean_code")
    public String eanCode;

    @SerializedName("merk_naam")
    public String merkNaam;

    public String getProductnaam(){

        return this.productNaam;
    }
    public String getEanCode(){

        return this.eanCode;
    }

    public String getMerkNaam(){
        return this.merkNaam;
    }
}
