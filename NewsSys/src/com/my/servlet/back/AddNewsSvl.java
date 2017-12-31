package com.my.servlet.back;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.my.biz.NewsBiz;
import com.my.entity.TNews;
import com.my.entity.TCategory;
import com.my.util.Log;
import com.my.util.NewsUtil;

/**
 * Servlet implementation class ItemsSvl
 */
public class AddNewsSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewsSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TCategory> allcg = null;
		NewsBiz biz = new NewsBiz();
	    try {
			allcg = biz.getAllcategory();
		} catch (Exception e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
		}
		request.setAttribute("allcg", allcg);
		request.getRequestDispatcher("/WEB-INF/jsp/back/newsadd.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SmartUpload smu = new SmartUpload();
		smu.initialize(this.getServletConfig(), request, response);
		smu.setCharset("utf-8");
		smu.setAllowedFilesList("gif,jpg,png,bmp,jpeg");
		smu.setMaxFileSize(1024*1024);
		try {
			smu.upload();
			com.jspsmart.upload.Request req = smu.getRequest();
			String title = req.getParameter("title");
//			System.out.println("标题为=="+title);
			String text = req.getParameter("test-editormd-html-code");
//			System.out.println(text);
			String ntype = req.getParameter("ntype");
			String source = req.getParameter("source");
			File file = smu.getFiles().getFile(0);
			String imgpath ="";
			if(!file.getFileName().trim().equals("")){
//				System.out.println(file.getFileName());
				java.io.File realfile = new java.io.File("F:/img/"+file.getFileName());
//				System.out.println(realfile.getAbsolutePath());
				file.saveAs(realfile.toString());
				
				imgpath = "http://10.0.19.79:8080/img/"+file.getFileName();
			}
			TNews news = new TNews();
			news.setCid(ntype);
			SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
			String nid = "n"+format.format(new Date());//n+时间 作为新闻的id
			news.setCid(ntype);
			news.setNid(nid);
			news.setPubtime(new Date());
			news.setCoverurl(imgpath);
			news.setSource(source);
			news.setTitle(title);
			news.setInfo(text);
			NewsBiz biz = new NewsBiz();
			biz.saveNews(news);
			//生成新闻页面
			NewsUtil.creatNews(news);
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		} catch (SmartUploadException e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
			request.getRequestDispatcher("/failed.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			Log.logger.error(e.getMessage());
			request.getRequestDispatcher("/failed.jsp").forward(request, response);
		}
	}

}
