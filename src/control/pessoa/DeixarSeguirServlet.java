package control.pessoa;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.pessoa.PessoaDao;
import model.Pessoa;

/**
 * Servlet implementation class DeixarSeguirServlet
 */
@WebServlet("/DeixarSeguirServlet")
public class DeixarSeguirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeixarSeguirServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PessoaDao pdao = new PessoaDao((Connection) request.getAttribute("conexao"));
		HttpSession session = request.getSession();
		int idPessoa = Integer.parseInt(request.getParameter("idPessoa"));
		Pessoa usuarioAtivo = (Pessoa)session.getAttribute("usuario");
		pdao.deixarDeSeguir(idPessoa, usuarioAtivo.getId());
		Pessoa p2 = pdao.retornaPessoaById(Integer.parseInt(request.getParameter("idPessoa")));
		RequestDispatcher rd = request.getRequestDispatcher("/VisitarServlet?pessoa="+p2.getUsuario());
		rd.forward(request, response);
	}

}
