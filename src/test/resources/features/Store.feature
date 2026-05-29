Feature: Store Management

  Scenario: Place an order for pet
    Given I have details of pet
    When I send a request to place order for the pet
    Then order should be created successfully with 10

  Scenario: Find purchase order by ID
    When I send request to find the order by 10
    Then I should see the respective order details

  Scenario: Delete order by orderID
    When I send request to delete the order by 10
    Then the order should get deleted