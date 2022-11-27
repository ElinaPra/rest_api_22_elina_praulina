package finalapi.stepdefinitions;


import com.github.javafaker.Faker;
import finalapi.domain.Folder;
import finalapi.domain.List;
import finalapi.domain.Space;
import finalapi.domain.Task;
import finalapi.helpers.TestCaseContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;

import static finalapi.client.ClickUpClient.*;
import static finalapi.constants.ProjectConstants.SPACE_ID;
import static finalapi.constants.ProjectConstants.SPACE_NAME;

public class ClickUpSteps {
    @Given("Space exists and contains correct information")
    public void getSpaceAndCheckInfo() {
        Response response = getSpaceInfo(SPACE_ID);
        Space space = response.as(Space.class);

        Assertions.assertThat(space.getId())
                .as("We check id of the space is correct")
                .isEqualTo(SPACE_ID);

        Assertions.assertThat(space.getName())
                .as("We check name of the space is correct")
                .isEqualTo(SPACE_NAME);
    }

    @When("I create folder with name {string} and save")
    public void createAndSaveFolder(String folderName) {
        Response response = postFolder(folderName, SPACE_ID);
        Folder folder = response.as(Folder.class);

        Assertions.assertThat(folder.getName())
                .as("We check the name of the folder is correct")
                .isEqualTo(folderName);

        TestCaseContext.setFolder(folder);
    }

    @Then("I create a new List with name {string} in the Folder")
    public void createList(String listName) {
        Response response = createListFolder(listName, TestCaseContext.getFolder().getId());
        List list = response.as(List.class);

        TestCaseContext.setList(list);
    }

    @And("I verify that the List name is correct")
    public void verifyListNameCorrect() {
        Response response = verifyListName(TestCaseContext.getList().getId());
        List list = response.as(List.class);

        Assertions.assertThat(list.getName())
                .as("We check the List name is correct")
                .isEqualTo(TestCaseContext.getList().getName());
    }

    @Then("I create Task with unique name in the list")
    public void createTask(){
        Faker faker = new Faker();
        String taskName = faker.bothify("???????");

        Response response = createTaskInList(taskName, TestCaseContext.getList().getId());
        Task task = response.as(Task.class);

        TestCaseContext.setTask(task);
    }

    @And("I verify Task name is correct")
    public void verifyTaskName() {
        Response response = checkTaskName(TestCaseContext.getTask().getId());
        Task task = response.as(Task.class);

        Assertions.assertThat(task.getName())
                .as("We check the task name is correct")
                .isEqualTo(TestCaseContext.getTask().getName());
    }

    @Then("I remove the Task from the list")
    public void removeTheTask() {
        deleteTask(TestCaseContext.getTask().getId());
    }

    @And("I fetch the List and verify the Task can't be found there")
    public void fetchTheList() {
        Response response = verifyTaskCantBeFound(TestCaseContext.getTask().getId());

        Assertions.assertThat((response.statusCode()))
                .as("We check the task can not be found anywhere")
                .isEqualTo(404);
    }
}
