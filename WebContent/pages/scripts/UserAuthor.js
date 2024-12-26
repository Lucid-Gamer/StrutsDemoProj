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