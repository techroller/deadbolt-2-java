package be.objectify.deadbolt.java.test.controllers.subject;

import be.objectify.deadbolt.java.test.DataLoader;
import com.google.common.collect.ImmutableMap;
import com.jayway.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

public class SubjectPresentControllerConstraintsTest
{

    private static final int PORT = 3333;

    @Before
    public void setUp()
    {
        RestAssured.port = PORT;
    }

    @Test
    public void testSubjectMustBePresent_noSubjectIsPresent()
    {
        running(testServer(PORT,
                           fakeApplication(new ImmutableMap.Builder<String, String>().put("deadbolt.java.handlers.defaultHandler",
                                                                                          "be.objectify.deadbolt.java.test.security.TestDeadboltHandler")
                                                                                     .build())),
                () -> {
                    RestAssured.given()
                               .cookie("user", "greet")
                               .expect()
                               .statusCode(401)
                               .when()
                               .get("/subject/present/c/subjectMustBePresent");
                });
    }

    @Test
    public void testSubjectMustBePresent_unrestricted_noSubjectIsPresent()
    {
        running(testServer(PORT,
                           fakeApplication(new ImmutableMap.Builder<String, String>().put("deadbolt.java.handlers.defaultHandler",
                                                                                          "be.objectify.deadbolt.java.test.security.TestDeadboltHandler")
                                                                                     .build())),
                () -> {
                    RestAssured.given()
                               .cookie("user", "greet")
                               .expect()
                               .statusCode(200)
                               .when()
                               .get("/subject/present/c/subjectMustBePresent/open");
                });
    }

    @Test
    public void testSubjectMustBePresent_subjectIsPresent()
    {
        running(testServer(PORT,
                           fakeApplication(new ImmutableMap.Builder<String, String>().put("deadbolt.java.handlers.defaultHandler",
                                                                                          "be.objectify.deadbolt.java.test.security.TestDeadboltHandler")
                                                                                     .build(),
                                           new DataLoader("/be/objectify/deadbolt/java/test/standard.xml"))),
                () -> {
                    RestAssured.given()
                               .cookie("user", "greet")
                               .expect()
                               .statusCode(200)
                               .when()
                               .get("/subject/present/c/subjectMustBePresent");
                });
    }

    @Test
    public void testSubjectMustBePresent_unrestricted_subjectIsPresent()
    {
        running(testServer(PORT,
                           fakeApplication(new ImmutableMap.Builder<String, String>().put("deadbolt.java.handlers.defaultHandler",
                                                                                          "be.objectify.deadbolt.java.test.security.TestDeadboltHandler")
                                                                                     .build(),
                                new DataLoader("/be/objectify/deadbolt/java/test/standard.xml"))),
                () -> {
                    RestAssured.given()
                            .cookie("user", "greet")
                            .expect()
                            .statusCode(200)
                            .when()
                            .get("/subject/present/c/subjectMustBePresent/open");
                });
    }
}