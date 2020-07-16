package com.example.closeuser.Models;

public class CardDetailsModel {

    String bankName;
    String cardNumber;
    String ex_month;
    String ex_year;
    String holderName;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getEx_month() {
        return ex_month;
    }

    public void setEx_month(String ex_month) {
        this.ex_month = ex_month;
    }

    public String getEx_year() {
        return ex_year;
    }

    public void setEx_year(String ex_year) {
        this.ex_year = ex_year;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
}
