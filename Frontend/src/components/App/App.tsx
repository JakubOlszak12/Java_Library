import type { ReactElement } from "react";
import { randomDefaults } from "~/config/constants";
import reactLogo from "./react.svg";
import Main from "../Main";
import { createBrowserRouter } from "react-router-dom";



export function App(): ReactElement {

    function test(){
        fetch('http://localhost:8080/api/books')
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(data => {
      // Do something with the fetched data (e.g., display it)
      console.log(data);
    })
    .catch(error => {
      // Handle errors
      console.error('There was a problem with the fetch operation:', error);
    });
    }

    return (
        <>
          <Main/>
        </>
    );
}
