if(!document.getElementsByClassName) {
	document.getElementsByClassName = function(className, element) {
		var children = (element || document).getElementsByTagName('*');
		var elements = new Array();
		for(var i = 0; i < children.length; i++) {
			var child = children[i];
			var classNames = child.className.split(' ');
			for(var j = 0; j < classNames.length; j++) {
				if(classNames[j] == className) {
					elements.push(child);
					break;
				}
			}
		}
		return elements;
	};
}

window.onload = function() {
	//滚动时出现的div
	var show_div = document.getElementsByClassName("go_top");
	window.onscroll = function() {
		var y = window.scrollY;
		if(y >= 190) {
			show_div[0].style.display = "block";
		} else {
			show_div[0].style.display = "none";
		}

	}

}