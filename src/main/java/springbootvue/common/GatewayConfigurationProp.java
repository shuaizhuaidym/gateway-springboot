package springbootvue.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
// @PropertySource("classpath:custom.properties")
@ConfigurationProperties(prefix = "gateway")
public class GatewayConfigurationProp {
	
	private String url_template = "%s://%s:%d/MessageService";
	
	private String schema;
	
    private String host;

    private int port;
    
    private String appID;

    private int random_source;
    
    private String message_version;

    private String access_control;
    
    private boolean pin_code;

    private String qrcode_poll_uri;

    private String qrcode_paint_uri;
    
    /***
     * build random and authenticate URL
     * @return
     */
	public String buildGatewayUrl() {
		return String.format(url_template, schema, host, port);
	}

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getRandom_source() {
        return random_source;
    }

    public void setRandom_source(int random_source) {
        this.random_source = random_source;
    }

    public String getAccess_control() {
        return access_control;
    }

    public void setAccess_control(String access_control) {
        this.access_control = access_control;
    }

    public String getQrcode_poll_uri() {
        return qrcode_poll_uri;
    }

    public void setQrcode_poll_uri(String qrcode_poll_uri) {
        this.qrcode_poll_uri = qrcode_poll_uri;
    }

    public String getQrcode_paint_uri() {
        return qrcode_paint_uri;
    }

    public void setQrcode_paint_uri(String qrcode_paint_uri) {
        this.qrcode_paint_uri = qrcode_paint_uri;
    }

    public boolean isPin_code() {
        return pin_code;
    }

    public void setPin_code(boolean pin_code) {
        this.pin_code = pin_code;
    }

	public String getAppID() {
		return appID;
	}

	public void setAppID(String appID) {
		this.appID = appID;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public String getMessage_version() {
		return message_version;
	}

	public void setMessage_version(String message_version) {
		this.message_version = message_version;
	}

}
