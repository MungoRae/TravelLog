Feature:  Can add a travel log
  As a user I want to be able to log my travels

  Scenario: Enter app click add button, get to screen
    Given The app has started
    When I press the Add button
    Then I see the Add Travel screen

  Scenario: I can enter a date for the travel log
    Given I have got to the Add Travel screen
    When I press the current date
    Then I can select the date I want from a calendar

  Scenario: New travel log gets added to list
    Given I have got to the Add Travel screen
    When I enter a travel name, and description and press Submit
    Then I see the travel list
    And I see my travel item