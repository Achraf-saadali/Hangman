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
	
	private static final String  API_KEY="Bearer API_KEY";
	
	private HttpResponse<String> response;
	
	private int CountHint;
	
	public QuestionHint(String word) {
		
		bodyrequest=new BodyReq(word);
		
		sendPost();
		
		
		
		
	}
	
	
	
	public HttpResponse<String> sendPost()  {
		
		HttpClient client=HttpClient.newHttpClient();
		
		HttpRequest request=HttpRequest.newBuilder()
				
				.uri(URI.create("https://openrouter.ai/api/v1/chat/completions"))
				
				.header("Authorization", API_KEY)
				
				.header("Content-Type", "application/json")
				
				.POST(BodyPublishers.ofString(bodyrequest.toString())).build();
		
		try 
		{response=client.send(request, HttpResponse.BodyHandlers.ofString());}
		catch(Exception e)
		{
		 System.err.println("An error has occured");
			
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



	public int getCountHint() {
		return CountHint;
	}



	public void setCountHint(int countHint) {
		CountHint = countHint;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}