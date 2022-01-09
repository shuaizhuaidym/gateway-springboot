package springbootvue.common;

import java.util.Map;

public class AuthenticaitonResult {
	String messageState;// true/false

	String authMode;
	String success;
	
	String messageCode;
	String messageDesc;
	
	String accessControlResult;// Permit/Deny

	Map<String, String> attributes;
	String token;

	public String getAuthMode() {
		return authMode;
	}

	public void setAuthMode(String authMode) {
		this.authMode = authMode;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessageState() {
		return messageState;
	}

	public void setMessageState(String messageState) {
		this.messageState = messageState;
	}

	public String getAccessControlResult() {
		return accessControlResult;
	}

	public void setAccessControlResult(String accessControlResult) {
		this.accessControlResult = accessControlResult;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessageDesc() {
		return messageDesc;
	}

	public void setMessageDesc(String messageDesc) {
		this.messageDesc = messageDesc;
	}

}
