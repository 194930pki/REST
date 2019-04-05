/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PKI;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Student
 */
@Path("RestService")
public class RestServiceResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestServiceResource
     */
    public RestServiceResource() {
    }

    /**
     * Retrieves representation of an instance of PKI.RestServiceResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of RestServiceResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String sayPlainTextHello(String data) throws Exception {
        JSONObject jsonRequest;
        JSONObject jsonResponse = new JSONObject("{\"Hello\":\"World\"}");
        try {
            jsonRequest = new JSONObject(data);
            jsonResponse.put("Hello", jsonRequest.get("name"));
        } catch (JSONException e){
            
        }
        return jsonResponse.toString();
    }
    
    @POST
    @Path("/score/wins")
    @Produces("text/plain")
    public int increaseWins() { 
        return Score.WINS++;
    }
    
    @POST
    @Path("/score/ties")
    @Produces("text/plain")
    public int increaseTies() { 
        return Score.TIES++;
    }
    
    @POST
    @Path("/score/losses")
    @Produces("text/plain")
    public int increaseLosses() { 
        return Score.LOSSES++;
    }
    
    @GET 
    @Path("/score") 
    @Produces("application/json")
    public String getScore() {
        String pattern =
        "{ \"wins\":\"%s\", \"losses\":\"%s\", \"ties\": \"%s\"}";
        return String.format(pattern, Score.WINS, Score.LOSSES, Score.TIES );
    }
    
    @PUT 
    @Path("/score") 
    @Produces("application/json")
    public String update(@QueryParam("wins") int wins,
    @QueryParam("losses") int losses,
    @QueryParam("ties") int ties) {
        Score.WINS = wins;
        Score.TIES = ties;
        Score.LOSSES = losses;
        String pattern =
        "{ \"wins\":\"%s\", \"losses\":\"%s\", \"ties\": \"%s\"}";
        return String.format(pattern, Score.WINS, Score.LOSSES, Score.TIES );
    }

}
