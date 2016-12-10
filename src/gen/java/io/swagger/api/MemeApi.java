package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.MemeApiService;
import io.swagger.api.factories.MemeApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Meme;
import io.swagger.model.Picture;
import io.swagger.model.MemePattern;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/meme")
@Consumes({ "application/json", "application/xml" })
@Produces({ "application/json", "application/xml" })
@io.swagger.annotations.Api(description = "the meme API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-08T07:10:17.438Z")
public class MemeApi  {
   private final MemeApiService delegate = MemeApiServiceFactory.getMemeApi();

    @GET
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "On récupère tous les memes. ", response = Meme.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "API key")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = Meme.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Vous devez êtes identifier pour accéder à cette ressource.", response = Meme.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Vous n'avez pas l'autorisation d'accéder à cette ressource.", response = Meme.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "Problème de connexion avec la base de donnée.", response = Meme.class, responseContainer = "List") })
    public Response memeGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.memeGet(securityContext);
    }
    @DELETE
    @Path("/{id}")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Suppression d'un meme avec son ID", response = void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "API key")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Successful response.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request. Pas d'utilisateur.", response = Meme.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Vous devez êtes identifier pour accéder à cette ressource.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Vous n'avez pas l'autorisation d'accéder à cette ressource.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "Problème de connexion avec la base de donnée.", response = void.class) })
    public Response memeIdDelete(@ApiParam(value = "L'id d'un meme",required=true) @PathParam("id") Integer id
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.memeIdDelete(id,securityContext);
    }
    @GET
    @Path("/{id}")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "On récupère un meme avec son ID.", response = Meme.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "API key")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = Meme.class),

        @io.swagger.annotations.ApiResponse(code = 400, message = "Bad request. Pas d'utilisateur.", response = Meme.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Vous devez êtes identifier pour accéder à cette ressource.", response = Meme.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Vous n'avez pas l'autorisation d'accéder à cette ressource.", response = Meme.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "ProxyImgFlip n'a pas répondu.", response = Meme.class) })
    public Response memeIdGet(@ApiParam(value = "L'id d'un Meme",required=true) @PathParam("id") Integer id
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.memeIdGet(id,securityContext);
    }
    @GET
    @Path("/picture")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "On récupère sur le site ImgFlip l'URL des 100 memes les plus populaire durant les 30 derniers jours. ", response = Picture.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "API key")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = Picture.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Vous devez êtes identifier pour accéder à cette ressource.", response = Picture.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Vous n'avez pas l'autorisation d'accéder à cette ressource.", response = Picture.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "ProxyImgFlip n'a pas répondu.", response = Picture.class, responseContainer = "List") })
    public Response memePictureGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.memePictureGet(securityContext);
    }
    @POST
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "On crée un meme.", response = Meme.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "API key")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response", response = Meme.class),

        @io.swagger.annotations.ApiResponse(code = 400, message = "Paramètres incorrectes", response = Meme.class),

        @io.swagger.annotations.ApiResponse(code = 401, message = "Vous devez êtes identifier pour accéder à cette ressource.", response = Meme.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Vous n'avez pas l'autorisation d'accéder à cette ressource.", response = Meme.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "Problème de connexion avec la base de donnée.", response = Meme.class) })
    public Response memePost(@ApiParam(value = "Meme que l'on veut créer" ,required=true) MemePattern memeToBuild
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.memePost(memeToBuild,securityContext);
    }
}
