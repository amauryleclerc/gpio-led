function on(){
	var url ="ws/led/on";
	 sendRequest(url);
}
function off(){
	var url ="ws/led/off";
	 sendRequest(url);
}

function blink(){
	var url ="ws/led";
	 sendRequest(url);
}

function sendRequest(url){
	$.get( url, function( data ) {
		});
}
