## rest-example  #
Project to demonstrate knowledge of developing and testing a ReST API in Java.

Live Example: http://api.pncie.io

### Specifications
- A microservice that stores and shuffles card decks.
- A card may be represented as a simple string such as “5-heart”, or “K-spade”.
- A deck is an ordered list of 52 standard playing cards.
- Expose a RESTful interface that allows a user to:
	- PUT an idempotent request for the creation of a new named deck.  New decks are created in some initial sorted order.
	- POST a request to shuffle an existing named deck.
	- GET a list of the current decks persisted in the service.
	- GET a named deck in its current sorted/shuffled order.
	- DELETE a named deck.
- Two shuffle algorithms, one random shuffle, the other that simulates "hand shuffle" by splitting the deck and interleaving cards multiple time.
- Deck state is persisted in memory by default. This can be changed to persisted in most any RDBMS via JDBC.
- Persistence method and shuffle algorithm are configurable at deploy time via application properties.  

###Implementation
This application was built with Spring framework as the foundation. The build is managed by Gradle. The distributable artifact is a Spring Boot application the can run stand-alone or can be deployed to most any J2EE compliant application server or cloud container.

### REST Endpoints

- Create Card Deck 
	- **http://localhost/api/decks/**		
		- HTTP Method: PUT / Status Code: 201
		- Input: application/x-www-form-urlencoded (deckName=Name+of+Deck)
		- Returns: JSON {deckName: "Name of Deck" {cards: ["A-Spades","2-Spades",...]}}
- Shuffle Deck
	- **http://localhost/api/decks/shuffle**  
		- HTTP Method: POST / Status Code: 200
		- Input: application/x-www-form-urlencoded (deckName=Name+of+Deck)
		- Returns: JSON {deckName: "Name of Deck" {cards: ["J-Diamonds","3-Clubs",...]}}
		- If deck not found in the database an empty JSON body is returned 
- Retrieve List of Decks
	- **http://localhost/api/decks/**		
		- HTTP Method: GET / Status Code: 200
		- Input: N/A
		- Returns: JSON [{"deckName":"another-test-deck"},{"deckName":"test-deck"}]
- Retrieve a Single Deck
	- **http://localhost/api/decks/Name+of+Deck**		
		- HTTP Method: GET / Status Code: 200
		- Input: N/A
		- Returns: Current deck state as JSON {deckName: "Name of Deck" {cards: ["A-Spades","2-Spades",...]}}
		- If deck not found in the database an empty JSON body is returned 
- Remove a Single Deck
	- **http://localhost/api/decks/Name+of+Deck**		
		- HTTP Method: DELETE / Status Code: 204
		- Input: N/A
		- Returns: Current deck state as JSON {deckName: "Name of Deck" {cards: ["A-Spades","2-Spades",...]}}

### Configuration
The configuration files are contained in the */resources* folders under the *main/* and *test/* folders. The *applications.properties* file is used solely to  configure logging. All other configurations are in the *application.yml* file. XML configuration files have been avoided at all costs. There are two Spring Configuration classes in the code base. These are used to initialize the proper Shuffler bean at boot-time.

Obviously, since these configuration files are in the */resources* folder, they are rolled into the WAR file at build time. The implecation is if any configuration changes are desired, such as selecting a different shuffler or data source, this requires a new build. If it is desired, the configuration can be externalized. By placing the configuration files (i.e. *application.properties* and *application.yml*) in a folder named *config/* located in the ROOT context of the application, the Spring framework will recognize this as the place to retrieve configurations at boot-time.