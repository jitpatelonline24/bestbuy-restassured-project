package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        //RestAssured.basePath = "/stores";

        response = given()
                .when()
                .get("/stores")
                .then()
                .statusCode(200);
    }


//   1. Verify the if the total is equal to 1561
@Test
public void test001() {
    response.body("total",equalTo(1561));

}
//   2. Verify the if the stores of limit is equal to 10
@Test
public void test002() {
    List<Integer> total = response.extract().path("data.limit");
    Assert.assertEquals(total.size(), 10);
    System.out.println("Total records are: " + total.size());
}
//   3. Check the single ‘Name’ in the Array list (Inver Grove Heights)
@Test
public void test003(){
    response.body("data.name",hasItem("Inver Grove Heights"));
}
//   4. Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
@Test
public void test004(){
    response.body("data.name",hasItems("Roseville", "Burnsville", "Maplewood"));
}
//   5. Verify the storied=7 inside storeservices of the third store of second services
@Test
public void test005(){
    response.body("data[2].services[3].storeservices.storeId" , equalTo(7));
}
//   6. Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
@Test
public void test006(){
    List<HashMap<String, Objects>> listOfValues = response.extract().path("data.findAll{it.name=='Roseville'}.createdAt");
    System.out.println("hash map values: store name = Roseville "+listOfValues);

}
//   7. Verify the state = MN of forth store
@Test
public void test007(){
    response.body("data[3].state",equalTo("MN"));
}
//   8. Verify the store name = Rochester of 9th store
@Test
public void test008(){
    response.body("data[8].name" ,equalTo("Rochester"));
}
//   9. Verify the storeId = 11 for the 6th store
@Test
public void test009(){
    response.body("data[5].services[4].storeservices.storeId" , equalTo(11));;
}
//   10. Verify the serviceId = 4 for the 7th store of forth service
@Test
public void test010(){
    response.body("data[6].services[3].storeservices.serviceId" ,equalTo(4) );}
}
