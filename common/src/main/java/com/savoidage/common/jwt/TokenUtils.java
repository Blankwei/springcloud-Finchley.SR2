package com.savoidage.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: created by savoidage
 * CreateTime: 2019-11-16 16:52
 * Description: jwt 加密解密
 */
public class TokenUtils {

    private static Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    // feign key
    private static final String DEMO_FEIGN_KEY = "thisisaprivatekey";
    // feign value
    private static final String DEMO_FEIGN_VALUE = "thisisaprivatevalue";
    // 过期时间 默认30分钟
    private static final int EXPIRE_TIME = 60 * 30;
    // token主体
    private static final String TOEKN_SUBJECT = "feign-token";

    /**
     * 生成feign请求的头部信息
     * @return 加密体
     */
    public static String getFeignToken(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE,EXPIRE_TIME);
        Date expireDate = calendar.getTime();
        Map<String,String> info = new HashMap<String, String>();
        info.put(DEMO_FEIGN_KEY,DEMO_FEIGN_VALUE);
        String subject = TOEKN_SUBJECT;
        return encode(info,subject,expireDate);
    }

    /**
     * 效验是否属于来自api的请求
     * @param headerToken 头部token
     * @return 效验结果
     */
    public static boolean getIsApiRequest(String headerToken){
        boolean isContinue = false;
        try {
            Claims claims = decode(headerToken);
            if(null != claims){
                if(null!=claims.get(DEMO_FEIGN_KEY)) {
                    String feginValue = claims.get(DEMO_FEIGN_KEY).toString();
                    if (feginValue.equalsIgnoreCase(DEMO_FEIGN_VALUE)) {
                        isContinue = true;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("解密feign-token失败...");
            e.printStackTrace();
        }
        return isContinue;
    }

    /**
     * 自定义生成jwt信息
     * @param info 生成字段
     * @param subject 标题
     * @param expireDate 过期时间
     * @return 返回生成结果
     */
    public static String encode(Map<String,String> info, String subject, Date expireDate){
        // 设置生成的参数
        JwtBuilder builder = Jwts.builder().setSubject(subject);
        for (Map.Entry<String,String> map:info.entrySet()){
            String key = map.getKey();
            String value = map.getValue();
            builder.claim(key,value);
        }
        // 设置过期时间
        builder.setExpiration(expireDate);
        String jwtString = builder.signWith(SignatureAlgorithm.HS512,"demo-token-key").compact();
        return jwtString;
    }

    /**
     * 解密token
     * @param headerToken token
     * @return 断言
     * @throws Exception 异常
     */
    public static Claims decode(String headerToken) throws Exception{
        Claims claims = Jwts.parser().setSigningKey("demo-token-key").parseClaimsJws(headerToken).getBody();
        return claims;
    }
}
