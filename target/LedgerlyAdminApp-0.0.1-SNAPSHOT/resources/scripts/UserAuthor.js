var contextPath = window.location.pathname.split('/')[1];

function authorizeUser(userId) {
	$('#viewUserDetailStaticModal').modal('hide');
	$('.modal-backdrop').remove();
	/*console.log("User Id: ", userId);*/
	if (userId !== null || userId.trim() !== '') {
		$.ajax({
			url: '/' + contextPath + '/user/authorizeAction',
			method: 'POST',
			data: {
				'userId': userId
			},
			success: function(response) {
				if (response.status === 'success') {
					alert(response.message || 'User authorization succesfull');
					loadPage('/userAuthor');
				} else {
					console.error("An error occurred: ", response.message);
					$('#viewUserDetailStaticModal').modal('hide');
				}
			},
			error: function(xht, status, error) {
				console.error("An error occurred: ", error);
			}
		});
	} else {
		alert("Something went wrong. Please try again later.")
	}
}

function rejectUser(userId) {
	$('#viewUserDetailStaticModal').modal('hide');
	$('.modal-backdrop').remove();

	if (userId !== null || userId.trim() !== '') {
		$.ajax({
			url: '/' + contextPath + '/user/rejectAction',
			method: 'POST',
			data: {
				'userId': userId
			},
			success: function(response) {
				if (response.status === 'success') {
					alert(response.message || 'User authorization succesfull');
					loadPage('/userAuthor');
				} else {
					console.error("An error occurred: ", response.message);
					$('#viewUserDetailStaticModal').modal('hide');
				}
			},
			error: function(xht, status, error) {
				console.error("An error occurred: ", error);
			}
		});
	} else {
		alert("Something went wrong. Please try again later.")
	}
}

function showUserDetails(userDetails) {
	const user = JSON.parse(userDetails);

	document.getElementById('viewUserDetailStaticModalLabel').innerText = `${user.firstName} ${user.lastName}`;

	document.querySelector('.custom-author-user-modal-user-id').innerText = user.userId;
	document.querySelector('.custom-author-user-modal-user-name').innerText = `${user.firstName} ${user.lastName}`;
	document.querySelector('.custom-author-user-modal-user-username').innerText = user.username;
	document.querySelector('.custom-author-user-modal-user-email').innerText = user.emailId;
	document.querySelector('.custom-author-user-modal-user-contact').innerText = user.contactNum;
	document.querySelector('.custom-author-user-modal-user-creation').innerText = user.createdOn;
	document.querySelector('.custom-author-user-modal-user-validity').innerText = user.validTill;
	document.querySelector('.custom-author-user-modal-user-makerid').innerText = user.makerCd;
	document.querySelector('.custom-author-user-modal-user-makerdt').innerText = user.makerDt;

	$('#viewUserDetailStaticModal').modal('show');

	$('#viewUserDetailStaticModal .modal-footer').html(`
		 <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
		 <button type="button" class="btn btn-success btn-sm" onclick="authorizeUser(${user.userId})">Approve</button>
		 <button type="button" class="btn btn-danger btn-sm" onclick="rejectUser(${user.userId})">Reject</button>
	`);
}