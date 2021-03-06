package com.codeimmig.yannick.consummer;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class OrderRestConsummer {
	//1.Autowired DC
	@Autowired
	private DiscoveryClient client;
	//2.
	public String getOrderData() {
		//2. Go to eureka with SID
		//3. fetch List<SI>
		List<ServiceInstance> list=client.getInstances("ORDER-SERVICE");
		
		//4.read one instance using index 
		
		ServiceInstance si=list.get(0);
		//5.read URi 
		URI uri=si.getUri();
		
		//6.Add path 
		
		String url=uri+"/order/data";
		
		//7.use resTemplate
		RestTemplate rt=new RestTemplate();
		
		//8.Make HTTP call 
		String resp=rt.getForObject(url,String.class);
		//9.return back to Consummer$RestController
		return resp;
	}
	

}
