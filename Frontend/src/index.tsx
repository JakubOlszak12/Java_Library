/* istanbul ignore file */
import "~/styles/index.scss";
import reactDOMClient from "react-dom/client";
import { BrowserRouter, createBrowserRouter, Route, RouterProvider, Routes } from "react-router-dom";
import { Register } from "./components/Register";
import { NavBar } from "./components/Navbar";
import React, { useState, useEffect } from "react";
import { App } from "./components/App";
import { Login } from "./components/Login";
import { ProtectedRoute } from "./components/ProtectedRoute";
import Client from "./components/Client";
import {CreateClient} from "./components/CreateClient";
import { CreateBook } from "./components/CreateBook";
import { AssignBook } from "./components/AssignBook";


const rootContainer = document.createElement("div");
document.body.appendChild(rootContainer);
const root = reactDOMClient.createRoot(rootContainer);

// Function to check if the token exists in cookies
const isAuthenticatedFromCookie = () => {
    const token = document.cookie.split('; ').find(row => row.startsWith('token='));
    return !!token; // Convert to boolean
};

const AppContainer = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(isAuthenticatedFromCookie());

    // Effect to update isAuthenticated when the component mounts
    useEffect(() => {
        setIsAuthenticated(isAuthenticatedFromCookie());
    }, []);

    return (
        <React.StrictMode>
            <BrowserRouter>
                <NavBar isAuthenticated={isAuthenticated} />
                <Routes>
                    <Route path="/" element={<App />} />
                    <Route path="/register" element={<Register />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/clients" element={
                    <ProtectedRoute>
                        <Client />
                    </ProtectedRoute>
                    } />
                    <Route path="/add-client" element={
                    <ProtectedRoute>
                        <CreateClient />
                    </ProtectedRoute>
                    } />
                    <Route path="/add-book" element={
                    <ProtectedRoute>
                        <CreateBook />
                    </ProtectedRoute>
                    } />
                    <Route path="/assign-book" element={
                    <ProtectedRoute>
                        <AssignBook />
                    </ProtectedRoute>
                    } />
                </Routes>
            </BrowserRouter>
        </React.StrictMode>
    );
};

root.render(
    <AppContainer />
);
