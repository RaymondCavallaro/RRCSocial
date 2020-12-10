package social.teste.resource.server.interceptor;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.util.StdDateFormat;

public class TimestamptzHeaderInterceptor implements HandlerInterceptor {

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		response.addHeader("timestamptz",
				ZonedDateTime.now().format(DateTimeFormatter.ofPattern(StdDateFormat.DATE_FORMAT_STR_ISO8601)));
	}
}