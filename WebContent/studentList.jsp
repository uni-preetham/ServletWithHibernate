<%@ page import="com.cl.example.entity.StudentInfo"%>
<%@ page import="java.util.List"%>
<html>
<head>
<title>Student List</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div class="container text-center w-100">
		<h2>Student List</h2><br/>
		<table class="table mx-auto w-75 text-center table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Email</th>
				</tr>
			</thead>
			<%
				List<StudentInfo> students = (List<StudentInfo>) request.getAttribute("students");
				if (students != null) {
					for (StudentInfo student : students) {
			%>
			<tbody>
				<tr>
					<td><%=student.getStudentId()%></td>
					<td><%=student.getStudentName()%></td>
					<td><%=student.getStudentEmail()%></td>
				</tr>
			</tbody>
			<%
				}
				} else {
					out.println("<tr><td colspan='3'>No students found.</td></tr>");
				}
			%>
		</table><br/><br/>

		<div class="forms d-flex justify-content-around">
			<div>
				<h3>Add New Student</h3>
				<form action="students" method="post">
					<input type="hidden" name="action" value="insert" /> 
					<label for="name">Name:</label>
						<input class="form-control" type="text" name="name" required />
					<br /> 
					<label for="email">Email: </label>
					<input class="form-control"
						type="text" name="email" required /><br />
					<button class="btn btn-primary" type="submit" value="Add Student">Add</button>
				</form>
			</div>


			<div>
				<h3>Update Student Email</h3>
				<form action="students" method="post">
					<input class="form-control" type="hidden" name="action"
						value="update" /> 
						<label for="id">ID: </label>
					<input class="form-control"
						type="text" name="id" required />
					<br /> 
					<label for="email">New Email: </label>
					<input class="form-control"
						type="text" name="email" required /><br />
					<button class="btn btn-warning" type="submit" value="Update Email">Update</button>
				</form>
			</div>


			<div>
				<h3>Delete Student</h3>
				<form action="students" method="post">
					<input type="hidden" name="action" value="delete" /> 
					<label for="id">ID:</label>
					<input class="form-control" type="text" name="id" required />
					<br />
					<button class="btn btn-danger" type="submit" value="Delete Student">Delete</button>
				</form>
			</div>
		</div>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>
