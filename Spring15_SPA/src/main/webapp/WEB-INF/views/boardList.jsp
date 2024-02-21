<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>전체 게시글</title>
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./js/boardListPage.js"></script>
<script type="text/javascript" src="./js/boardListEvent.js"></script>
</head>
<body>
   <button onclick="location.href='./logout.do'">로그아웃</button>
  사용자 정보: ${loginVo}<br>
  model 페이지 정보: ${page}<br>
  session 페이지 정보:  ${row }<br>
   <hr>

   <div class="container">
      <table class="table table-hover">
         <thead>
            <tr class="info">
               <c:if test="${loginVo.auth eq 'A'}">
                  <th>
                     <input type="checkbox" class="allCheck" id="allCheck" onclick="checkAll(this.checked)">
                  </th>
               </c:if>
               <th>글번호</th>
               <th>작성자</th>
               <th>제목</th>
               <c:if test="${loginVo.auth eq 'A'}">
                  <th>삭제여부</th>
               </c:if>
               <th>작성일</th>
            </tr>
         </thead>
<!--          <tbody> -->
<%--             <c:forEach var="vo" items="${lists}" varStatus="vs"> --%>
<!--                <tr> -->
<%--                   <c:if test="${loginVo.auth eq 'A'}"> --%>
<!--                      <td> -->
<%--                         <input type="checkbox" name="delCheck" value="${vo.seq}"> --%>
<!--                      </td> -->
<%--                   </c:if> --%>
<%--                   <td>${vs.index}</td> --%>
<%--                   <td>${vo.id}</td> --%>
<!--                   <td> -->
<%--                      <c:set var="title" value="${vo.title}"/> --%>
<%--                         ${title.replaceAll("<img>","<img src='../images/reply.png'>")} --%>
<!--                   </td> -->
<%--                   <c:if test="${loginVo.auth eq 'A'}"> --%>
<%--                      <td>${vo.delflag}</td> --%>
<%--                   </c:if> --%>
<!--                   <td> -->
<%--                      <fmt:parseDate var="patternDate" value="${vo.createat}" pattern="yyyy-MM-dd HH:mm:ss"/> --%>
<%--                      <fmt:formatDate value="${patternDate}" pattern="yyyy년도 MM월 dd일"/> --%>
<!--                   </td> -->
<!--                </tr> -->
<%--             </c:forEach> --%>
<!--          </tbody> -->
         
<!--          <tfoot style="text-align: center;"> -->
<!--             <tr> -->
<%--                <td colspan="${loginVo.auth eq 'A' ? 6:4 }"> --%>
<!--                   <ul class="pagination pagination-lg"> -->
<%--                      <c:if test="${page.stagePage>1}"> --%>
<!--                           <li><a href="#" onclick="pageFirst()"><span class="glyphicon glyphicon-fast-backward"></span></a></li>                      -->
<%--                      </c:if> --%>
<%--                      <c:if test="${page.stagePage - page.countPage >= 0 }"> --%>
<%--                           <li><a href="#" onclick="pagePrev(${page.stagePage}, ${page.countPage})"><span class="glyphicon glyphicon-step-backward"></span></a></li> --%>
<%--                      </c:if> --%>
                  
<%--                      <c:forEach var="i" begin="${page.stagePage}" end="${page.endPage}" step="1"> --%>
<%--                           <li ${page.page==i? "class=active" : "" }><a href="javascript:page(${i})">${i}</a></li> --%>
<%--                      </c:forEach> --%>
                     
