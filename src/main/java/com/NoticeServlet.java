package com.xadmin.notices.web;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xadmin.notices.bean.Notices;
import com.xadmin.notices.dao.NoticesDao;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/list")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticesDao noticesDao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		noticesDao = new NoticesDao();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch(action)
		{
		case "/new":
		   showNewForm(request, response);
		   break;
		   
		case "/insert":
			try {
				insertNotice(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   break;
			   
		case "/delete":
			try {
				deleteNotices(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/edit":
			try {
				showEditForm(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			   
		case "/update":
			try {
				updateNotices(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 break;
			   
		default:
			try {
				listNotices(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
				   
		}
	}
		
		private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher("notices-form.jsp");
			dispatcher.forward(request, response);
		}
		
		//insert Notice
		private void insertNotice(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
		{
			String Topic = request.getParameter("Topic");
			String areasAffected = request.getParameter("areasAffected");
			String Date = request.getParameter("Date");
			String Details = request.getParameter("Details");
			Notices newNotice = new Notices(Topic, areasAffected, Date, Details);
			
			noticesDao.insertNotice(newNotice);
			response.sendRedirect("list");
		}
		
		//delete Notice
		private void deleteNotices(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			int noticeID = Integer.parseInt(request.getParameter("noticeID"));
			try {
				noticesDao.deleteNotices(noticeID);
			}catch (Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("list");
		}
		
		//edit Notices
		private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException  {
			int noticeID = Integer.parseInt(request.getParameter("noticeID"));
			
			Notices existingNotices;
			try {
				existingNotices = noticesDao.selectNotices(noticeID);
				RequestDispatcher dispatcher = request.getRequestDispatcher("notice-form.jsp");
				request.setAttribute("Notices", existingNotices);
				dispatcher.forward(request, response);
			}catch (Exception e){
				e.printStackTrace();
			}
			
		}
		
		//update Notices
		private void updateNotices(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			int noticeID = Integer.parseInt(request.getParameter("noticeID"));
			String Topic = request.getParameter("Topic");
			String areasAffected = request.getParameter("areasAffected");
			String Date = request.getParameter("Date");
			String Details = request.getParameter("Details");
			
			Notices notices = new Notices(Topic, areasAffected, Date, Details);
			noticesDao.updateNotices(notices);
			response.sendRedirect("list");
		}
		
		//default
		private void listNotices(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException  {
			try {
				List<Notices> listNotices = noticesDao.selectAllNotices();
				request.setAttribute("listNotices", listNotices);
				RequestDispatcher dispatcher = request.getRequestDispatcher("notice-form.jsp");
				dispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}


