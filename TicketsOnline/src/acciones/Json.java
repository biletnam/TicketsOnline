package acciones;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Json extends ActionSupport {
	private static final long serialVersionUID = 1662872640323562930L;
	public static final String JSON = "json";
	
	private int limit = 1;
	private int page = 1;
	private int total = 0;
	private int estado = 0;
	private Object data;
	private String msg = ""; 
	private String evento = "";
	private String eventoNombre = "";
	private boolean success = true;
	private String usrType = "0";
	private int state = 0;
	
	
	public String getActionName() {
	    return ActionContext.getContext().getName();
	}
	
	public String getActionNamePrefix() {
		String an = getActionName();
	    return an.indexOf('_')>0? an.substring(0, an.indexOf('_')) : an;
	}
	
	private HashMap<String, Format> mapFormats = new HashMap<String, Format>();
	public String formatDate(Date date) {
		String frm = getText("format.date");
		Format format = mapFormats.get(frm);
		if(format==null){
			format = new SimpleDateFormat(frm);
			mapFormats.put(frm, format);
		}
		return format.format(date);
	}
	public Date parseDate(String date) throws ParseException {
		String frm = getText("format.date");
		Format format = mapFormats.get(frm);
		if(format==null){
			format = new SimpleDateFormat(frm);
			mapFormats.put(frm, format);
		}
		return (Date)format.parseObject(date);
	}
	
	public String getJsonData(){
		try {
			HashMap<String, Object> jsonData = new HashMap<String, Object>();
			jsonData.put("success", getSuccess());
			jsonData.put("limit", Integer.valueOf(getLimit()));
			jsonData.put("page", Integer.valueOf(getPage()));
			jsonData.put("total", getTotal());
			jsonData.put("data", getData());
			jsonData.put("estado", estado);
			jsonData.put("msg", msg);
			jsonData.put("evento", evento);
			jsonData.put("eventoNombre", eventoNombre);
			jsonData.put("usrType", usrType);
			jsonData.put("state", state);
			return JSONUtil.serialize(jsonData);
		} catch (Exception e) {
			LOG.equals(e);
		}
		return "{}";
	}
	
	@SuppressWarnings("rawtypes")
	public int getSize(Object data){
		if(data==null){
			return 0;
		}
		int total=1;
		if(data instanceof Collection){
			total = ((Collection)data).size();
		} else if(data instanceof Map){
			total = ((Map)data).size();
		}
		return total;
	}

	private Map<String, Object> parameters = null;
	public Map<String, Object> getParameters(){
		if(parameters==null){
			parameters=ActionContext.getContext().getParameters();
		}
		return parameters;
	}
	public Object getParam(String key){
		return getParameters().get(key);
	}
	public String getParamStr(String key){
		Object val = getParam(key);
		if(val==null){
			return null;
		}
		if(val instanceof String[]){
			return String.valueOf(((String[])val)[0]);
		}
		return String.valueOf(val);
	}
	public String getParamStrNoNull(String key){
		String val = getParamStr(key);
		if(val==null){
			val="";
		}else{
			val=val.trim();
		}
		return val;
	}
	public int getParamInt(String key, int defValue){
		try {
			String val = getParamStr(key);
			if(val!=null){
				return Integer.parseInt(val);
			}
		} catch (NumberFormatException e) {
			//Nada que hacer con e
			return defValue;
		} catch (Exception e) {
			LOG.error(e.toString());
		}
		return defValue;
	}
	public int getParamInt(String key){
		return getParamInt(key, 0);
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		if(total==0){
			total = getSize(data);
		}
		return total;
	}
	public int getTotalParam() {
		return getParamInt("total");
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public void setEstado(int estado_) {
		this.estado = estado_;
	}
	
	public void initPaging() {
		setLimit(getParamInt("limit"));
		setPage(getParamInt("page", 1));
		setTotal(getParamInt("total", 0));
	}
	
	public String index(){
		return SUCCESS;
	}
	
	public static String noNulo(String cadena) {
		return noNulo(cadena, "");
	}

	public static String noNulo(String cadena, boolean trim) {
		return noNulo(cadena, "", trim);
	}

	public static String noNulo(String cadena, String valDefault) {
		return noNulo(cadena, valDefault, true);
	}

	/** @return String con la Cadena sin espacio en blanco, de cadena ser nula o vac�a retorna el valor de Default */
	public static String noNulo(String cadena, String valDefault, boolean trim) {
		if (cadena == null || (trim && cadena.trim().equals(""))){
			return valDefault;
		}else if (trim){
			return cadena.trim();
		}else{
			return cadena;
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getEventoNombre() {
		return eventoNombre;
	}

	public void setEventoNombre(String eventoNombre) {
		this.eventoNombre = eventoNombre;
	}

	public String getUsrType() {
		return usrType;
	}

	public void setUsrType(String usrType) {
		this.usrType = usrType;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
}
