package Hangman;




 public class Person {
	 private static int count;
	 private int id;
	 private int Games_Count=0;
	
	 private String First_Name;
	 private String Last_Name;
	 private GameSession Game;
	 
	 public Person(String F_N,String L_N) {
		 setId(++count);
		 setFirst_Name(F_N);
		 setLast_Name(L_N);
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	};
	
	


	public GameSession getGame() {
		return Game;
	}
	public void setGame() {
		Game= new GameSession();
		setGames_Count(Games_Count+1);
		
		
	}

	public int getGames_Count() {
		return Games_Count;
	}

	private void setGames_Count(int games_Count) {
		Games_Count = games_Count;
	}
	 

}
