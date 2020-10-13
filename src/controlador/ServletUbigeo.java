package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.Gson;

import entidad.Ubigeo;
import lombok.Getter;
import lombok.Setter;
import model.UbigeoModel;

@WebServlet("/cargaUbigeo")
public class ServletUbigeo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(ServletUbigeo.class);

	
	@Getter@Setter
	private List<Ubigeo> lstDepartamento = new ArrayList<Ubigeo>();
	
	@Getter@Setter
	private List<Ubigeo> lstProvincia = new ArrayList<Ubigeo>();
	
	@Getter@Setter
	private List<Ubigeo> lstDistrito = new ArrayList<Ubigeo>();



	private UbigeoModel model = new UbigeoModel();
	

	@SuppressWarnings("unchecked")
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String departamento = request.getParameter("departamento");
		String provincia = request.getParameter("provincia");
		
		Gson gson = new Gson();
		String json = "";
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("objDepartamentos") == null){
			lstDepartamento = model.traeDepartamentos();
			json = gson.toJson(lstDepartamento);
			session.setAttribute("objDepartamentos", lstDepartamento);
		}else{
			lstDepartamento = (List<Ubigeo>)session.getAttribute("objDepartamentos");
			json = gson.toJson(lstDepartamento);
		}
		
		if(departamento!= null && !departamento.trim().equals("")&& !departamento.trim().equals("-1")){
			Ubigeo ubigeoBean = new Ubigeo();
			ubigeoBean.setIdDepartamento(departamento);
			lstProvincia = model.traeProvincias(ubigeoBean);	
			json = gson.toJson(lstProvincia);
		}
		

		if(departamento!= null && provincia!= null && !departamento.trim().equals("") && !provincia.trim().equals("")&& !departamento.trim().equals("-1") && !provincia.trim().equals("-1")){
			Ubigeo ubigeoBean2 = new Ubigeo();
			ubigeoBean2.setIdDepartamento(departamento);
			ubigeoBean2.setIdProvincia(provincia);
			lstDistrito = model.traeDistritos(ubigeoBean2);	
			json = gson.toJson(lstDistrito);
		}
		
		

		log.info("json-->" + json);
		
		//Notificamos el tipo de archivo que se envía al browser
		response.setContentType("application/json;charset=UTF-8");

		// Se genera el archivo JSON y se envía al browser
		PrintWriter out = response.getWriter();
		out.println(json);
		
	}
	
	


	

}
