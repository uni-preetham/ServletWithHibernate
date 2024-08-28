package com.cl.example.entity;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 8536795752827314484L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<StudentInfo> studentList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			studentList = session.createQuery("from StudentInfo", StudentInfo.class).list();
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception
			request.setAttribute("error", "An error occurred while retrieving student data.");
			request.getRequestDispatcher("/error.jsp").forward(request, response); //
			// Forward to an error page
			return;
		}

		request.setAttribute("students", studentList);
		request.getRequestDispatcher("/studentList.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		if ("insert".equals(action)) {
			insertStudent(request);
		} else if ("delete".equals(action)) {
			deleteStudent(request);
		} else if ("update".equals(action)) {
			updateStudent(request);
		}
		response.sendRedirect("students");
	}

	private void insertStudent(HttpServletRequest request) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();
			StudentInfo student = new StudentInfo();
			student.setStudentName(name);
			student.setStudentEmail(email);
			session.save(student);
			tx.commit();
		}
	}

	private void deleteStudent(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();
			StudentInfo student = session.get(StudentInfo.class, id);
			if (student != null) {
				session.delete(student);
			}
			tx.commit();
		}
	}

	private void updateStudent(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("id"));
		String email = request.getParameter("email");
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();
			StudentInfo student = session.get(StudentInfo.class, id);
			if (student != null) {
				student.setStudentEmail(email);
				session.update(student);
			}
			tx.commit();
		}
	}
}
