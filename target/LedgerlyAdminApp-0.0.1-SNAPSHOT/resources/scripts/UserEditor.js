/**
 * 
 */

var inputs = document.querySelectorAll("#viewUserDetailStaticModal input");
var originalData = {};
var editableData = ['username', 'emailId', 'contactNum', 'validTill'];
var editBtn = document.getElementById("editUserDetailsBtn");
var saveBtn = document.getElementById("saveUserDetailsBtn");



function showUserDetails(userJson) {
	try {
		var user = JSON.parse(userJson); // Parse JSON string into an object
		/*console.log("User: ", user);*/

		// Populate modal input fields with user data
		document.getElementById('userId').value = user.userId || '';
		document.getElementById('name').value = user.firstName + ' '
			+ user.lastName || '';
		document.getElementById('username').value = user.username || '';
		document.getElementById('emailId').value = user.emailId || '';
		document.getElementById('contactNum').value = user.contactNum || '';
		document.getElementById('makerCd').value = user.makerCd || '';

		if (user.validTill) {
			var formattedDate = user.validTill.split(' ')[0]; // Extract 'YYYY-MM-DD'
			document.getElementById('validTill').value = formattedDate;
		} else {
			document.getElementById('validTill').value = '';
		}

		if (user.makerDt) {
			var formattedDate = user.makerDt.split(' ')[0];
			document.getElementById('makerDt').value = formattedDate;
		} else {
			document.getElementById('makerDt').value = '';
		}

		$('#viewUserDetailStaticModal').modal('show');
		storeOriginalData();
	} catch (e) {
		console.error('Failed to parse user details:', e);
	}
}

function storeOriginalData() {
	inputs.forEach(input => {
		originalData[input.id] = input.value;
	});

	/*console.log("Original Data: ", originalData);*/
}

function editfunction() {
	inputs.forEach(input => {
		if (editableData.includes(input.id || input.name)) {
			input.removeAttribute("readonly");
		}
	});
	editBtn.style.display = "none";
	saveBtn.style.display = "inline-block";
}

function saveFunction() {
	var contextPath = window.location.pathname.split('/')[1];
	let updatedData = {};
	$('#viewUserDetailStaticModal').modal('hide');
	$('.modal-backdrop').remove();
	inputs.forEach(input => {
		var key = input.id;
		if (originalData[key] !== input.value) {
			updatedData[key] = input.value;

		}
		input.setAttribute("readonly", "readonly");
	});

	if (Object.keys(updatedData).length > 0) {
		console.log("Modified Data:", updatedData);

		$.ajax({
			url: '/' + contextPath + '/user/userUpdate',
			type: 'POST',
			contentType: 'application/json', 
			data: JSON.stringify({ updatedData: updatedData }) ,
			success: function(response) {
				if (response.status === 'success') {
					loadPage('/userUpdater');
					console.log('Success:', data);
					alert('User details updated successfully!');
				} else {
					loadPage('/userUpdater');
					/*console.error('Error');*/
					alert('Failed to update user details!');
				}
			},
			error: function(xhr, status, error) {
				alert("An error occurred: " + error);
				console.error(error);
			}
		});
	} else {
		alert('No changes detected!');
	}

	editBtn.style.display = "inline-block";
	saveBtn.style.display = "none";
}

function closeModal() {
	editBtn.style.display = "inline-block";
	saveBtn.style.display = "none";
	$('#viewUserDetailStaticModal').modal('hide');

} 
