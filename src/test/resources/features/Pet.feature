Feature: Pet Management in Petstore

	Scenario: Add a new pet
		Given I have details of a new pet
		When I send a request to add the pet
		Then the pet should be created successfully
		And the response should contain the name "doggie"


	#Method 1 - inline values
	Scenario: Find pet by status
		When I search for pets with status "available"
		Then I should get a list of available pets

	#Method 2 - Docstring
	Scenario: Add pet using full JSON body
		When I send request to add the following pet:
		"""
		{
			"id": 202,
			"name": "Bruno",
			"status": "available",
			"photoUrls": ["https://example.com/bruno.jpg"]
      	}
		"""
		Then the pet should be created successfully

	#Method 3 - datatable
	Scenario: Add a pet using datatable
		When I add a pet with following details:
		|id|303|
		|name|Charlie|
		|status|available|
		Then the pet should be created successfully