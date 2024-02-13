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

        // itemName과 entpName 가져오기
        const itemName = result.itemName;
        const entpName = result.entpName;

        // 약 이미지 가져오는 함수 호출
        await spreadPillImgToServer(itemName, entpName);

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

        const thumbnail = result.thumbnail;
        // 이미지 정보와 약 정보 함께 전달하여 약 정보 표시 함수 호출
        spreadPillNameFromAPI(itemName, entpName, thumbnail);

    } catch (error) {
        console.log(error);
    };
};

function spreadPillNameFromAPI(itemName, entpName, thumbnail) {
    const pillRankItem = document.getElementById('pillRankItemList');
    pillRankItem.innerHTML = '';
    //result.forEach(item => {
        let pillItem = `<div class="col">`;
        pillItem += `<div><img src="${thumbnail}" alt='이미지가 없습니다'.></div>`;
        pillItem += `<div data-itemName="${itemName}" >${itemName}</div>`;
        pillItem += `<div data-entpName="${entpName}" >${entpName}</div>`;
        pillItem += `<div>`;
        pillItem += `<button type="button">장바구니</button>`;
        pillItem += `<button type="button">바로구매</button>`;
        pillItem += `</div></div>`;
        //pillItemArr.push(pillItem);
        pillRankItem.innerHTML += pillItem;
    //});
};
