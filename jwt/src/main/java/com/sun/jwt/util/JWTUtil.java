package com.sun.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zcm


  jwt含有三部分：头部（header）、载荷（payload）、签证（signature）
*(1)头部一般有两部分信息：声明类型、声明加密的算法（通常使用HMAC SHA256）
*(2)载荷该部分一般存放一些有效的信息。jwt的标准定义包含五个字段：
*    -iss：该JWT的签发者
    - sub: 该JWT所面向的用户
    - aud: 接收该JWT的一方
    - exp(expires): 什么时候过期，这里是一个Unix时间戳
    - iat(issued at): 在什么时候签发的
* (3)签证（signature） JWT最后一个部分。该部分是使用了HS256加密后的数据；包含三个部分：
* */
@Service("jwtUtil")
public class JWTUtil {

    Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    private String SECRET = "!QAZ@WSX#EDC";

    private String JWT_ISSUER = "JWT_ISSUER";

    /**
     * 创建token
     * @return
     */
    public String createJwtToken(){
        final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.ES256;

        final long nowMillis = System.currentTimeMillis();
        //过期时间
        final long ttlMillis = 5*60*100000;
        final long expMillis = nowMillis + ttlMillis;

        final Date now = new Date(nowMillis);
        final Date exp = new Date(expMillis);

        //创建签名密钥
        final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Base64.getEncoder().encodeToString(SECRET.getBytes()));
        final Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());

        final Map<String,Object> headerMap = new HashMap<String, Object>();
        headerMap.put("alg","HS256");
        headerMap.put("typ","JWT");

        final JwtBuilder builder = Jwts.builder().setHeaderParams(headerMap).setIssuedAt(now).setExpiration(exp).setIssuer(JWT_ISSUER).signWith(signatureAlgorithm,signingKey);
        return builder.compact();
    }

    public Claims parseJWTToken(String token){
        Claims claims = null;

        try {
            claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET)).parseClaimsJws(token).getBody();
        } catch (Exception e){
            logger.info(e.getMessage());
            return null;
        }
        return claims;
    }


}
