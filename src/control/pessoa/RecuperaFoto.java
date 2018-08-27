package control.pessoa;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.pessoa.PessoaDao;
import model.Pessoa;

/**
 * Servlet implementation class RecuperaFoto
 */
@WebServlet("/RecuperaFoto")
public class RecuperaFoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecuperaFoto() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	synchronized protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		OutputStream out = response.getOutputStream();
		HttpSession session = request.getSession();
		PessoaDao dao = new PessoaDao((Connection) request.getAttribute("conexao"));
		int id = Integer.parseInt(request.getParameter("id"));
		File file = dao.resgataImagem(id);
		FileInputStream in = new FileInputStream(file);
		System.out.println("Recuperando foto usuário id: "+id);
		
		 BufferedImage bufferedImage = ImageIO.read(file);
		 
		 // get DataBufferBytes from Raster
		 WritableRaster raster = bufferedImage .getRaster();
		 
		 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
		 
		 byte b[] = data.getData();
		 
		
		 int count = 0;
	       while ((count = in.read(b)) >= 0) {
	         out.write(b, 0, count);
	      }
	       in.close();
		response.setContentType("image/jpg");
		out.flush();
		out.close();
		
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
