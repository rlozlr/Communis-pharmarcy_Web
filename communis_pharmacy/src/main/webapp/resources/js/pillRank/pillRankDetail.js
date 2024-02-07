console.log ('pillRnakDetail js in~!!');

if (document.getElementById('pillName')) {
    spreadPillInfoToServer();
}

async function spreadPillInfoToServer() {
    try {
        const url = "/pillRank/getPillData";
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

        spreadPillNameFromAPI(result);

    } catch (error) {
        console.log(error);
    };
};

function spreadPillNameFromAPI(result) {
    const pillRankItem = document.getElementById('pillRankItemList');
    pillRankItem.innerHTML = '';
    result.body.items.forEach(item => {
        let pillItem = `<div class="col">`;
        pillItem += `<div>이미지</div>`;
        pillItem += `<div>${item.itemName}</div>`;
        pillItem += `<div>${item.entpName}</div>`;
        pillItem += `<div>`;
        pillItem += `<button type="button">장바구니</button>`;
        pillItem += `<button type="button">바로구매</button>`;
        pillItem += `</div></div>`;
        //pillItemArr.push(pillItem);
        pillRankItem.innerHTML += pillItem;
    });
};