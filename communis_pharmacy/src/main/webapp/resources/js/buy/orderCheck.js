document.getElementById('buyerPhoneNumber').addEventListener('input', function() {
    // 특수 문자를 제거한 후 값을 업데이트
    //this.value = this.value.replace(/[^0-9]/g, '');
    this.value = this.value.replace(/\D/g, '');
});

document.getElementById('recipientPhoneNumber').addEventListener('input', function() {
    // 특수 문자를 제거한 후 값을 업데이트
    this.value = this.value.replace(/\D/g, '');
});

document.getElementById('sameAsBuyer').addEventListener('change', function() {
    if (this.checked) {
        // 체크박스가 체크되었을 때
        var buyerName = document.getElementById('buyerName').value;
        var buyerPhoneNumber = document.getElementById('buyerPhoneNumber').value;
        
        // 특수 문자를 제거한 후 값을 업데이트
        var cleanedBuyerPhoneNumber = buyerPhoneNumber.replace(/\D/g, '');
        
        // 받는이 정보 입력 필드에 구매자 정보를 복사
        document.getElementById('recipientName').value = buyerName;
        document.getElementById('recipientPhoneNumber').value = cleanedBuyerPhoneNumber;
    } else {
        // 체크박스가 체크 해제되었을 때
        // 받는이 정보 입력 필드를 비움
        document.getElementById('recipientName').value = '';
        document.getElementById('recipientPhoneNumber').value = '';
    }
});

document.querySelector('form').addEventListener('submit', function(event) {
    // 각 입력 필드의 값을 가져옴
    var buyerName = document.getElementById('buyerName').value;
    var buyerEmail = document.getElementById('buyerEmail').value;
    var buyerPhoneNumber = document.getElementById('buyerPhoneNumber').value;
    var recipientName = document.getElementById('recipientName').value;
    var recipientPhoneNumber = document.getElementById('recipientPhoneNumber').value;
    var sample6_postcode = document.getElementById('sample6_postcode').value;
    var sample6_address = document.getElementById('sample6_address').value;
    var sample6_detailAddress = document.getElementById('sample6_detailAddress').value;

    // 입력 필드가 하나라도 비어 있으면 주문하기 버튼 비활성화
    if (buyerName === '' || buyerEmail === '' || buyerPhoneNumber === '' || recipientName === '' || recipientPhoneNumber === '' || sample6_postcode === '' || sample6_address === '') {
        event.preventDefault(); // 폼 제출을 막음
        alert('정보를 모두 입력해주세요.');
    }
});

// 버튼 요소들을 가져옵니다.
const bankTransferBtn = document.getElementById('btnBankTransfer');
const cardPaymentBtn = document.getElementById('btnCardPayment');
const kakaoPayBtn = document.getElementById('btnKakaoPay');
const naverPayBtn = document.getElementById('btnNaverPay');

// 모든 버튼의 클릭 이벤트에 대한 처리를 구현합니다.
bankTransferBtn.addEventListener('click', function() {
    // 현재 버튼이 이미 활성화되어 있는지 확인합니다.
    const isActive = bankTransferBtn.classList.contains('active');
    // 현재 버튼이 활성화되어 있지 않으면
    if (!isActive) {
        // 버튼 클릭 시 모든 버튼의 활성화 상태를 해제하고 현재 버튼만 활성화 상태로 변경합니다.
        resetButtonState();
        bankTransferBtn.classList.add('active');
    } else {
        // 이미 활성화된 버튼을 클릭한 경우, 해당 버튼의 활성화 상태를 해제합니다.
        bankTransferBtn.classList.remove('active');
    }
});

cardPaymentBtn.addEventListener('click', function() {
    const isActive = cardPaymentBtn.classList.contains('active');
    if (!isActive) {
        resetButtonState();
        cardPaymentBtn.classList.add('active');
    } else {
        cardPaymentBtn.classList.remove('active');
    }
});

kakaoPayBtn.addEventListener('click', function() {
    const isActive = kakaoPayBtn.classList.contains('active');
    if (!isActive) {
        resetButtonState();
        kakaoPayBtn.classList.add('active');
    } else {
        kakaoPayBtn.classList.remove('active');
    }
});

naverPayBtn.addEventListener('click', function() {
    const isActive = naverPayBtn.classList.contains('active');
    if (!isActive) {
        resetButtonState();
        naverPayBtn.classList.add('active');
    } else {
        naverPayBtn.classList.remove('active');
    }
});

// 모든 버튼의 활성화 상태를 초기화하는 함수입니다.
function resetButtonState() {
    bankTransferBtn.classList.remove('active');
    cardPaymentBtn.classList.remove('active');
    kakaoPayBtn.classList.remove('active');
    naverPayBtn.classList.remove('active');
}



// 카카오 우편 open source
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                //document.getElementById("sample6_extraAddress").value = extraAddr;
            
            } else {
                document.getElementById("sample6_extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
};
