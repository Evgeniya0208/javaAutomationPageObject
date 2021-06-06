package com.customertimes.model;

public class Product {
    private String name;
    private String price;
    private String picture;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public static Product.Builder newBuilder() {
        return new Builder();
    }

    public Product() {
    }

    private Product(final Builder builder) {
        name = builder.name;
        price = builder.price;
        picture = builder.picture;
    }

    public static final class Builder {
        private String name;
        private String price;
        private String picture;

        private Builder() {

        }

        public Builder withName(final String val) {
            name = val;
            return this;
        }

        public Builder withPrice(final String val) {
            price = val;
            return this;
        }

        public Builder withPicture(final String val) {
            picture = val;
            return this;
        }

        public Product build() { return new Product(this);}
    }
}
