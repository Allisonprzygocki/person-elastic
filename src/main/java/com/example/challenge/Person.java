package com.example.challenge;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;

import com.fasterxml.jackson.annotation.JsonProperty;

// pojos for each field in a Person record
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
	//@XmlAttribute
	@XmlElement(name = "EmployeeId")
    private String employeeId;
	@XmlElement(name = "Name")
    private String name;
	@XmlElement(name = "Active")
    private boolean active;
	@XmlElement(name = "Phone")
    private String phone;
	@XmlElement(name = "Logitude")
    private float longitude;
	@XmlElement(name = "Latitude")
    private float latitude;
	@XmlElement(name = "zipcode")
    private int zipcode;
    @XmlElement(name = "City")
    private String city;
    @XmlElement(name = "StreetAddress")
    private String streetAddress;
    @XmlElement(name = "Email")
    private String email;
    @XmlElement(name = "Age")
    private int age;

    @JsonProperty("EmployeeId")
    public String getEmployeeId()
    {
        return employeeId;
    }

    @JsonProperty("EmployeeId")
    public void setEmployeeId(String employeeId)
    {
        this.employeeId = employeeId;
    }

    @JsonProperty("Name")
    public String getName()
    {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name)
    {
        this.name = name;
    }

    @JsonProperty("Active")
    public boolean getActive()
    {
        return active;
    }

    @JsonProperty("Active")
    public void setActive(boolean active)
    {
        this.active = active;
    }

    @JsonProperty("Phone")
    public String getPhone()
    {
        return phone;
    }
    
    @JsonProperty("Phone")
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @JsonProperty("Logitude")
    public float getLongitude()
    {
        return longitude;
    }

    @JsonProperty("Logitude")
    public void setLogitude(float longitude)
    {
        this.longitude = longitude;
    }

    @JsonProperty("Latitude")
    public float getLatitude()
    {
        return latitude;
    }

    @JsonProperty("Latitude")
    public void setLatitude(float latitude)
    {
        this.latitude = latitude;
    }

    @JsonProperty("zipcode")
    public int getZipcode()
    {
        return zipcode;
    }

    @JsonProperty("zipcode")
    public void setZipcode(int zipcode)
    {
        this.zipcode = zipcode;
    }
    
    @JsonProperty("City")
    public String getCity()
    {
        return city;
    }

    @JsonProperty("City")
    public void setCity(String city)
    {
        this.city = city;
    }
    
    @JsonProperty("StreetAddress")
    public String getStreetAddress()
    {
        return streetAddress;
    }

    @JsonProperty("StreetAddress")
    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }
    
    @JsonProperty("Email")
    public String getEmail()
    {
        return email;
    }

    @JsonProperty("Email")
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    @JsonProperty("Age")
    public int getAge()
    {
        return age;
    }

    @JsonProperty("Age")
    public void setAge(int age)
    {
        this.age = age;
    }

}
