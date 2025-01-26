import { useEffect, useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { useLocalState } from './util/useLocalStorage'
import { Route } from 'react-router-dom'
import Dashboard from './Dashboard'
import Homepage from './Homepage'
import PrivateRoute from './PrivateRoute/PrivateRoute'
import Login from './Login/login'

function App() {

  
  const [count, setCount] = useState(0)
  const [jwt, setJwt] = useLocalState("", "jwt");

  return (
    <Routes>
      <Route path='/dashboard' element={
        <PrivateRoute>
          <Dashboard/>
        </PrivateRoute>
        } 
      />
      <Route path='login' element={<Login/>}/>
      <Route path='/' element={<homePage/>}/>
    </Routes>
  )
}

export default App
