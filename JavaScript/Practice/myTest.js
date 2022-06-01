
// run the JavaScript file through the terminal: rhino myTest.js

var m = null;

if(!m) print("m is null");


function getParameterByName(name, url) {

    if (!url) {
        url = window.location.href;
    }

    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);

    if (!results) return null;
    if (!results[2]) return '';

    return decodeURIComponent(results[2].replace(/\+/g, " "));
}



var urlName = "http://localhost:63342/WalletClient/balance.html?walletId=12&walletName=puut";

print ("The Wallet Id = " + getParameterByName("walletId", urlName))
print ("The Wallet Name = " + getParameterByName("walletName", urlName))