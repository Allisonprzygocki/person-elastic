package com.example.challenge;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;

// pojos for list of "persons" records in people
@XmlAccessorType(XmlAccessType.FIELD)
public class People {

	@XmlElement(name = "Person")
	private List<Person> people = new ArrayList<Person>();

    public List<Person> getPeople()
    {
        return people;
    }

    public void setPeople(List<Person> people)
    {
        this.people = people;
    }

}
