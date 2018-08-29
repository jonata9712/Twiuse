package control.pessoa;

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
import model.Twitter;

/**
 * Servlet implementation class VisitarServlet
 */
@WebServlet("/VisitarServlet")
public class VisitarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisitarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		PessoaDao pdao = new PessoaDao((Connection) request.getAttribute("conexao"));
		TwitterDao tdao = new TwitterDao((Connection) request.getAttribute("conexao"));
		Pessoa p = pdao.retornaPessoaByUsuario(request.getParameter("pessoa"));
		Pessoa usuarioAtivo = (Pessoa) session.getAttribute("usuario");
		List<Twitter> listatwt = tdao.listarTwitterPessoa(p.getId());
		session.setAttribute("pessoa", p);
		session.setAttribute("listatwt", listatwt);
		List<Pessoa> todasPessoas = pdao.listarTodasQuemSeguir(usuarioAtivo.getId());
		session.setAttribute("listaTodasPessoas", todasPessoas);
		boolean segue = pdao.verificaSeguindo(p.getId(), usuarioAtivo.getId());
		if(segue){
			session.setAttribute("botaoDeixarSeguir", segue);
		}else{
			session.setAttribute("botaoDeixarSeguir", null);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/visitante.jsp");
		rd.forward(request, response);
	}

}
