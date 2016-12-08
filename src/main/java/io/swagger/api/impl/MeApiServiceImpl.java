package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.Meme;
import io.swagger.model.Token;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-08T07:10:17.438Z")
public class MeApiServiceImpl extends MeApiService {
    @Override
    public Response meMemeGet(Token token, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
