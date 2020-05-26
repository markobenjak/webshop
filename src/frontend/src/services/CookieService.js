function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) 
        return parts.pop().split(';').shift();
}
/**
 * 
 * @param {string} name 
 * @param {number|string} value 
 * @param {number} days  
 * @param {Date} expires 
 * @return {void} 
 */
function setCookie(name,value,days = 0, expires = 0) {
    var expires = "";
    if (days > 0 && expires === 0) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    else if (expires !==0 && days === 0){
        expires = expires.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}

export const CookieService = {
    getCookie: getCookie,
    setCookie: setCookie
}


