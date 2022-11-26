Feature: This feature tests ClickUp api

  Scenario: Create and delete a new task
    Given Space exists and contains correct information
    When I create folder with name "Folder name" and save
    Then I create a new List with name "List name" in the Folder
    And I verify that the List name is correct
    Then I create Task with unique name in the list
    And I verify Task name is correct
    Then I remove the Task from the list
    And I fetch the List and verify the Task can't be found there