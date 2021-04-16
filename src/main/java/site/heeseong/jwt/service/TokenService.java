package site.heeseong.jwt.service;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    final String secretKey = "token";

    public String testToken() {
        String token = createToken();
        System.out.println(token);
        return token;
    }

    public String createToken(){
        //토큰 구조
        //header.payload.signature

        //토큰 헤더
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        //정
        Map<String, Object> payload = new HashMap<>();
        payload.put("data", this.dummyData());

        //토큰 유효 시간 2시간
        //1000 * 60 = 1분
        //1000 * 60 * 60 = 1시간
        //1000 * 60 * 60 * 2 = 2시
        int expireTime = 1000 * 60;

        //토
        Date expireDate = new Date(); // 토큰 만료 시간
        expireDate.setTime(expireDate.getTime() + expireTime);

        //토큰 발급
        String jwt = Jwts.builder()
                .setHeader(header)
                .setClaims(payload)
                .setSubject("accountUser")
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();

        return jwt;
    }

    private Map<String, String> dummyData(){
        Map<String, String> dummyData = new HashMap<>();
        dummyData.put("userId","hhsung0120");
        dummyData.put("userName","한희성");

        return dummyData;
    }

    public Map<String, Object> verificationJwt(String jwt) {
        Map<String, Object> claimsData = new HashMap<>();

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey.getBytes("UTF-8")) // Set Key
                    .parseClaimsJws(jwt) // 파싱 및 검증
                    .getBody();
            claimsData = claims;

        } catch (ExpiredJwtException e) {
            // 토큰이 만료
            e.printStackTrace();
        } catch (Exception e) {
            // 그외 에러
            e.printStackTrace();
        }

        System.out.println(claimsData);
        System.out.println(claimsData.get("sub"));
        System.out.println(claimsData.get("data"));
        System.out.println(claimsData.get("data"));
        Map<String, Object> data = (Map<String,Object>)claimsData.get("data");
        System.out.println(data.get("userName"));

        return claimsData;
    }
}
