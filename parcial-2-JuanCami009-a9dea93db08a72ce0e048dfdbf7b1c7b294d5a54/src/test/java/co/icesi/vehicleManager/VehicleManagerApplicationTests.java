package co.icesi.vehicleManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VehicleManagerApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private String authToken;

	@Test
	void testLoginEndpoint_shouldAuthenticateUser() {
		String loginJson = """
				{
					"username": "jdoe",
					"password": "password"
				}
				""";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(loginJson, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("/login", request, String.class);

		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
		assertThat(response.getBody()).isNotEqualTo("Invalid password");
		assertThat(response.getBody()).isNotEqualTo("User not found");
	}

	@BeforeEach
	void obtainAuthToken() {
		String loginJson = """
				{
				    "username": "jdoe",
				    "password": "password"
				}
				""";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(loginJson, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("/login", request, String.class);
		this.authToken = response.getBody();
	}

	@Test
	void testLoginEndpoint_shouldHaveValidToken() {
		assertThat(authToken).isNotNull();
		assertThat(authToken).isNotEqualTo("Invalid password");
		assertThat(authToken).isNotEqualTo("User not found");
	}

	private HttpHeaders getAuthHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		if (authToken != null) {
			headers.set("Authorization", "Bearer " + authToken);
		}
		return headers;
	}

	/*
	 * ------------------------------------- REAL TESTS
	 * ------------------------------------------
	 */

	@Test
	void testCreateVehicle() {
		Map<String, Object> vehicle = new HashMap<>();
		vehicle.put("licensePlate", "GHD842");
		vehicle.put("model", "Test Vehicle");
		vehicle.put("make", "honda");
		vehicle.put("color", "GREEN");
		vehicle.put("yearRelease", 2025);
		vehicle.put("ownerId", 1);

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(vehicle, getAuthHeaders());
		ResponseEntity<Map> response = restTemplate.postForEntity("/vehicles", request, Map.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(response.getBody()).isNotNull();
		assertThat(response.getBody().get("id")).isNotNull();
		assertThat(response.getBody().get("model")).isEqualTo("Test Vehicle");
		assertThat(response.getBody().get("ownerId")).isEqualTo(1);

	}

	@Test
	void testUpdateVehicle() {
		Map<String, Object> vehicle = new HashMap<>();
		vehicle.put("licensePlate", "GHD842");
		vehicle.put("model", "Test Vehicle");
		vehicle.put("make", "honda");
		vehicle.put("ownerId", 2);

		HttpEntity<Map<String, Object>> createRequest = new HttpEntity<>(vehicle, getAuthHeaders());
		ResponseEntity<Map> createResponse = restTemplate.postForEntity("/vehicles", createRequest, Map.class);

		Long vehicleId = Long.valueOf(createResponse.getBody().get("id").toString());

		vehicle.put("model", "Test Mode");
		vehicle.put("make", "chevrolet");
		vehicle.put("ownerId", 1);
		vehicle.put("id", vehicleId);
		HttpEntity<Map<String, Object>> updateRequest = new HttpEntity<>(vehicle, getAuthHeaders());
		ResponseEntity<Map> updateResponse = restTemplate.exchange("/vehicles", HttpMethod.PUT, updateRequest,
				Map.class);

		assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(updateResponse.getBody()).isNotNull();
		assertThat(updateResponse.getBody().get("model")).isEqualTo("Test Mode");
		assertThat(updateResponse.getBody().get("make")).isEqualTo("chevrolet");
		assertThat(updateResponse.getBody().get("ownerId")).isEqualTo(1);

	}

	@Test
	void testDeleteVehicle() {
		Map<String, Object> vehicle = new HashMap<>();
		vehicle.put("licensePlate", "GHD842");
		vehicle.put("model", "Test Vehicle");
		vehicle.put("make", "honda");
		vehicle.put("ownerId", 2);

		HttpEntity<Map<String, Object>> createRequest = new HttpEntity<>(vehicle, getAuthHeaders());
		ResponseEntity<Map> createResponse = restTemplate.postForEntity("/vehicles", createRequest, Map.class);

		Long vehicleId = Long.valueOf(createResponse.getBody().get("id").toString());

		HttpEntity<Void> deleteRequest = new HttpEntity<>(getAuthHeaders());
		ResponseEntity<Map> getResponse = restTemplate.exchange("/vehicles/" + vehicleId, HttpMethod.GET, deleteRequest,
				Map.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		ResponseEntity<Void> deleteResponse = restTemplate.exchange("/vehicles/" + vehicleId, HttpMethod.DELETE,
				deleteRequest, Void.class);

		assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

		getResponse = restTemplate.exchange("/vehicles/" + vehicleId, HttpMethod.GET, deleteRequest,
				Map.class);
		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

	@Test
	void testGetAllVehicles() {
		HttpEntity<Void> request = new HttpEntity<>(getAuthHeaders());
		ResponseEntity<Map[]> response = restTemplate.exchange("/vehicles", HttpMethod.GET, request, Map[].class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
	}

	@Test
	void testCreateVehicle_WithUser() {

		String loginJson = """
				{
				    "username": "asmith",
				    "password": "password"
				}
				""";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(loginJson, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("/login", request, String.class);
		String authTokenAlice = response.getBody();

		System.out.println(authTokenAlice);

		HttpHeaders headersRequest = new HttpHeaders();
		headersRequest.setContentType(MediaType.APPLICATION_JSON);
		if (authTokenAlice != null) {
			headersRequest.set("Authorization", "Bearer " + authTokenAlice);
		}

		Map<String, Object> vehicle = new HashMap<>();
		vehicle.put("licensePlate", "GHD842");
		vehicle.put("model", "Test Vehicle");
		vehicle.put("make", "honda");
		vehicle.put("color", "GREEN");
		vehicle.put("yearRelease", 2025);
		vehicle.put("ownerId", 1);

		HttpEntity<Map<String, Object>> requestCreateVehicle = new HttpEntity<>(vehicle, headersRequest);
		ResponseEntity<Map> responseCreateVehicle = restTemplate.postForEntity("/vehicles", requestCreateVehicle,
				Map.class);

		assertThat(responseCreateVehicle.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
	}

}
