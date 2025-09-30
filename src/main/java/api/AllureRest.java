package api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.Filter;

public final class AllureRest {
    private AllureRest() {}
    public static Filter filter() {
        return new AllureRestAssured()
                .setRequestTemplate("http-request.ftl")
                .setResponseTemplate("http-response.ftl");
    }
}