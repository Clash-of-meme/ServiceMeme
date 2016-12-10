package io.swagger.api;

import io.swagger.api.factories.MeApiServiceFactory;

import io.swagger.annotations.ApiParam;

import io.swagger.model.Meme;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/me")
@Consumes({ "application/json", "application/xml" })
@Produces({ "application/json", "application/xml" })
@io.swagger.annotations.Api(description = "the me API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-08T07:10:17.438Z")
public class MeApi  {
   private final MeApiService delegate = MeApiServiceFactory.getMeApi();

    @GET
    @Path("/{token}/meme")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "On récupère les memes d'un utilisateur avec son token.", response = Meme.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "API key")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = Meme.class, responseContainer = "List"),

        @io.swagger.annotations.ApiResponse(code = 204, message = "No content", response = Meme.class, responseContainer = "List"),

        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request", response = Meme.class, responseContainer = "List"),

        @io.swagger.annotations.ApiResponse(code = 401, message = "Vous devez êtes identifier pour accéder à cette ressource.", response = Meme.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Vous n'avez pas l'autorisation d'accéder à cette ressource.", response = Meme.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "La base de donnée n'a pas répondu.", response = Meme.class, responseContainer = "List") })
    public Response meMemeGet(@ApiParam(value = "token d'un utilisateur" ,required=true) @PathParam("token") String token
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.meMemeGet(token,securityContext);
    }
}
