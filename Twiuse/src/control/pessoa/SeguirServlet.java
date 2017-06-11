package control.pessoa;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class SeguirServlet
 */
@WebServlet("/SeguirServlet")
public class SeguirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeguirServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>");
		out.println("Método de requisição inválido!");
		out.println("</h1>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Seguindo: " + request.getParameter("idPessoa"));
		PessoaDao pdao = new PessoaDao((Connection) request.getAttribute("conexao"));
		HttpSession session = request.getSession();
		Pessoa p = (Pessoa)session.getAttribute("usuario");
		Pessoa p2 = pdao.retornaPessoaById(Integer.parseInt(request.getParameter("idPessoa")));
		pdao.seguirPessoa(Integer.parseInt(request.getParameter("idPessoa")), p.getId());
		
		RequestDispatcher rd = request.getRequestDispatcher("/VisitarServlet?pessoa="+p2.getUsuario());
		rd.forward(request, response);
	}

}
