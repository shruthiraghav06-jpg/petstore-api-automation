Feature: Authenticated Pet Management

  # Pattern 1 — API Key Auth
  Scenario: Add a pet using API key authentication
    Given I have a valid API key
    When I add a pet with name "Bruno" and status "available"
    Then the pet should be added successfully

  # Pattern 2 — Bearer Token Auth (Login first)
  Scenario: Add a pet after logging in
    Given I login with username "user1" and password "user1"
    And I should be logged in successfully
    When I add a pet with name "Rocky" and status "pending"
    Then the pet should be added successfully