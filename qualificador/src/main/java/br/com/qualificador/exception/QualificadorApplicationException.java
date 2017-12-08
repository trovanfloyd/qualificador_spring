package br.com.qualificador.exception;

import javax.ejb.ApplicationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@ApplicationException(rollback = true)
public class QualificadorApplicationException extends WebApplicationException {

	private static final long serialVersionUID = 1L;

	public QualificadorApplicationException(ClientError clientError) {

		super(Response.status(Response.Status.BAD_REQUEST).entity(clientError.toErrorResponse()).build());

	}

	public QualificadorApplicationException(ClientError clientError, Integer StatusCode) {

		super(Response.status(StatusCode).entity(clientError.toErrorResponse()).build());

	}
}
