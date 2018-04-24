package br.com.prefeitura.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController{
	
	private static final Logger LOGGER = 
		      Logger.getLogger(ExceptionController.class); 
	
	@ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError500(HttpServletRequest request, Exception e)   {
		LOGGER.info(request.getRequestURI() +
   			 " raised " + e);
        return new ModelAndView("contratos");
    }
	
	
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView handleError(HttpServletRequest request, Exception e)   {
    	
    	LOGGER.info(request.getRequestURI() +
    			 " raised " + e);
        return new ModelAndView("error");
    }
	
//    @ExceptionHandler(PortalCustomException.class)
//    @RequestMapping("/error2")
//    public ModelAndView handleError400(HttpServletRequest request, Exception e)   {
////        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + e);
//    	
//    	LOGGER.info(request.getRequestURI() +
//    			 " raised " + e);
//        return new ModelAndView("error2");
//    }
    

//    @ExceptionHandler(NullPointerException.class)
//    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
////        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Request: " + request.getRequestURL() + " raised " + e);
//    	
//    	LOGGER.info(request.getRequestURI() +
//    			 " raised " + e);
//        return new ModelAndView("contratos");
//    }
    
}