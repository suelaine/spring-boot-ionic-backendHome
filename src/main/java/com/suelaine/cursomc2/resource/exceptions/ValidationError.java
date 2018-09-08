package com.suelaine.cursomc2.resource.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	
	private static final long serialVersionUID = 1L;
	private List<FieldMessage> erros = new ArrayList<>();
	
	
	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
		// TODO Auto-generated constructor stub
	}
	
	
	public List<FieldMessage> getErrors() {
		return erros;
	}

	public void addError(String fieldName,String message) {
		erros.add(new FieldMessage(fieldName,message));
	}


	
	

}
