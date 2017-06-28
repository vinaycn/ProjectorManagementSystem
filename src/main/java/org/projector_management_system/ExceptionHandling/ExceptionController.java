package org.projector_management_system.ExceptionHandling;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;;

@ControllerAdvice
public class ExceptionController {

	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFoundException(){
		return "error";
	}
	
	
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public String handleGeneralException(final Exception exception,HttpServletRequest request) {
        //logException(exception);
    	System.out.println(exception.getMessage());
    	return "something went wrong with us! Please try after some time";
    }

   
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class })
    @ResponseBody
    public String handleBadRequestException(final Exception exception) {

        return "Sorry we cant process your request";
    }
}
