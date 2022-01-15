package springbootvue.sso;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import springbootvue.common.AuthenticaitonResult;
public class TokenInterceptor implements HandlerInterceptor {
    public static final String header_token = "gateway_token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

        String token = request.getHeader(header_token);
        ObjectMapper mapper = new ObjectMapper();
        AuthenticaitonResult result;
        response.setCharacterEncoding("UTF-8");

        System.out.println(handler.getClass().getTypeName());
        if (StringUtils.isEmpty(token)||StringUtils.equals("null", token)) {
            logger.warn("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" + request.getRemoteAddr());
            result = new AuthenticaitonResult("true", "false", "A00F000", "用户未认证。");
            response.getWriter().write(mapper.writeValueAsString(result));
            return false;
        }

        result = (AuthenticaitonResult) request.getSession().getAttribute(header_token);
        if (result == null) {
            result = new AuthenticaitonResult("true", "false", "A00F001", "Token无效，请重新认证或提供有效Token。");
            response.getWriter().write(mapper.writeValueAsString(result));
            return false;
        }

        if (!StringUtils.equals("false", result.getMessageState())) {
            response.getWriter().write(mapper.writeValueAsString(result));
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
