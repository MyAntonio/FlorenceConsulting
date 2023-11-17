package it.florenceconsulting.esercizio.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String, String> errori = new HashMap<>();
		
		//Prendo tutti gli errori e per ogni campo salvo nome ed errore
		for(ObjectError errore : ex.getBindingResult().getAllErrors()) {
			String nomeCampo = ((FieldError) errore).getField();
			String messaggio = errore.getDefaultMessage();
			errori.put(nomeCampo, messaggio);
		}
		return new ResponseEntity<Object>(errori, HttpStatus.BAD_REQUEST);
	}
}