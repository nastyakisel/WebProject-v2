
    function sendError(mainElem, errorMessage) {
      mainElem.className = 'error';
      var errorMsgs = document.createElement('span');
      errorMsgs.className = "error-message";
      errorMsgs.innerHTML = errorMessage;
      mainElem.appendChild(errorMsgs);
    }

    function deleteError(mainElem) {
    	mainElem.className = '';
      if (mainElem.lastChild.className == "error-message") {
    	  mainElem.removeChild(mainElem.lastChild);
      }
    }

    function validate(form) {
      var elems = form.elements;
      
      var first_name = elems.first_name.value;
      var second_name = elems.second_name.value;
      var password = elems.password.value;
      var repeat_password = elems.repeat_password.value;
      var email = elems.email.value;
      var locale = elems.locale.value;
      var without_errors = true;
      
      
      deleteError(elems.first_name.parentNode);
      if (!first_name) {
    	  if (locale == "ru" || locale == "") {
    	  sendError(elems.first_name.parentNode, ' *Поле обязательно для заполнения.');
    	  }
    	  if (locale == "en") {
        	  sendError(elems.first_name.parentNode, ' *The field is necessary to fill in.');
        	  }
    	  without_errors = false;
      }
      
      deleteError(elems.second_name.parentNode);
      if (!second_name) {
    	  if (locale == "ru" || locale == "") {
    	  sendError(elems.second_name.parentNode, ' *Поле обязательно для заполнения.');
    	  }
    	  if (locale == "en") {
    		  sendError(elems.second_name.parentNode, ' *The field is necessary to fill in.');
    	  }
    	  without_errors = false;
      }
      
      deleteError(elems.password.parentNode);
      if (!password) {
    	  if (locale == "ru" || locale == "") {
    	  sendError(elems.password.parentNode, ' *Введите пароль.');
    	  }
    	  if (locale == "en") {
    		  sendError(elems.password.parentNode, ' *Insert password.');
    	  }
    	  without_errors = false;
     
       
      } else if (password != repeat_password) {
    	  if (locale == "ru" || locale == "") {
    	  sendError(elems.password.parentNode, ' *Пароли не совпадают!');
    	  }
    	  if (locale == "en") {
    		  sendError(elems.password.parentNode, ' *Passwords do not match!');
    	  }
    	  without_errors = false;
      }
      
      
      deleteError(elems.email.parentNode);
      if (!email) {
    	  if (locale == "ru" || locale == "") {
    	  sendError(elems.email.parentNode, ' *Поле обязательно для заполнения.');
    	  }
    	  if (locale == "en") {
    		  sendError(elems.email.parentNode, ' *The field is necessary to fill in.');
    	  }
    	  without_errors = false;
      }
      else if (!/[@]/.test(email) || !/[.]/.test(email)) {
    	  if (locale == "ru" || locale == "") {
    	  sendError(elems.email.parentNode, ' *Поле заполнено некорректно.');
    	  }
    	  if (locale == "en") {
    		  sendError(elems.email.parentNode, ' *Field filled in uncorrectly.');
    	  }
    	  without_errors = false;
      }
      return without_errors;
    }
    
    
    function loginvalidate(form) {
        var elems = form.elements;
        
        var user_login = elems.user_login.value;
        var user_password = elems.user_password.value;
        var locale = elems.locale.value;
        var without_errors = true;
        
        
        deleteError(elems.user_login.parentNode);
        if (!user_login) {
      	  if (locale == "ru" || locale == "") {
      	  sendError(elems.user_login.parentNode, ' *Поле обязательно для заполнения.');
      	  }
      	  if (locale == "en") {
          	  sendError(elems.user_login.parentNode, ' *The field is necessary to fill in.');
          	  }
      	  without_errors = false;
        }
        
        deleteError(elems.user_password.parentNode);
        if (!user_password) {
      	  if (locale == "ru" || locale == "") {
      	  sendError(elems.user_password.parentNode, ' *Поле обязательно для заполнения.');
      	  }
      	  if (locale == "en") {
      		  sendError(elems.user_password.parentNode, ' *The field is necessary to fill in.');
      	  }
      	  without_errors = false;
        }
        
        return without_errors;
      }
