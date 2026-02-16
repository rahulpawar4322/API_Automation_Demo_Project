Feature: GoRest User API Automation

  Scenario: Create user with valid data
    Given user prepares valid user payload
    When user sends POST request to create user
    Then response status should be 201

  Scenario: Get created user
    When user sends GET request for created user
    Then response status should be 200

  Scenario: Update user details
    When user sends PUT request to update user
    Then response status should be 200

  Scenario: Delete user
    When user sends DELETE request
    Then response status should be 204

  #Scenario: Create user with invalid email
    #Given user prepares invalid user payload
    #When user sends POST request to create user
    #Then response status should be 422
