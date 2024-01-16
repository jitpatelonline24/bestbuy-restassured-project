package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
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

    //      1. Extract the limit
    @Test
    public void test001() {

        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Extract the limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }

    //      2. Extract the total
    @Test
    public void test002() {

        int listOfIds = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the total : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }

    //      3. Extract the name of 5th store
    @Test
    public void test003() {

        String storeName = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Extract the name of 5th store is : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //      4. Extract the names of all the store
    @Test
    public void test004() {

        List<String> listOfStoreName = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the names of all the store : " + listOfStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    //      5. Extract the storeId of all the store
    @Test
    public void test005() {

        List<String> listOfStoreIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Extract the storeId of all the store: " + listOfStoreIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //      6. Print the size of the data list
    @Test
    public void test006() {

        List<String> listOfData = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data list is : " + listOfData.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //      7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {

        List<HashMap<String, Objects>> listOfStores = response.extract().path("data.findAll{it.name=='St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the value of the store where store name = St Cloud : " + listOfStores);
        System.out.println("------------------End of Test---------------------------");
    }

    //      8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {

        List<HashMap<String, Objects>> listOfStoreNames = response.extract().path("data.findAll{it.name=='Rochester'}.address");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get the address of the store where store name : " + listOfStoreNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //      9. Get all the services of 8th store
    @Test
    public void test009() {

        List<String> storesName = response.extract().path("data[7].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the services of 8th store : " + storesName);
        System.out.println("------------------End of Test---------------------------");
    }

    //      10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {

        HashMap<String, String> listOfStoreNames = response.extract().path("data[7].services[5].storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get storeservices of the store where service name = Windows Store : " + listOfStoreNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //      11. Get all the storeId of all the store
    @Test
    public void test011() {

        List<String> listOfStoreId = response.extract().path("data.findAll{it.services}.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get all the storeId of all the store : " + listOfStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //      12. Get id of all the store
    @Test
    public void test012() {

        List<String> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get id of all the store : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //      13. Find the store names Where state = ND
    @Test
    public void test013() {

        List<HashMap<String, Objects>> findStoreName = response.extract().path("data.findAll{it.state=='ND'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the store names Where state = ND: " + findStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    //      14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {

        List<HashMap<String, Objects>> listOfServices = response.extract().path("data.findAll{it.name=='Rochester'}.services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The first product name is : " + listOfServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //      15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {

        List<String> listOfServices = response.extract().path("data.find{it.services}.services.findAll{it.name =='Windows Store'}.storeservices.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the createdAt for all services whose name = “Windows Store” : " + listOfServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //      16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {

        List<String> listOfAllServices = response.extract().path("data.findAll{it.name=='Fargo'}.services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the name of all services Where store name = “Fargo” : " + listOfAllServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //      17. Find the zip of all the store
    @Test
    public void test017() {

        List<Integer> listOfZipCodes = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of all the store : " + listOfZipCodes);
        System.out.println("------------------End of Test---------------------------");
    }

    //      18. Find the zip of store name = Roseville
    @Test
    public void test018() {

        List<HashMap<String, Objects>> listOfZipStore = response.extract().path("data.findAll{it.name=='Roseville'}.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Find the zip of store name = Roseville : " + listOfZipStore);
        System.out.println("------------------End of Test---------------------------");
    }

    //      19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {

        List<String> nameofServices = response.extract().path("data.services*.findAll{it.name == 'Magnolia Home Theater'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("19. Find the storeservices details of the service name = Magnolia Home Theater : " + nameofServices);
        System.out.println("------------------End of Test---------------------------");
    }

    //      20. Find the lat of all the stores
    @Test
    public void test020() {

        List<String> latName = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The first product name is : " + latName);
        System.out.println("------------------End of Test---------------------------");
    }
}
