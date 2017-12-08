package br.com.qualificador.exception;

public class UserDevMessages {

	private String userMsg;
	private String devMsg;

	public UserDevMessages(String userMsg, String devMsg) {
		super();
		this.userMsg = userMsg;
		this.devMsg = devMsg;
	}

	public String getUserMsg() {
		return userMsg;
	}

	public void setUserMsg(String userMsg) {
		this.userMsg = userMsg;
	}

	public String getDevMsg() {
		return devMsg;
	}

	public void setDevMsg(String devMsg) {
		this.devMsg = devMsg;
	}

}
