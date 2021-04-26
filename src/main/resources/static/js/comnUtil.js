var zoneCode     = '';
var roadAddr1    = '';
var roadAddr2    = '';
var jibunAddr1   = '';
var jibunAddr2   = '';
var etcAddr      = '';

/**
 * 우편번호찾기 팝업
 * @param 
 * @returns
 */
function fnPostcode(str) {
	Dialog.open('#dialogAdress');
	execDaumPostcode(str);
}

/**
 * 우편번호찾기 팝업2
 * @param 
 * @returns
 */
function fnPostcode2(str) {
	Dialog.open('#dialogAdress2');
	execDaumPostcode2(str);
}


/**
 * daum주소팝업
 * @param 
 * @returns
 */
// Daum API 동작
function execDaumPostcode(str) {
	$('#daumloc').val(str);	// 부모창에서 POST 방식으로 가져옴 H: 자택, C: 직장, E: 기타
	var element_wrap = document.getElementById('daumWrap');  // 우편번호 찾기 화면을 넣을 element
	
	var themeObj = {
		bgColor : "#FFFFFF",
		outlineColor : "#57a095" 
	};
	new daum.Postcode({
		oncomplete: function(data) {
			// 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수
            var addrType = '';  // 신구주소 구분
            var addrArr = new Array();
            
            var _roadAddress = !data.roadAddress ? data.autoRoadAddress : data.roadAddress;
            var _jibunAddress = !data.jibunAddress ? data.autoJibunAddress : data.jibunAddress;

            if(data.buildingName){
            	_roadAddress += " (" + data.buildingName + ")";
            	_jibunAddress += " (" + data.buildingName + ")";
            }
            
            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = _roadAddress;
                addrType = "R";
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = _jibunAddress;
                addrType = "J";
            }
            
            //JR이면 도로명주소 지번지 배열로 반환
            if(str == "RJ") {
            	addrArr[0] = _roadAddress;
            	addrArr[1] = _jibunAddress;
                // 우편번호와 주소 정보를 해당 필드에 넣는다.           
                fnSetZipCode(str, data.zonecode, addrArr, "RJ");   
            } else {
                // 우편번호와 주소 정보를 해당 필드에 넣는다.           
                fnSetZipCode(str, data.zonecode, addr, addrType);    	
            }

            Dialog.close('#dialogAdress');
 
		},
		// 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 넓이, 높이값을 조정한다.
		onresize : function(size) {
			element_wrap.style.height = size.height+'px';
		},
		width : '100%',
		height : '100%',
		//theme : themeObj,
		animation : true,
		autoMapping : true,
		hideEngBtn : true
	}).embed(element_wrap, { // iframe 을 사용하기 위해 embed를 사용해야하며 autoClose가 false여야 주소를 선택해도 창이 닫히지 않는다.
			autoClose: false //기본값 true
	});
	
	// iframe을 넣은 element를 보이게 한다.
	element_wrap.style.display = 'block';
	
}


/**
 * daum주소팝업
 * @param 
 * @returns
 */
// Daum API 동작
function execDaumPostcode2(str) {
	$('#daumloc').val(str);	// 부모창에서 POST 방식으로 가져옴 H: 자택, C: 직장, E: 기타
	var element_wrap = document.getElementById('daumWrap');  // 우편번호 찾기 화면을 넣을 element
	
	var themeObj = {
		bgColor : "#FFFFFF",
		outlineColor : "#57a095" 
	};
	
	new daum.Postcode({
		oncomplete: function(data) {
			
			//초기화
			zoneCode    = '';
			roadAddr1   = '';
			roadAddr2   = '';
			jibunAddr1  = '';
			jibunAddr2  = '';
			etcAddr     = '';
			
			$('#roadAddr').html('');
			$('#jibunAddr').html('');
			$('#etcAddr').val('');
			
			// 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수
            var addrType = '';  // 신구주소 구분
            var addrArr = new Array();
            
            var _roadAddress = !data.roadAddress ? data.autoRoadAddress : data.roadAddress;
            var _jibunAddress = !data.jibunAddress ? data.autoJibunAddress : data.jibunAddress;
            
            if(data.buildingName){
            	_roadAddress += " (" + data.buildingName + ")";
            	_jibunAddress += " (" + data.buildingName + ")";
            }
            
            fnSetAddr('roadAddr', data.zonecode, _roadAddress, '');
            fnSetAddr('jibunAddr',  data.zonecode, _jibunAddress, '');
            
            $('#etcAddr').focus();
		},
		// 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 넓이, 높이값을 조정한다.
		onresize : function(size) {
			element_wrap.style.height = size.height+'px';
		},
		width : '100%',
		height : '100%',
		//theme : themeObj,
		animation : true,
		autoMapping : true,
		hideEngBtn : true
	}).embed(element_wrap, { // iframe 을 사용하기 위해 embed를 사용해야하며 autoClose가 false여야 주소를 선택해도 창이 닫히지 않는다.
			autoClose: false //기본값 true
	});

	// iframe을 넣은 element를 보이게 한다.
	//element_wrap.style.display = 'block';
	
	
}

/**
 * daum주소팝업 주소값 셋팅 (모바일 용)
 * @param 
 * @returns
 */
// Daum API 동작
function fnSetAddr(id, code, addr1, addr2){
	var fullAddr = '';
	if(code !== '') fullAddr += code.trim();
	if(addr1 !== '')	fullAddr += ' ' + addr1.trim();
	if(addr2 !== '')	fullAddr += ' ' + addr2.trim();
	$('#'+id).val(fullAddr);
	zoneCode     = code;
	if(id == "roadAddr") {
		roadAddr1    = addr1;
		roadAddr2    = addr2;
		$("#roadAddrSpan").text(addr1);
	} else {
		jibunAddr1   = addr1;
		jibunAddr2   = addr2;
		$("#jibunAddrSpan").text(addr1);
		// 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분
		$('#daum_zipcode_view').show();	//상세입력영역 노출
		$('#fixed_bottom_view').show();	//확인버튼 노출
		$('#etcAddr').val('');
		$('#etcAddr').focus(); //상세주소로 커서이동
	}

}

/**
 * daum주소팝업 닫기
 * @param 
 * @returns
 */
// Daum API 동작
function closeDaumPostcode() {
	
	var element_wrap = document.getElementById('daumWrap');  // 우편번호 찾기 화면을 넣을 element
	
	// iframe을 넣은 element를 안보이게 한다.
//	element_wrap.style.display = 'none';
    Dialog.close('#dialogAdress');
}

/**
 * daum주소팝업 닫기
 * @param 
 * @returns
 */
// Daum API 동작
function closeDaumPostcode2() {
	
	var element_wrap = document.getElementById('daumWrap');  // 우편번호 찾기 화면을 넣을 element

	$("#roadAddr").val();
	$("#jibunAddr").val();
	$("#etcAddr").val();
	$("#daum_zipcode_view").hide();
	$("#fixed_bottom_view").hide();
	// iframe을 넣은 element를 안보이게 한다.
//	element_wrap.style.display = 'none';
    Dialog.close('#dialogAdress2');
}