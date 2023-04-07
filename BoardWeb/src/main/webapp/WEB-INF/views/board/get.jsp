<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Read Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="form-group">
					<label>Bno</label> <input class="form-control" type="text"
						name="bno" value="${board.bno }" readonly>
				</div>
				<div class="form-group">
					<label>Title</label> <input class="form-control" type="text"
						name="title" value="${board.title }" readonly>
				</div>
				<div class="form-group">
					<label>Writer</label> <input class="form-control" type="text"
						name="writer" value="${board.writer }" readonly>
				</div>
				<div class="form-group">
					<label>Text Area</label>
					<textarea class="form-control" name="content">${board.content }</textarea>
				</div>
				<button data-oper="modify" class="btn btn-default">Modify</button>
				<button data-oper="list" class="btn btn-default">List</button>
				<form id="operForm" action="/board/modify" method="get">
					<input type="hidden" id="bno" name="bno" value="${board.bno }">
					<input type="hidden" name="pageNum" value="${cri.pageNum }">
					<input type="hidden" name="amount" value="${cri.amount }">
					<input type="hidden" name="type" value="${cri.type }">
					<input type="hidden" name="keyword" value="${cri.keyword }">
					
				</form>
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
		var operForm = $('#operForm');
		$('button[data-oper="modify"]').on('click', function(){
			operForm.attr('action', '/board/modify').submit();
		})
		$('button[data-oper="list"]').on('click', function(){
			operForm.find('#bno').remove(); //목록이동일 경우 parameter필요없음
			operForm.attr('action', '/board/list').submit();
		})
	})
</script>

<jsp:include page="../includes/footer.jsp"></jsp:include>