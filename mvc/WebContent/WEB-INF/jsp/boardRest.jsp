<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	$(() => {
		$.ajax({
			url:'/mvc/board/list/1'
			,method:'GET'
			,success: data => {
				let $boardPage = $('div.boardPage');

				let boardPageData = "";
				boardPageData += '<h3>게시물 목록</h3>';
				boardPageData += data.currentPage + '/' + data.totalPage + '<br>';

				let arr = data.list;
				arr.forEach(el => {
					boardPageData += '<div>'+el.board_no+'</div>';
				});
				$boardPage.html(boardPageData);
			}
			,error: data => {
				alert("실패" + data.status);
			}
		});
	});
</script>
</head>
<body>
	<div class="boardPage">
	
	</div>
</body>
</html>