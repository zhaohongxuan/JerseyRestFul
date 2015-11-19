package com.zeusjava.client;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;
import com.zeusjava.entity.User;
import org.junit.Test;

public class UserClient {
	private WebResource r = null;
	@Test
	public void insertUser(){
		r = Client.create().resource("http://localhost:8081/jersey/api/users");
		Form form = new Form();
		form.add("userId", "002");
		form.add("userName", "ZhaoHongXuan");
		form.add("userAge", 23);
		ClientResponse response = r.type(MediaType.APPLICATION_FORM_URLENCODED)
				.post(ClientResponse.class, form);
		System.out.println(response.getStatus());
	}
	@Test
	public void findUser(){
		r = Client.create().resource("http://localhost:8081/jersey/api/users/002");
		String jsonRes = r.accept(MediaType.APPLICATION_XML).get(String.class);
		System.out.println(jsonRes);
	}

	@Test
	public void updateUser(){
		r = Client.create().resource("http://localhost:8081/jersey/api/users");
		User user = new User("002","ZhaoXiaoXuan",24);
		ClientResponse response = r.path(user.getUserId()).accept(MediaType.APPLICATION_XML)
				.put(ClientResponse.class, user);
		System.out.println(response.getStatus());
	}
	@Test
	public void deleteUser(){
		r = Client.create().resource("http://localhost:8081/jersey/api/users");

		GenericType<JAXBElement<User>> generic = new GenericType<JAXBElement<User>>() {};
		JAXBElement<User> jaxbContact = r
				.path("002")
				.type(MediaType.APPLICATION_XML)
				.get(generic);
		User user = jaxbContact.getValue();
		System.out.println(user.getUserId() + ": " + user.getUserName());
		ClientResponse response = r.path("002").delete(ClientResponse.class);
		System.out.println(response.getStatus());
	}


}
