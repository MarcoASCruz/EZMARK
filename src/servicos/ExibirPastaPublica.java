package servicos;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class ExibirPastaPublica
 */
@WebServlet("/ExibirPastaPublica")
public class ExibirPastaPublica extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ExibirPastaPublica() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idPasta = Integer.parseInt(request.getParameter("id"));
		try {
			if(!pastaPublica(idPasta)){
				request.setAttribute("pasta", new DAO.PastaDAO().buscarPastaCompleta(idPasta));
				request.getRequestDispatcher("view/PastaPublica.jsp").forward(request, response);
			}
			else{
				throw new Exception("pasta é privada!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean pastaPublica (int idPasta) throws SQLException{
		return new DAO.PastaDAO().buscar(idPasta).isPublica();
	}
	
	private JSONObject buscarPastaRaiz (int idPasta) throws Exception{
		JSONObject pasta = new JSONObject();
		pasta.put("pasta", new DAO.PastaDAO().buscarPastaCompleta(idPasta));
		return pasta;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
