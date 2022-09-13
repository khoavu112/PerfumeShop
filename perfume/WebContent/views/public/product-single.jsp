<%@page import="models.Comment"%>
<%@page import="java.util.List"%>
<%@page import="models.Picture"%>
<%@page import="models.Perfume"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@include file="/templates/public/inc/header.jsp" %>

<%	DecimalFormat df = new DecimalFormat("###,###,###");
	if(request.getAttribute("product") != null){
		Perfume product = (Perfume) request.getAttribute("product");	
		%>
<section class="section-banner">
    <div class="container">
      <ul class="breadcrumb">
        <li><a href="<%=request.getContextPath()%>/home">Home</a></li>
        <li><a href="">Nước hoa</a></li>
        <li class="dropdown active breadcrumb-search">
          <a href="#" id="one" data-toggle="dropdown"><%=product.getPerfumes()%></a>
        </li>
      </ul>
    </div>
  </section><!-- /section-banner -->

	<section class="section-compact">
		<div class="container">

			<div class="single-container">
				<div class="product-single">
				  <div class="featured-box">
				    <div class="row">
				      <div class="col-sm-5">
				      <%
				      	if(request.getAttribute("picID") != null){
				      		Picture picID = (Picture) request.getAttribute("picID");
				      		
				      		%>
				      	<figure class="image">
				          <img src="<%=picID.getPicture() %>" alt="image"style="width:100%;height:300px">
				          <figcaption>
				            <a href="#product-more" data-toggle="collapse">Ẩn bớt  <i class="flaticon-small63"></i></a>
				          </figcaption>
				        </figure>
				      		<%
				      	}else{
				      		%>
				      	<figure class="image">
				          <img src="<%=request.getContextPath()%>/templates/public/images/noimage.gif" alt="image"style="width:100%;height:300px">
				          <figcaption>
				            <a href="#product-more" data-toggle="collapse">Ẩn bớt  <i class="flaticon-small63"></i></a>
				          </figcaption>
				        </figure>
				      		<%
				      	}
				      %>
				      </div>
				      <div class="col-sm-7">
				        <div class="text">
				        <input class="" id="idpro" name="idpro"type="hidden"value="<%=product.getId()%>"/>
				          <p>Thương hiệu: <a href="#" class="text-primary"><%=product.getBrand()%></a></p>
				          <h4><%=product.getPerfumes()%></h4>
				          <p>Lượt xem: <%=product.getViews() %></p>
				          <p>Số lượng: <%=product.getAmount() %></p>
				          <div id="info-1" class="collapse in">
				            <p>Giá: <%=df.format(product.getMoney())%>đ</p>
				            <p>Mô tả: <%=product.getDescription()%></p>
				            <p>Nhập số lượng để thêm vào giỏ hàng:</p>
				            <%if(product.getAmount()!=0){ %>
				            
				            <form name="Form" method="GET" onsubmit="return validateForm()" action="<%=request.getContextPath()%>/addtocart" >
	      		<input type="hidden" name="idGH" value="<%=product.getId()%>"  />
   	      		<input id="a" type="text" name="quantity" value="1" class="form-control" placeholder="Nhập số lượng" maxlength="2" style='width:10em' onkeypress="validate(event)" />
            	<input type="submit" name="GioHang" value="Thêm vào giỏ hàng" class="btn btn-default add-to-card"   />
            	
             </form>				            
				            
				         	<%}else{ %>
				         	<p>Tạm thời hết hàng</p>
				         	<%} %>
				          </div>
				        </div>
				      </div>
				    </div>
				  </div><!-- /featured-box -->
				  <div id="product-more" class="product-more collapse in">
				    <div class="cart-table">
				      <div class="text">
				          <p>Thương hiệu: <a href="#" class="text-primary"><%=product.getBrand() %></a></p>
				          <h4><%=product.getPerfumes()%></h4>
				          <div id="info-1" class="collapse in">
				            <p><%=product.getCreate_date()%></p>
				            <p>
				            	<%=product.getDetail()%>
				            </p>
				          </div>
				        </div>
				    </div><!-- /cart-table -->
				  </div><!-- /product-more -->
				</div><!-- /product-single -->

			<%
		      	if(request.getAttribute("ortherProList") != null){
		      		List<Perfume> ortherProList = (List<Perfume>)request.getAttribute("ortherProList");
		      		if(ortherProList.size() > 0){
		      			%>
	      <div class="other-products">
		      <header class="heading">
		        <span class="caro-prev other-prev"><i class="flaticon-arrowhead7"></i></span>
		        <span class="caro-next other-next"><i class="flaticon-arrow487"></i></span>
		        <h4>Các sản phẩm khác từ (Thương hiệu)</h4>
		      </header>
		      <div class="carousel-multiple owl-carousel">
		      <%
		      	for(Perfume objOther : ortherProList){
		      		%>
		      	<div class="item">
		          <div class="thumbnail thumbnail-product">
		          <%
		          	if(request.getAttribute("picPro"+objOther.getId()) != null){
		          		Picture picPro = (Picture) request.getAttribute("picPro"+objOther.getId());
		          		%>
		          	<figure class="image-zoom">
		              <img alt="image" src="<%=picPro.getPicture()%>" style="width:200px;height:200px;">
		            </figure>
		          		<%
		          	}else{
		          		%>
		          	<figure class="image-zoom">
		              <img alt="image" src="<%=request.getContextPath()%>/templates/public/images/noimage.gif" style="width:200px;height:200px;">
		            </figure>
		          		<%
		          	}
		          %>
		            
		            <div class="caption text-center">
		              <h5><a href="<%=request.getContextPath()%>/productsingle?id=<%=objOther.getId()%>"><%=objOther.getPerfumes() %></a></h5>
		              
		              <p class="prod-price text-primary"><%=df.format(objOther.getMoney())%>đ</p>
		            </div>
		          </div><!-- /thumbail -->
		        </div>
		      		<%
		      	}
		      %>
		      </div>
				</div><!-- /other-products -->
				<%
      		}
      	}
      %>
				<div class="product-description">
					<div class="row">
						<div class="col-sm-6">
							<div class="prod-desc-box">
								<h4>Mô tả và đánh giá sản phẩm</h4>
								<p><%=product.getDetail() %></p>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="row">
								<div class="col-sm-6">
									<div class="prod-desc-box">
										<h4>Thành phần</h4>
										<h5>Sản xuất</h5>
										<p><%=product.getMade()%></p>

										<h5>Loại sản phẩm</h5>
										<p><%=product.getCatPer().getCatPerfume()%></p>

										<h5>Dung tích</h5>
										<p><%=product.getCapacity() %></p>
									</div><!-- /prod-desc-box -->
								</div>
								<div class="col-sm-6">
									<div class="prod-desc-box">
										<h4>Đánh giá</h4>
										<h5>Độ yêu thích của sản phẩm</h5>
							              <div class="rating-star">
							                <i class="flaticon-favourites7 selected"></i>
							                <i class="flaticon-favourites7 selected"></i>
							                <i class="flaticon-favourites7 selected"></i>
							                <i class="flaticon-favourites7 selected"></i>
							                <i class="flaticon-favourites7"></i>
							              </div><!-- /rating-star -->
									</div><!-- /prod-desc-box -->
								</div>
							</div>
						</div>
					</div>
		<%
	}
