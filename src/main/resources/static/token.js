document.addEventListener("DOMContentLoaded", () => {

    const formElement = document.getElementById('login');

    let req = {
        "username": 0,
        "password": 0,
    }
    function getCookie(name) {    // возвращает куки с указанным name,
        // или undefined, если ничего не найдено
        let matches = document.cookie.match(new RegExp(
            "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
        ));
        return matches ? decodeURIComponent(matches[1]) : undefined;
    }

    async function fetchWithAuth(url, options) {

        const loginUrl = '/login'; // url страницы для авторизации
        let tokenData = null; // объявляем локальную переменную tokenData

        if (getCookie('token') !== undefined) { // если в sessionStorage присутствует tokenData, то берем её
            tokenData = getCookie('token');
        } else {
            return window.location.replace(loginUrl); // если токен отсутствует, то перенаправляем пользователя на страницу авторизации
        }

        if (!options.headers) { // если в запросе отсутствует headers, то задаем их
            options.headers = {};
        }

        if (tokenData) {

            options.headers.Authorization = `Bearer_${tokenData}`; // добавляем токен в headers запроса
        }

        return fetch(url, options).then(req => req).then(r => r.body); // возвращаем изначальную функцию, но уже с валидным токеном в headers
    }


    formElement.addEventListener('submit', (e) => {
        e.preventDefault();
        const formData = new FormData(formElement); // создаём объект FormData, передаём в него элемент формы
        // теперь можно извлечь данные
        const name = formData.get('username'); // 'John'
        const password = formData.get('password'); // 'Smith'

        req.username = name;
        req.password = password;

        fetch('/api/v1/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(req),
        })
            .then(response => response.json())
            .then(result =>  {
                document.cookie = `user=${result.username}`;
                document.cookie = `token=Bearer_${result.token}`;
                location.href = "/main"
        });

    })

});





// fetch('/menu', {
//     method: 'GET',
//     headers: {
//         'Authorization': `Bearer_${getCookie('token')}`
//     }
// }).then(response => response.json())
//     .then(result => {
//         window.location.href = '/menu';
//     });



// fetch('/admin_menu', {
//     method: 'GET',
//     headers: {
//         'Authorization': `Bearer_${getCookie('token')}`,
//     }
// }).then(response => response).then(req => window.location.href = '/admin_menu');