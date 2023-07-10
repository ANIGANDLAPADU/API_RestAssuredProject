package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;
@Listeners(api.utilites.ExtentReportManager.class)
public class UserTest {
	Faker faker;
	User user;
	Logger logger;

	@BeforeClass
	public void setupData() {
		faker = new Faker();
		user = new User();

		logger = LogManager.getLogger(this.getClass());

		user.setId(faker.idNumber().hashCode());
		logger.info("***setId ***");
		user.setUsername(faker.name().username());
		logger.info("***setUserName ***");
		user.setFirstName(faker.name().firstName());
		logger.info("***setFirstName ***");
		user.setLastName(faker.name().lastName());
		logger.info("***setLastName ***");
		user.setEmail(faker.internet().safeEmailAddress());
		logger.info("***setEmailAddress ***");
		user.setPassword(faker.internet().password(5, 10));
		logger.info("***setPassword ***");
		user.setPhone(faker.phoneNumber().cellPhone());
		logger.info("***setPhoneNumber ***");
	}

	@Test(priority = 1)
	public void testPostUser() {
		Response response = UserEndPoints.createUser(user);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2)
	public void testgetUser() {
		Response response = UserEndPoints.readUser(this.user.getUsername());
		logger.info("***getUserName ***");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 3)
	public void testUpDateUser() {
		user.setFirstName(faker.name().firstName());
		logger.info("***setFirstName ***");
		user.setLastName(faker.name().lastName());
		logger.info("***setLastName ***");

		user.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndPoints.updateUser(this.user.getUsername(), user);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		Response responseafterupdate = UserEndPoints.readUser(this.user.getUsername());
		responseafterupdate.then().log().all();
		Assert.assertEquals(responseafterupdate.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void testdeleteUser() {
		Response response = UserEndPoints.deleteUser(this.user.getUsername());
		logger.info("***deletingUserName***");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}
}
