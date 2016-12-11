package io.swagger.api.impl;

import io.swagger.api.Constants;
import io.swagger.api.MemeApiService;
import io.swagger.api.NotFoundException;
import io.swagger.common.MemeBO;
import io.swagger.common.UserBO;
import io.swagger.model.Meme;
import io.swagger.model.MemePattern;
import io.swagger.persistence.HibernateUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.glassfish.jersey.internal.util.Base64;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.swagger.common.statistique.impl.StatistiqueMemeImpl.*;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-08T07:10:17.438Z")
public class MemeApiServiceImpl extends MemeApiService {

    private static final Logger logger = LogManager.getLogger(MemeApiServiceImpl.class);

    @Override
    public Response memeGet(SecurityContext securityContext) throws NotFoundException {
        ArrayList<Meme> listMeme = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from MemeBO");
            List<MemeBO> memeBOs = query.getResultList();
            session.close();
            for(MemeBO memeBO : memeBOs){
                Meme meme = new Meme();
                meme.setId(memeBO.getId());
                meme.setName(memeBO.getName());
                meme.setElo(memeBO.getElo());
                meme.setCreationDate(memeBO.getCreation_date());
                meme.setUrl(memeBO.getUrl());
                meme.setInQueue(memeBO.getIn_queue());

                meme.setMatchCount(getMatchCount(memeBO.getId()));
                meme.setWinrate(getWinRate(memeBO.getId()));
                meme.setLoginUser(getLoginUser(memeBO.getId_user()));
                meme.setInMatch(getInmatch(memeBO.getId()));


                listMeme.add(meme);
            }
            return Response.status(Constants.OK).entity(listMeme).build();
        }
        catch (ExceptionInInitializerError ex)
        {
            logger.info("Erreur avec la base de donnée : " + ex);

        }
        return Response.status(Constants.ERROR).build();
    }

    @Override
    public Response memeIdDelete(Integer id, SecurityContext securityContext) throws NotFoundException {

        logger.info("suppression du meme : "+ id);
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from MemeBO where id = :id");
            query.setParameter("id",id);
            List<MemeBO> memeBOs = query.getResultList();
            if (memeBOs.size() == 1){
                logger.info("Meme a delete trouvé");
                MemeBO memeToDelete = memeBOs.get(0);
                session.delete(memeToDelete);
                session.getTransaction().commit();
                session.close();
                return Response.status(Constants.DELETE).build();
            }
            else{
                logger.info("Meme introuvable");
                session.close();
                return Response.status(Constants.BAD_REQUEST).build();
            }


        }
        catch (ExceptionInInitializerError ex)
        {
            logger.info("Erreur avec la base de donnée : " + ex);

        }
        return Response.status(Constants.ERROR).build();
    }
    @Override
    public Response memeIdGet(Integer id, SecurityContext securityContext) throws NotFoundException {

        logger.info("Meme demandé : " + id);

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from MemeBO where id = :id");
            query.setParameter("id",id);
            List<MemeBO> memeBOs = query.getResultList();
            session.close();
            if(memeBOs.size() == 1){
                logger.info("Meme trouvé");
                MemeBO memeBO = memeBOs.get(0);
                Meme meme = new Meme();
                meme.setId(memeBO.getId());
                meme.setName(memeBO.getName());
                meme.setElo(memeBO.getElo());
                meme.setCreationDate(memeBO.getCreation_date());
                meme.setUrl(memeBO.getUrl());
                meme.setInQueue(memeBO.getIn_queue());

                meme.setMatchCount(getMatchCount(memeBO.getId()));
                meme.setWinrate(getWinRate(memeBO.getId()));
                meme.setLoginUser(getLoginUser(memeBO.getId_user()));
                meme.setInMatch(getInmatch(memeBO.getId()));
                return Response.status(Constants.OK).entity(meme).build();
            }

            logger.info("Meme non trouvé");
            return Response.status(Constants.BAD_REQUEST).build();

        }
        catch (ExceptionInInitializerError ex)
        {
            logger.info("Erreur avec la base de donnée : " + ex);

        }
        return Response.status(Constants.ERROR).build();
    }
    @Override
    public Response memePictureGet(SecurityContext securityContext) throws NotFoundException {
        logger.info("Appel du service : " + Constants.SERVICE_MEME);

        HttpClient client = new DefaultHttpClient();
        URIBuilder builder = new URIBuilder();
        builder.setScheme(Constants.PROTOCOLE).setHost(Constants.HOST).setPort(Constants.PORT).setPath(Constants.SERVICE_MEME);
        logger.info("URL : " + builder.toString() );

        String token = Base64.encodeAsString(Constants.LOGIN_PROXY+":"+Constants.PASSWORD_PROXY);
        logger.info("Token d'authentification : "+token);

        HttpGet request = new HttpGet(builder.toString());
        request.addHeader(Constants.AUTH,(Constants.BASIC_AUTH+token));

         logger.info(request.getAllHeaders());
        logger.info(request.getURI());
        try {
            HttpResponse response = client.execute(request);
           logger.info("Le code retour du service est : " + response.getStatusLine().getStatusCode());
           if(response.getStatusLine().getStatusCode() == Constants.OK) {
               logger.info(response.getEntity().getContentType());
               String result = EntityUtils.toString(response.getEntity());
               logger.info(result);
               return Response.status(Constants.OK).entity(result).build();
           }
        } catch (IOException e) {
            Service_Error(e);
        }
        return Response.status(Constants.ERROR).build();
    }
    @Override
    public Response memePost(MemePattern memeToBuild, SecurityContext securityContext) throws NotFoundException {

        HttpClient client = new DefaultHttpClient();
        URIBuilder builder = new URIBuilder();
        builder.setScheme(Constants.PROTOCOLE).setHost(Constants.HOST).setPort(Constants.PORT).setPath(Constants.SERVICE_MEME);
        logger.info("URL : " + builder.toString() );

        String token = Base64.encodeAsString(Constants.LOGIN_PROXY+":"+Constants.PASSWORD_PROXY);
        logger.info("Token d'authentification : "+token);

        HttpPost request = new HttpPost(builder.toString());
        request.addHeader(Constants.AUTH,(Constants.BASIC_AUTH+token));

        logger.info("Meme que l'on veut créer" + memeToBuild.toString());

        StringEntity se = new StringEntity(memeToBuild.toString(), HTTP.UTF_8);
        request.setEntity(se);
        request.setHeader("Accept", "application/json");
        request.setHeader("Content-type", "application/json");

        try {
            HttpResponse response = client.execute(request);
            logger.info("Le code retour du service est : " + response.getStatusLine().getStatusCode());
            if(response.getStatusLine().getStatusCode() == Constants.CREATED) {
                String result = EntityUtils.toString(response.getEntity());
                JSONObject res_json = new JSONObject(result);
                MemeBO memeBO = new MemeBO();
                memeBO.setName(memeToBuild.getName());
                memeBO.setUrl(res_json.getString("url"));
                memeBO.setElo(0);
                memeBO.setIn_queue(false);
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:SS,sssss");
                Date date = new Date();
                logger.info("Creation du meme :"+date);
                memeBO.setCreation_date(date);
                //recuperation de l'ID utilisateur
                Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                Query query = session.createQuery("from UserBO where token = :token");
                query.setParameter("token",memeToBuild.getTokenUSer());
                logger.info("Utilisateur : " +memeToBuild.getTokenUSer());
                logger.info("Recherche de l'utisateur ...");
                List<UserBO> userBOs = query.getResultList();
                String loginuser = "";
                if(userBOs.size() != 0){
                    loginuser = userBOs.get(0).getLogin();
                    memeBO.setId_user(userBOs.get(0).getId());
                    logger.info("L'ID de l'utilisateur est : "+ userBOs.get(0).getId());
                }
                else{
                    logger.info("Pas d'utilisateur");
                    return Response.status(Constants.BAD_REQUEST).build();
                }
                session.save(memeBO);
                session.getTransaction().commit();
                session.close();

                //si tout c'est bien passé
                Meme memeCree = new Meme();
                memeCree.setId(memeBO.getId());
                memeCree.setName(memeBO.getName());
                memeCree.setElo(memeBO.getElo());
                memeCree.setCreationDate(memeBO.getCreation_date());
                memeCree.setMatchCount(0);
                memeCree.setWinrate(0.00);
                memeCree.setLoginUser(loginuser);
                memeCree.setInMatch(false);
                memeCree.setInQueue(false);
                memeCree.setUrl(memeBO.getUrl());

                return Response.status(Constants.OK).entity(memeCree).build();
            }
            else if (response.getStatusLine().getStatusCode() == Constants.BAD_REQUEST)
            {
                return Response.status(Constants.BAD_REQUEST).build();
            }
            else
            {
                return Response.status(Constants.ERROR).build();
            }
        } catch (IOException e) {
            Service_Error(e);
        }
        return Response.status(Constants.ERROR).build();
    }

    private void Service_Error(IOException e){
        logger.info("Le service : "+Constants.SERVICE_MEME + " n'est pas disponible");
        logger.info("Error : " + e);
    }
}
