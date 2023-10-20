Feature:  Can add a travel log
  As a user I want to be able to log my travels

  Scenario: The user can get to the Add Travel screen
    Given The app has started
    When I press the Add button
    Then I see the Add Travel screen

  Scenario: I can enter a date for the travel log
    Given I have got to the Add Travel screen
    When I press the date field
    And I select the date yesterday
    Then I can see the date displayed as text

  Scenario: I can add images to my travel log
    Given I have got to the Add Travel screen
    When I press the add photo button
    And I select a photo
    Then I see the photo on the screen

  Scenario: New travel log gets added to list
    Given I have got to the Add Travel screen
    When I enter a travel name, and description and press Submit
    Then I see the travel list
    And I see my travel item