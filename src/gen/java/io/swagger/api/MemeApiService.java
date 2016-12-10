package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Meme;
import io.swagger.model.Picture;
import io.swagger.model.MemePattern;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-08T07:10:17.438Z")
public abstract class MemeApiService {
    public abstract Response memeGet(SecurityContext securityContext) throws NotFoundException;
    public abstract Response memeIdDelete(Integer id,SecurityContext securityContext) throws NotFoundException;
    public abstract Response memeIdGet(Integer id,SecurityContext securityContext) throws NotFoundException;
    public abstract Response memePictureGet(SecurityContext securityContext) throws NotFoundException;
    public abstract Response memePost(MemePattern memeToBuild,SecurityContext securityContext) throws NotFoundException;
}
