package cn.edu.cqut.weknow.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.cqut.weknow.customer.po.WeKnowException;

public class WeKnowExceptionResolver implements HandlerExceptionResolver
{
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex)
	{
		WeKnowException weKnowException = null;
		if(ex instanceof WeKnowException)
			weKnowException = (WeKnowException)ex;
		else
			weKnowException = new WeKnowException("未知错误："+ex.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorMessage", weKnowException.getMessage());
		modelAndView.setViewName("global/error");
		return modelAndView;
	}
}
