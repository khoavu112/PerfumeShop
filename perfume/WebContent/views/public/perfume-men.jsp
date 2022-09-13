<%@page import="models.Picture"%>
<%@page import="java.util.List"%>
<%@page import="models.Perfume"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="/templates/public/inc/header.jsp" %>
	
	<%	DecimalFormat df = new DecimalFormat("###,###,###");
		if(request.getAttribute("product") != null){
			Perfume product = (Perfume) request.getAttribute("product");
			%>
				<div class="section-banner">
		<div class="container">
			<ul class="breadcrumb">
				<li><a href="index-2.html">Trang chủ</a></li>
				<li class="active"><%=product.getCatPer().getCatPerfume() %></li>
			</ul>
		</div>
	</div><!-- /section-banner -->

	<section class="section">
	    <div class="container">
	   		      
		      <%
		      	if(request.getAttribute("allPro") != null){
		      		List<Perfume> allPro = (List<Perfume>)request.getAttribute("allPro");
		      		if(allPro.size() > 0){
		      			%>
		      <div class="row">
				<header class="heading">
					<span class="caro-prev caro-featured-prev"><i class="flaticon-arrowhead7"></i></span>
					<span class="caro-next caro-featured-next"><i class="flaticon-arrow487"></i></span>
					<h4>Tất cả sản phẩm</h4>
				</header>
				<%
					for(Perfume allProduct : allPro){
						%>
				<div class="col-sm-6 col-md-3">
					<div class="thumbnail thumbnail-product">
					<%
						if(request.getAttribute("picPro"+allProduct.getId()) != null){
							Picture picPro = (Picture)request.getAttribute("picPro"+allProduct.getId());
							%>
						<figure class="image-zoom">
			              <img src="<%=picPro.getPicture()%>" alt="image"style="width:100%;height:200px">
			            </figure>
							<%
						}else{
							%>
						<figure class="image-zoom">
			              <img src="<%=request.getContextPath()%>/templates/public/images/noimage.gif" alt="image">
			            </figure>
							<%
						}
					%>
					   
						<div class="caption text-center">
							<h5><a href="<%=request.getContextPath()%>/productsingle?id=<%=allProduct.getId()%>"><%=allProduct.getPerfumes() %></a></h5>
							
							<p class="prod-price text-primary">$<%=df.format(allProduct.getMoney()) %>đ</p>
							<div class="row">
								<div class = "col-md-5">
									<button idPro="<%=allProduct.getId() %>" id="idpro-order"type="button" class="btn btn-info idpro-order">
										Giỏ hàng
									</button>
								</div>
								<div class = "col-md-5">
									<button type="button" class="btn btn-success">
									<a href="<%=request.getContextPath()%>/addtocart?id=<%=allProduct.getId()%>">Mua ngay</a>
									</button>
								</div>
							</div>
						</div>
				  </div><!-- /thumbail -->
				</div>
						<%
					}
				%>
		      </div><!--  Tất cả sản phẩm -->
		      			<%
		      		}
		      	}
		      %>
		      
	      </div>
      </section>
			<%
		}else{
			%>
				<div class="alert alert-danger" role="alert">
				  Không có sản phẩm
				</div>
			<%
		}
	%>
	<script>
$(document).on("click", ".idpro-order", (function () {
	var idpro = $(this).attr("idpro");
	//lấy dữ liêu
	swal.fire({
        title: 'Thêm sản phẩm vào giỏ hàng',
        text: "Dữ liệu sẽ được cập nhật!",
        icon: 'warning',
        padding: '3em',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Đồng ý!',
        cancelButtonText: 'Hủy bỏ',
        reverseButtons: true,
        customClass: null
    }).then((result) => {
    	  if (result.value) {
    		  $.ajax({
    			  	async: false,
    	            type: "GET",
    	            url: "<%=request.getContextPath()%>/addtocart",
    	            data: {id: idpro},
    	            success: function (data) {
    	            	if(data==false){
	    	            		Swal.fire(
   	    	                    'Thông Báo!',
   	    	                    'Không thành công.',
   	    	                    'danger'
   	    	                ).then(function () {
   	    	                    location.reload();
   	    	                })
    	            	}else{
   	            			Swal.fire(
	            			      'Thành công!',
	            			      'Thêm vào giỏ hàng thành công.',
	            			      'success'
	            			    ).then(function () {
   	    	                    	location.reload();
   	    	                })
    	            	}
    	            },
    	            error: function (xhr, ajaxOptions, thrownError) {
    	                Swal.fire(
    	                    'Thông Báo!',
    	                    'Thao tác không thể thực hiện.',
    	                    'danger'
    	                ).then(function () {
    	                    location.reload();
    	                })
    	            }
    	        });
    		  } else if (result.dismiss === Swal.DismissReason.cancel) {
    		    swal.fire(
    		      'Thất bại',
    		      'Bạn Chưa thực hiện thao tác này',
    		      'error'
    		    )
    		  }
    		})
}))
</script>
	<%@include file="/templates/public/inc/footer.jsp" %>