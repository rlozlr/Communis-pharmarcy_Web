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
        document.getElementById('files').click();
    } else if (e.target.id == 'files') {
        document.getElementById('files').addEventListener('change', () => {
            // 파일 선택 창이 닫힌 후에 filelist를 가져옴
            const fileObj = document.getElementById('files').files;
            console.log(fileObj);
    
            document.getElementById('regBtn').disabled = false;
    
            let div = document.getElementById('fileZone');
            div.innerHTML = '';
    
            let isOk = 1;  
            let ul = `<ul class="list-group list-group-flush">`;
                for(let file of fileObj) {
                    let vaildResult = fileValidation(file.name, file.size);
                    isOk *= vaildResult;
                    ul += `<li class="list-group-item">`;
                    ul += `<div class="mb-3">`;
                    ul += `${vaildResult ? '<div class="fw-bold"> 업로드 가능</div>' : '<div class="fw-bold text-danger">} 업로드 불가능 </div>'}`;
                    ul += `${file.name}</div>`;
                    ul += `<span class="badge rounded-pill text-bg-${vaildResult ? 'success':'danger'}">${file.size}Byte</span>`;
                    ul += `</li>`;
                }
            ul += `</ul>`;
            div.innerHTML = ul;
    
            if(isOk == 0) {
                document.getElementById('regBtn').disabled = true;
            }
        })
    }
});

const regExp = new RegExp("\.(exe|sh|bat|mis|dll|jar)$");   // 실행파일 패턴
const regExpImg = new RegExp("\.(jpg|jpeg|gif|png|bmp)$");  // 이미지 파일 패턴
const maxSize = 1024*1024*20;   // 20MB 사이즈로 설정

// Validation : 규칙설정
// return 0 / 1로 리턴
function fileValidation(fileName, fileSize) {
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
    <input type="file" name="files" class="form-control" id="files" multiple="multiple" style="display: none">
    </div>
    <!-- 파일 목록 표시라인 -->
    <div class="mb-3" id="fileZone"></div>
    
    <button type="submit" id="regBtn">등록</button>
    </form>
    `;
}


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