/**
 * 
 */

function formatValue(value) {
    return value === null || value === "null" || value === undefined ? "-" : value;
}

function showUserDetails(userDetails) {
	let user;
	try {
		user = JSON.parse(userDetails);
	} catch (e) {
		console.error("Invalid JSON passed to showUserDetails", e);
		return;
	}
	document.getElementById('viewUserDetailStaticModalLabel').innerText = `${formatValue(user.firstName)} ${formatValue(user.lastName)}`;

	document.querySelector('.custom-user-reader-modal-user-id').innerText = formatValue(user.userId);
	document.querySelector('.custom-user-reader-modal-user-name').innerText = `${formatValue(user.firstName)} ${formatValue(user.lastName)}`;
	document.querySelector('.custom-user-reader-modal-user-username').innerText = formatValue(user.username);
	document.querySelector('.custom-user-reader-modal-user-email').innerText = formatValue(user.emailId);
	document.querySelector('.custom-user-reader-modal-user-contact').innerText = formatValue(user.contactNum);
	document.querySelector('.custom-user-reader-modal-user-creation').innerText = formatValue(user.createdOn);
	document.querySelector('.custom-user-reader-modal-user-validity').innerText = formatValue(user.validTill);
	document.querySelector('.custom-user-reader-modal-user-makerid').innerText = formatValue(user.makerCd);
	document.querySelector('.custom-user-reader-modal-user-makerdt').innerText = formatValue(user.makerDt);

	$('#viewUserDetailStaticModal').modal('show');

	$('#viewUserDetailStaticModal .modal-footer').html(`
		 <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Close</button>
	`);
}