package springbootvue.message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springbootvue.sso.TokenInterceptor;
/**
 * pretend to be real data request must with token
 * 
 * @author yanming_dai
 *
 */
@RestController
public class DataController {
    @RequestMapping(value = "/data")
    public Object data(HttpServletRequest request, HttpServletResponse response) {
        return request.getSession(false).getAttribute(TokenInterceptor.header_token);
    }

}
