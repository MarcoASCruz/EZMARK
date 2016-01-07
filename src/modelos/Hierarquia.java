package modelos;

public class Hierarquia {
	private String id;
	private String parent;
	private String text;
	private boolean publica;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		String result = null;
		if (parent.equals(id) && (id != null)){
			result = "#";
		}
		else {
			result = parent;
		}
		this.parent = result;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isPublica() {
		return publica;
	}
	public void setPublica(boolean publica) {
		this.publica = publica;
	}
	
}
