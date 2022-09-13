<%@page import="models.CatUser"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
  <%@include file="/templates/admin/inc/header.jsp" %>
  <%@include file="/templates/admin/inc/sidebar.jsp" %>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0">Loại người dùng</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
              <li class="breadcrumb-item active">Người dùng</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->
     <%
        	if(request.getAttribute("err") != null){
        		%>
        			<div class="alert alert-danger" role="alert">
						  ${err}
					</div>
        		<%
        	}
        %>
    <%
		if(!"".equals(request.getParameter("msg"))){
			String msg = request.getParameter("msg");
			if("OK".equals(msg)){
				%>
					<div class="alert alert-success" role="alert">
						 Xử lý thành công!
					</div>
				<%
			}
		}
	%>
	<%
		  	if(request.getAttribute("listCatUser") != null){
		  		List<CatUser> listCatUser = (List<CatUser>) request.getAttribute("listCatUser");
		  		if(listCatUser.size() > 0){
		  			%>
    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
           <div class="col-sm-6">
              
           </div>
        </div><br></br>
        <!-- /.row -->
        <table class="table table-striped table-bordered">
		  <thead>
		    <tr>
		      <th scope="col">STT</th>
		      <th scope="col">Loại tài khoản</th>
		      <th scope="col">Chức năng</th>
		    </tr>
		  </thead>
		  <tbody>
		  <%
		  int i = 0;
		  	for(CatUser objCatUser : listCatUser){
		  		i++;
		  		int idCatUser = objCatUser.getId();
		  		String catUser = objCatUser.getCatUser();
		  		%>
		  			<tr>
				      <th scope="row"><%=i %></th>
				      <td><%=catUser%></td>
				      <td>
				      <%
				      	if(idCatUser > 1){
				      		%>
								<a href="<%=request.getContextPath()%>/admin/categoriesuser/edit?id=<%=idCatUser %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
								
				      		<%
				      	}
				      %>
				      </td>
					</tr>
		  		<%
		  	}
		  %>
		  </tbody>
		</table>
        <!-- Main row -->
        
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
    <%
		  		}else{
		  			%>
		  				<div class="alert alert-danger" role="alert">
						  Không có dữ liệu
						</div>
		  			<%
		  		}
		  	}
		  %>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <%@include file="/templates/admin/inc/footer.jsp" %>