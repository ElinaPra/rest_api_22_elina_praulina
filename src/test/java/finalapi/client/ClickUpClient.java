package finalapi.client;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static finalapi.constants.ProjectConstants.API_TOKEN;

public class ClickUpClient {
    public static Response getSpaceInfo(String spaceId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .queryParam("archived", false)
                .header("Authorization", API_TOKEN)
                .when()
                .get("https://api.clickup.com/api/v2/space/" + spaceId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response postFolder(String folderName, String spaceId) {

        JSONObject payload = new JSONObject();
        payload.put("name", folderName);

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .body(payload.toString())
                .when()
                .post("https://api.clickup.com/api/v2/space/" + spaceId + "/folder")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createListFolder(String listName, String folderId) {

        JSONObject payload = new JSONObject();
        payload.put("name", listName);

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .body(payload.toString())
                .when()
                .post("https://api.clickup.com/api/v2/folder/" + folderId + "/list")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response verifyListName(String listId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .get("https://api.clickup.com/api/v2/list/" + listId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createTaskInList(String taskName, String listId) {

        JSONObject payload = new JSONObject();
        payload.put("name", taskName);

        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .body(payload.toString())
                .when()
                .post("https://api.clickup.com/api/v2/list/" + listId + "/task")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response checkTaskName(String taskId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .get("https://api.clickup.com/api/v2/task/" + taskId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response deleteTask(String taskId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .delete("https://api.clickup.com/api/v2/task/" + taskId)
                .then().log().all()
                .statusCode(204)
                .extract().response();
    }

    public static Response verifyTaskCantBeFound(String taskId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .get("https://api.clickup.com/api/v2/task/" + taskId)
                .then().log().all()
                .statusCode(404)
                .extract().response();
    }

    public static Response deleteFolder(String folderId) {
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN)
                .when()
                .delete("https://api.clickup.com/api/v2/folder/" + folderId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }
}
