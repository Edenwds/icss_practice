package com.icss.control.back;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.biz.BookBiz;
import com.icss.entity.TBook;
import com.icss.util.Log;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class BookAddSvl
 */
public class BookAddSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookAddSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/back/BookAdd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SmartUpload smu = new SmartUpload();
		smu.initialize(this.getServletConfig(), request, response);
		smu.setCharset("utf-8");
		smu.setAllowedFilesList("gif,jpg,png,bmp");
		smu.setMaxFileSize(200*1024);
		String isbn = null;
		TBook book = new TBook();
		try {
			smu.upload();
			com.jspsmart.upload.Request req = smu.getRequest();
			isbn = req.getParameter("isbn");
			String bname = req.getParameter("bname");
			String author = req.getParameter("author");
			String press = req.getParameter("press");
			String pudate = req.getParameter("pudate");
			String price = req.getParameter("price");
			book.setIsbn(isbn);
			book.setBname(bname);
			book.setAuthor(author);
			book.setPress(press);
			if(pudate != null && !pudate.trim().equals("")){
				SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				book.setPudate(format.parse(pudate));
			}
			if(price != null && !price.trim().endsWith("")){
				book.setPrice(Double.parseDouble(price));
			}
			File file = smu.getFiles().getFile(0);
			if(file != null){
				int size = file.getSize();
				byte [] bytesPic = new byte[size];
				for(int i = 0; i < size; i++){
					bytesPic[i] = file.getBinaryData(i);
				}
				book.setPic(bytesPic);
			}
			BookBiz biz =  new BookBiz();
			biz.addBook(book);
			request.setAttribute("msg", bname+"录入成功");
			request.getRequestDispatcher("/WEB-INF/back/BookAdd.jsp").forward(request, response);
			
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			request.setAttribute("book", book);
			request.setAttribute("msg", isbn+"已存在，请检查");
			request.getRequestDispatcher("/WEB-INF/back/BookAdd.jsp").forward(request, response);
		} catch (java.lang.SecurityException e) {
			request.setAttribute("book", book);
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/back.BookAdd.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
			request.setAttribute("errMsg","网络异常，请和管理员联系");
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
		
		
	}

}
