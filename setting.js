let token;
fetch('http://localhost:8080/api/v1/auth/login',
    {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({email: '1', password: '1'})
    })
    .then(response => response.json())
    .then(json => {
        console.log(json)
        token = json.token
    })
    .catch(console.log)

fetch('http://localhost:8080/api/v1/developers',
    {
        method: 'GET',
        headers: {'Authorization': token},
    })
    .then(response => response.json())
    .then(json => console.log(json))//[[PromiseValue]]
    .catch(console.log)
