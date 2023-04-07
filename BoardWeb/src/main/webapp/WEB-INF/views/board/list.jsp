<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="../includes/header.jsp"></jsp:include>
<p>result(addFlashAttribute): ${result }</p>
<p>message(addAttribute): ${message }</p>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tables</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Board List Page
				<button id='regBtn' type='button' class='btn btn-xs pull-right'>Register Board</button>
			</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover"
					id="dataTables-example">
					<thead>
						<tr>
							<th>#번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${list }">
							<tr>
								<td><c:out value="${board.bno }"></c:out></td>
								<td><a class="move" href="${board.bno }"><c:out value="${board.title }"></c:out></a></td>
								<td><c:out value="${board.writer }"></c:out></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.regdate }" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd"
										value="${board.updatedate }" /></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
				<!-- /.table-responsive -->
				
				<!-- 검색조건 시작부분-->
				<div class="row">
					<div class="col-lg-12">
						<form id="searchForm" action="/board/list" method="get">
							<select name="type">
								<option value="">---</option>
								<option value="T" ${pageMaker.cri.type eq 'T' ? 'selected' : '' }>제목</option>
								<option value="C" ${pageMaker.cri.type eq 'C' ? 'selected' : '' }>내용</option>
								<option value="W" ${pageMaker.cri.type eq 'W' ? 'selected' : '' }>작성자</option>
								<option value="TC" ${pageMaker.cri.type eq 'TC' ? 'selected' : '' }>제목 or 내용</option>
								<option value="TW" ${pageMaker.cri.type eq 'TW' ? 'selected' : '' }>제목 or 작성자</option>
								<option value="TCW" ${pageMaker.cri.type eq 'TCW' ? 'selected' : '' }>제목 or 내용 or 작성자</option>
							</select>
							<input type="text" name="keyword" value="${pageMaker.cri.keyword }">
							<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
					        <input type="hidden" name="amount" value="${pageMaker.cri.amount }">
							<button type="submit" class="btn btn-default">Search</button>
						</form>
					</div>
				</div>
				<!-- 검색조건 끝부분-->
				
				<!-- 페이징 시작부분-->
				<div class="pull-right">
					<ul class="pagination">
						<c:if test="${pageMaker.prev }">
							<li class="paginate_button previous">
								<a href="${pageMaker.startPage-1 }">Previous</a>
							</li>
						</c:if>
						
						<c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }">
							<li class="paginate_button ${pageMaker.cri.pageNum == num ? 'active' : '' }">
								<a href="${num }">${num }</a>
							</li>
						</c:forEach>
						
						<c:if test="${pageMaker.next }">
							<li class="paginate_button next">
								<a href="${pageMaker.endPage+1 }">Next</a>
							</li>
						</c:if>
					</ul>
				</div>
				<form action="/board/list" method="get" id="actionForm">
					<input type="hidden" name="pageNum" value="${pageMaker.cri.pageNum }">
					<input type="hidden" name="amount" value="${pageMaker.cri.amount }">
					<input type="hidden" name="type" value="${pageMaker.cri.type }">
					<input type="hidden" name="keyword" value="${pageMaker.cri.keyword }">
				</form>
				<!-- 페이징 끝부분-->
				
				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">Modal title</h4>
							</div>
							<div class="modal-body">처리가완료되었습니다</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">Close</button>
								<button type="button" class="btn btn-primary">Save
									changes</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.modal -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<script>
	$(document).ready(function(){
		console.log("start: ${result}")
		checkModal("${result}");
		
		//Modal을 활용해서 메세지 보여주기
		function checkModal(result){
			if(result == ''){
				return;
			}
			if(parseInt(result) > 0){
				$('.modal-body').html('게시글' + result + '번이 등록되었습니다.');
				$('#myModal').modal('show');//모달 보여주기
			}
		}//end of function checkModal(result)
		
		$('#regBtn').on('click', function(){
			self.location = '/board/register';
		})
		
		//페이지 이동
		var actionForm = $('#actionForm');
		$(".paginate_button a").on("click", function(e){
			e.preventDefault();
			actionForm.find('input[name="pageNum"]').val($(this).attr('href'));
			actionForm.submit();
		})
		
		//상세페이지 이동
		$('a.move').on('click', function(e){
			e.preventDefault();
			var bno = $(this).attr('href')
			actionForm.append('<input type="hidden" name="bno" value="'+bno+'">');
			actionForm.attr('action', '/board/get');
			actionForm.submit();
		})
		
		//검색처리
		var searchForm = $('#searchForm');
		$('#searchForm button').on('click', function(e){
			if(!searchForm.find('option:selected').val()){
				alert("검색종류를 선택하세요!")
				return false;
			}
			if(!searchForm.find('input[name="keyword"]').val()){
				alert("검색어를 입력하세요!")
				return false;
			}
			//검색어를 검색 -> 첫페이지
			searchForm.find("input[name='pageNum']").val('1');
			//searchForm.submit();
		})
		
	});
	
	
</script>

<jsp:include page="../includes/footer.jsp"></jsp:include>
