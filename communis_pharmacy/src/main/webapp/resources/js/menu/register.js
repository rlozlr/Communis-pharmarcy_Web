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