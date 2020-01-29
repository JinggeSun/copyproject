package com.sun.jwt.filter;

import com.sun.jwt.bean.HttpResponseBean;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

/**
 *
 * 自定义过滤器
 * @author zcm
 */
@Configuration
public class JwtFilter extends GenericFilterBean {

    Logger logger = LoggerFactory.getLogger(JwtFilter.class.getName());

    private static final String TOKEN_HEADER = "X-AUTH-TOKEN";
    private static final String HTTP_STATUS_ERROR = "ERROR";
    private static final String HTTP_STATUS_OK = "OK";
    private static final String HTTP_TOKEN_MESSAGE_INVALID = "token validate failure";

    /**
     * 自定义密钥
     */
    private String SECRET = "!QAZ@WSX#EDC";
    private String JWT_ISSUER = "JWT_ISSUER";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("filer 。。。。。");

        // http
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Base64.getEncoder().encodeToString(SECRET.getBytes()));

        //获取http头上的token
        String jsonTokenWeb = request.getHeader(TOKEN_HEADER);

        HttpResponseBean httpResponseBean = new HttpResponseBean();

        try {
            Claims claims = Jwts.parser().setSigningKey(apiKeySecretBytes).parseClaimsJws(jsonTokenWeb).getBody();

            String iss = Objects.isNull(claims.get("iss")) ? null : String.valueOf(claims.get("iss"));
            if (!JWT_ISSUER.equals(iss)){
                setResponseWsBean(httpResponseBean,HTTP_STATUS_ERROR,HTTP_TOKEN_MESSAGE_INVALID+"iss is incorrect");
                response.getWriter().write(JSONObject.fromObject(httpResponseBean).toString());
            }else {
                filterChain.doFilter(request,response);
            }


        }catch (Exception e){
            logger.info(e.getMessage());
            setResponseWsBean(httpResponseBean,HTTP_STATUS_ERROR,HTTP_TOKEN_MESSAGE_INVALID+e.getMessage());
            response.getWriter().write(JSONObject.fromObject(httpResponseBean).toString());
        }

    }

    private void  setResponseWsBean(HttpResponseBean responseWsBean,String httpStatus,String message){
        responseWsBean.setHttpStatus(httpStatus);
        responseWsBean.setMessage(message);
    }
}
