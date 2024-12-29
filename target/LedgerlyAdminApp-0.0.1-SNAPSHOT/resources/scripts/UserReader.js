/**
 * 
 */

function showUserDetails(userDetails) {
	const user = JSON.parse(userDetails);

	document.getElementById('viewUserDetailStaticModalLabel').innerText = `${user.firstName} ${user.lastName}`;

	document.querySelector('.custom-user-reader-modal-user-id').innerText = user.userId;
	document.querySelector('.custom-user-reader-modal-user-name').innerText = `${user.firstName} ${user.lastName}`;
	document.querySelector('.custom-user-reader-modal-user-username').innerText = user.username;
	document.querySelector('.custom-user-reader-modal-user-email').innerText = user.emailId;
	document.querySelector('.custom-user-reader-modal-user-contact').innerText = user.contactNum;
	document.querySelector('.custom-user-reader-modal-user-creation').innerText = user.createdOn;
	document.querySelector('.custom-user-reader-modal-user-validity').innerText = user.validTill;
	document.querySelector('.custom-user-reader-modal-user-makerid').innerText = user.makerCd;
	document.querySelector('.custom-user-reader-modal-user-makerdt').innerText = user.makerDt;

	$('#viewUserDetailStaticModal').modal('show');

	$('#viewUserDetailStaticModal .modal-footer').html(`
		 <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
	`);
}