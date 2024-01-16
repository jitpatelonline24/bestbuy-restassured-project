package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        //RestAssured.basePath = "/stores";

        response = given()
                .when()
                .get("/products")
                .then()
                .statusCode(200);
    }
    //  21.    Extract the limit
    @Test
    public void test001() {

        int theLimit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Extract the limit is : " + theLimit);
        System.out.println("------------------End of Test---------------------------");
    }
//  22. Extract the total
@Test
public void test002() {

    int theTotal = response.extract().path("total");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Extract the total is : " + theTotal);
    System.out.println("------------------End of Test---------------------------");
}
//  23. Extract the name of 5th product
@Test
public void test003() {

    String nameOfProducts = response.extract().path("data[4].name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Extract the name of 5th product : " + nameOfProducts);
    System.out.println("------------------End of Test---------------------------");
}
//  24. Extract the names of all the products
@Test
public void test004() {

    List<String> productOfAllItems = response.extract().path("data.name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Extract the names of all the products : " + productOfAllItems);
    System.out.println("------------------End of Test---------------------------");
}
//  25. Extract the productId of all the products
@Test
public void test005() {

    List<Integer> productIdOfAllProducts = response.extract().path("data.id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("The Extract the productId of all the products : " + productIdOfAllProducts);
    System.out.println("------------------End of Test---------------------------");
}
//  26. Print the size of the data list
@Test
public void test006() {

    List<String> listOfData = response.extract().path("data");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Print the size of the data list : " + listOfData.size());
    System.out.println("------------------End of Test---------------------------");
}
//  27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
@Test
public void test007() {

    List<String> listOfAllValues =  response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all the value of the product where product name : " + listOfAllValues);
    System.out.println("------------------End of Test---------------------------");
}
//  28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
@Test
public void test008() {

    List<String> modelOfProduct =  response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("et the model of the product where product name : " + modelOfProduct);
    System.out.println("------------------End of Test---------------------------");
}
//  29. Get all the categories of 8th products
@Test
public void test009() {

    List<String> allProductsCategories = response.extract().path("data[7].categories");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all the categories of 8th products : " + allProductsCategories);
    System.out.println("------------------End of Test---------------------------");
}
//  30. Get categories of the store where product id = 150115
@Test
public void test010() {

    List<String> storedProductId = response.extract().path("data[7].categories");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get categories of the store where product id : " + storedProductId);
    System.out.println("------------------End of Test---------------------------");
}
//  31. Get all the descriptions of all the products
@Test
public void test011() {

    List<String> describedAllProducts = response.extract().path("data.description");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get all the descriptions of all the products : " + describedAllProducts);
    System.out.println("------------------End of Test---------------------------");
}
//  32. Get id of all the all categories of all the products
@Test
public void test012() {

    List<String> listOfIds = response.extract().path("data.id");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Get id of all the all categories of all the products : " + listOfIds);
    System.out.println("------------------End of Test---------------------------");
}
//  33. Find the product names Where type = HardGood
@Test
public void test013() {

    List<String> productNames = response.extract().path("data.findAll{it.type == 'HardGood'}.name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the product names Where type = HardGood : " + productNames);
    System.out.println("------------------End of Test---------------------------");
}
//  34. Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
@Test
public void test014() {

    List<String> categoriesOfProduct = response.extract().path("data[1].categories");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the Total number of categories for the product : " + categoriesOfProduct);
    System.out.println("------------------End of Test---------------------------");
}
//  35. Find the createdAt for all products whose price < 5.49
@Test
public void test015() {

    List<String> createdAtForAllProducts = response.extract().path("data.findAll{it.price < 5.49}.findAll{it.createdAt}.createdAt");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the createdAt for all products whose price < 5.49 : " + createdAtForAllProducts);
    System.out.println("------------------End of Test---------------------------");
}
//  36. Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
@Test
public void test016() {

    List<String> nameOfAllCategories = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the name of all categories Where product name : " + nameOfAllCategories);
    System.out.println("------------------End of Test---------------------------");
}
//  37. Find the manufacturer of all the products
@Test
public void test017() {

    List<String> manufacturerOfAllProducts = response.extract().path("data.manufacturer");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the manufacturer of all the products : " + manufacturerOfAllProducts);
    System.out.println("------------------End of Test---------------------------");
}
//  38. Find the imge of products whose manufacturer is = Energizer
@Test
public void test018() {

    List<String> listOfTitles = response.extract().path("data.limit");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the imge of products whose manufacturer is = Energizer : " + listOfTitles);
    System.out.println("------------------End of Test---------------------------");
}
//  39. Find the createdAt for all categories products whose price > 5.99
@Test
public void test019() {

    List<String> imageOfAllProducts =  response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the createdAt for all categories products whose price > 5.99 : " + imageOfAllProducts);
    System.out.println("------------------End of Test---------------------------");
}
//  40. Find the uri of all the products
@Test
public void test020() {

    List<String> listOfUrl = response.extract().path("data.url");

    System.out.println("------------------StartingTest---------------------------");
    System.out.println("Find the uri of all the products : " + listOfUrl);
    System.out.println("------------------End of Test---------------------------");
}
}
