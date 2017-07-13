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
	var node_textDiv = document.getElementsByClassName("preText");
	var y = node_textDiv.length;
	var node_img = document.getElementsByClassName("img");
	var node_preArticle =document.getElementsByClassName("preArticle");
	for(var i = 0; i < y; ++i) {
		node_img[i].src = "articleImg/0" + i + ".jpg";
	}
	for(var j = 0; j < y; ++j) {
		heightValue(j);
	}

	function heightValue(a) {
		var img_url = "articleImg/0" + a + ".jpg";
		var img = new Image();
		var x, theHight;
		img.src = img_url;
		if(img.complete) {
			// 打印
			x = img.width / 140;
			theHight = img.height / x;
			node_textDiv[a].style.height = Math.floor(theHight) + 'px';
			node_preArticle[a].style.height =Math.floor(theHight)+10+"px";
		} else {
			// 加载完成执行
			img.onload = function() {
				x = img.width / 140;
				theHight = img.height / x;
				node_textDiv[a].style.height = Math.floor(theHight) + 'px';
				node_preArticle[a].style.height =Math.floor(theHight)+10+"px";
				
			}

		}
	}
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