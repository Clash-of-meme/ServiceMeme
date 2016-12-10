package io.swagger.api.impl;

import io.swagger.api.Constants;
import io.swagger.api.MeApiService;
import io.swagger.api.NotFoundException;
import io.swagger.common.MemeBO;
import io.swagger.common.UserBO;
import io.swagger.model.Meme;
import io.swagger.model.Token;
import io.swagger.persistence.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

import static io.swagger.common.statistique.impl.StatistiqueMemeImpl.*;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-08T07:10:17.438Z")
public class MeApiServiceImpl extends MeApiService {


    private static final Logger logger = LogManager.getLogger(MeApiServiceImpl.class);

    @Override
    public Response meMemeGet(String token, SecurityContext securityContext) throws NotFoundException {

        logger.info("Recherche des memes de l'utilisateur");
        logger.info("token : "+token);
        ArrayList<Meme> listMeme = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Query query =session.createQuery("from UserBO where token = :token");
            query.setParameter("token", token);
            List<UserBO> userBOs = query.getResultList();
            UserBO user;
            if (userBOs.size() ==1){
                user =userBOs.get(0);
                logger.info("Utilisateur trouvé");
            }
            else
            {
                logger.info("Pas d'utilisateur");
                return Response.status(Constants.BAD_REQUEST).build();
            }
            Query query2 = session.createQuery("from MemeBO where id_user = :id_user");
            query2.setParameter("id_user",user.getId());
            List<MemeBO> memeBOs = query2.getResultList();
            session.close();
            if (memeBOs.size() != 0) {
                logger.info("Memes trouves");
                for (MemeBO memeBO : memeBOs) {
                    Meme meme = new Meme();
                    meme.setId(memeBO.getId());
                    meme.setName(memeBO.getName());
                    meme.setElo(memeBO.getElo());
                    meme.setCreationDate(memeBO.getCreation_date());
                    meme.setUrl(memeBO.getUrl());
                    meme.setInQueue(memeBO.getIn_queue());

                    meme.setMatchCount(getMatchCount(memeBO.getId()));
                    meme.setWinrate(getWinRate(memeBO.getId()));
                    meme.setLoginUser(user.getLogin());
                    meme.setInMatch(getInmatch(memeBO.getId()));

                    listMeme.add(meme);
                }
                return Response.status(Constants.OK).entity(listMeme).build();
            }
            logger.info("Pas de meme pour l'utilisateur");
            return Response.status(Constants.NO_CONTENT).entity(listMeme).build();

        } catch (ExceptionInInitializerError ex) {
            logger.info("Erreur avec la base de donnée : " + ex);

        }
        return Response.status(Constants.ERROR).build();

    }
}
