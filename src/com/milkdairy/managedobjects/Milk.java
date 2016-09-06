package com.milkdairy.managedobjects;

public class Milk {
 private String name;
 private String quantity;
 private String rate;
 private double perPrice;
 private String padValue;
 private String date;
 private String formerID;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getQuantity() {
	return quantity;
}
public void setQuantity(String quantity) {
	this.quantity = quantity;
}
public String getRate() {
	return rate;
}
public void setRate(String rate) {
	this.rate = rate;
}
public double getPerPrice() {
	return perPrice;
}
public void setPerPrice(double perPrice) {
	this.perPrice = perPrice;
}
public String getPadValue() {
	return padValue;
}
public void setPadValue(String padValue) {
	this.padValue = padValue;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getFormerID() {
	return formerID;
}
public void setFormerID(String formerID) {
	this.formerID = formerID;
}

@Override
public String toString(){
	return formerID.concat(",").concat(name).concat(",").concat(quantity).concat(",").concat(rate).concat(",")
			.concat(padValue).concat(",").concat(date);
}
}
