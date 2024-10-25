// detail.js

// DOM 요소들이 모두 로드된 후 실행

const stdNo = new URLSearchParams(location.search).get("stdNo"); // 할 일 번호

document.addEventListener("DOMContentLoaded", function() {
  // 버튼 요소 가져오기
  const updateBtn = document.getElementById("updateBtn");
  const deleteBtn = document.getElementById("deleteBtn");
  const goToListBtn = document.getElementById("goToListBtn");

  // 수정 버튼 클릭 이벤트
  if(updateBtn) {
      updateBtn.addEventListener("click", function() {
          // stdNo는 JSP에서 전달받은 값을 사용
          const stdNo = this.getAttribute("data-std-no");
          location.href = `/std/update?stdNo=${stdNo}`;
      });
  }

  // 삭제 버튼 클릭 이벤트
  if(deleteBtn) {
      deleteBtn.addEventListener("click", function() {
          const stdNo = this.getAttribute("data-std-no");
          deleteStudent(stdNo);
      });
  }

  // 목록으로 버튼 클릭 이벤트
  if(goToListBtn) {
      goToListBtn.addEventListener("click", function() {
          location.href = "/";
      });
  }
});

// 학생 삭제 함수
function deleteStudent(stdNo) {
  // 삭제 확인 대화상자
  if(confirm("정말 삭제하시겠습니까?")) {
      try {
          location.href = `/std/delete?stdNo=${stdNo}`;
      } catch(error) {
          console.error("삭제 중 오류 발생:", error);
          alert("삭제 중 오류가 발생했습니다. 다시 시도해 주세요.");
      }
  }
}