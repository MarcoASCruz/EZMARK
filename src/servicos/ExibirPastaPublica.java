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

import modelos.Pasta;

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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			if(pastaPublica(idPasta)){
				request.setAttribute("pasta", buscarPastaRaiz(idPasta));
			}
			else{
				request.setAttribute("erro", erroPasta(1));
//				throw new Exception("pasta é privada!");
			}
			request.getRequestDispatcher("/PastaPublica.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", erroPasta(2));
			request.getRequestDispatcher("/PastaPublica.jsp").forward(request, response);
		}

	}
	
	private boolean pastaPublica (int idPasta) throws SQLException{
		return new DAO.PastaDAO().buscar(idPasta).isPublica();
	}
	
	private JSONObject buscarPastaRaiz (int idPasta) throws Exception{
		JSONObject pasta = new JSONObject();
		ArrayList<Pasta> l = new ArrayList<Pasta>();
		l.add(new DAO.PastaDAO().buscarPastaCompleta(idPasta));
		pasta.put("pasta", l);
		return pasta;
	}
	
	private JSONObject erroPasta (int numeroErro){
		JSONObject erro = new JSONObject();
		String titulo="Erro!";
		String mensagem="Erro desconhecido!";
		
		if (numeroErro == 1)
		{
			titulo = "Erro!";
			mensagem = "Pasta Privada!";
		}
		
		if (numeroErro == 2){
			titulo = "Erro!";
			mensagem = "Pasta não encontrada.";
		}
		erro.put("titulo", titulo);
		erro.put("erro", mensagem);
		return erro;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
