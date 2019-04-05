import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClientProtocolException, IOException{
        Scanner scan = new Scanner(System.in);
        String url = null, url1 = null , url2 = null;
        int type, choice = 0;
        while (true) {
            if (url1 == null || url2 == null) {
                System.out.println("Podaj glowna czesc url zapytania:");
                url1 = scan.nextLine();
                System.out.println("Podaj rozszerzona czesc url zapytania:");
                url2 = scan.nextLine();
            } else {
                System.out.println("Chcesz zmienic czesc glowna url? 1 == tak");
                choice = scan.nextInt();
                if (choice == 1){
                    System.out.println("Podaj glowna czesc url zapytania:");
                    url1 = scan.nextLine();
                    choice = 0;
                }
                System.out.println("Chcesz zmienic rozszerzona czesc url? 1 == tak");
                choice = scan.nextInt();
                if (choice == 1){
                    System.out.println("Podaj rozszerzona czesc url zapytania:");
                    url2 = scan.nextLine();
                    choice = 0;
                }
            }
            url = url1 + url2;
            System.out.println("Podaj typ zapytania: 1-GET, 2-POST");
            type = scan.nextInt();
            switch (type) {
                case 1:
                    get(url);
                    break;
                case 2:
                    post(url);
                    break;
                default:
                    break;
            }
        }


    }

    @SuppressWarnings("Duplicates")
    private static void post(String url) throws ClientProtocolException, IOException{
        Scanner scan = new Scanner(System.in);
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        System.out.println("Podaj dane do wysłania: ");
        String product = scan.nextLine();
        StringEntity input = new StringEntity(product);
        input.setContentType("application/json");
        post.setEntity(input);
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }

    @SuppressWarnings("Duplicates")
    private static void get(String url) throws ClientProtocolException, IOException{
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }
}