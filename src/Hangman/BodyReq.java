package Hangman;

import com.google.gson.*;
import java.util.ArrayList;

public class BodyReq {
	
	String model;
	ArrayList<MessReq> messages=new ArrayList<>();
	
	
	public BodyReq(String Word) {
		model="deepseek/deepseek-r1:free";
		
		messages.add(new MessReq("user",Word));
		
		
		
		
	}
	
	
	public String toString() {
		
		return new Gson().toJson(this);
	}
	
	
	private static class MessReq{
		private String role;
		
		private String content;
		
		private MessReq(String Role,String Word) {
			
			setRole(Role);
			
			setContent("Give me a  two word hint maximum about the Word : [ "+Word+" ] your answer should be commencing  in addition to your response by : it is ");
			
			
			
		}
        @SuppressWarnings("unused")
		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
		@SuppressWarnings("unused")
		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	
		}
	
	
	
	
	

}
