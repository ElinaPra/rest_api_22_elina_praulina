package finalapi.stepdefinitions;

import finalapi.helpers.TestCaseContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static finalapi.client.ClickUpClient.deleteFolder;

public class Hooks {
    @Before
    public void beforeHook() {
        TestCaseContext.init();
        System.out.println("The scenario has started!");
    }

    @After
    public void afterHook() {
        deleteFolder(TestCaseContext.getFolder().getId());
        System.out.println("The scenario has ended!");
    }
}