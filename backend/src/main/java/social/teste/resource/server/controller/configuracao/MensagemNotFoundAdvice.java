package social.teste.resource.server.controller.configuracao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class MensagemNotFoundAdvice {

	@ResponseBody
	@ExceptionHandler(MensagemNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String employeeNotFoundHandler(MensagemNotFoundException ex) {
		return ex.getMessage();
	}
}