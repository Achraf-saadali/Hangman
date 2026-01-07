import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class QuestionHint extends Thread {

    private String wordToGuess ;

    public String getTheHint() {
        return theHint;
    }

    private  String theHint ;
    private final static String API_KEY =  (Dotenv.load()).get("API_KEY");

    private static String extractContent(String content)
    {
        return content.split("\"content\":")[1].split("\",")[0];
    }




    public QuestionHint(String wordToGuess)
    {  super();
        this.wordToGuess = wordToGuess ;
    }



    public String sendRequest() throws IOException , InterruptedException, ExecutionException
    {
        HttpResponse.BodyHandler<String> responseBody = HttpResponse
                .BodyHandlers
                .ofString() ;

        String content
                = "explain in one single sentence of maximum of 7 words the word : "
                +wordToGuess+" without mentionning it explicitly.";

        Data requestBody = new Data(content);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest httpRequest =  HttpRequest
                .newBuilder(URI.create("https://openrouter.ai/api/v1/chat/completions"))
                .setHeader("Authorization","Bearer "+API_KEY)
                .setHeader("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();


         String responseContent =  client
                 .sendAsync(httpRequest , responseBody)
                 .get()
                 .body()
                 .toString();

         theHint = QuestionHint.extractContent(responseContent);
         return theHint ;




    }
    @Override
    public void run(){

        try {
            sendRequest();
        }catch(Exception ex)
        {
            System.out.println("An exception occured : "+ex);
        }


    }

}





class Data
{
    private final  String model = (Dotenv.load()).get("MODEL") ;
    private List<Map<String,String>> messages ;

    public Data(String content)
    {messages = List.of(Map.of("role","user","content",content));}

    public String toString()
    {return new Gson().toJson(this);}

}
