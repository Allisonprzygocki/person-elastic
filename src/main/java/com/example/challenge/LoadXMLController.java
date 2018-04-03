package com.example.challenge;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXB;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/person")
public class LoadXMLController {
	
	//method to to change xml to json, and load into elasticsearch index
	@RequestMapping(value="loadElasticsearch", method=RequestMethod.GET) 
    public @ResponseBody void getJson() throws IOException {
		
		RestHighLevelClient client = new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost("localhost", 9200, "http"),
		                new HttpHost("localhost", 9300, "http")));
		
		// xml to json
		String xmlFile = "src/main/resources/static/Persons.xml";
		People people = JAXB.unmarshal(new File(xmlFile), People.class);
		ObjectMapper mapper = new ObjectMapper();

		// iterator - loop through people json to load each person into elasticsearch
		Iterator<Person> iterator = people.getPeople().iterator();
		while (iterator.hasNext()) {

			// load docs into "persons" index
			IndexRequest request = new IndexRequest(
			        "persons", 
			        "doc");		
			
			String json = mapper.writeValueAsString(iterator.next());

			request.source(json, XContentType.JSON);
			client.index(request);

		}
		client.close();
    }
	
	
	@SuppressWarnings("resource")
	//method to search the "persons" index by field
	@RequestMapping(value = "getPerson", method=RequestMethod.GET)
	public @ResponseBody List<Person> getPerson(@RequestParam(value="employeeId", required=false) String employeeId,
			@RequestParam(value="name", required=false) String name,
			@RequestParam(value="active", required=false) String active,
			@RequestParam(value="phone", required=false) String phone,
			@RequestParam(value="longitude", required=false) String longitude,
			@RequestParam(value="latitude", required=false) String latitude,
			@RequestParam(value="zipcode", required=false) String zipcode,
			@RequestParam(value="city", required=false) String city,
			@RequestParam(value="streetAddress", required=false) String streetAddress,
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="age", required=false) String age
			) throws IOException {
		
	
		RestHighLevelClient client = new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost("localhost", 9200, "http"),
		                new HttpHost("localhost", 9300, "http")));
		
		// set up query builder - if no field is specified for search, bring back all records
		List<Person> personResult = new ArrayList<>();
		QueryBuilder matchQueryBuilder = QueryBuilders.matchAllQuery();
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
			searchSourceBuilder.size(200);

			// set up possible queries by field - if no field is specified, bring back all records
			if (employeeId != null) {
				matchQueryBuilder = QueryBuilders.matchQuery("EmployeeId", employeeId);
			}
			else if (name != null) {
				matchQueryBuilder = QueryBuilders.matchQuery("Name", name);
			}
			else if (active != null) {
				matchQueryBuilder = QueryBuilders.matchQuery("Active", active);
			}
			else if (phone != null) {
				matchQueryBuilder = QueryBuilders.matchQuery("Phone", phone).operator(Operator.AND);
			}
			else if (longitude != null) {
				matchQueryBuilder = QueryBuilders.matchQuery("Logitude", longitude);
			}
			else if (latitude != null) {
				matchQueryBuilder = QueryBuilders.matchQuery("Latitude", latitude);
			}
			else if (zipcode != null) {
				matchQueryBuilder = QueryBuilders.matchQuery("zipcode", zipcode);
			}
			else if (city != null) {
				matchQueryBuilder = QueryBuilders.matchQuery("City", city).operator(Operator.AND);
			}
			else if (streetAddress != null) {
				matchQueryBuilder = QueryBuilders.matchQuery("StreetAddress", streetAddress).operator(Operator.AND);
			}
			else if (email != null) {
				matchQueryBuilder = QueryBuilders.matchQuery("Email", email);
			}
			else if (age != null) {
				matchQueryBuilder = QueryBuilders.matchQuery("Age", age);
			}
			else{
				matchQueryBuilder = QueryBuilders.matchAllQuery();
			}

		searchSourceBuilder.query(matchQueryBuilder);
		
		// set up request to search the "persons" index that are of type "doc"
		SearchRequest searchRequest = new SearchRequest("persons");
		searchRequest.types("doc");
		searchRequest.source(searchSourceBuilder);
		
		// get the records that match the query specified and display the resulting records
		SearchResponse searchResponse = client.search(searchRequest);
		SearchHits hits = searchResponse.getHits();
			for (SearchHit hit : hits) {
				String sourceAsString = hit.getSourceAsString();
				ObjectMapper objectMapper = new ObjectMapper();
				try {
                    Person person = objectMapper.readValue(sourceAsString, Person.class);
                    personResult.add(person);
                } catch (Exception e) {
                    throw e;
                }
		}
		return personResult;
    }
}
