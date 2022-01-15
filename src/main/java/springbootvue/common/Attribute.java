package springbootvue.common;

public class Attribute {

	private String name;

	private String namespace;

	private String value;

	public Attribute() {
		super();
	}

	public Attribute(String name, String namespace) {
		super();
		this.name = name;
		this.namespace = namespace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
