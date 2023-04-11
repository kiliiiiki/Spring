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
					<input type="hidden" name="type" value="${cri.type }"> <input
						type="hidden" name="keyword" value="${cri.keyword }">

				</form>
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<!-- 댓글 목록 페이지 추가 -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i> Reply
				<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New
					Reply</button>
			</div>
			<div class="panel-body">
				<ul class="chat">
					<li class="left clearfix" data-rno="12">
						<div>
							<div class="header">
								<strong class="primary-font">user00</strong> <small
									class="pull-right text-muted">2023-04-03 13:20</small>
							</div>
							<p>good</p>
						</div>
					</li>
				</ul>
			</div>
			<div class = "panel-footer">
				<!-- 페이지 정보 -->
				
			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Reply Modal</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<label>Reply</label> <input type="text" class="form-control"
						name="reply" value="샘플값">
				</div>
				<div class="form-group">
					<label>Replyer</label> <input type="text" class="form-control"
						name="replyer" value="user00">
				</div>
				<div class="form-group">
					<label>Reply Date</label> <input type="text" class="form-control"
						name="replydate" value="2023-04-05 13:23">
				</div>
			</div>
			<div class="modal-footer">
				<button id="modalModBtn" type="button" class="btn btn-warning">Modify</button>
				<button id="modalRemoveBtn" type="button" class="btn btn-danger">Remove</button>
				<button id="modalRegisterBtn" type="button" class="btn btn-default">Register</button>
				<button id="modalCloseBtn" type="button" type="button"
					class="btn btn-default">Close</button>

			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script src="/resources/js/reply.js"></script>
<script>
	$(document)
			.ready(
					function() {
						var operForm = $('#operForm');
						$('button[data-oper="modify"]').on('click', function() {
							operForm.attr('action', '/board/modify').submit();
						})
						$('button[data-oper="list"]').on('click', function() {
							operForm.find('#bno').remove(); //목록이동일 경우 parameter필요없음
							operForm.attr('action', '/board/list').submit();
						})

						// replyService사용.등록.
						/* replyService.add({bno: 300, reply: 'reply test', replyer: 'user00'}, function(result){
							alert("Result: "+result);
						}) */

						//목록
						//원본글번호, 페이지
						var bnoValue = "${board.bno }";
						var replyUl = $('.chat');

						showList("-1"); //마지막 페이지 보여주기
						
						function showList(page) {

							replyService
									.getList(
											{
												bno : bnoValue,
												page : page || 1
											},
											function(replyCnt, list) {
												//전체 페이지의 끝부분 계산
												if(page == -1){
													pageNum = Math.ceil(replyCnt / 10.0); //15건 -> 2페이지
													showList(pageNum);
													return; //페이지의 값을 -1로 지정하면 마지막 페이지를 보여줌
												}
												if (list == null
														|| list.length == 0) {
													replyUl.html("");
													return;
												}
												var str = "";
												for (var i = 0; i < list.length; i++) {
													console.log(list[i]);
													str += "<li class='left clearfix' data-rno='" + list[i].rno + "'>";
													str += "<div><div class='header'><strong class='primary-font'>"
															+ list[i].replyer
															+ "</strong>"
													str += "<small class='pull-right text-muted'>"
															+ replyService
																	.displayTime(list[i].replydate)
															+ "</small></div>";
													str += "<p>"
															+ list[i].reply
															+ "</p></div></li>";
												}
												replyUl.html(str);
												
												//페이지 정보
												showReplyPage(replyCnt);

											}, function(result) {
												console.log(result);
											});
						}//end of showList()

						//modal등록
						var modal = $('.modal');
						var modalInputReply = modal.find('input[name="reply"]');
						var modalInputReplyer = modal
								.find('input[name="replyer"]');
						var modalInputReplyDate = modal
								.find('input[name="replydate"]');

						var modalModBtn = $('#modalModBtn');
						var modalRemoveBtn = $('#modalRemoveBtn');
						var modalRegisterBtn = $('#modalRegisterBtn');

						$('#addReplyBtn').on('click', function(e) {
							modal.find('input').val('');

							modalInputReplyDate.closest('div').hide(); //등록일자 화면에서 숨김
							modal.find('button[id != "modalCloseBtn"]').hide();
							modalRegisterBtn.show();

							$('.modal').modal('show'); //modal창을 화면에 open
						})

						//등록버튼 클릭
						modalRegisterBtn.on('click', function(e) {
							var reply = {
								reply : modalInputReply.val(),
								replyer : modalInputReplyer.val(),
								bno : bnoValue
							}
						replyService.add(reply, function(result) {
							alert('result: ' + result);
							modal.find('input').val('');
							modal.modal('hide'); //화면에서 숨김
							
							showList(-1); //마지막 페이지로 이동
						})
						})
						
						//특정댓글클릭하면 수정, 삭제 modal 보여주기
						$('.chat').on('click', 'li', function(e){
							var rno = $(this).data('rno');
							replyService.get(rno, function(reply){
								modalInputReply.val(reply.reply);
								modalInputReplyer.val(reply.replyer);
								modalInputReplyDate.val(replyService.displayTime(reply.replydate));
								modal.data('rno', reply.rno); //<li data-rno=32>
								
								modal.find('button[id!="modalCloseBtn"]').hide();
								modalModBtn.show();
								modalRemoveBtn.show();
								
								$('.modal').modal('show');
							})
						}) //수정, 삭제 modal 창 보여주기
						
						//수정처리
						modalModBtn.on('click', function(e){
							var reply = {rno: modal.data('rno'), reply: modalInputReply.val()};
							replyService.update(reply, function(result){
								alert(result);
								modal.modal('hide');
								
								showList("1");
							})
						})
						
						//삭제처리
						modalRemoveBtn.on('click', function(e){
							var rno = modal.data('rno');
							replyService.remove(rno, function(result){
								alert(result);
								modal.modal('hide');
								
								showList(pageNum);
							})
						})
						
						//페이징 정보
						var pageNum = 1;
						var replyPageFooter=$('.panel-footer');
						function showReplyPage(replyCnt){
							var endNum = Math.ceil(pageNum / 10.0) * 10; //23건 -> 3페이지가 마지막
							var startNum = endNum - 9;
							var prev = startNum != 1;
							var next = false;
							
							if(endNum * 10 > replyCnt){
								endNum = Math.ceil(replyCnt / 10.0);// 실제 마지막 페이지
							}
							if(endNum * 10 < replyCnt){
								next = true;
							}
							var str = "<ul class='pagination pull-right'>";
							if(prev){
								str += "<li class='page-item'><a class='page-link' href='"+(startNum-1)+"'>Previous</a></li>";
							}
							for(var i = startNum; i<=endNum; i++){
								var active = pageNum == i ? 'active' : '';
								str += "<li class='page-item "+active+"'><a class='page-link' href='"+i+"'>"+i+"</a></li>";
							}
							if(next){
								str += "<li class='page-item'><a class='page-link' href='"+(endNum+1)+"'>Next</a></li>";
							}
							str += "</ul>";
							replyPageFooter.html(str);
							
						}
						
						//페이지 번호 링크 연결
						replyPageFooter.on('click', 'li a', function(e){
							e.preventDefault();
							var targetPageNum = $(this).attr('href');
							pageNum = targetPageNum;
							
							showList(pageNum);
						})

					});
</script>

<jsp:include page="../includes/footer.jsp"></jsp:include>