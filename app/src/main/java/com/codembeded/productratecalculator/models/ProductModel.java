package com.codembeded.productratecalculator.models;

public class ProductModel {
    int productId;
    String  productName;
    double weight,expense;

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    public ProductModel(int productId, String productName, double weight, double expense) {
        this.productId = productId;
        this.productName = productName;
        this.weight = weight;
        this.expense = expense;

    }

    public ProductModel(String productName, double weight, double expense, double perGramPrice) {
        this.productName = productName;
        this.weight = weight;
        this.expense = expense;
        this.perGramPrice = perGramPrice;
    }

    public double getPerGramPrice() {
        return perGramPrice;
    }

    public void setPerGramPrice(double perGramPrice) {
        this.perGramPrice = perGramPrice;
    }

    public ProductModel(String productName, double perGramPrice) {
        this.productName = productName;
        this.perGramPrice = perGramPrice;
    }

    double perGramPrice;

    public ProductModel() {
    }

    public ProductModel(int productId, String productName, double weight) {
        this.productId = productId;
        this.productName = productName;
        this.weight = weight;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
