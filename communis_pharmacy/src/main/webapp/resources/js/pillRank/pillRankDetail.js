console.log ('pillRnakDetail js in~!!');

spreadPillInfoToServer();


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
        console.log(result);

        // itemname과 entpname 추출
        const itemName = result.itemName;
        const entpName = result.entpName;
        spreadPillImgToServer(itemName, entpName);

    } catch (error) {
        console.log(error);
    };
};

async function spreadPillImgToServer(itemName, entpName) {
    try {
        const url = "/pillRank/getPillImg";
        const config ={
            method : "post",
            headers : {
                'content-type' : 'application/json; charset=utf-8'
            },
            body : JSON.stringify({ itemName, entpName })
        };
        const resp = await fetch(url, config);
        const result = await resp.json();
        console.log(result);

    } catch (error) {
        console.log(error);
    };
};

function spreadPillNameFromAPI(pillInfo) {
    const pillRankItem = document.getElementById('pillRankItemList');
    pillRankItem.innerHTML = '';
    pillInfo.forEach(item => {
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
