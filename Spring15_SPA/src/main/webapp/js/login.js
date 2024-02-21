/**
 * 
 */
 
 //enter evnet 발생시에 loginCheck() 실행 
 
 function enterKey() { 
   if(window.event.keycode == 13) {
      loginCheck();
   }
}
/*
window.onload = function(){ ... }: 이 부분은 웹 페이지가 로드될 때 실행할 함수를 정의합니다. 즉, 전체 HTML 문서가 로드되었을 때 이 함수 내의 코드가 실행됩니다.

document.querySelector("button[type=submit]"): 이 부분은 문서에서 첫 번째로 발견되는 submit 타입의 버튼을 선택합니다. 일반적으로 이는 로그인 버튼을 의미합니다.

onclick=function(e){ ... }: 이 부분은 선택한 버튼이 클릭되었을 때 실행될 함수를 정의합니다. e는 이벤트 객체를 나타내며, preventDefault() 함수를 사용하여 기본 동작을 중지시킵니다. 이 경우에는 폼이 제출되는 동작을 중지시킵니다.

console.log("로그인 버튼 클릭");: 클릭 이벤트가 발생할 때 콘솔에 "로그인 버튼 클릭" 메시지를 출력합니다.

loginCheck();: 이 부분은 loginCheck()라는 함수를 호출합니다. 아마도 이 함수는 사용자의 로그인 상태를 확인하고 필요한 작업을 수행할 것으로 예상됩니다.
*/
// 창이 열리자마자 
window.onload = function(){ 
   document.querySelector("button[type=submit]").onclick=function(e){
      console.log("로그인 버튼 클릭");
      e.preventDefault();
      loginCheck(); 
   }
}

function loginCheck() { 
   console.log("로그인 검수")
   var id = document.getElementById("id").value;
   var password = document.getElementById("pwd").value;
   
   console.log("화면의 값 :  ", id, password);
   
   var form = document.forms[0];
   form.action='./login.do';
   form.method='post';
   
   ///^(?=.*\d)/ 문자로 시작하고 숫자를 포함하는 0 이상의 반복되는 문자열 
   var regEx = /^(?=.*\d)(?=.*[a-zA-Z]).{4,}$/;
   var obj = { 
      id:id,
      password:password 
   }
   
   objectData.id=id;
   objectData.password=password;
   
   console.log(obj, typeof obj);
   console.log(objectData, typeof objectData)
   
   if(!regEx.test(id) && !regEx.test(password)){
      alert("잘못된 아이디와 패스워드를 입력하셨습니다.")
   } else { 
      fetch("./loginCheckText.do", { 
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:
            //""쓰면안됌 
         //      JSON.stringify({id:id}) //stringify에 javascript 객체 {} 통한 전송 
               JSON.stringify(obj), //외부에서 javascript 객체를 생성하여 전송
         //      JSON.stringify(objectData), // javascript 클래스를 통한 전송    
      }
         //ajax는 success fetch는 then 
      ).then( // fetch Ajax의 실행 결과인 response의 성공을 isOk을 판단하여 오류처리  
            // 성공을 한다면, 반환되는 response가 promise의 객체로 반환되고, 반환타입은 형태에 따라서 함수 호출 
             response => { 
               if(!response.ok){
                  console.log(response)
                  throw new Error("검색된 회원의 정보가 없습니다 \n 회원가입을 진행해주세요. :( ")
               }else{
                  return response.json();
               }
            }
      ).then( // 성공을 했을 경우 혹은 반환되는 data를 받아서 처리 하는 곳 
         data => { 
            console.log(data)
         //   location.href="./login.do";
         if(data =="등록 오류 입니다."){
            throw new Error(data);
            }
         location.href="./login.do"
            
         }
      ).catch(
            (e) => {alert(e.message);}
      );
   }
}