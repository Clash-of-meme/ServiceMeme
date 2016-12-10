package io.swagger.common.statistique.impl;/**
 * Created by Guillaume on 10/12/2016.
 */

import io.swagger.api.Constants;
import io.swagger.common.DuelBO;
import io.swagger.common.MemeBO;
import io.swagger.common.UserBO;
import io.swagger.persistence.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * {Insert class description here}
 */
public class StatistiqueMemeImpl {

    private static final Logger log = Logger.getLogger(StatistiqueMemeImpl.class);

    /**
     * Creates a new instance of statistiqueMemeImpl
     */

    public StatistiqueMemeImpl() {
    }

    public static Boolean getInmatch(Integer id) {


        
        LocalDate localDate = LocalDate.now();
        Date date_limite = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).minusDays(Constants.DUREE_DUEL).toInstant());

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from DuelBO where id_meme1 = :id or id_meme2 = :id");
            query.setParameter("id",id);
            List<DuelBO> duelBOs = query.getResultList();
            session.close();
            if(duelBOs.size() != 0){
                for(DuelBO duel : duelBOs){
                    if (duel.getStart_date().before( date_limite )){
                        return true;
                    }
                }
                return false;
            } else {
                return false;
            }
        }
        catch (ExceptionInInitializerError ex){

        }
        return null;
    }

    public static String getLoginUser(int id_user) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from UserBO where id = :id");
            query.setParameter("id",id_user);
            List<UserBO> userBOs = query.getResultList();
            session.close();
            if(userBOs.size()==1) {
                return userBOs.get(0).getLogin();
            }
        }
        catch (ExceptionInInitializerError ex){

        }
        return null;
    }

    public static Double getWinRate(Integer id) {

            Integer nombre_match_joue = getMatchCount(id);
            if (nombre_match_joue == 0) {
                return Constants.ZERO_DOUBLE;
            } else if (nombre_match_joue == Constants.NEGATIVE) {
                return Constants.NEGATIVE_DOUBLE;
            } else {
                try {
                    Session session = HibernateUtil.getSessionFactory().openSession();

                    session.beginTransaction();
                    Query query = session.createQuery("from DuelBO where id_meme1 = :id and vote_meme1 > vote_meme2");
                    query.setParameter("id", id);
                    List<DuelBO> duelBOs1 = query.getResultList();
                    query = session.createQuery("from DuelBO where id_meme2 = :id and vote_meme2 > vote_meme2");
                    query.setParameter("id", id);
                    List<DuelBO> duelBOs2 = query.getResultList();
                    session.close();
                    Integer nombre_match_gagne = duelBOs1.size() + duelBOs2.size();
                    return Double.valueOf(nombre_match_gagne / nombre_match_joue);
                } catch (ExceptionInInitializerError ex) {
                }
                return Constants.NEGATIVE_DOUBLE;
            }
    }

    public static Integer getMatchCount(Integer id) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from DuelBO where id_meme1 = :id or id_meme2 = :id");
            query.setParameter("id",id);
            List<DuelBO> duelBOs = query.getResultList();
            session.close();
            return duelBOs.size();
        }
        catch (ExceptionInInitializerError ex){

        }
        return Constants.NEGATIVE;
    }
}
