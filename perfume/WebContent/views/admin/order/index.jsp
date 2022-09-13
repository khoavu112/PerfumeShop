<%@page import="models.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="models.Order"%>
<%@page import="models.Order"%>
<%@page import="java.awt.List"%>
<%@page import= "daos.ItemsDao"%>
<%@page import= "daos.OrderDao"%>
<%@page import="java.text.DecimalFormat"%>

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
            <h1 class="m-0">Quản lý đơn hàng</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Trang chủ</a></li>
              <li class="breadcrumb-item active">Đơn hàng</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->
    
 <%		DecimalFormat df = new DecimalFormat("###,###,###");	
 	ItemsDao itemDao = new ItemsDao();
 	OrderDao orderDao = new OrderDao();
		if(!"".equals(request.getParameter("msg"))){
			String msg = request.getParameter("msg");
			if("SUCCESS".equals(msg)){
				%>
					<div class="alert alert-success" role="alert">
						Xóa đơn hàng thành công.!
					</div>
				<%
			}
		}
	%>
	<%	int test = -15; 
		ArrayList<Item> itemList = new ArrayList<Item>();
		if(request.getAttribute("itemList") != null){
			itemList = (ArrayList<Item>)request.getAttribute("itemList");
			if(itemList.size() > 0){
				%>
<!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
	      <div class="col-sm-6">
	       <h3>Số lượng đơn đã duyệt: <%=orderDao.countOrderDaDuyet()%> </h3>
           <h3>Số lượng đơn chưa duyệt: <%=orderDao.countID() - orderDao.countOrderDaDuyet() - orderDao.countOrderHuy() %> </h3>
	      <h3>Số lượng đơn đã hủy: <%=orderDao.countOrderHuy()%></h3>
	       </div>
	      <div class="col-sm-6">
	      	<form method="GET" action="<%=request.getContextPath()%>/admin/order/search" class="form-inline">
	      		
            			<select id="statusOrder" class="btn-sm form-control" name="statusOrder">
	      				  <option value="0">--- Tình trạng đơn ---</option>
	      				 
	      				  		<option value="1">Đơn đã duyệt</option>
	      				  		<option value="2">Đơn chưa duyệt</option>
	      				  		<option value="3">Đơn đã hủy</option>
	      				  		
	      				</select>
            		
	      		
	      		
	      		
	      		
	      		
	      		<input type="number" name="orderID" value=""class="form-control" placeholder="Nhập mã đơn" style="float:right; width: 300px;" />
            	<input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning" style="float:right" />
            	<div style="clear:both"></div>
             </form>
             </div>
         <!-- /.col -->
        </div><br></br>
        <!-- /.row -->
        <table class="table table-striped table-bordered">
		  <thead>
		    <tr>
		      <th scope="col">STT</th>
		       <th scope="col">Mã đơn hàng</th>
		      <th scope="col">Tên sản phẩm</th>
		      <th scope="col">Người mua</th>
		      <th scope="col">Số lượng mua</th>
		      <th scope="col">Giá tiền</th>
		      <th scope="col">Trạng thái</th>
		      <th scope="col">Chức năng</th>
		    </tr>
		  </thead>
		  <tbody>
		  <%
		  	int i = 0;
		  	Long money = 0L;
			for(Item product : itemList){
				
				int quantity = product.getQuantity();
				Long price = product.getPrice();
				money = quantity * price;
				int idPer = product.getOrder().getId();
				%>
			<tr class="
				<%
		      		if(product.getOrder().getStatus() == false){
		      			out.print("table-warning");
		      		}
		      	%>
		      	"
			>
				 <% if(product.getOrder().getId() != test){ 
					 i++;
					 
				 		%>
		      		<th scope="row" rowspan=<%=itemDao.countItem(product.getOrder().getId())%>><%=i %></th>
		      			<% }else{ %>
		      			
		      			<% } %>
		      <% if(product.getOrder().getId() != test){ %>
		      
		      
		      <td rowspan=<%=itemDao.countItem(product.getOrder().getId())%>><%=product.getOrder().getId() %></td>
		      <% }else {%>
		      <%} %>
		      <td><%=product.getProduct().getPerfumes() %></td>
		      <td><%=product.getOrder().getCustomer().getFullname() %></td>
		      <td><%=product.getQuantity() %></td>
		      <td><%=df.format(money)%></td>
		      <%  
		    				
		    					if(product.getOrder().getId() != test){ 
		    						if(!product.getOrder().getPhone().contains("x")){
		    					%>
		      <td rowspan=<%=itemDao.countItem(product.getOrder().getId())%>>
		      	<div class="form-group">
		    		<div class="col-sm-7 col-md-7">
		    			<div class="input-group">
		    				<div class="form-check form-check-inline">
		    				
							  <input id="toggle-state" class="toggle-state-add toggle-state-status" type="checkbox" data-toggle="toggle"
							  	<%
							  		if(product.getOrder().getStatus() == true){
							  			out.print("checked");
							  		}else{
							  			out.print("unchecked");
							  		}
							  
							  	%>
							  	idPer = <%=idPer%>
							  	
							  >
							  
								<div id="console-event"></div>
							</div>
		    			</div>
		    		</div>
		    	 </div>
		      </td> 
		      <%}else{ %>
		      <td rowspan=<%=itemDao.countItem(product.getOrder().getId())%>>Đã hủy đơn</td> 
		      
		      <%} %>
		      
		      <% }else{ 
		      %>
		      
		      <%} %>
		      <td>
		      	<button idOrder=<%=product.getId() %> type="button" class="btn btn-primary view-product" data-toggle="modal" data-target="#exampleModal">
					Xem
				</button>
			<%	if(product.getOrder().getId() != test){ 
					test = product.getOrder().getId();
					
					%> 
				<a  href="<%=request.getContextPath()%>/admin/order/del?idOrderDel=<%=product.getOrder().getId()%>" class="btn btn-danger del-user"><i class="fa fa-trash" aria-hidden="true"></i> Xóa</a>
		      	<%} %>
		      	
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
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
					<%
			
		}else{
			%>
				<div class="alert alert-danger" role="alert">
				  Không có dữ liệu
				</div>
			<%
		}}
	%>
	
	<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header bg-info">
        <h5 class="modal-title" id="exampleModalLabel">Thông tin đơn hàng</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
       	<div class="row order-view-product">
	       	<!-- <table class="table table-bordered table-striped">
			  <tr>
			  	<th>a</th>
			  	<td>1</td>
			  </tr>
	       	</table> -->
       	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Thoát</button>
      </div>
    </div>
  </div>
</div>
	
<script>
$(document).ready(function() {
	$('.toggle-state-add').change(function() {
	     var giatri = $(this).prop('checked')
	     var idPer = $(this).attr("idPer")
	     const swalWithBootstrapButtons = Swal.mixin({
			  customClass: {
			    confirmButton: 'btn btn-success',
			    cancelButton: 'btn btn-danger'
			  },
			  buttonsStyling: false
			})
	      swalWithBootstrapButtons.fire({
	    	  title: 'Bạn có chắc chắn?',
	    	  text: "Thao tác này sẽ ảnh hưởng đến đơn hàng của bạn!",
	    	  icon: 'warning',
	    	  showCancelButton: true,
	    	  confirmButtonText: 'Tiếp tục',
	    	  cancelButtonText: 'Hủy bỏ',
	    	  reverseButtons: true
		 }).then((result) => {
			   if (result.isConfirmed) { 
				   $.ajax({
		            type: "post",
		            url: "<%=request.getContextPath()%>/admin/order/status",
		            data: {
		            	giatri : giatri,
		            	idPer : idPer
		            	},
		            context:this,
		            success: function (response) {
		              if(response == false)
		              {
		            	  swalWithBootstrapButtons.fire(
	    	                    'Thông Báo!',
	    	                    'Bạn không thể thay đổi trạng thái của đơn hàng.',
	    	                    'danger'
	    	                ).then(function () {
	    	                    location.reload();
	    	                })
		              }else{
		            	  swalWithBootstrapButtons.fire(
	            			      'Thành công!',
	            			      'Thay đổi trạng thái thành công.',
	            			      'success'
	            			    ).then(function () {
  	    	                    location.reload();
  	    	                })
		              }
		            },
		            error: function (xhr, ajaxOptions, thrownError) {
		            	swalWithBootstrapButtons.fire(
	    	                    'Thông Báo!',
	    	                    'Thao tác không thể thực hiện.',
	    	                    'danger'
	    	                ).then(function () {
	    	                    location.reload();
	    	                })
	    	            }
			   });
			   }else if (result.dismiss === Swal.DismissReason.cancel) {
	    		    swalWithBootstrapButtons.fire(
  	    		      'Thất bại',
  	    		      'Bạn đã hủy thao tác',
  	    		      'error'
  	    		    ).then(function () {
	                    location.reload();
	                })
  	    		  }
	    })
	})
	
});
$(document).on("click",".view-product",function() {
	var idOrder = $(this).attr("idOrder");
	$.ajax({
		url: '<%=request.getContextPath()%>/admin/order/viewOder',
		type: 'POST',
		cache: false,
		data:{
			idOrder : idOrder
		},
	success: function(data){
		$(".order-view-product").html(data);
	},
	error: function(){
		alert("lỗi");
	}
	});
	return false;
});
</script>
  <%@include file="/templates/admin/inc/footer.jsp" %>