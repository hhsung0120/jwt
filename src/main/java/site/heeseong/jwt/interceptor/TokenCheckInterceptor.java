package site.heeseong.jwt.interceptor;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import site.heeseong.jwt.util.Jwt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenCheckInterceptor implements AsyncHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwt = request.getHeader("jwt");
        System.out.println("요청으로 들어온 토큰 => " + jwt);
        if (jwt != null && !"".equals(jwt)) {
            try {
                Jwt.verification(jwt);
            } catch (Exception e) {
                response.sendRedirect("/invalidToken");
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }
}