<%--                      <c:if test="${page.page < page.totalPage}"> --%>
<%--                         <c:if test="${page.stagePage + page.countPage < page.totalCount }"> --%>
<%--                            <li><a href="#" onclick="pageNext(${page.stagePage}, ${page.countPage})"><span class="glyphicon glyphicon-step-forward"></span></a></li> --%>
<%--                         </c:if> --%>
<%--                            <li><a href="#" onclick="pageLast(${page.totalPage})"><span class="glyphicon glyphicon-fast-forward"></span></a></li> --%>
<%--                      </c:if> --%>
                     
                     
<!--                   </ul> -->
<!--                </td> -->
<!--             </tr> -->
<!--          </tfoot> -->

      <!-- SPA 작업 -->

      <tbody>
         <c:forEach var="vo" items="${lists}" varStatus="vs">
            <tr>
               <c:if test="${loginVo.auth eq 'A'}">
                  <td>
                     <input type="checkbox" name="delCheck" value="${vo.seq}">
                  </td>
               </c:if>
               <td>
                  ${page.totalCount - (page.page-1) * page.countList - vs.index}
               </td>
               <td>
                  ${vo.id}
               </td>
               <td>
                  <div class="panel-heading">
                     <a class="panel-title" data-toggle="collapse" data-parent="#accordion" href="#collapse${vo.seq}">
                     <c:set var="title" value="${vo.title}"/>
                     ${title.replaceAll("<img>","<img src ='./images/reply.png'>")}
                     </a>
                  </div>
               </td>
               <c:if test="${loginVo.auth eq 'A'}">
                  <td>${vo.delflag}</td>
               </c:if>
               <td>
                  <fmt:parseDate var="patternDate" value="${vo.createat }" pattern="yyyy-MM-dd HH:mm:ss"/>
                  <fmt:formatDate value="${patternDate}" pattern="yyyy년 MM월 dd일"/>
               </td>
            </tr>
            <tr>
               <td colspan="${loginVo.auth eq 'A' ? 6:4}">
                  <div id="collapse${vo.seq}" class="panel-collapse collapse">
                     <div class="form-group">
                        <label>내용</label>
                        <textarea class="form-control" rows="4" readonly="readonly">${vo.content}</textarea>
                        <div>
                           <c:if test="${vo.id eq loginVo.id}">
                              <input type="button" class="btn btn-primary" value="수정" onclick="modify('${vo.seq}')">
                           </c:if>
                           <c:if test="${vo.id eq loginVo.id || loginVo.auth eq 'A'}">
                              <input type="button" class="btn btn-danger" value="삭제" onclick="del('${vo.seq}')">
                           </c:if>
                           <c:if test="${loginVo.auth eq 'U'}">
                              <input type="button" class="btn btn-success" value="답글" onclick="reply('${vo.seq}')">
                           </c:if>
                        </div>
                     </div>
                  </div>
               </td>
            </tr>
         </c:forEach>
      </tbody>
      <tfoot style="text-align: center;"> 
            <tr>
               <td colspan="${loginVo.auth eq 'A' ? 6:4 }">
                  <ul class="pagination pagination-lg">
                     <c:if test="${page.stagePage>1}">
                          <li><a href="#" onclick="pageFirst()"><span class="glyphicon glyphicon-fast-backward"></span></a></li>                     
                     </c:if>
                     <c:if test="${page.stagePage - page.countPage >= 0 }">
                          <li><a href="#" onclick="pagePrev(${page.stagePage}, ${page.countPage})"><span class="glyphicon glyphicon-step-backward"></span></a></li>
                     </c:if>
                  
                     <c:forEach var="i" begin="${page.stagePage}" end="${page.endPage}" step="1">
                          <li ${page.page==i? "class=active" : "" }><a href="javascript:page(${i})">${i}</a></li>
                     </c:forEach>
                     
                     <c:if test="${page.page < page.totalPage}">
                        <c:if test="${page.stagePage + page.countPage < page.totalCount }">
                           <li><a href="#" onclick="pageNext(${page.stagePage}, ${page.countPage})"><span class="glyphicon glyphicon-step-forward"></span></a></li>
                        </c:if>
                           <li><a href="#" onclick="pageLast(${page.totalPage})"><span class="glyphicon glyphicon-fast-forward"></span></a></li>
                     </c:if>
                  </ul>
               </td>
            </tr>
         </tfoot>
      </table>
   </div>
   
   
   <!-- Modal -->
<div id="modify" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">글수정</h4>
      </div>
      <div class="modal-body">
      	<form method="post" id="frmModify">
        		<input>
        </form>
      </div>
     </div>
    </div>
   </div>
   
    <!-- Modal -->
<div id="reply" class="modal fade" role="dialog">
  <div class="modal-dialog modal-sm">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">답글작성</h4>
      </div>
      <div class="modal-body">
      	<form method="post" id="frmReply">
        		<input>
        </form>
      </div>
     </div>
    </div>
   </div>

</body>

<script type="text/javascript">
/* 체이닝...** */
$("table").click(function() {
   console.log("tbody클릭");
   $(".collapse").on("show.bs.collapse",function(){
      $(".collapse.in").collapse('hide');
   })
});
</script>
</html>