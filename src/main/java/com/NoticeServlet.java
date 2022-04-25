 package com;

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

import model.Notice;
import model.NoticeDao;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet("/")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeDao noticeDao;
       
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init() throws ServletException {
		noticeDao = new NoticeDao();
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
		switch(action) {
		
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
				deleteNotice(request, response);
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
				updateNotice(request, response);
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
				listNotice(request, response);
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
		
		
		private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			RequestDispatcher dispatcher = request.getRequestDispatcher("notice-form.jsp");
			dispatcher.forward(request, response);
			
		}
		//insert notice
		private void insertNotice(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
			String topic = request.getParameter("topic");
			String areasAffected = request.getParameter("areasAffected");
			String date = request.getParameter("date");
			String details = request.getParameter("details");
			Notice newNotice = new Notice(topic, areasAffected, date, details);
			    noticeDao.insertNotice(newNotice);
			response.sendRedirect("list");
		}
		
		//delete Notice 
		private void deleteNotice(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				noticeDao.deleteNotice(id);
			}catch(Exception e) {
				e.printStackTrace();
			}
			response.sendRedirect("list");
		}
		
		//edit Notice
		private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
			int id = Integer.parseInt(request.getParameter("id"));
			
			Notice existingNotice;
			try {
				existingNotice = noticeDao.selectNotice(id);
				RequestDispatcher dispatcher = request.getRequestDispatcher("notice-form.jsp");
				request.setAttribute("notice", existingNotice);
				dispatcher.forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//update Notice
		private void updateNotice(HttpServletRequest request, HttpServletResponse response)throws SQLException, IOException {
			    int id = Integer.parseInt(request.getParameter("id"));
			String topic = request.getParameter("topic");
			String areasAffected = request.getParameter("areasAffected");
			String date = request.getParameter("date");
			String details = request.getParameter("details");
			
			Notice notice = new Notice(id, topic, areasAffected, date, details);
			     noticeDao.updateNotice(notice);
			     response.sendRedirect("list");
		}
	
       //default
		private void listNotice(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
			try {
				List<Notice> listNotice = noticeDao.selectAllNotices();
				request.setAttribute("listNotice", listNotice);
				RequestDispatcher dispatcher = request.getRequestDispatcher("notice-list.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}


}
	
	
	


