package cn.slkj.taxi.util;

import org.springframework.context.ApplicationContext;

public class Const
{
  public static final String SESSION_SECURITY_CODE = "sessionSecCode";
  public static final String SESSION_USER = "sessionUser";
  public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
  public static final String SESSION_menuList = "menuList";
  public static final String SESSION_allmenuList = "allmenuList";
  public static final String SESSION_QX = "QX";
  public static final String SESSION_userpds = "userpds";
  public static final String SESSION_USERROL = "USERROL";
  public static final String SESSION_USERNAME = "USERNAME";
  public static final String TRUE = "T";
  public static final String FALSE = "F";
  public static final String LOGIN = "/login_toLogin.do";
  public static final String SYSNAME = "admin/config/SYSNAME.txt";
  public static final String PAGE = "admin/config/PAGE.txt";
  public static final String WEIXIN = "admin/config/WEIXIN.txt";
  public static final String YEAR = "admin/config/YEAR.txt";
  public static final String ADDRESSEE = "admin/config/ADDRESSEE.txt";
  public static final String FILEPATHIMG = "uploadFiles/uploadImgs/";
  public static final String FILEPATHFILE = "uploadFiles/file/";
  public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/";
  public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(reports)|(websocket)).*";
}
