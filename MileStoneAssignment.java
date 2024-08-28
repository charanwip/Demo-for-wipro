package Assignment;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class MileStoneAssignment {
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://reqres.in/api";
		
	}
@Test(priority=1)
	public void GetAllBooks() {
		RestAssured.given()
		            .when()
		            .get("/users")
		            .then()
		            .statusCode(200)
		            .body("page", equalTo(1))
		            .body("data", notNullValue())
		            .log().all();
	}
    @Test(priority=2)
    public void GetOneBook() {
    	RestAssured.given()
    	           .pathParam("id", 2)
    	           .when()
    	           .get("/users/{id}")
    	           .then()
    	           .statusCode(200)
    	           .body("data.id" , equalTo(2))
    	           .body("data.email", equalTo("janet.weaver@reqres.in"))
    	           .body("data.first_name", equalTo("Janet"))
    	           .body("data.last_name", equalTo("Weaver"));
    	
    	           
    }
   @Test(priority=3)
   public void Post() {
	   String requestBody = "{\"name\": \"john\":,\"job\":\"Developer\"}";
	   
	   RestAssured.given()
	              .body(requestBody)
	              .when()
	              .post("/user/")
	              .then()
	              .statusCode(201);
	   
	              
	              
	   }
   
   @Test(priority=4)
   public void UpdateBook() {
	   String requestBody = "{\"name\": \"jhon\", \"job\": \"Senior Developer\"}";
	   RestAssured.given()
	              .pathParam("id", 2)
	              .body(requestBody)
	              .when()
	              .then()
	              .statusCode(200);
   }
   @Test(priority=5)
   public void PatchBook() {
	   String requestBody = "{\"job\": \"Lead Developer\"}";
	   RestAssured.given()
	              .pathParam("id", 2)
	              .body(requestBody)
	              .patch("/users/{id}")
	              .then()
	              .statusCode(200);
	   
			   
   }
   @Test(priority=6)
   public void DeleteBook() {
	   RestAssured.given()
	              .pathParam("id",2)
	              .when()
	              .delete("/users/{id}")
	              .then()
	              .statusCode(204);
	   
   }
}
