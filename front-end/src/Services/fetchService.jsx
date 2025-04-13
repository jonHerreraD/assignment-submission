function ajax (url, jwt,requestMethod, requestBody){
    const fetchData = {
        headers: {
            "content-type": "application/json"
        },
        method: requestMethod
    }

    if (jwt){
        fetchData.headers.Authorization = `Bearer ${jwt}`
    }

    if(requestBody){
        fetchData.body = JSON.stringify(requestBody)
    }
    fetch(url, fetchData).then(response => {
        if (response.status === 200) return response.json();
    })


}

export default ajax;
