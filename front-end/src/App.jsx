import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { useLocalState } from './util/useLocalStorage'
import { Route } from 'react-router-dom'
import Dashboard from './Dashboard'
import homePage from './Homepage'

function App() {

  
  const [count, setCount] = useState(0)
  const [jwt, setJwt] = useLocalState("", "jwt");

  useEffect(() => {

    if(!jwt){
      const requestBody = {
        "username": "jon",
        "password": "jonsnow"
      }
    
      fetch('api/auth/login',{
        headers : {
          "Content-Type": "application/json"
        },
        method : "post",
        body: JSON.stringify(requestBody)
      })
      .then((response) => Promise.all([response.json(), response.headers]))
      .then(([body, headers])=>{
        setJwt(headers.get("authorization"));
      });
    }
  }, []);
  
  useEffect(() =>{
    console.log(`JWT is: ${jwt}` );
  },[jwt]);

  return (
    <Routes>
      <Route path='/dashboard' element={<Dashboard/>} />
      <Route path='/' element={<homePage/>}/>
    </Routes>
  )
}

export default App
