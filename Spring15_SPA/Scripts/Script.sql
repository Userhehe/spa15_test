------답변형 게시판--------

SELECT * 
      FROM (
      <![CDATA[
         SELECT SEQ, ID, TITLE,
         CASE WHEN STEP>0 THEN LPAD(' ', 1+(STEP*6)*5, '&emsp;') || '<img>' || CONCAT(SUBSTR(TITLE, 0, 10),'....') 
         ELSE TITLE END 
         CONTENT, STEP , "DEPTH" , "REF" , DELFLAG , CREATEAT, 
         ROW_NUMBER() OVER (ORDER BY REF DESC, STEP ASC) rn 
         FROM ANSWERBOARD a)
      ]]>
      
      <if test="auth == 'U'">
            WHERE DELFLAG = 'N'      
      </if>
            WHERE rn BETWEEN #{first} AND #{last};
         
   </select>
   
      <select id="getAllBoardCount" resultType="java.lang.Integer">
         SELECT COUNT(*) 
         FROM ANSWERBOARD a ;
      </select>
   
   <delete id="setBoardDelFlag">
         UPDATE ANSWERBOARD SET DELFLAG = 'Y' 
         WHERE SEQ = #{seq}   
   </delete>
   
   <select id="getOneBoard" resultType="com.hee.edu.vo.BoardVo">
   SELECT SEQ, ID, TITLE, CONTENT, TO_CHAR(CREATEAT, 'YYYY-MM-DD
   hh24:mi:ss') CREATEAT
   FROM ANSWERBOARD a
   WHERE SEQ = #{seq}
   </select>
   
   <update id="setBoardUpdate">
   UPDATE ANSWERBOARD SET TITLE = #{title}, CONTENT = #{content}
   WHERE SEQ = #{seq} 
      
   </update>
   
   <update id="setReplyUpdate">
   UPDATE ANSWERBOARD SET STEP = STEP + 1
   WHERE "REF" = (SELECT REF FROM ANSWERBOARD a WHERE SEQ= #{seq})
   AND STEP > (SELECT STEP FROM ANSWERBOARD a2 WHERE SEQ =#{seq})
      
   </update>

   <insert id="setReplyInsert">
   INSERT INTO ANSWERBOARD (SEQ, ID, TITLE, CONTENT,
   STEP, "DEPTH", "REF",
   DELFLAG, CREATEAT)
   VALUES ((SELECT NVL(MAX(SEQ),0)+1 FROM ANSWERBOARD a),#{id},#{title},#{content},
   (SELECT STEP + 1 FROM ANSWERBOARD a4 WHERE SEQ=#{seq}),
   (SELECT "DEPTH" + 1 FROM ANSWERBOARD a2 WHERE SEQ =#{seq}),
   (SELECT REF FROM ANSWERBOARD a3 WHERE SEQ=#{seq}),
   'N',SYSDATE); 
  
  SELECT ID, NAME, AUTH , JOINDATE 
  	FROM USERINFO u
  	WHERE ID='snoopy' AND PASSWORD='1234' AND ENABLED ='Y';
  
  SELECT COUNT(*)
  	FROM USERINFO u 
  	WHERE ID='snoopy';
  
  INSERT INTO (ID, PASSWORD, NAME, AUTH, ENABLED, JOINDATE)
  VALUES('','','','U','Y',SYSDATE);
 
 SELECT *
 	FROM(
 SELECT ID, NAME, AUTH, ENABLED, JOINDATE, ROW_NUMBER() OVER(ORDER BY JOINDATE) rm
 	FROM USERINFO u 
 	);
 WHERE rm BETWEEN '' AND '';
	





