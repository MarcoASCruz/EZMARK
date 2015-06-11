package modelos;

import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.omg.PortableInterceptor.SUCCESSFUL;

public class AppResponse {
	private boolean sucess;
	private JSONObject jsonResult;
	
	public AppResponse(){
		 jsonResult = new JSONObject();
	}
	
	public boolean getSuccess(){
		return sucess;
	}
	public void setSuccess(boolean sucess){
		this.sucess = sucess;
		jsonResult.put("sucess", sucess);
	}
	
	public void setContent(String content){
		jsonResult.put("content", content);
	}
	public void setContent(Object content){
		jsonResult.put("content", content);
	}
	
	public void setStackTrace(StackTraceElement[] stackTrace){
		jsonResult.put("stackTrace", stackTrace);
	}
	
	public void addException(Exception e){
		setSuccess(false);
		setContent(e.getMessage());
		setStackTrace(e.getStackTrace());
	}
	
	public Response buildResponse(){
		if (sucess == true){
			return buildOKResponse();
		}
		else {
			return buildErrorResponse();
		}
	}
	
	public Response buildOKResponse(){
		return Response.status(200).entity(jsonResult.toString()).build();
	}
	
	public Response buildErrorResponse(){
		return Response.status(500).entity(jsonResult.toString()).build();
	}
	
}
