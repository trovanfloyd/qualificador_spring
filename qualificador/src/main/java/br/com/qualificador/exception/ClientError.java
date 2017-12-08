package br.com.qualificador.exception;

import java.util.List;

import javax.ws.rs.core.GenericEntity;

import org.json.JSONObject;

public class ClientError {

	private List<String> userMessages;
	private List<String> devMessages;
	private Long errorCode;

	public ClientError() {

	}

	public ClientError(List<String> userMessages) {
		this.setUserMessages(userMessages);
	}

	public ClientError(List<String> userMessage, List<String> devMessage) {
		this.setUserMessages(userMessage);
		this.setDevMessages(devMessage);
	}

	public ClientError(List<String> userMessages, Long errorCode) {
		this.setUserMessages(userMessages);
		this.setErrorCode(errorCode);
	}

	public ClientError(List<String> userMessage, List<String> devMessage, Long errorCode) {
		this.setUserMessages(userMessage);
		this.setErrorCode(errorCode);
		this.setDevMessages(devMessage);
	}

	public Long getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Long errorCode) {
		this.errorCode = errorCode;
	}

	public GenericEntity<String> toErrorResponse() {
		JSONObject response = new JSONObject();
		response.accumulate("erros", this.toJson());
		return new GenericEntity<String>(response.toString()) {};
	}

	public String toJson() {
		return new JSONObject(this).toString();
	}

	public List<String> getUserMessages() {
		return userMessages;
	}

	public void setUserMessages(List<String> userMessages) {
		this.userMessages = userMessages;
	}

	public List<String> getDevMessages() {
		return devMessages;
	}

	public void setDevMessages(List<String> devMessages) {
		this.devMessages = devMessages;
	}

}
