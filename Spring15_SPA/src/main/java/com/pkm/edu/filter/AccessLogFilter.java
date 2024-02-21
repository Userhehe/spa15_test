package com.pkm.edu.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessLogFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.debug("Client의 로그 : 필터를 통해 WAS에 접속한 정보를 확인한다~~~~~~~!");  //이걸 가지고 중복로그인을 확인한다던지하는 작업이 필요하다.
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		String remoteAddr = req.getRemoteAddr();
		String uri = req.getRequestURI();
		String queryString = (req.getQueryString()==null)?"":req.getQueryString();
		
		String referer = StringUtils.defaultIfEmpty(req.getHeader("Referer"),"-");
		String agent = StringUtils.defaultIfEmpty(req.getHeader("User-Agent"),"-");
		
		log.debug("요청된 주소:{}", remoteAddr.concat(":").concat(uri).concat("?").concat(queryString));
		log.debug("유입 경로: {}", referer);
		log.debug("유입 소프트웨어 정보: {}", agent);
		
		chain.doFilter(request, response);
	}

}