%>
					<!-- comment -->
					  <div class="row">
					    <div class="col-sm-10 col-sm-offset-1" id="logout">
					        <div class="page-header">
					            <h3 class="reviews">Bình luận</h3>
					            <!-- <div class="logout">
					                <button class="btn btn-default btn-circle text-uppercase" type="button" onclick="$('#logout').hide(); $('#login').show()">
					                    <span class="glyphicon glyphicon-off"></span> Logout                    
					                </button>                
					            </div> -->
					        </div>
					        <div class="comment-tabs">
					            <ul class="nav nav-tabs" role="tablist">
					                <li class="active"><a href="#comments-logout" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Mọi người</h4></a></li>
					                <li><a href="#add-comment" role="tab" data-toggle="tab"><h4 class="reviews text-capitalize">Bình luận của bạn</h4></a></li>
					            </ul>      
					            <div class="tab-content">
					                <div class="tab-pane active" id="comments-logout">                
					                    <ul class="media-list">
				                           <!-- Kết quả comment -->
				                           <%	
				                           	if(request.getAttribute("listCMT") != null){
				                           		
				                           		List<Comment> listCmt = (List<Comment>)request.getAttribute("listCMT");
				                           		if(listCmt.size()>0){
				                           		for(Comment objCMT : listCmt){
				                           			%>
		     						       <li class="media">
		     		                        <div class="media-body">
		     		                          <div class="well well-lg">
		     		                              <h4 class="media-heading text-uppercase reviews"><%=objCMT.getName_comment() %></h4>
		     		                              <ul class="media-date text-uppercase reviews list-inline">
		     		                                <li class="dd"><%=objCMT.getCreate_comment() %></li>
		     		                              </ul>
		     		                              <p class="media-comment">
		     		                                <%=objCMT.getMessage() %>
		     		                              </p>
		     		                              <!-- <a class="btn btn-info btn-circle text-uppercase" href="#" id="reply"><span class="glyphicon glyphicon-share-alt"></span> Reply</a>
		     		                              <a class="btn btn-warning btn-circle text-uppercase" data-toggle="collapse" href="#replyThree"><span class="glyphicon glyphicon-comment"></span> 2 comments</a> -->
		     		                          </div>              
		     		                        </div>
		     		                      </li> 
				     				             <%
				                           		}
				                           		}else{%>
				                           			<div class="alert alert-light" role="alert">
									  Hãy là người đầu tiên bình luận ...
									</div>
				                           			
				                           <% 		}
				                           	}else{
				                           	%>
	                           		<div class="alert alert-light" role="alert">
									  Hãy là người đầu tiên bình luận ...
									</div>
				                           	<%
				                           	}
				                           %>
					                    </ul> 
					                </div>
					                <div class="tab-pane" id="add-comment">
					                    <form class="form-horizontal" id="commentForm" role="form"> 
					                        
					                        <div class="form-group">
					                            <label for="email" class="col-sm-2 control-label">Tên của bạn</label>
					                            <div class="col-sm-10">
					                             <input class="form-control"id="fullname" type="text" value="" name="fullname"/>
					                            </div>
					                        </div>
					                        <div class="form-group">
					                            <label for="email" class="col-sm-2 control-label">Nội dung</label>
					                            <div class="col-sm-10">
					                              <textarea class="form-control" name="addComment" id="addComment" rows="5"></textarea>
					                            </div>
					                        </div>
					                        <div class="form-group">
					                            <div class="col-sm-offset-2 col-sm-10">                    
					                                <button onclick="return getComment()"class="btn btn-success btn-circle text-uppercase" id="submitComment"><span class="glyphicon glyphicon-send"></span> Bình luận</button>
					                            </div>
					                        </div>            
					                    </form>
					                </div>
					            </div>
					        </div>
						</div>
					  </div>
					
					<!-- comment -->
				</div><!-- /product-description -->

			</div><!-- /single-container -->
    </div>
    <script type="text/javascript">
    	function getComment() {
    		var idpro = $("#idpro").val();
			var fullname = $("#fullname").val();
			var addComment = $("#addComment").val();
			$.ajax({
				url: '<%=request.getContextPath()%>/comment',
				type: 'POST',
				cache: false,
				data:{
					idpro : idpro,
					fullname : fullname,
					addComment : addComment
				},
			success: function(data){
				$(".media-list").html(data);
			},
			error: function(){
				alert("lỗi");
			}
			});
			return false;
    	}
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
    	 function validateForm() {
    		    var a = document.forms["Form"]["quantity"].value;
    		   
    		    if (a == null || a == "") {
    		      alert("Hãy nhập số lượng!");
    		      return false;
    		    }
    		  }
    </script>
   
  <%@include file="/templates/public/inc/footer.jsp" %>
