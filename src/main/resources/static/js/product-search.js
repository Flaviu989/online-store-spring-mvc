window.onload = function() {
	hideRowsOnLoad();
};

function hideRowsOnLoad(){
	var start = document.getElementById('start_message');
	start.style.display = "none";
	var rows = document.getElementsByClassName('row');
	for(let i = 0; i < rows.length; i++) {
    	rows[i].style.display = "none";
    }
	var end = document.getElementById('end_message');
	end.style.display = "none";
}

function showProperRows(event) {
	/*display 3 elements when button is pressed*/
	event.preventDefault();
	var clickedElement = event.target;
	var value = parseInt(clickedElement.getAttribute('value'));
	let new_value = value + 3;
	console.log("displaying elements: ",new_value);
	
	var rows = document.getElementsByClassName('row');
	var start = document.getElementById('start_message');
	var end = document.getElementById('end_message');
	for(let i = value; i < new_value; i++) {
		if(i >= rows.length) {
			/*if boundries are reached hide buttn and exit*/
			clickedElement.style.display = "none";
			end.style.display = "block";
			console.log("done!");
			return;
		}
		if(i ==0 ) {
			start.style.display = "block";
		}
		rows[i].style.display = "block";
    }
	clickedElement.setAttribute('value', new_value);
}
