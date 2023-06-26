console.log("Reply Module...");

let replyService = {
  add: (reply, callback, error) => {
	  
    $.ajax({
      type: "POST",
      url: "/replies/new",
      data: JSON.stringify(reply),
      contentType: "application/json; charset=utf-8",
      success: callback,
      error: error
    });
  },

  getList: (param, callback, error) => {
    let bno = param.bno;
    let page = param.page || 1;

    $.getJSON("/replies/pages/" + bno + "/" + page,
      function(list) {
        if (callback) {
          callback(list);
        }
      }
    );
  },
  
  remove : (rno, callback, error) => {
	  $.ajax({
		  type : "DELETE",
		  url: "/replies/" + rno,
		  success : function(result, status, xhr){
			  if(callback){
				  callback(result);
			  }
		  },
		  eroor: error
	  });
  },
  
  update : (reply, callback, error ) => {
	  $.ajax({
		  type : "PATCH",
		  url : "/replies/" + reply.rno,
		  data : JSON.stringify(reply),
		  contentType : "application/json; charset=utf-8",
		  success: function(result, status, xhr){
			  if(callback){
				  callback(result);
			  }
		  }
	  });
  },
  get : (rno, callback, error) => {
		$.get("/replies/" + rno,
			function(result) {
				if(callback){
					callback(result);
				}
			}
		)
	} 
};