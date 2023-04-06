<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="../includes/header.jsp"></jsp:include>

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
								<td><a href="get?bno=${board.bno }"><c:out value="${board.title }"></c:out></a></td>
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
		
	})
	
	
</script>

<jsp:include page="../includes/footer.jsp"></jsp:include>