// -----------------USER OPERATIONS---------------------------------------------
// ADD USER BUTTON
$(function() {
  $('#addUserBtn').click(function() {
    var data = {
      fullName: $('#fullName').val(),
      userName: $('#userName').val(),
      address: $('#address').val(),
      email: $('#email').val(),
      phoneNumber: $('#phoneNumber').val(),
      userPassword: $('#userPassword').val(),
      role: $('#role').val()
    };
    $.ajax({
      url: '/users/save',
      method: 'post',
      data: JSON.stringify(data),
      contentType: 'application/json',
      dataType: "json",
      cache: false
    }).done(function(data, status, jqxhr) {
	    alert("Added a new user successfully!!!");
	    $('#exampleModal').modal('hide');
	    $("#fullName").val("");
	    $("#userName").val("");
	    $("#userPassword").val("");
	    $("#address").val("");
	    $("#email").val("");
	    $("#phoneNumber").val("");
	    $("#errorFullName").text("");
	    $("#errorUserName").text("");
	    $("#errorPassword").text("");
	    $("#errorAddress").text("");
	    $("#errorEmail").text("");
	    $("#errorPhoneNumber").text("");
    }).fail(function(data, status, jqxhr) {
        alert("Failed to add a new user!!!");
        $.each(data.responseJSON.errors,(index, value) => {
        if(value.field == "fullName") {
            $('#errorFullName').text(value.defaultMessage).css({"display":"block", "color":"red"});
        }else{
            $("#errorFullName").text("");
        }
        if(value.field == "userName") {
            $('#errorUserName').text(value.defaultMessage).css({"display":"block", "color":"red"});
        }else{
            $("#errorUserName").text("");
        }
        if(value.field == "userPassword") {
	        $('#errorPassword').text(value.defaultMessage).css({"display":"block", "color":"red"});
	    }else{
	        $("#errorPassword").text("");
	    }
		if(value.field == "address") {
             $('#errorAddress').text(value.defaultMessage).css({"display":"block", "color":"red"});
        }else{
            $("#errorAddress").text("");
        }
        if(value.field == "email"){
            $('#errorEmail').text(value.defaultMessage).css({"display":"block", "color":"red"});
        }else{
            $("#errorEmail").text("");
        }
        if(value.field == "phoneNumber") {
            $('#errorPhoneNumber').text(value.defaultMessage).css({"display":"block", "color":"red"});
        }else{
            $("#errorPhoneNumber").text("");
        }
        });
    });
   });
 });


// USER UPDATE BUTTON
$(function() {
  $('#userUpdateBtn').click(function() {
    var data = {
      id: $('#id').val(),
      fullName: $('#fullName').val(),
      address: $('#address').val(),
      phoneNumber: $('#phoneNumber').val(),
      role: $('#role').val()
    };
    $.ajax({
      url: '/users/saveAndUpdate',
      method: 'post',
      data: JSON.stringify(data),
      contentType: 'application/json',
      dataType: "json",
      cache: false
    }).done(function(data, status, jqxhr) {
        window.location = "/users/list";
    }).fail(function(data, status, jqxhr) {
        console.log(data);
        alert('Failed to update!!!');
        $.each(data.responseJSON.errors,(index, value) => {
            if(value.field == "fullName") {
                $('#errorFullName').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorFullName").text("");
            }
            if(value.field == "userName") {
                $('#errorUserName').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorUserName").text("");
            }
            if(value.field == "userPassword") {
                $('#errorPassword').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorPassword").text("");
            }
            if(value.field == "address") {
                 $('#errorAddress').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorAddress").text("");
            }
            if(value.field == "email"){
                $('#errorEmail').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorEmail").text("");
            }
            if(value.field == "phoneNumber") {
                $('#errorPhoneNumber').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorPhoneNumber").text("");
            }
        });
    });
   });
 });

// LOCK/UNLOCK BUTTON - UPDATE STATUS
function changeStatus(id, userStatus) {
// 1 và 0 ở đây dùng để gán lại giá trị vào server để hiện thị Status nghịch
    userStatus = $("#lockedId-" + id).text() == "Locked" ? '1' : '0';
    var data = {
      id: id,
      userStatus:userStatus
    };
    $.ajax({
      url: '/users/updateStatus',
      method: 'post',
      data: JSON.stringify(data),
      contentType: 'application/json',
      dataType: "json",
      cache: false
    }).done(function(data, status, jqxhr) {
        if($("#buttonUnlock-" + id).hasClass("disabled")){
            $("#buttonUnlock-" + id).removeClass("disabled");
            $("#buttonLock-" + id).addClass("disabled");
            $("#lockedId-" + id).text("Locked").removeClass("blue").addClass("red").show();
        }else if($("#buttonLock-" + id).hasClass("disabled")){
            $("#buttonLock-" + id).removeClass("disabled");
            $("#buttonUnlock-" + id).addClass("disabled");
            $("#lockedId-" + id).text("Unlocked").addClass("blue").show();
        }

    }).fail(function(data, status, jqxhr) {
        alert('Failed to change user status!!!');
    });
 };

 // -----------------DONATION OPERATIONS---------------------------------------------
