function del(val) {
	console.log(val);
	location.href = "./boardDelete.do?seq=" + val;
}


function modify(val) {
	console.log("수정 Modal 버튼 클릭 : ", val);
	var id = "[href*=collapse" + val + "]";
	$(id).closest("tr").css("background", "brown");   //끌 때 시블링스로 지워보기

	ajaxModify(val);

	$("#modify").modal({ backdrop: "static", keyboard: false });
}


var ajaxModify = function(val) {
	$.ajax({
		url: "./modifyForm.do",
		method: "post",
		data: "seq=" + val,
		success: function(data) {
			console.log("조회된 결과 : ", data, typeof data);
			var json = JSON.parse(data);
			console.log("JSON 객체 변환 : ", typeof json, json);

			html = "";
			html += "   <div class='form-group'>                               ";
			html += "     <label for='id'>아이디</label>            ";
			html += "     <input type='hidden' name='seq' value='" + json.seq + "'> ";
			html += "     <b class='form-control' id='id'>" + json.id + "</b>";
			html += "   </div>                                                 ";

			html += "   <div class='form-group'>                               ";
			html += "     <label for='createat'>작성일</label>            ";
			html += "     <b class='form-control'>" + json.createat + "</b>";
			html += "   </div>                                                 ";

			html += "   <div class='form-group'>                               ";
			html += "     <label for='title'>제목</label>  <br>          ";
			html += "     <input style='width: 100%;' type='text' name='title' required value='" + json.title + "'> ";
			//         html += "     <b class='form-control'>"+json.title+"</b>";
			html += "   </div>                                                 ";

			html += "   <div class='form-group'>                               ";
			html += "     <label for='content'>내용</label>            ";
			html += "     <textarea class='form-control' row='5' name='content' id='content'>" + json.content + "</textarea>";
			html += "   </div>                                                 ";

			html += "<div class='modal-footer'>                                                         ";
			html += "   <input type='button' class='btn btn-success' value='글수정' onclick='modifyVal()'>";
			html += "   <input type='reset' class='btn btn-info' value='초기화'>                          ";
			html += "  <button type='button' class='btn btn-default' data-dismiss='modal'>취소</button> ";
			html += "</div>                                                                             ";



			$("#frmModify").html(html);

		},
		error: function(e) {
			console.log("잘못된 요청값 : ", e);
		}
	});

}

var modifyVal = function() {
	var frm = $("#frmModify");
	var idx = $(".active").text();

	$.ajax({
		url: "./modify.do",
		type: "post",
		data: frm.serialize(),
		success: function(msg) {
			console.log("결과 : ", typeof msg, msg);
			console.log(msg.isc == true);
			if (msg.isc == true) {
				$("#modify").modal("hide");
				spa_ajax(idx);
			} else {
				location.href = "./logout.do";
			}
		},
		error: function(e) {
			alert("잘못된 요청 처리 \n" + e);
		}
	});

}


function reply(seq) {
	console.log("답글작성 seq : ", seq);
	ajaxReply(seq);
	$("#reply").modal();
}


var ajaxReply = function(seq) {
	$.ajax({
		url: "./replyForm.do",
		type: "post",
		data: { "seq": seq },
		dataType: "json",
		success: function(data) {
			console.log("반환되는 결과 :", typeof data, data)
			$("#frmReply").html("");

			/* javaScript의 String Concatnation 방법으로 HTML 구성
			   html += "   <div class='form-group'>                               ";
			   html += "     <label for='id'>아이디</label>            ";
			   html += "     <input type='hidden' name='seq' value='"+json.seq+"'> ";
			   html += "     <b class='form-control' id='id'>"+json.id+"</b>";
			   html += "   </div>                                                 ";
			*/


			/* jQuery HTML생성
			   var div = $("<div>").attr("class","form-group");
			   var label = $("<label>").attr("for","id").text("아이디:");
			   var input = $("<input>").attr({"type":"hidden","name":"seq","value":"10"});
			   var b = $("<b>").attr("class","form-control").text("Sunny1");
			   div.append(label).append(input).append(b);
			   $("#frmReply").append(div);
			*/


			html = "";
			html += "   <div class='form-group'>                               ";
			html += "     <input type='hidden' name='seq' value='" + data.seq + "'> ";
			html += "     <label for='id'>부모글의 정보(" + data.seq + ")</label> <br>           ";
			html += "     <b>작성일 : " + data.createat + "</b>";
			html += "   </div>                                                 ";

			html += "   <div class='form-group'>                               ";
			html += "     <label>작성자</label>            ";
			html += "     <b>Sunny1</b>";
			html += "   </div>                                                 ";

			html += "   <div class='form-group'>                               ";
			html += "     <label id='tit'>제목(원본)</label>            ";
			html += "     <input type='text' class='form-control' id='title' name='title' value='" + data.title + "'> ";
			html += "   </div>                                                 ";


			html += "   <div class='form-group'>                               ";
			html += "     <input type='hidden' name='hiddenContent' value='" + data.content + "'> ";
			html += "     <label id='con'>내용(원본)</label>             ";
			html += "     <textarea type='text' class='form-control' id='textValue' name='content' onclick='chk()'>" + data.content + "</textarea>";
			html += "   </div>                                         ";

			html += "<div class='modal-footer'>                                                         ";
			html += "   <input type='button' class='btn btn-success' value='답글작성' onclick='replyVal()'>";
			html += "   <span onclick='reset()'><input type='reset' class='btn btn-info' value='초기화'><span>    ";
			html += "   <button type='button' class='btn btn-default' data-dismiss='modal'>취소</button> ";
			html += "</div>";

			$("#frmReply").html(html);
		},
		error: function() {
			alert("잘못된 요청처리 입니다.");
		}
	});

}

function chk() {
	var tit = document.getElementById("tit");
	var con = document.getElementById("con");
	var title = document.getElementById("title");
	var textValue = document.getElementById("textValue");
	var hiddenContent = document.getElementsByName("hiddenContent");

	console.log(tit, con, title, textValue, hiddenContent);
	
	

	if (textValue.value == hiddenContent.value) {
			tit.innerHTML = "답글 제목";
			con.innerHTML = "답글 내용";
			textValue.value = "";
			title.value = "";

	}
}

function reset() {
	document.getElementById("tit").innerHTML = "제목(원본)";
	document.getElementById("con").textContent = "내용(원본)";
}

function replyVal() {
	var tit = document.getElementById("tit").textContent;

	if (tit == "제목(원본)") {

		alert("답글작성 오류", "제목과 내용을 입력해 주세요");
		
	}
	
	console.log("if문에 따른 하위 실행");
	

	var parentSeq = document.getElementsByName("seq")[0].value;
	var title = document.getElementById("title").value;
	var content = document.getElementsByName("content")[0].value;

	const extraTextPattern = /(<([^>])+>)/gi;

	let convertTitle = title.replace(extraTextPattern,'');
	let convertContent = content.replace(extraTextPattern,'');

//	console.log(convertContent, convertTitle)

	var idx = $(".active").text();

	$.ajax({
		url: "./reply.do",
		type: "post",
		data: {"seq": parentSeq, "title": convertTitle, "content": convertContent },
		success: function(msg) {
			if (msg.isc == true) {
				$("#reply").maodal("hide");
				spa_ajax(idx);
			} else {
				alert("답글작성 오류", "다시 작성해주세요");
			}
		},
		error: function() {
			alert("잘못된 요청처리");
		}
	});

}