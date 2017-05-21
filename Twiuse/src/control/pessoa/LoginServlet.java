package control.pessoa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.pessoa.PessoaDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PessoaDao dao = new PessoaDao((Connection) request.getAttribute("conexao"));
		HttpSession session = request.getSession();
		if (!dao.loginUsuario(request.getParameter("usuario"), request.getParameter("senha")).isEmpty()
				&& session.getAttribute("usuario") == null) {

			session.setAttribute("usuario",
					dao.loginUsuario(request.getParameter("usuario"), request.getParameter("senha")).get(0));
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>");
			out.println("Logado com sucesso!");
			out.println("</h1>");
			out.println("</body>");
			out.println("</html>");

		} else if (session.getAttribute("usuario") != null) {
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>");
			out.println("Já existe um usuário logado, saia antes de iniciar uma nova sessão");
			out.println("</h1>");
			out.println("</body>");
			out.println("</html>");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>");
			out.println("Usuário e/ou senha inválidos");
			out.println("</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
