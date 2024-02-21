package com.pkm.edu.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements AsyncHandlerInterceptor {
	
	
	//preHandle란? => 비동기 요청이 처리되기 전에 호출됨, 반환은 boolean값을 반환하고 false를 반환하면 해당 요청은 처리되지 않음
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("인터셉터 시작~~~ 로그인 세션확인.... 존재하면 true, 없다면 false & logout.do 호출");
		if(request.getSession().getAttribute("loginVo") == null) {
			log.info("로그인 정보가 없습니다. ");
			response.sendRedirect("./logout.do");
			return false;
		}
		
		return true;
	}

	//postHandle란? => 요청 처리가 완료되고 뷰가 렌더링 되기 전에 호출
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("인터셉터 종료");
		AsyncHandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	//afterCompletion란? => 요청처리가 완료되고 뷰가 렌더링 된 후 호출, 예외가 발생하면 afterCompletion 에서드는 예외처리를 하고 다시
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("인터셉터 View 렌더링이 끝난 직후");
		AsyncHandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	//afterConcurrentHandlingStarted : 비동기식 요청이 처리되기 시작했을 때 호출된다, 반환타입이 void 이다.
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("비동기(ResponseBody)식 호출되었을때 호출");
		AsyncHandlerInterceptor.super.afterConcurrentHandlingStarted(request, response, handler);
	}
	
	
	
	
}
