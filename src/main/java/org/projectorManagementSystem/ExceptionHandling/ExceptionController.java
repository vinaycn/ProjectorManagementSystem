package org.projectorManagementSystem.ExceptionHandling;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;;

@ControllerAdvice
public class ExceptionController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleNotFoundException(final HttpServletRequest request){
		logger.error("-- 404 for this " + " " + request.toString());
		return "error";
	}
	
	
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public ResponseEntity<String> handleGeneralException(final Exception exception,HttpServletRequest request) {
        //logException(exception);
    	logger.error("Exception for request " + request.toString() + "With exception message " +exception.getMessage());
    	return new ResponseEntity<String>("Something Went wrong with us. Please try after some time",HttpStatus.INTERNAL_SERVER_ERROR);
    }

   
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class })
    @ResponseBody
    public ResponseEntity<String> handleBadRequestException(final Exception exception,final HttpServletRequest request) {

    	logger.error("Badrequest " + request.toString() + "With exception message " +exception.getMessage());
    	return new ResponseEntity<String>("Unable to process the request.Please  try after some time ",HttpStatus.BAD_REQUEST);
    }
}
