/**
 * 
 */

function showUserDetails(user) {
	try {
		/*const user = JSON.parse(userJson); // Parse JSON string into an object*/
		console.log("User: ",user);
		// Populate modal input fields with user data
		document.getElementById('userId').value = user.userId || '';
		document.getElementById('userName').value = user.firstName + ' '
				+ user.lastName || '';
		document.getElementById('userUsername').value = user.username || '';
		document.getElementById('userEmail').value = user.emailId || '';
		document.getElementById('userContact').value = user.contactNum || '';
		document.getElementById('userValidTill').value = user.validTill || '';

		// Show the modal
		$('#viewUserDetailStaticModal').modal('show');
	} catch (e) {
		console.error('Failed to parse user details:', e);
	}
}

document.addEventListener("DOMContentLoaded", () => {
    const editBtn = document.getElementById("editUserDetailsBtn");
    const saveBtn = document.getElementById("saveUserDetailsBtn");
    const inputs = document.querySelectorAll("#viewUserDetailStaticModal input");

    let originalData = {};

    // Store Original Data When Modal Opens
    function storeOriginalData() {
        originalData = {};
        inputs.forEach(input => {
            originalData[input.className.split(' ').pop()] = input.value;
        });
    }

    // Open Modal and Store Initial Data
    $('#viewUserDetailStaticModal').on('shown.bs.modal', storeOriginalData);

    // Toggle Edit Mode
    editBtn.addEventListener("click", () => {
        inputs.forEach(input => input.removeAttribute("readonly"));
        editBtn.style.display = "none";
        saveBtn.style.display = "inline-block";
    });

    // Save Changes
    saveBtn.addEventListener("click", () => {
        let updatedData = {};
        $('#viewUserDetailStaticModal').modal('hide');
    	$('.modal-backdrop').remove();
        inputs.forEach(input => {
            const key = input.className.split(' ').pop();
            if (originalData[key] !== input.value) {
                updatedData[key] = input.value; // Only include modified fields
            }
            input.setAttribute("readonly", "readonly");
        });

        if (Object.keys(updatedData).length > 0) {
            console.log("Modified Data:", updatedData); 
            
            // Send to Backend
            fetch('/users/updateUpdater', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedData)
            })
            .then(response => response.json())
            .then(data => {
            	loadPage('/userAuthor');
                console.log('Success:', data);
                alert('User details updated successfully!');
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('Failed to update user details!');
            });
        } else {
            alert('No changes detected!');
        }

        editBtn.style.display = "inline-block";
        saveBtn.style.display = "none";
    });
});