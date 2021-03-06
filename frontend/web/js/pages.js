let stylesheets = [];
let scripts = [];
let currentPage = "pages/home.html";
let lastPage = "";
let navStack = [];
let disableNavigation = false;
const api = "https://kultur.cyber-city.systems/api/v1/";
//const api = "http://localhost:8080/api/v1/";
const auth = "https://auth.cyber-city.systems/api/";
const cccolor = '#ffa047';

function start() {
    setTimeout(() => {
        redirectWithoutToken();
    }, 500);
}

function redirectWithoutToken(){
    //enableNavBack();
    navStack = [];
    if(window.location.href.split("#").length > 1) {
        let hash = window.location.href.split("#", 2)[1];
        console.log(hash);
        page(hash);
    } else {
        page("home");
    }
}

function checkToken() {
    cookies = document.cookie.split(";");
    token;
    for(let i = 0; i < cookies.length; i ++) {
        if(cookies[i].split("=")[0].trim() == "cybercity-auth") {
            token = cookies[i].split("=")[1].replace("%20", " ");
        }
    }
    if (token) {
        $.ajax(auth + "validate_token", {
            method: "GET",
            headers: {
                "accept": "application/json",
                "Authorization": token
            },
            dataType: "json",
            success: function (data) {
                if (data.code === "SUCCESS") {
                    disableNavBack();
                    navStack = [];
                    if(window.location.href.split("#").length > 1) {
                        let hash = window.location.href.split("#", 2)[1];
                        console.log(hash);
                        page(hash);
                    } else {
                        page("home");
                    }
                } else {
                    localStorage.removeItem("token");
                    enableNavBack();
                    window.location.href = "https://cyber-city.systems/login?target=https://kultur.cyber-city.systems";
                    navStack = [];
                }
            },
            error: function (data) {
                enableNavBack();
                window.location.href = "https://cyber-city.systems/login?target=https://kultur.cyber-city.systems";
                navStack = [];
            }
        });
    } else {
        enableNavBack();
        window.location.href = "https://cyber-city.systems/login?target=https://kultur.cyber-city.systems";
        navStack = [];
    }
}

function loadPage(path, isBack = false) {
    if(!isBack) {
        if(navStack[navStack.length - 1] === path) {
            navStack.pop();
        } else {
            navStack.push(currentPage);
        }
    }

    for(let i = 0; i < stylesheets.length; i ++) {
        stylesheets[i].parentNode.removeChild(stylesheets[i]);
    }
    stylesheets = [];
    for(let i = 0; i < scripts.length; i ++) {
        scripts[i].parentNode.removeChild(scripts[i]);
    }
    scripts = [];
    $("ccpc").load(path, function() {
        let styles = document.getElementsByTagName("ccstyle");
        for(let i = 0; i < styles.length; i ++) {
            let src = $(styles[i]).attr("src");
            let element = document.createElement("link");
            element.setAttribute("rel", "stylesheet");
            element.setAttribute("type", "text/css");
            element.setAttribute("href", src);
            stylesheets.push(element);
            try {
                document.getElementsByTagName("head")[0].appendChild(element);
            } catch(e) {
                console.log(e);
            }
        }
        let tescripts = document.getElementsByTagName("ccscript");
        for(let i = 0; i < tescripts.length; i ++) {
            let src = $(tescripts[i]).attr("src");
            let element = document.createElement("script");
            element.setAttribute("src", src);
            scripts.push(element);
            try {
                document.getElementsByTagName("body")[0].appendChild(element);
            } catch(e) {
                console.log(e);
            }
        }
        while(tescripts.length > 0) {
            tescripts[0].parentNode.removeChild(tescripts[0]);
        }
        while(styles.length > 0) {
            styles[0].parentNode.removeChild(styles[0]);
        }
        let title = document.getElementsByTagName("ccTitle");
        if (title.length > 0) {
            document.title = title[0].innerHTML;
            title[0].remove();
        }
        currentPage = path;
        if(navStack.length > 0 && !disableNavigation) {
            $("#navBack").show();
        } else {
            $("#navBack").hide();
        }
        onPageLoaded();
    });
}

function navBack() {
    if (navStack.length > 0) {
        loadPage(navStack.pop(), true);
    }
}

function disableNavBack() {
    $("#navBack").hide();
    disableNavigation = true;
}

function enableNavBack() {
    if(navStack.length > 0) {
        $("#navBack").show();
    }
    disableNavigation = false;
}

function loadNavbar() {
    let navbar = $("ccnavbar");
    if(navbar.html() === "" || navbar.html() === undefined) {
       navbar.load("/navbar.html", () => {});
    } else {
        console.log($("#navbar").html()) 
    }
}

function page(name) {
    //if(name === lastPage && lastPage !== "login" && lastPage !== "register") return;
    if(name !== "login" && name !== "register") {
        loadNavbar();
    }

    switch(name) {
        case "login":
            loadPage("pages/login.html");
            window.history.replaceState(null, null, "/#login");
            lastPage = "login";
        break;
        case "register":
            loadPage("pages/register.html");
            window.history.replaceState(null, null, "/#register");
            lastPage = "register";
        break;
        case "forgotPassword":
            loadPage("pages/forgotPassword.html");
            window.history.replaceState(null, null, "/#forgotPassword");
            lastPage = "forgotPassword";
        break;
        case "home":
            loadPage("pages/home.html");
            window.history.replaceState(null, null, "/#home");
            lastPage = "home";
        break;
        case "club-home":
            loadPage("pages/club.html");
            window.history.replaceState(null, null, "/#club-home");
            lastPage = "club";
        break;
        case "chat":
            loadPage("pages/chat.html");
            window.history.replaceState(null, null, "/#chat");
            lastPage = "chat";
        break;
        case "library":
            loadPage("pages/library.html");
            window.history.replaceState(null, null, "/#library");
            lastPage = "library";
        break;
        case "cityMap":
            loadPage("pages/cityMap.html");
            window.history.replaceState(null, null, "/#cityMap");
            lastPage = "cityMap";
        break;
        case "account":
            loadPage("pages/account.html");
            window.history.replaceState(null, null, "/#account");
            lastPage = "account";
            break;
        case "logout":
            cookies = document.cookie.split(";");
            for(let i = 0; i < cookies.length; i ++) {
                if(cookies[i].split("=")[0].trim() == "cybercity-auth") {
                    document.cookie = cookies[i] + ";max-age=0";
                    document.href = "https://cyber-city.systems";
                }
            }
    }
}

function onPageLoaded() {}

start();