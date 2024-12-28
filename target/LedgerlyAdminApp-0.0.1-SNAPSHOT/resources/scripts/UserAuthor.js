/**
 * 
 */

var contextPath = window.location.pathname.split('/')[1];

function authorizeUser(userId) {
	console.log("User Id: ",userId);
	if(userId !== null || userId.trim() !== '') {
		$.ajax({
			url: '/' + contextPath + '/user/authorAction',
			method: 'POST',
			data: {
				'userId': userId
			},
			success: function(response) {
				if(response.status === 'success') {
					alert(response.message || 'User authorization succesfull');
					loadPage('/userAuthor');
				} else {
					console.error("An error occurred: ",response.message);
				}	
			},
			error: function (xht,status,error) {
				console.error("An error occurred: ", error);
			}
		});
	}else {
		alert("Something went wrong. Please try again later.")
	}
}

function showUserDetails(user) {
	console.log("User data: ",user);
	$('#viewUserDetailStaticModalLabel').text(`${user.firstName} ${user.lastName}`);
	$('#viewUserDetailStaticModalLabelDate').text(user.createdOn);
	
	$('.custom-author-user-modal-user-id').text(user.userId);
	$('.custom-author-user-modal-user-name').text(`${user.firstName} ${user.lastName}`);
	$('.custom-author-user-modal-user-username').text(user.username);
	$('.custom-author-user-modal-user-email').text(user.emailId);
	$('.custom-author-user-modal-user-contact').text(user.contactNum);
	$('.custom-author-user-modal-user-creation').text(user.createdOn);
	$('.custom-author-user-modal-user-validity').text(user.validTill);
	$('.custom-author-user-modal-user-makerid').text(user.makerCd);
	$('.custom-author-user-modal-user-makerdt').text(user.makerDt);
	
	$('#viewUserDetailStaticModal').modal('show');
	
	$('#viewUserDetailStaticModal .modal-footer').html(`
	       <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
	       <button type="button" class="btn btn-success btn-sm" onclick="authorizeUser(${user.userId})">Approve</button>
	   `);
	
	/*<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>*/
}