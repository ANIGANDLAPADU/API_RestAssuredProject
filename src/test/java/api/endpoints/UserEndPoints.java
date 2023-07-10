package api.endpoints;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints {
	public static Response createUser(User user)
	{
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(user)
				.when()
					.post(Routes.post_url);
		return response;
	}

	public static Response readUser(String userName)
	{
		Response response = 
				given()
					.pathParam("username", userName)
				.when()
					.get(Routes.get_url);
		return response;
	}

	public static Response updateUser(String userName, User user)
	{
		Response response = 
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.pathParam("username", userName)
					.body(user)
				.when()
					.put(Routes.delete_url);
		return response;
	}

	public static Response deleteUser(String userName)
	{
		Response response = 
				given()
					.pathParam("username", userName)
				.when()
					.delete(Routes.delete_url);
		return response;
	}

}