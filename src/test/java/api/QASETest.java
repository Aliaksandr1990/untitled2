package api;

import api.model.request.ProjectModel;
import api.model.response.ProjectResponseModel;
import api.model.response.Result;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static api.helpers.CustomAllureListener.withCustomTemplates;
import static api.specs.QASESpec.*;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QASETest {

    @Test
    void jdwjdwd() {

        ProjectModel projectModel = new ProjectModel();
        projectModel.setTitle("dwdwd");
        projectModel.setCode("sdhd");

        ProjectResponseModel response = given()
                .spec(REQ_SPEC)
                .body(projectModel)
                .post("/project")
                .then().spec(responseWithStatusCode(200))
                .extract().as(ProjectResponseModel.class);

        assertThat(response)
                .isNotNull()
                .extracting(ProjectResponseModel::getResult)
                .extracting(Result::getCode)
                .isEqualTo(projectModel.getCode());


    }
}
