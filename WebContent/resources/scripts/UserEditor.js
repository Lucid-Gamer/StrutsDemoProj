/**
 * 
 */

var inputs = document.querySelectorAll("#viewUserDetailStaticModal input");
var originalData = {};
var editableData = ['user.username', 'user.emailId', 'user.contactNum', 'user.validTill'];
var editBtn = document.getElementById("editUserDetailsBtn");
var saveBtn = document.getElementById("saveUserDetailsBtn");



function showUserDetails(userJson) {
	try {
		var user = JSON.parse(userJson);
		/*console.log("User: ", user);*/

		document.getElementsByName('updateModel.userId').value = user.userId || '';
		document.getElementsByName('updateModel.firstName').value = user.firstName || ' ';
		document.getElementsByName('updateModel.lastName').value =  user.lastName || '';
		document.getElementsByName('updateModel.username').value = user.username || '';
		document.getElementsByName('updateModel.emailId').value = user.emailId || '';
		document.getElementsByName('updateModel.contactNum').value = user.contactNum || '';
		document.getElementsByName('updateModel.makerCd').value = user.makerCd || '';

		if (user.validTill) {
			var formattedDate = user.validTill.split(' ')[0]; // Extract 'YYYY-MM-DD'
			document.getElementsByName('updateModel.validTill').value = formattedDate;
		} else {
			document.getElementsByName('updateModel.validTill').value = '';
		}

		if (user.makerDt) {
			var formattedDate = user.makerDt.split(' ')[0];
			document.getElementsByName('updateModel.makerDt').value = formattedDate;
		} else {
			document.getElementsByName('updateModel.makerDt').value = '';
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
