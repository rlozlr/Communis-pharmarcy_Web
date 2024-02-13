console.log ('pillRnakDetail js in~!!');

async function spreadPillInfoToServer() {
    try {
        const url = "/pillRank/getPillInfo";
        const config ={
            method : "post",
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body : JSON.stringify()
        };
        const resp = await fetch(url, config);
        const result = await resp.json();

        // 가져온 업체명과 제품명을 가지고 이미지를 가져오는 함수 호출
        await spreadPillImgToServer(result.itemName, result.entpName);

        spreadPillNameFromAPI(result.body);

    } catch (error) {
        console.log(error);
    };
};

async function spreadPillImgToServer(itemName, entpName) {
    try {
        const url = "/pillRank/getPillImg";
        const pillInfo = { itemName, entpName };    // 요청에 필요한 데이터
        const config ={
            method : "post",
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body : JSON.stringify(pillInfo) // 데이터를 요청 본문에 포함
        };
        const resp = await fetch(url, config);
        const result = await resp.json();
        console.log(result);

    } catch (error) {
        console.log(error);
    };
};

function spreadPillNameFromAPI(result) {
    const pillRankItem = document.getElementById('pillRankItemList');
    pillRankItem.innerHTML = '';
    result.forEach(item => {
        let pillItem = `<div class="col">`;
        pillItem += `<div><img src="${item.thumbnail}" alt='이미지가 없습니다'.></div>`;
        pillItem += `<div data-itemName="${item.itemName}" >${item.itemName}</div>`;
        pillItem += `<div data-entpName="${item.entpName}" >${item.entpName}</div>`;
        pillItem += `<div>`;
        pillItem += `<button type="button">장바구니</button>`;
        pillItem += `<button type="button">바로구매</button>`;
        pillItem += `</div></div>`;
        //pillItemArr.push(pillItem);
        pillRankItem.innerHTML += pillItem;
    });
};
