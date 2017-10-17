package com.sm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class BusinessExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		
		BusinessException businessException=null;
		
		if(ex instanceof BusinessException){
			businessException = (BusinessException) ex;
		}else{
			businessException = new BusinessException("未知错误");
		}
		
		ModelAndView modelAndView = new ModelAndView();
		//把错误信息传递到页面
		modelAndView.addObject("msg", businessException.getMsg());
		//指向错误页面
		modelAndView.setViewName("error");
		return modelAndView;

	}

}
