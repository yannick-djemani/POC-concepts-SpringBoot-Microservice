package com.codeimmig.yannick.runner;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TestPostDataRunner  implements CommandLineRunner{
	private static final Logger LOG=org.slf4j.LoggerFactory.getLogger(TestGetStringDataRunner.class);
	@Autowired
	private RestTemplate rt;


	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//2. Define Request URL
		String url = "http://localhost:8081/rest/student/savea";

		/**Request Creation ********************/
		//String body = "{}";

		String body = "{\"sid\":101,\"sname\":\"A\",\"sfee\":300.0}";
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		//RequestEntity = Body + Header
		HttpEntity<String> request=new HttpEntity<String>(body, headers);

		/***Request creation***/
		//3. make HTTP call and get response
		// URL(String), request(HttpEntity), responseType(T.class), PathVariables

		ResponseEntity<String> response=rt.postForEntity(url, request, String.class);
		System.out.println("///////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println(response.getBody());
		System.out.println(response.getStatusCodeValue());
		System.out.println(response.getStatusCode().name());


		String url8 = "http://localhost:8081/rest/student/update/{id}";

		/**Request Details**/
		String body8 = "{\"sname\":\"A\",\"sfee\":300.0}";

		HttpHeaders headers8 = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> httpEntity = new HttpEntity<>(body8, headers8);

		/**-----**/
		//rt.put(url, httpEntity, 550); --> void type

		//[ URL, HttpMethod, HttpEntity, ResponseType, PathVariables]
		ResponseEntity<String> response8=rt.exchange(url8, HttpMethod.PUT, httpEntity, String.class, 5501);

		LOG.info("Response Body => " + response8.getBody());
		LOG.info("Response Status Code => " + response8.getStatusCode().name());
		LOG.info("Response Status Value => " + response8.getStatusCodeValue());
		LOG.info("Response Content-Type => " + response8.getHeaders().getContentType());

	}
}
