package com.kdmeubichinho.enums;

import org.springframework.http.HttpStatus;

public enum EnumException {

	ITEM_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "O recurso solicitado não está disponivel no servidor");

	private final HttpStatus httpStatus;
	private final String description;

	EnumException(HttpStatus httpStatus, String description) {
		this.httpStatus = httpStatus;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
