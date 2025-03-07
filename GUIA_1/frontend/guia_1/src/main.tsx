import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { App } from './App'
import { ToastContainer } from 'react-toastify'

import './index.css'
import 'react-tabs/style/react-tabs.css'

createRoot(document.getElementById('root')!).render(
    <StrictMode>
        <App />
        <ToastContainer />
    </StrictMode>,
)