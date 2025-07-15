package Hangman;

import java.net.URI;

import java.net.http.HttpClient;

import java.net.http.HttpRequest;

import java.net.http.HttpRequest.BodyPublishers;

import java.net.http.HttpResponse;

import com.google.gson.JsonArray;

import com.google.gson.JsonObject;

import com.google.gson.JsonParser;


public class QuestionHint{
	
	private BodyReq bodyrequest;
	
	private static final String  API_KEY="API_KEY";
	
	private  HttpResponse<String> response;
	
	private boolean Loading=true;
	
	private String wording="Loading.....";
	
	
	
	
	
	
	public QuestionHint(String word) {
		
		bodyrequest=new BodyReq(word);
		
		sendPost();
		
		
		
		
	}
	
	
	
	public HttpResponse<String> sendPost()  {
		if(isLoading()) {
			System.out.println(getWording());}
		HttpClient client=HttpClient.newHttpClient();
		
		HttpRequest request=HttpRequest.newBuilder()
				
				.uri(URI.create("https://openrouter.ai/api/v1/chat/completions"))
				
				.header("Authorization","Bearer "+API_KEY)
				
				.header("Content-Type", "application/json")
				
				.POST(BodyPublishers.ofString(bodyrequest.toString())).build();
		
		try 
		{response=client.send(request, HttpResponse.BodyHandlers.ofString());
		setLoading(false);}
		catch(Exception e)
		{
		 System.err.println("An error has occured");
			
		}
		if(!isLoading()) {
			setWording("");
		}
		   
		
		return response;
		
		
		
		
		
		
		
	
	}
	
	public String toString() {
		JsonObject responseJson = JsonParser.parseString(response.body()).getAsJsonObject();
		
		JsonArray messages = responseJson.getAsJsonArray("choices");
		
		JsonObject choices = messages.get(0).getAsJsonObject();
		
        JsonObject ActualMessage = choices.getAsJsonObject("message");
		
		
		return ActualMessage.get("content").getAsString();		
		
		
	}



	public boolean isLoading() {
		return Loading;
	}



	public void setLoading(boolean loading) {
		Loading = loading;
	}



	public String getWording() {
		return wording;
	}



	public void setWording(String wording) {
		this.wording = wording;
	}
	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}