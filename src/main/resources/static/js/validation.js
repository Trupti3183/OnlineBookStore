function validateForm() {
	var name = document.getElementById("name").value;
	var email = document.getElementById("email").value;
	var password = document.getElementById("password").value;
	var mobileNumber = document.getElementById("mobileNumber").value;
	var address = document.getElementById("address").value;
	var govtId = document.getElementById("govtId").value;

	// Simple validation for each field
	if (name.trim() === "" || email.trim() === "" || mobileNumber.trim() === "" || address.trim() === "" || govtId.trim() === "") {
		alert("All fields are required.");
		return false;
	}

	// Password validation
	if (!validatePassword(password)) {
		alert("Password must contain at least one special character, one lowercase letter, one uppercase letter, and be at least 8 characters long.");
		return false;
	}

	return true; // Submit the form if all validations pass
}

function validatePassword(password) {
	// Password must contain at least one special character, one lowercase letter, one uppercase letter, and be at least 8 characters long
	var passwordRegex = /^(?=.*[!@#$%^&*(),.?":{}|<>])(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d!@#$%^&*(),.?":{}|<>]{8,}$/;
	return passwordRegex.test(password);
}