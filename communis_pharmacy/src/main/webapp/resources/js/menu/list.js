console.log('menu list js in~!');

// 재고관리 모달 열기
function showUpdateModal() {
    const updateModal = document.getElementById('updateModal');
    if (updateModal) {
        updateModal.classList.add('show');
        updateModal.style.display = 'block';
    }
}

document.addEventListener('click', (e)=>{
    console.log(e.target);
    if(e.target.classList.contains('showUpdateModalBtn')) {
        
        let tr = e.target.closest('tr');
        document.getElementById('showUpdateModalBtn').setAttribute('data-pillid', tr.dataset.pillid);
        document.getElementById('showUpdateModalBtn').setAttribute('data-itemname', tr.dataset.itemname);
        document.getElementById('showUpdateModalBtn').setAttribute('data-entpname', tr.dataset.entpname);
        document.getElementById('showUpdateModalBtn').setAttribute('data-pillstock', tr.dataset.pillstock);
        document.getElementById('showUpdateModalBtn').setAttribute('data-pillprice', tr.dataset.pillprice);

        // 모달에 값을 넣어주기
        document.getElementById('pillId').value = tr.dataset.pillid;
        document.getElementById('itemName').value = tr.dataset.itemname;
        document.getElementById('entpName').value = tr.dataset.entpname;
        document.getElementById('pillStock').value = tr.dataset.pillstock;
        document.getElementById('pillPrice').value = tr.dataset.pillprice;

    } else if(e.target.id == 'updateBtn' ) {
        let updateInvetoryData = {
            pillId : document.getElementById('pillId').value,
            itemName : document.getElementById('itemName').value,
            entpName : document.getElementById('entpName').value,
            pillStock : document.getElementById('pillStock').value,
            pillPrice : document.getElementById('pillPrice').value,
        };
        updateInventory(updateInvetoryData).then(result =>{
            if(result == '1') {
                document.querySelector('.btn-close').click();
                location.reload();
            } else {
                alert('업데이트 실패');
                document.querySelector('.btn-close').click();
            }
        });
    } 
});

// 재고와 가격 업데이트 함수
async function updateInventory(updateInvetoryData) {
    try {
        const url = '/menu/update';
        const config ={
            method : 'put',
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body : JSON.stringify(updateInvetoryData)
        };
        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
};