PERSON-ELASTIC
The person-elastic project takes a Persons.xml file, and changes it into JSON using jackson.  It then uploads each record into an 
elasticsearch index called "persons".  Once the records are in the index, you can search by the fields in a record.  This project is set
up to run on localhost:9003.


CLASSES
The Person.java class contains pojos for all the fields in a record.
The People.java class contains pojos for the list of persons records.
The LoadXMLController.java class contains two methods: one to load an elasticsearch index ("persons") with records, and one to search the
the index for records by field.


ENDPOINTS
The Persons.xml file must be in the "src/main/resources/static/Perons.xml" location to run.  To load documents into the "persons" index,
run elasticsearch and run the LoadXMLController.  Then enter the following in a browser:
	localhost:9003/person/loadElasticsearch
	
If the records were successfully uploaded, you can search on any of the fields in a given record.  Fields should be entered in camel
case.  If no fields are specified, or are entered incorrectly, all records will return.  Examples of searching for records in
the "persons" index:
	localhost:9003/person/getPerson?city=san diego
	localhost:9003/person/getPerson?age=36&name=connor
	localhost:9003/person/getPerson?streetAddress=5932 keene
	

SOFTWARE
ELASTICSEARCH version 6.2.2
SPRINGBOOT version 1.5.10
JACKSON version 2.8.10