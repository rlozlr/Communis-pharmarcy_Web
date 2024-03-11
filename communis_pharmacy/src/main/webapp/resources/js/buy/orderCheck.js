function showAddressModal() {
    const addressModal = document.getElementById('addressModal');
    if (addressModal) {
        addressModal.classList.add('show');
        addressModal.style.display = 'block';
    }
};

document.addEventListener('click', (e)=>{
    console.log(e.target);
    if (e.target.id == 'addressSearchBtn') {
        let roadName = document.getElementById('roadName').value; // roadName 값을 여기서 가져옵니다.
        console.log(roadName);
        addressInfo(roadName)
            .then(result => {
                console.log(result);
                if (result && result.length > 0) {
                    displaySearchResults(result);
                } else {
                    alert('검색 실패');
                    document.querySelector('.btn-close').click();
                }
            })
            .catch(error => {
                console.error('주소 정보를 가져오는 도중 오류가 발생했습니다:', error);
            });
    }
})

async function addressInfo(roadName) {
    try {
        const url = `/orderController/${roadName}`;
        const config = {
            method: 'GET', 
            headers: {
                'Content-Type': 'application/xml; charset=utf-8'
            }
        };
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}
