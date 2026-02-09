package api.specs;

import api.helpers.CustomAllureListener;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class QASESpec {

    public static final RequestSpecification REQ_SPEC = with()

            .baseUri("https://api.qase.io/v1")
            .filter(CustomAllureListener.withCustomTemplates())
            .log().uri()
            .contentType(JSON)
            .header("Token", "8dfccc56ea78cbd18936fa297cdc0155731cb3ef8bc2fe6ce9a6838f32ac85f7");

    public static final ResponseSpecification RES_SPEC = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification responseWithStatusCode(int statusCode) {
        return new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectStatusCode(statusCode)
                .build();
    }
}
