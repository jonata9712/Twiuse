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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				PessoaDao dao = new PessoaDao((Connection) request.getAttribute("conexao"));
				HttpSession session = request.getSession();
				Pessoa p = dao.loginUsuario(request.getParameter("usuario"), request.getParameter("senha"));
				if (p != null
						&& session.getAttribute("usuario") == null) {

					session.setAttribute("usuario", p);
					RequestDispatcher rd = request.getRequestDispatcher("/inicio");
					rd.forward(request, response);

				} else if (session.getAttribute("usuario") != null) {
					RequestDispatcher rd = request.getRequestDispatcher("/inicio");
					rd.forward(request, response);
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

}
