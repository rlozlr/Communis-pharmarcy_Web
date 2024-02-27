// 간편관리 모달 열기
function pillSearchModal() {
    const updateModal = document.getElementById('pillSearchModal');
    if (updateModal) {
        updateModal.classList.add('show');
        updateModal.style.display = 'block';
    }
};

document.addEventListener('click',(e)=>{
    console.log(e.target);
    if(e.target.classList.contains('pillSearchBtn')) {
        let itemName = document.getElementById('pillName').value;
        console.log(itemName);
        searchPillInfoFromAPI(itemName).then(result =>{
            console.log(result);
            if (result && result.length > 0) {
                displaySearchResults(result);
            } else {
                alert('검색 실패');
                document.querySelector('.btn-close').click();
            }
        });
    } else if(e.target.id == 'selectBtn') {
        let parentRow = e.target.closest('tr');
        let cells = parentRow.querySelectorAll('td'); // 각 셀(td)을 가져옴
        let pillInfo = {
            itemName : cells[0].innerText, // 첫 번째 셀에 있는 텍스트 가져오기
            entpName : cells[1].innerText, // 두 번째 셀에 있는 텍스트 가져오기
            efcyQesitm : cells[2].innerText, // 세 번째 셀에 있는 텍스트 가져오기
        };

        //document.querySelector('.btn-close').click();
        displayPillInfoForm(pillInfo);
         // 선택 버튼을 누르면 모달 창을 닫음
        const modal = document.getElementById('pillSearchModal');
        const modalBackdrop = document.querySelector('.modal-backdrop');
        modal.style.display = 'none';
        modalBackdrop.style.display = 'none';
    } else if (e.target.id == 'trigger') {
        document.getElementById('files').value = null; // 파일 선택 input 초기화
        document.getElementById('files').click(); // 파일 선택 input 클릭
    } else if (e.target.id === 'files') {
        // 파일 선택 창이 닫힌 후에 filelist를 가져옴
        document.getElementById('files').addEventListener('change', function() {
            const fileObject = this.files;
            console.log(fileObject);

            // 한 번 true가 되면 다시 false로 돌아올 수 없음. 버튼 활성화
            document.getElementById('regBtn').disabled = false;
            let div = document.getElementById('fileZone');
            div.innerHTML ='';  // 기존 추가했던 파일 삭제
            let ul = `<ul class="list-group">`;
            // fileValidation 함수의 리턴 여부를 체크
            // 모든 파일이 1이여야 가능
            let isOk = 1;   // * 로 isOk 처리를 하여 모든 파일이 1이여야 통과
            // 최대 한 개의 파일만 업로드 가능하도록 수정
            for(let file of fileObject) {
                let ulItem = `<li class="list-group-item">`; // 현재 파일에 대한 <li> 요소 초기화
                let validResult = fileValidation(file.name, file.size); // 한 파일에 대한 결과
                isOk *= validResult;
                // 업로드 가능 여부 표시
                ulItem += `<div>${validResult? '업로드 가능' : '업로드 불가능'}</div>`;
                ulItem += `${file.name}`; // 변경된 부분: file.name -> file.pillImgName
                ulItem += `<span class="badge rounded-pill text-bg-${validResult?'success' : 'danger'}">${file.size}Byte</span>`;
                // 이미지 파일인 경우에만 미리보기 표시
                if (file.type.startsWith('image/')) {
                    const reader = new FileReader();
                    reader.onload = function (e) {
                        // 이미지가 로드되면 해당 li에 이미지 추가
                        ulItem += `<div><img src="${e.target.result}" class="img-thumbnail" style="max-width: 100px; max-height: 100px;">`;
                        ulItem += `</div></li>`; // 현재 파일에 대한 <li> 요소 닫기
                        ul += ulItem; // 현재 파일의 <li> 요소를 <ul>에 추가
                        div.innerHTML = ul; // <ul>을 div에 할당하여 화면에 표시
                    };
                    // reader가 이미지 읽도록 하기
                    reader.readAsDataURL(file);
                } else {
                    // 이미지가 아닌 경우에도 해당 li를 닫아줘야 함
                    ulItem += `</li>`; // 현재 파일에 대한 <li> 요소 닫기
                    ul += ulItem; // 현재 파일의 <li> 요소를 <ul>에 추가
                }
            }
            ul += `</ul>`; // <ul> 요소 닫기
            div.innerHTML = ul; // <ul>을 div에 할당하여 화면에 표시

            // 업로드 불가능한 파일이 1개라도 있다면...
            if(isOk == 0) {
                document.getElementById('regBtn').disabled = true;  // 버튼 비활성화
            }
        })
    }
});

async function searchPillInfoFromAPI(itemName) {
    try {
        const url = `/menu/${itemName}`;
        const config ={
            method : 'get',
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body: JSON.stringify()
        };
        const resp = await fetch(url, config);
        const result = await resp.json(); // 텍스트 데이터로 변환
        return result;
    } catch (error) {
        console.log(error);
    }
};

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

function displaySearchResults(results) {
    const modalBody = document.querySelector('#pillSearchResults');
    modalBody.innerHTML = ''; // 모달 내용 초기화
    
    if (results && results.length > 0) {
        results.forEach(pillInfo => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${pillInfo.itemName}</td>
                <td>${pillInfo.entpName}</td>
                <td>${pillInfo.efcyQesitm}</td>
                <td><button type="button" id="selectBtn">선택</button></td>
            `;
            modalBody.appendChild(row);
        });
    } else {
        const row = document.createElement('tr');
        row.innerHTML = `<td colspan="3">검색된 약품 정보가 없습니다.</td>`;
        modalBody.appendChild(row);
    }
};

function displayPillInfoForm(pillInfo) {
    const regForm = document.getElementById('pillRegForm');
    regForm.innerHTML = ``;
    regForm.innerHTML = `
        <form action="/menu/register" method="post" enctype="multipart/form-data">
        <br>
        <h2>약품 정보</h2>
        <div class="mb-3">
            <label for="itemName" class="form-label">제품명</label>
            <input type="text" name="itemName" class="form-control" 
                id="itemName" value="${pillInfo.itemName}">
        </div>
        <div class="mb-3">
            <label for="entpName" class="form-label">제조사</label>
            <input type="text" name="entpName" class="form-control" 
                id="entpName" value="${pillInfo.entpName}">
        </div>
        <div class="mb-3">
            <label for="efcyQesitm" class="form-label">효과·효능</label>
            <input type="text" name="efcyQesitm" class="form-control" 
                id="efcyQesitm" value="${pillInfo.efcyQesitm}">
        </div>
        <div class="mb-3">
            <label for="pillPrice" class="form-label">가격</label>
            <input type="number" name="pillPrice" class="form-control" 
                id="pillPrice">
        </div>
        <div class="mb-3">
            <label for="pillStock" class="form-label">재고</label>
            <input type="number" name="pillStock" class="form-control" 
                id="pillStock">
        </div>
        <div class="mb-3">
            <label for="files" class="form-label">제품사진</label>
            <span><button type="button" id="trigger">사진등록</button></span>
            <input type="file" name="pillImgName" class="form-control" id="files" style="display: none"><br>
        </div>
        <!-- 파일 목록 표시라인 -->
        <div class="mb-3" id="fileZone"></div>
            
        <button type="submit" id="regBtn">등록</button>
    `;
}



