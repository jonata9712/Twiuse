package control.pessoa;

import java.io.IOException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import dao.pessoa.PessoaDao;
import model.Pessoa;

/**
 * Servlet implementation class EnviarFoto
 */
@WebServlet("/uploadFoto")
public class EnviarFoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnviarFoto() {
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
		HttpSession session = request.getSession();
		Pessoa pessoa = (Pessoa) session.getAttribute("usuario");
		
		PessoaDao dao = new PessoaDao( (Connection) request.getAttribute("conexao"));
		
		// ADICIONA FOTO
		boolean isMultiPart = FileUpload.isMultipartContent(new ServletRequestContext(request));
		if (isMultiPart) {
			System.out.println("multipart content");
			FileItemFactory factory = new DiskFileItemFactory();

			ServletFileUpload upload = new ServletFileUpload(factory);

			String formulario = "";

			try {

				List items = upload.parseRequest(new ServletRequestContext(request));

				Iterator iter = items.iterator();

				while (iter.hasNext()) {

					FileItem item = (FileItem) iter.next();

					if (item.getFieldName().equals("tipoForm")) {

						formulario = item.getString();

					}

					if (!item.isFormField()) {

						if (item.getName().length() > 0) {

							dao.inserirImagem(item, pessoa.getId());

						}

					}

				}

			}

			catch (FileUploadException ex) {

				ex.printStackTrace();

			}

			catch (Exception ex) {

				ex.printStackTrace();

			}

		}
		// ADICIONA FOTO

		
		RequestDispatcher rd = request.getRequestDispatcher("/inicio");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
