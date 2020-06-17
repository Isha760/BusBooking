package com.NewBusBookingApp.NBBA.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPDigestAuthFilter;

@RestController
@RequestMapping("/api/internal")
public class TestApiImpl {

//Client Settings
	public static Client client = null;
	String url="http://test.etravelsmart.com/etsAPI/api/";
	

	public static Client getClient()
	{
		if(client==null)
		{
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
			client = Client.create(clientConfig);  
			client.addFilter(new HTTPDigestAuthFilter("TixdoAPI1", "123456"));
		}
		return client;
	}
	
	@RequestMapping(value="/getStations",method=RequestMethod.GET,produces={"application/json"})
	public String StationList() {
		String Response=null;
		WebResource wr=getClient().resource(url+"getStations");
		Response=wr.get(String.class);
		return Response;
		
	}
	@RequestMapping(value="/getBusInfo",method=RequestMethod.GET,produces={"application/json"})
	public String busInfo(@RequestParam("from")String from,@RequestParam("to")String to,@RequestParam("doj")String doj) {
		String Response=null;
		WebResource wr=getClient().resource(url+"getAvailableBuses?sourceCity="
				+ from+"&destinationCity="
				+to+ "&doj="
				+ doj);
		Response=wr.get(String.class);
		return Response;
		
	}
	
	
	@RequestMapping(value="/getBusLayoutInfo",method=RequestMethod.GET,produces={"application/json"})
	public String busInfo(@RequestParam("from")String from,@RequestParam("to")String to,@RequestParam("doj")String doj,@RequestParam("invenType")String invenType,@RequestParam("rsId")String rsId ) {
		String Response=null;
		WebResource wr=getClient().resource(url+"getBusLayout?sourceCity="
				+ from+"&destinationCity="
				+to+ "&doj="
				+ doj+"&inventoryType="
						+ invenType+"&routeScheduleId="
						+ rsId);
		Response=wr.get(String.class);
		return Response;
		
	}
}
