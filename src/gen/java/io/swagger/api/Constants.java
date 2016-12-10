package io.swagger.api;/**
 * Created by Guillaume on 29/11/2016.
 */

import org.apache.log4j.Logger;

/**
 * Classe non instanciable référencant les constante du service ProxyImgFlip
 */
public class Constants {

    private static final Logger log = Logger.getLogger(Constants.class);

    public static final int ERROR = 503;
    public static final int OK = 200;
    public static final int CREATED = 201;
    public static final int DELETE = 201;
    public static final int NO_CONTENT = 204;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final int FORBIDDEN = 401;
    public static final int UNAUTHORIZE = 403;
    public static final int ZERO = 0;
    public static final int NEGATIVE = 0;
    public static final Double NEGATIVE_DOUBLE = -1.0;
    public static final Double ZERO_DOUBLE = 0.0;
    public static final String API_LOGIN ="ServiceMeme";
    public static final String API_PASSWORD ="epsi2016";

    public static final String PROTOCOLE = "http";
    public static final String HOST = "164.132.54.5";
    public static final int PORT = 8080;
    public static final String SERVICE_MEME = "/ProxyImgFlip/meme";
    public static final String AUTH = "Authorization";
    public static final String BASIC_AUTH ="Basic ";

    public static final String LOGIN_PROXY = "ProxyImgFlip";
    public static final String PASSWORD_PROXY = "epsi2016";


    public static final long DUREE_DUEL = 7;
}