// ADD DONATION BUTTON
$(function() {
  $('#addDonationBtn').click(function() {
    var data = {
      donationCode: $('#donationCode').val(),
      donationName: $('#donationName').val(),
      startDate: $('#startDate').val(),
      endDate: $('#endDate').val(),
      organizationName: $('#organizationName').val(),
      phoneNumber: $('#phoneNumber').val(),
      donationDescription: $('#donationDescription').val()
    };
    console.log(data);
    $.ajax({
      url: '/donations/save',
      method: 'post',
      data: JSON.stringify(data),
      contentType: 'application/json',
      dataType: "json",
      cache: false
    }).done(function(data, status, jqxhr) {
        alert("Added a new donation successfully!!!");
        $('#exampleModal').modal('hide');
        $("#donationCode").val("");
        $("#startDate").val("");
        $("#endDate").val("");
        $("#donationName").val("");
        $("#organizationName").val("");
        $("#phoneNumber").val("");
        $("#donationDescription").val("");
        $("#errorDonationCode").text("");
        $("#errorStartDate").text("");
        $("#errorEndDate").text("");
        $("#errorDonationName").text("");
        $("#errorOrganizationName").text("");
        $("#errorPhoneNumber").text("");
        $("#errorDonationDescription").text("");
    }).fail(function(data, status, jqxhr) {
	    alert("Failed to add a new donation!!!");
        $.each(data.responseJSON.errors,(index, value) => {
            if(value.field == "donationCode"){
                $('#errorDonationCode').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorDonationCode").text("");
            }
            if(value.field == "startDate") {
                $('#errorStartDate').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorStartDate").text("");
            }
            if(value.field == "endDate") {
                $('#errorEndDate').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorEndDate").text("");
            }
            if(value.field == "donationName") {
                $('#errorDonationName').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorDonationName").text("");
            }
            if(value.field == "organizationName") {
                $('#errorOrganizationName').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorOrganizationName").text("");
            }
            if(value.field == "phoneNumber") {
                $('#errorPhoneNumber').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorPhoneNumber").text("");
            }
            if(value.field == "donationDescription") {
                $('#errorDonationDescription').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorDonationDescription").text("");
            }
        });
     });
   });
 });

// DONATION UPDATE BUTTON
$(function() {
  $('#donationUpdateBtn').click(function() {
    var data = {
    id: $('#id').val(),
          donationCode: $('#donationCode').val(),
          donationName: $('#donationName').val(),
          startDate: $('#startDate').val(),
          endDate: $('#endDate').val(),
          organizationName: $('#organizationName').val(),
          phoneNumber: $('#phoneNumber').val(),
          donationDescription: $('#donationDescription').val()
        };
    $.ajax({
      url: '/donations/saveAndUpdate',
      method: 'post',
      data: JSON.stringify(data),
      contentType: 'application/json',
      dataType: "json",
      cache: false
    }).done(function(data, status, jqxhr) {
    alert("Updated the donation's information Successfully!!!");
        window.location = "/donations/list";
    }).fail(function(data, status, jqxhr) {
        alert('Failed to update the donation!!!');
        $.each(data.responseJSON.errors,(index, value) => {
            if(value.field == "donationCode"){
                $('#errorDonationCode').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorDonationCode").text("");
            }
            if(value.field == "startDate") {
                $('#errorStartDate').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorStartDate").text("");
            }
            if(value.field == "endDate") {
                $('#errorEndDate').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorEndDate").text("");
            }
            if(value.field == "donationName") {
                $('#errorDonationName').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorDonationName").text("");
            }
            if(value.field == "organizationName") {
                $('#errorOrganizationName').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorOrganizationName").text("");
            }
            if(value.field == "phoneNumber") {
                $('#errorPhoneNumber').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorPhoneNumber").text("");
            }
            if(value.field == "donationDescription") {
                $('#errorDonationDescription').text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorDonationDescription").text("");
            }
        });
      });
   });
 });

 // DONATE/END/CLOSE BUTTONS - UPDATE STATUS
 function changeDonationStatus(id, donationStatus) {
 // 1 và 0 ở đây dùng để gán lại giá trị vào server để hiện thị Status nghịch
	if($('#donateIdBtn-' + id).text() == "Donate"){
       donationStatus = 1;
    } else if($('#endIdBtn-' + id).text() == "End"){
       donationStatus = 2;
    }else if($('#closeIdBtn-' + id).text() == "Close"){
		donationStatus = 3;
    }
     var data = {
       id: id,
       donationStatus:donationStatus
     };
     $.ajax({
       url: '/donations/updateStatus',
       method: 'post',
       data: JSON.stringify(data),
       contentType: 'application/json',
       dataType: "json",
       cache: false
     }).done(function(data, status, jqxhr) {
        if($('#donateIdBtn-' + id).text() == "Donate"){ // truong hop khong load lai trang thi thay doi chung Id cua mot nut va mot trang thai
            $('#createdId-' + id).text("Progress").removeClass("blue").addClass("green");
            $('#donateIdBtn-' + id).text("End");
        } else if($('#donateIdBtn-' + id).text() == "End"){
            $('#createdId-' + id).text("Ended").removeClass("green").addClass("yellow");
            $('#donateIdBtn-' + id).text("Close");
        }else if($('#donateIdBtn-' + id).text() == "Close"){
	         $('#createdId-' + id).text("Closed").removeClass("yellow").addClass("red");
	         $('#donateIdBtn-' + id).hide();
	         $('#deleteDonationBtn-' + id).removeClass("red").addClass("disabled");
	         $('#updateDonationBtn-' + id).removeClass("blue").addClass("disabled");
        }else if($('#endIdBtn-' + id).text() == "End"){ // truong hop load lai trang thi cac id cua cac nut, cac trang thai thay doi theo thu tu
				$('#progressId-' + id).text("Ended").removeClass("green").addClass("yellow");
				$('#endIdBtn-' + id).text("Close");
        }else if($('#closeIdBtn-' + id).text() == "Close"){
		        $('#endedId-' + id).text("Closed").removeClass("yellow").addClass("red");
		        $('#closeIdBtn-' + id).hide();
		        $('#deleteDonationBtn-' + id).removeClass("red").addClass("disabled");
		        $('#updateDonationBtn-' + id).removeClass("blue").addClass("disabled");
       }
     }).fail(function(data, status, jqxhr) {
        alert('Failed to change donation status!!!');
     });
  };


