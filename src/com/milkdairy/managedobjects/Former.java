package com.milkdairy.managedobjects;

public class Former {
	
private String name;
private String id;
private String email;
private String phoneNum;
private String imageUrl="";
private String address;
private String startdate;
private String enddate="";


public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public String getPhoneNum() {
	return phoneNum;
}
public void setPhoneNum(String phoneNum) {
	this.phoneNum = phoneNum;
}
public String getStartdate() {
	return startdate;
}
public void setStartdate(String startdate) {
	this.startdate = startdate;
}
public String getEnddate() {
	return enddate;
}
public void setEnddate(String enddate) {
	this.enddate = enddate;
}
private Milk milk;
public Milk getMilk() {
	return milk;
}
public void setMilk(Milk milk) {
	this.milk = milk;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getImageUrl() {
	return imageUrl;
}
public void setImageUrl(String imageUrl) {
	this.imageUrl = imageUrl;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}


@Override
public String toString(){
	
	return id+","+name+","+startdate+","+enddate+","+address+","+phoneNum+","+imageUrl;
}


}
