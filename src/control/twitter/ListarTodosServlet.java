package control.twitter;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.pessoa.PessoaDao;
import dao.twitter.TwitterDao;
import model.Pessoa;

/**
 * Servlet implementation class ListarTodosServlet
 */
@WebServlet("/inicio")
public class ListarTodosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarTodosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		TwitterDao tdao = new TwitterDao((Connection) request.getAttribute("conexao"));
		PessoaDao pdao = new PessoaDao((Connection) request.getAttribute("conexao"));
		Pessoa p = (Pessoa) session.getAttribute("usuario");
		boolean leituraInicial = true;
		if(p == null){
			session.setAttribute("listatwt", tdao.listarTudo());
		}else{
			List<Pessoa> listaSeguidores = pdao.retornaSeguindo(p.getId());
			List<Pessoa> todasPessoas = pdao.listarTodasQuemSeguir(p.getId());
			session.setAttribute("listaTodasPessoas", todasPessoas);
			session.setAttribute("seguidores", listaSeguidores);
			session.setAttribute("listatwt", tdao.listarTwitterSeguindo(p.getId()));
		}
		session.setAttribute("leituraInicial", leituraInicial);
		RequestDispatcher rd = request.getRequestDispatcher("/inicio.jsp");
		rd.forward(request, response);
	}

}
