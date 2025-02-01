/**
 * 
 */

$(document).ready(function() {

	$("#customLoginButton").click(function(event) {
		event.preventDefault();
		var loginButton = $(this);
		loginButton.addClass('disabled');
		loginButton.prop('disabled', true);
		var formdata = {
			"loginModel.username": $("#username").val(),
			"loginModel.password": $("#password").val()
		};
		$.ajax({
			url: 'login/loginUser',
			method: "POST",
			data: formdata,
			success: function(response) {
				// console.log(response);
				// if (response.status === "success") {
				// 	alert("Login Successfull");

				// 	window.location.href='dashboard';
				// } else if(response.status === "errorLock"){
				// 	alert(response.message);
				// 	loginButton.prop('disabled',false);
				// 	loginButton.removeClass('disabled');
				// } else if(response.status === "errorPass") {
				// 	alert(response.message);
				// 	loginButton.prop('disabled',false);
				// 	loginButton.removeClass('disabled');
				// } else if(response.status === "errorUname") {
				// 	alert(response.message);
				// 	loginButton.prop('disabled',false);
				// 	loginButton.removeClass('disabled');
				// } else {
				// 	alert(response.message);
				// 	loginButton.prop('disabled',false);
				// 	loginButton.removeClass('disabled');
				// }
				if (response.status === "success") {
					showLoginModal(
						'Login Successful',
						'You have logged in successfully',
						'btn-success',
						'OK',
						true
					);
				} else {
					showLoginModal(
						'login Failed',
						response.message || "Invalid Username or Password",
						"btn-danger",
						"Close",
						false
					)
					loginButton.prop('disabled', false);
					loginButton.removeClass('disabled');
				}
			},
			error: function(xhr, status, error) {
				console.log(error);
				showLoginModal(
					'Error',
					'Something went wrong. Please try again,',
					'btn-danger',
					'Close',
					false
				)
				loginButton.prop('disabled', false);
				loginButton.removeClass('disabled');
			}
		});
	});

	function showLoginModal(title, body, btnClass, btnText, redirect) {
		$('#loginPageModalTitle').text(title);
		$('#loginPageModal .modal-body').html(body);
		$('#loginPageModal .modal-footer').html(`
					<button type="button" class="btn ${btnClass}" id="modalActionButton">${btnText}</button>
				`);

		$('#loginPageModal').modal('show');

		$('#modalActionButton').off('click').on('click', function() {
			if (redirect) {
				window.location.href = "dashboard";
			} else {
				$('#loginPageModal').modal('hide');
			}
		})
	}

});