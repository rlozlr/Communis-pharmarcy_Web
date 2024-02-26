console.log ('menu register js in~!!');

document.addEventListener('click',(e)=>{
    if(e.target.id == 'regBtn') {
        oneInsert();
    } else if(e.target.id == 'selectedBtn') {
        selectedInsert();
    }
});

async function sendOneData(pillInfo) {
    try {
        const url = "/menu/insert";
        const config = {
            method: 'post',
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(pillInfo)
        };
        
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result; 
    } catch (error) {
        console.log(error);
    }
}

async function sendSelectedData(selectedItems) {
    try {
        const url = "/menu/insertSelected";
        const config = {
            method: 'post',
            headers: {
                'content-type': 'application/json; charset=utf-8'
            },
            body: JSON.stringify(selectedItems)
        };
        
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result; 
    } catch (error) {
        console.log(error);
    }
}

function selectedInsert() {
    let selectedItems = [];
    let checkboxes = document.querySelectorAll('input[name="selectedItems"]:checked');
    checkboxes.forEach(function(checkbox) {
        let parentForm = checkbox.closest('form'); // form 요소를 찾음
        if (parentForm) {
            selectedItems.push({
                itemName: parentForm.querySelector('input[name="itemName"]').value,
                entpName: parentForm.querySelector('input[name="entpName"]').value,
                efcyQesitm: parentForm.querySelector('input[name="efcyQesitm"]').value,
                thumbnail: parentForm.querySelector('input[name="thumbnail"]').value
            });
        } else {
            console.error("Parent form element is null for checkbox:", checkbox);
        }
    });

    sendSelectedData(selectedItems).then(result =>{
        if (result === 'success') {
            alert('등록 성공했습니다.');
            window.location.reload();
        } else if (result === 'duplicate') {
            alert('이미 존재하는 약품입니다.');
        } else if (result == 'isContain') {
            alert('이미 존재하는 약품을 제외하고 등록했습니다.');
        } else {
            alert('등록에 실패했습니다.');
        }
    });
}

function oneInsert() {
    let btn = document.getElementById('regBtn');
    let parentForm = btn.closest('form'); 
    let pillInfo = {
        itemName: parentForm.querySelector('input[name="itemName"]').value,
        entpName: parentForm.querySelector('input[name="entpName"]').value,
        efcyQesitm: parentForm.querySelector('input[name="efcyQesitm"]').value,
        thumbnail: parentForm.querySelector('input[name="thumbnail"]').value
    };
    
    sendOneData(pillInfo).then(result =>{
        if (result === 'success') {
            alert('등록 성공했습니다.');
            window.location.reload();
        } else if (result === 'duplicate') {
            alert('이미 존재하는 약품입니다.');
        } else if (result == 'isContain') {
            alert('이미 존재하는 약품을 제외하고 등록했습니다.');
        } else {
            alert('등록에 실패했습니다.');
        }
    });
}

// 여기서부터 모달 시도
// 약품 검색 버튼 클릭 시 모달 열기
document.getElementById('pillSearchBtn').addEventListener('click', function() {
    pillSearchModal();
});

// 재고관리 모달 열기
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
            if(result == '1') {
                displaySearchResults(result);
            } else {
                alert('검색 실패');
                document.querySelector('.btn-close').click();
            }
        })
    }
});

async function searchPillInfoFromAPI(itemName) {
    try {
        const url = `/menu/{itemName}`;
        const config ={
            method : 'GET', // GET 메소드로 변경
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            }
        };
        const resp = await fetch(url, config);
        const result = await resp.text(); // 텍스트 데이터로 변환
        return result;
    } catch (error) {
        console.log(error);
    }
};

function displaySearchResults(results) {
    const modalBody = document.getElementById('pillSearchResults');
    modalBody.innerHTML = ''; // 모달 내용 초기화
    
    if (results && results.length > 0) {
        results.forEach(pillInfo => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${pillInfo.itemName}</td>
                <td>${pillInfo.entpName}</td>
                <td>${pillInfo.efcyQesitm}</td>
                <td>
                    <button type="button" class="btn btn-primary selectPillBtn"
                        data-item-name="${pillInfo.itemName}"
                        data-entp-name="${pillInfo.entpName}"
                        data-efcy-qesitm="${pillInfo.efcyQesitm}">
                        선택
                    </button>
                </td>
            `;
            modalBody.appendChild(row);
        });
    } else {
        const row = document.createElement('tr');
        row.innerHTML = `<td colspan="4">검색된 약품 정보가 없습니다.</td>`;
        modalBody.appendChild(row);
    }
}
