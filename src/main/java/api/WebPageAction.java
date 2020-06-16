package api;

import DTO.LogInDTO;
import controllers.ActionController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/actions")
public class WebPageAction {

    private static ActionController controller = new ActionController();
    public WebPageAction(){

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getter(){
        return "Action page";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("log-in")
    public Response logIn(LogInDTO logInDTO){
        controller.logIn(logInDTO.getUsername(), logInDTO.getPassword());
        return Response.status(201).build();
    }

}
