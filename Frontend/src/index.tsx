/* istanbul ignore file */
import "~/styles/index.scss";
import reactDOMClient from "react-dom/client";
import { BrowserRouter, createBrowserRouter, Route, RouterProvider, Routes } from "react-router-dom";
import { Register } from "./components/Register";
import { NavBar } from "./components/Navbar";
import React from "react";
import { App } from "./components/App";


const rootContainer = document.createElement("div");
document.body.appendChild(rootContainer);
const root = reactDOMClient.createRoot(rootContainer);
root.render(
    <React.StrictMode>
    <BrowserRouter>
    <NavBar></NavBar>
    <Routes>        
      <Route path="/"  element={<App/>} />
      <Route path="/register"  element={<Register />}/>
    </Routes>
    </BrowserRouter>
  </React.StrictMode>
);
