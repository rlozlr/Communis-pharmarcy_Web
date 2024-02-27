// document.getElementById('trigger').addEventListener('click',()=>{
//     document.getElementById('files').click();
// })

// 정규표현식을 사용하여 파일 형식제한 함수 만들기
// 실행파일 막기(exe, bat, sh, mis, dll, jar)
// 파일 사이즈 체크 (20M 사이즈보다 크면 막기)
// 이미지 파일만 올리기(jpg, jpeg, gif, png, bmp)

const regExp = new RegExp("\.(exe|sh|bat|mis|dll|jar)$");   // 실행파일 패턴
const regExpImg = new RegExp("\.(jpg|jpeg|gif|png|bmp)$");  // 이미지 파일 패턴
const maxSize = 1024*1024*20;   // 20MB 사이즈로 설정

// Validation : 규칙설정
// return 0 / 1로 리턴
function fileValidation(name, fileSize) {
    let fileName = name.toLowerCase();
    if(regExp.test(fileName)) { // 파일이름을 실행 파일인지 확인
        return 0;
    } else if (fileSize > maxSize) {
        return 0;
    } else if (!regExpImg.test(fileName)) {
        return 0;
    } else {
        return 1;
    }
}

// 첨부파일에 따라 등록가능한지 체크 함수
// document.addEventListener('change',(e)=>{
//     console.log(e.target);
//     if(e.target.id === 'files') {
//         // 여러개의 파일이 배열로 들어옴
//         const fileObject = document.getElementById('files').files;
//         console.log(fileObject);
//         // 한 번 true가 되면 다시 false로 돌아올 수 없음. 버튼 활성화
//         document.getElementById('regBtn').disabled = false;
//         let div = document.getElementById('fileZone');
//         div.innerHTML ='';  // 기존 추가했던 파일 삭제
//         let ul = `<ul class="list-group">`;
//         // fileValidation 함수의 리턴 여부를 체크
//         // 모든 파일이 1이여야 가능
//         let isOk = 1;   // * 로 isOk 처리를 하여 모든 파일이 1이여야 통과
//         // 최대 한 개의 파일만 업로드 가능하도록 수정
//         if(fileObject.length > 1) {
//             alert("한 개의 파일만 업로드 가능합니다.");
//             return; // 함수 종료
//         }
//         for(let file of fileObject) {
//             let ulItem = `<li class="list-group-item">`; // 현재 파일에 대한 <li> 요소 초기화
//             let validResult = fileValidation(file.name, file.size); // 한 파일에 대한 결과
//             isOk *= validResult;
//             // 업로드 가능 여부 표시
//             ulItem += `<div>${validResult? '업로드 가능' : '업로드 불가능'}</div>`;
//             ulItem += `${file.name}`; // 변경된 부분: file.name -> file.pillImgName
//             ulItem += `<span class="badge rounded-pill text-bg-${validResult?'success' : 'danger'}">${file.size}Byte</span>`;
//             // 이미지 파일인 경우에만 미리보기 표시
//             if (file.type.startsWith('image/')) {
//                 const reader = new FileReader();
//                 reader.onload = function (e) {
//                     // 이미지가 로드되면 해당 li에 이미지 추가
//                     ulItem += `<div><img src="${e.target.result}" class="img-thumbnail" style="max-width: 100px; max-height: 100px;">`;
//                     ulItem += `</div></li>`; // 현재 파일에 대한 <li> 요소 닫기
//                     ul += ulItem; // 현재 파일의 <li> 요소를 <ul>에 추가
//                     div.innerHTML = ul; // <ul>을 div에 할당하여 화면에 표시
//                 };
//                 // reader가 이미지 읽도록 하기
//                 reader.readAsDataURL(file);
//             } else {
//                 // 이미지가 아닌 경우에도 해당 li를 닫아줘야 함
//                 ulItem += `</li>`; // 현재 파일에 대한 <li> 요소 닫기
//                 ul += ulItem; // 현재 파일의 <li> 요소를 <ul>에 추가
//             }
//         }
//         ul += `</ul>`; // <ul> 요소 닫기
//         div.innerHTML = ul; // <ul>을 div에 할당하여 화면에 표시

//         // 업로드 불가능한 파일이 1개라도 있다면...
//         if(isOk == 0) {
//             document.getElementById('regBtn').disabled = true;  // 버튼 비활성화
//         }
//     }
// });


async function removeFileToServer(pillId) {
    try {
        const url = "/menu/remove/" + pillId;
        const config ={
            method : "delete"
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
};