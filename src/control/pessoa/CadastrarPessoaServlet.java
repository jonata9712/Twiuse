package control.pessoa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.pessoa.PessoaDao;

/**
 * Servlet implementation class CadastrarPessoaServlet
 */
@WebServlet(urlPatterns = "/CadastrarPessoaServlet", asyncSupported = true)
public class CadastrarPessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CadastrarPessoaServlet() {
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
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>");
		out.println("M�todo de requisi��o n�o suportado para esta opera��o!");
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
		System.out.println("cadastrando pessoa");
		String nome = request.getParameter("nome");
		String usuario = request.getParameter("usuario");				
		String senha = request.getParameter("senha");
		
		PessoaDao dao = new PessoaDao((Connection) request.getAttribute("conexao"));
		if (!dao.verificaExisteNomeUsuario(usuario).isEmpty()) {
			System.out.println("verificando usu�rio existente");
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<h1>");
			out.println("Este usu�rio j� existe!");
			out.println("</h1>");
			out.println("</body>");
			out.println("</html>");
			System.out.println("passou no teste de usu�rio");
		} else {
			try {
				dao.incluirPessoa(nome, usuario,
						senha);
				RequestDispatcher rd = request.getRequestDispatcher("/LoginServlet?usuario="+usuario+"&senha="+senha);
				System.out.println("Pessoa inclusa");
				rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
				e.printStackTrace();
			}
		}

	}

}