//  --------------------USER DONATION-------------------------------------
//DONATE BUTTON
function donateBtn(id) {
    var data = {
          donationId: id,
          userDonationName: $('#userDonationName-' + id).val(),
          userDonationMoney: $('#userDonationMoney-' + id).val(),
          userDonationText: $('#userDonationText-' + id).val(),
        };
    $.ajax({
      url: '/user-donations/save',
      method: 'post',
      data: JSON.stringify(data),
      contentType: 'application/json',
      dataType: "json",
      cache: false
    }).done(function(data, status, jqxhr) {
	    alert('Donated Successfully!!!');
	    $("#exampleModal-" + id).modal('hide');
	    $("#userDonationName-" + id).val("");
	    $("#userDonationMoney-" + id).val("");
	    $("#userDonationText-" + id).val("");
	    $("#errorUserDonationName-" + id).text("");
	    $("#errorUserDonationMoney-" + id).text("");
	    $("#errorUserDonationText-" + id).text("");
    }).fail(function(data, status, jqxhr) {
        alert("Failed to donate!!!")
        $.each(data.responseJSON.errors,(index, value) => {
            if(value.field == "userDonationName"){
                $("#errorUserDonationName-" + id).text("").text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorUserDonationName-" + id).text("");
            }
            if(value.field == "userDonationMoney") {
                $("#errorUserDonationMoney-" + id).text("").text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorUserDonationMoney-" + id).text("");
            }
            if(value.field == "userDonationText") {
                $("#errorUserDonationText-" + id).text("").text(value.defaultMessage).css({"display":"block", "color":"red"});
            }else{
                $("#errorUserDonationText-" + id).text("");
            }
        });
    });
};

// APPROVE/APPROVED BUTTONS
function changeUserDonationStatus(id, userDonationStatus) {
// 1 và 0 ở đây dùng để gán lại giá trị vào server để hiện thị Status nghịch
    userDonationStatus = $("#udStatusButton-" + id).text() == "Approve" ? '1' : '0';
    var data = {
      id: id,
      userDonationStatus:userDonationStatus
    };
    $.ajax({
      url: '/user-donations/updateStatus',
      method: 'post',
      data: JSON.stringify(data),
      contentType: 'application/json',
      dataType: "json",
      cache: false
    }).done(function(data, status, jqxhr) {
        if($("#buttonApprove-" + id).text() == "Approve"){
	        $("#buttonApprove-" + id).addClass("hiddenAll");
	        $("#udStatusButton-" + id).text("Approved").addClass("green");
        }
        $("#money").val(data + "VND");
    }).fail(function(data, status, jqxhr) {
        alert('Failed to change user status!!!');
    });
 };