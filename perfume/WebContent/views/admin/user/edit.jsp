<%@page import="models.CatUser"%>
<%@page import="java.util.List"%>
<%@page import="models.User"%>
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
            <h1 class="m-0">Quản lý người dùng</h1>
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

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
           <div class="col-md-12">
                <h2>Sửa người dùng</h2>
            </div>
        </div>
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
        	if(request.getAttribute("user") != null){
        		User user = (User) request.getAttribute("user");
        		int id = user.getId();
        		String username = user.getUsername();
        		String fullname = user.getFullname();
        		int catUser = user.getCatUser().getId();
        		%>
        			<div class="row">
	        <div class="col-md-12">
	        	<div class="card">
			    	<div class="card-body">
			    		<div class="col-md-12">
			                <form role="form" method="post"id="form" action="<%=request.getContextPath()%>/admin/user/edit">
			                	<div class="form-group">
			                        <input type="hidden" id="id" value="<%=id %>" name="idUser" class="form-control"/>
			                    </div>
			                    
			                    
			                     <div class="form-group">
			                        <label for="fullname">Tên đăng nhập</label>
			                        <input type="text" id="username" value="<%=username %>" name="username" class="form-control" readonly />
			                    </div>
			                    <div class="form-group">
			                        <label for="fullname">Tên đầy đủ</label>
			                        <input type="text" id="fullname" value="<%=fullname %>" name="fullname" class="form-control" />
			                    </div>
			                    <div class="form-group">
			                        <label for="money">Số tiền</label>
			                        <input type="text" id="money" value="<%=user.getMoney()%>" name="money" class="form-control" onkeypress="validate(event)" />
			                    </div>
			                    
			                    <div class="form-group">
			                        <label for="cars">Loại tài khoản: </label>&emsp;
			                        <%
			                        	if(request.getAttribute("catUserList") != null){
			                        		List<CatUser> catUserList= (List<CatUser>) request.getAttribute("catUserList");
			                        		if(catUserList.size() > 0){
			                        			%>
			                        				<select name="catUser" id="cars">
													    <option value="0">--- chọn ---</option>
													    <%
													    	for(CatUser objCU : catUserList){
													    		int idCU =  objCU.getId();
													    		String nameCU = objCU.getCatUser();
													    		if(!nameCU.contains("tor")){
													    		%>
													    			<option value="<%=idCU%>"
													    			<%
													    				if(catUser == idCU) out.print("selected");
													    			%>
													    			><%=nameCU %></option>
													    		<%
													    	
													    		}
													    		}
													    %>
													</select>
			                        			<%
			                        		}
			                        	}
			                        %>
			                    </div>
			                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
			                </form>
			            </div>
			    	</div>
			  	</div>
	        </div>
        </div>
        		<%
        	}
        %>
        <!-- Main row -->
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <script>
  function validate(evt) {
	  var theEvent = evt || window.event;

	  // Handle paste
	  if (theEvent.type === 'paste') {
	      key = event.clipboardData.getData('text/plain');
	  } else {
	  // Handle key press
	      var key = theEvent.keyCode || theEvent.which;
	      key = String.fromCharCode(key);
	  }
	  var regex = /[0-9]|\./;
	  if( !regex.test(key) ) {
	    theEvent.returnValue = false;
	    if(theEvent.preventDefault) theEvent.preventDefault();
	  }
	}
  
  
  </script>
  <!-- /.content-wrapper -->
  <%@include file="/templates/admin/inc/footer.jsp" %>